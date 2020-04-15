package dias.springframework.bootstrap;

import dias.springframework.domain.Location;
import dias.springframework.domain.Requests;
import dias.springframework.repositories.LocationRepository;

import dias.springframework.repositories.RequestsRepository;
import dias.springframework.services.RequestsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class LocationLoader implements ApplicationListener<ContextRefreshedEvent> {

    private LocationRepository locationRepository;
    private RequestsRepository requestsRepository;

    private Logger log = LogManager.getLogger(LocationLoader.class);

    @Autowired
    public void setLocationRepository(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Autowired
    public void setRequestsRepository(RequestsRepository requestsRepository) {
        this.requestsRepository = requestsRepository;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Location teste = new Location();
        teste.setAqi(34);
        teste.setLatitude(38.5);
        teste.setId(1);
        teste.setLongitude(-10.3);
        teste.setName("Teste");
        teste.setNo2(5.2);
        teste.setO3(6.2);
        teste.setP((double) 50);
        teste.setPm25((double) 15);
        teste.setPm10((double) 12);
        teste.setSo2((double) 12);
        teste.setTime("2019-10-5");
        teste.setTimezone("+01:00");
        //locationRepository.save(teste);

        Requests requests = new Requests();
        requests.setId(1);
        System.out.println(requests);
        requestsRepository.save(requests);
        System.out.println(requestsRepository.findById(1));
        System.out.println(requestsRepository.findById(1).toString());
    }
}
