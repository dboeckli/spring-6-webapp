package ch.springframeworkguru.spring6webapp.controller;

import ch.springframeworkguru.spring6webapp.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AuthorController {

    private final AuthorService authorService;

    @RequestMapping("/authors")
    public String getAuthors(Model model) {
        log.info("Authors requested");
        model.addAttribute("authors", authorService.getAuthors());
        return "authors";
    }
}
