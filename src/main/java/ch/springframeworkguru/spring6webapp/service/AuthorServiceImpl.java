package ch.springframeworkguru.spring6webapp.service;

import ch.springframeworkguru.spring6webapp.domain.Author;
import ch.springframeworkguru.spring6webapp.repo.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Iterable<Author> getAuthors() {
        return authorRepository.findAll();
    }
}
