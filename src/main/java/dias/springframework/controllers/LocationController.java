package dias.springframework.controllers;

import dias.springframework.domain.Location;
import dias.springframework.http.AirQualityHttpClient;
import dias.springframework.http.HttpClient;
import dias.springframework.repositories.LocationRepository;
import dias.springframework.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

@Controller
public class LocationController {

    private LocationService locationService;

    private static HttpURLConnection connection;

    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    // Get All Notes
    @GetMapping("/region")
    public List<Location> getAllNotes() {
        return null; //LocationRepository.findAll();
    }

//    @GetMapping("/{name}")
//    public void getFromAPI(@PathVariable(value = "name") String name) throws URISyntaxException, IOException {
//        HttpClient httpClient = new AirQualityHttpClient();
//        URIBuilder uriBuilder = new URIBuilder("https://api.waqi.info/feed/" + name);
//        String response = httpClient.get(uriBuilder.build().toString());
//        System.out.println("RESPONSE: " + response);
//    }





    // EXTRAS FROM THE TEMPLATE -------------------------------------------------------------------------------------------

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String list(Model model) throws URISyntaxException, IOException {
        model.addAttribute("locations", locationService.listAllLocations());
        System.out.println("TESTING || TESTING");
        HttpClient httpClient = new AirQualityHttpClient(); //Está a morrer aqui

        URIBuilder uriBuilder = new URIBuilder("https://api.waqi.info/feed/" + "lisbon");
        String response = httpClient.get(uriBuilder.build().toString());
        System.out.println("RESPONSE: " + response);
        return "products";
    }

    @RequestMapping("product/{id}")
    public String showProduct(@PathVariable Integer id, Model model){
        model.addAttribute("product", locationService.getProductById(id));
        return "productshow";
    }
}
