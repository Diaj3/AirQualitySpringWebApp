package dias.springframework.services;

import dias.springframework.domain.Requests;
import dias.springframework.repositories.RequestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestsServiceImpl implements RequestsService {

    private RequestsRepository requestsRepository;

    @Autowired
    public void setRequestsRepository(RequestsRepository requestsRepository) {
        this.requestsRepository = requestsRepository;
    }

    @Override
    public Requests getById(Integer id) {
        return requestsRepository.findById(id).orElse(null);
    }

    @Override
    public Requests save(Requests requests) {
        return requestsRepository.save(requests);
    }

    @Override
    public Iterable<Requests> listAllRequests() {
        return requestsRepository.findAll();
    }
}
