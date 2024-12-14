package ch.springframeworkguru.spring6webapp.repo;

import ch.springframeworkguru.spring6webapp.domain.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
