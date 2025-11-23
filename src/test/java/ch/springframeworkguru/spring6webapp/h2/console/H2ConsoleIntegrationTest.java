package ch.springframeworkguru.spring6webapp.h2.console;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
class H2ConsoleIntegrationTest {

    @Test
    void h2ConsoleShouldBeAccessible(@Autowired TestRestTemplate testRestTemplate) {
        // Wir rufen die H2-Console URL auf (mit abschließendem Slash, um Redirects zu vermeiden)
        ResponseEntity<String> response = testRestTemplate.getForEntity("/h2-console/", String.class);

        // Prüfen, ob die Seite erreichbar ist (Status 200)
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Prüfen, ob es wirklich die H2 Console ist (anhand des Titels im HTML)
        assertThat(response.getBody()).contains("<title>H2 Console</title>");
    }

    @TestConfiguration
    static class RestTemplateTestConfig {
        @Bean
        RestTemplate buildRestTemplate() {
            RestTemplateBuilder builder = new RestTemplateBuilder();
            return builder.build();
        }
    }
}