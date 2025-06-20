package ch.springframeworkguru.spring6webapp;

import ch.springframeworkguru.spring6webapp.repo.AuthorRepository;
import ch.springframeworkguru.spring6webapp.repo.BookRepository;
import ch.springframeworkguru.spring6webapp.repo.PublisherRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(useMainMethod = SpringBootTest.UseMainMethod.ALWAYS)
@Slf4j
class Spring6webappApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PublisherRepository publisherRepository;

	@Test
	void contextLoads() {
        assertNotNull(applicationContext, "Application context should not be null");
        log.info("Testing Spring 6 Application {}", applicationContext.getApplicationName());
        assertAll("Repository counts",
            () -> assertEquals(2, authorRepository.count(), "Author count should be 2"),
            () -> assertEquals(2, bookRepository.count(), "Book count should be 2"),
            () -> assertEquals(1, publisherRepository.count(), "Publisher count should be 1")
        );
    }

}
