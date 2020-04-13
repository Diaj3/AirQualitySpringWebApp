package dias.springframework.services;


import dias.springframework.domain.Location;

public interface LocationService {
    Iterable<Location> listAllProducts();

    Location getProductById(Integer id);

    Location saveProduct(Location location);

    Location getLocationById(Integer id);

    Location saveLocation(Location location);

    Iterable<Location> listAllLocations();
}
