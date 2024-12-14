package ch.springframeworkguru.spring6webapp.service;

import ch.springframeworkguru.spring6webapp.domain.Book;
import ch.springframeworkguru.spring6webapp.repo.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Iterable<Book> getBooks() {
        return bookRepository.findAll();
    }
}
