package ch.springframeworkguru.spring6webapp.repo;

import ch.springframeworkguru.spring6webapp.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long>  {
}
