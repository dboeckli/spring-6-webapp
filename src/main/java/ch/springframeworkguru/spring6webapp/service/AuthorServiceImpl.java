package ch.springframeworkguru.spring6webapp.service;

import ch.springframeworkguru.spring6webapp.domain.Author;
import ch.springframeworkguru.spring6webapp.repo.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public Iterable<Author> getAuthors() {
        return authorRepository.findAll();
    }
}
