package ch.springframeworkguru.spring6webapp.observability;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import ch.springframeworkguru.spring6webapp.controller.AuthorController;
import ch.springframeworkguru.spring6webapp.controller.BookController;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.micrometer.metrics.test.autoconfigure.AutoConfigureMetrics;
import org.springframework.boot.micrometer.tracing.test.autoconfigure.AutoConfigureTracing;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
@AutoConfigureMetrics
@AutoConfigureTracing
public class ObservabilityTest {

    @LocalServerPort
    private int port;

    @Test
    void test_logMessage_in_AuthorController_viaLogbackAppender(@Autowired TestRestTemplate testRestTemplate) {
        Logger logger = (Logger) LoggerFactory.getLogger(AuthorController.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);

        String url = "http://localhost:" + port + "/authors";
        testRestTemplate.getForEntity(url, String.class);

        List<ILoggingEvent> logEvents = listAppender.list;
        assertAll(
            () -> assertNotNull(logEvents),
            () -> assertEquals(1, logEvents.size()),
            () -> assertThat(logEvents.getFirst().getFormattedMessage()).isEqualTo("Authors requested"),
            () -> assertThat(logEvents.getFirst().getMDCPropertyMap().get("traceId")).isNotBlank().matches("[0-9a-f]{32}"),
            () -> assertThat(logEvents.getFirst().getMDCPropertyMap().get("spanId")).as("span_id").isNotBlank().matches("[0-9a-f]{16}")
        );

        logger.detachAppender(listAppender);
        listAppender.stop();
    }

    @Test
    void test_logMessage_in_BookController_viaLogbackAppender(@Autowired TestRestTemplate testRestTemplate) {
        Logger logger = (Logger) LoggerFactory.getLogger(BookController.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);

        String url = "http://localhost:" + port + "/books";
        testRestTemplate.getForEntity(url, String.class);

        List<ILoggingEvent> logEvents = listAppender.list;
        assertAll(
            () -> assertNotNull(logEvents),
            () -> assertEquals(1, logEvents.size()),
            () -> assertThat(logEvents.getFirst().getFormattedMessage()).isEqualTo("Books requested"),
            () -> assertThat(logEvents.getFirst().getMDCPropertyMap().get("traceId")).isNotBlank().matches("[0-9a-f]{32}"),
            () -> assertThat(logEvents.getFirst().getMDCPropertyMap().get("spanId")).as("span_id").isNotBlank().matches("[0-9a-f]{16}")
        );

        logger.detachAppender(listAppender);
        listAppender.stop();
    }
}
