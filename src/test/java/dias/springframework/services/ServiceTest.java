package dias.springframework.services;

import dias.springframework.domain.Location;
import dias.springframework.repositories.LocationRepository;
//import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    Location location = new Location();

    @Mock
    private LocationRepository locationRepository;

    @Autowired
    private LocationService locationService ;

    @Test
    public void getLocationDetails_returnsLocationInfo() {

        location.setId(1);
        location.setName("Lisbon");
        locationService.saveLocation((location));

        //Given an attribute, check if the others correspond
        Location getLocation = locationService.getLocationById(1);
        assertEquals(getLocation.getName(), location.getName());
    }
}