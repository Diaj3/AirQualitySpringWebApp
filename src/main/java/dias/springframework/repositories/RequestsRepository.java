package dias.springframework.repositories;

import dias.springframework.domain.Location;
import dias.springframework.domain.Requests;
import org.springframework.data.repository.CrudRepository;

public interface RequestsRepository extends CrudRepository<Requests, Integer>{
}
