package dias.springframework.repositories;

import dias.springframework.configuration.RepositoryConfiguration;
import dias.springframework.domain.Location;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

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
    public void testSaveProduct(){
        //setup product
        Location location = new Location();
        location.setDescription("Spring Framework Guru Shirt");
        location.setPrice(new BigDecimal("18.95"));
        location.setProductId("1234");

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
        assertEquals(location.getDescription(), fetchedLocation.getDescription());

        //update description and save
        fetchedLocation.setDescription("New Description");
        locationRepository.save(fetchedLocation);

        //get from DB, should be updated
        Location fetchedUpdatedLocation = locationRepository.findById(fetchedLocation.getId()).orElse(null);
        assertEquals(fetchedLocation.getDescription(), fetchedUpdatedLocation.getDescription());

        //verify count of products in DB
        long productCount = locationRepository.count();
        assertEquals(productCount, 1);

        //get all products, list should only have one
        Iterable<Location> products = locationRepository.findAll();

        int count = 0;

        for(Location p : products){
            count++;
        }

        assertEquals(count, 1);
    }
}
