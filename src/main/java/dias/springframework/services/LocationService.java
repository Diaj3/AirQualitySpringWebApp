package dias.springframework.services;


import dias.springframework.domain.Location;

public interface LocationService {

    Location getLocationById(Integer id);

    Location saveLocation(Location location);

    Iterable<Location> listAllLocations();

    long count();
}
