package ch.springframeworkguru.spring6webapp;

import ch.springframeworkguru.spring6webapp.repo.AuthorRepository;
import ch.springframeworkguru.spring6webapp.repo.BookRepository;
import ch.springframeworkguru.spring6webapp.repo.PublisherRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(useMainMethod = SpringBootTest.UseMainMethod.ALWAYS)
@Slf4j
class Spring6webappApplicationTests {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PublisherRepository publisherRepository;

	@Test
	void contextLoads() {
        log.info("Testing Spring 6 Application...");
        assertAll("Repository counts",
            () -> assertEquals(2, authorRepository.count(), "Author count should be 2"),
            () -> assertEquals(2, bookRepository.count(), "Book count should be 2"),
            () -> assertEquals(1, publisherRepository.count(), "Publisher count should be 1")
        );
    }

}
