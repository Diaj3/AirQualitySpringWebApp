package dias.springframework.repositories;

import dias.springframework.configuration.RepositoryConfiguration;
import dias.springframework.domain.Location;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {RepositoryConfiguration.class})
public class LocationRepositoryTest {

    private LocationRepository locationRepository;

    @Autowired
    public void setLocationRepository(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Test
    public void testSaveLocation(){
        //setup product
        Location location = new Location();
        location.setName("Test Location");
        location.setAqi(30);
        location.setLatitude(30.2);
        location.setLongitude(20.2);
        location.setPm10(20.2);
        location.setPm25(20.6);
        location.setP(12.3);
        location.setSo2(16.3);
        location.setO3(15.2);
        location.setNo2(12.90);
        location.setTimezone("+01:00");
        location.setTime("2020-04-12 00:00");

        //rep.findIdlocaion.getid

        //save product, verify has ID value after save
        assertNull(location.getId()); //null before save
        locationRepository.save(location);
        assertNotNull(location.getId()); //not null after save
        //fetch from DB
        Location fetchedLocation = locationRepository.findById(location.getId()).orElse(null);

        //should not be null
        assertNotNull(fetchedLocation);

        //should equal
        assertEquals(location.getId(), fetchedLocation.getId());
        assertEquals(location.getName(), fetchedLocation.getName());

        //update description and save
        fetchedLocation.setName("Location For Testing");
        locationRepository.save(fetchedLocation);

        //get from repository, should be updated
        Location fetchedUpdatedLocation = locationRepository.findById(fetchedLocation.getId()).orElse(null);
        assertEquals(fetchedLocation.getName(), fetchedUpdatedLocation.getName());

        //verify count of locations in the repository
        long numberOfLocations = locationRepository.count();
        assertEquals(numberOfLocations, 1);

        //get all locations, list should only have one
        Iterable<Location> locations = locationRepository.findAll();
        int count = 0;
        for(Location p : locations){ count++; }
        assertEquals(count, 1);
    }
}
