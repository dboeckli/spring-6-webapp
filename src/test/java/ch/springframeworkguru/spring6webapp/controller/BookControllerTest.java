package ch.springframeworkguru.spring6webapp.controller;

import ch.springframeworkguru.spring6webapp.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    @Autowired
    BookController controller;

    @Test
    void testGetBooks() {
        Model model = new ExtendedModelMap();
        String viewName = controller.getBooks(model);
        assertEquals("books", viewName);
        List<Book> bookList = (List<Book>)model.getAttribute("books");
        assertEquals(2, bookList.size());
    }
}
