package ch.springframeworkguru.spring6webapp.service;

import ch.springframeworkguru.spring6webapp.domain.Author;

public interface AuthorService {

    Iterable<Author> getAuthors();

}
