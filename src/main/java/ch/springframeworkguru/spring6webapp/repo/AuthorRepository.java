package ch.springframeworkguru.spring6webapp.repo;

import ch.springframeworkguru.spring6webapp.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
