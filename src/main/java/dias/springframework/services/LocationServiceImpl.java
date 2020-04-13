package dias.springframework.services;

import dias.springframework.domain.Location;
import dias.springframework.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {
    private LocationRepository locationRepository;

    @Autowired
    public void setLocationRepository(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Iterable<Location> listAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public Location getLocationById(Integer id) {
        return locationRepository.findById(id).orElse(null);
    }

    @Override
    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    //Extras ------------------------------------------------------------------

    @Override
    public Iterable<Location> listAllProducts() {
        return locationRepository.findAll();
    }

    @Override
    public Location getProductById(Integer id) {
        return locationRepository.findById(id).orElse(null);
    }

    @Override
    public Location saveProduct(Location location) {
        return locationRepository.save(location);
    }

}
