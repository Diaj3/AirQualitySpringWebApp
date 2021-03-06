package dias.springframework.services;

import dias.springframework.domain.Location;
import dias.springframework.domain.Requests;

public interface RequestsService {

    Requests getById(Integer id);

    Requests save(Requests requests);

    Iterable<Requests> listAllRequests();
}