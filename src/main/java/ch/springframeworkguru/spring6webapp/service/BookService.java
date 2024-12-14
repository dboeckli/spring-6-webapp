package ch.springframeworkguru.spring6webapp.service;

import ch.springframeworkguru.spring6webapp.domain.Book;

public interface BookService {

    Iterable<Book> getBooks();

}
