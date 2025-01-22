package ch.springframeworkguru.spring6webapp.controller;

import ch.springframeworkguru.spring6webapp.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthorControllerTest {

    @Autowired
    AuthorController controller;

    @Test
    void testGetAuthors() {
        Model model = new ExtendedModelMap();
        String viewName = controller.getAuthors(model);
        assertEquals("authors", viewName);
        List<Author> authorList = (List<Author>)model.getAttribute("authors");
        assertEquals(2, authorList.size());
    }

}
