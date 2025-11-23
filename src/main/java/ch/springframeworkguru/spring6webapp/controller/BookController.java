package ch.springframeworkguru.spring6webapp.controller;

import ch.springframeworkguru.spring6webapp.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;

    @RequestMapping("/books")
    public String getBooks(Model model) {
        log.info("Books requested");
        model.addAttribute("books", bookService.getBooks());
        return "books";
    }
}
