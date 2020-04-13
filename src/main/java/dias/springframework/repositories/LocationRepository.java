package dias.springframework.repositories;

import dias.springframework.domain.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, Integer>{
}
