package dias.springframework.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import dias.springframework.domain.Location;
import dias.springframework.domain.Requests;
import dias.springframework.services.LocationService;
import dias.springframework.services.RequestsService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Scanner;

@Controller
public class LocationController {

    private LocationService locationService;

    private RequestsService requestsService;

    private static HttpURLConnection connection;

    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("locations", locationService.listAllLocations());
        return "locations";
    }

    // Get Info from API + Get info from JSON -------------------------------------------------------------------------------------------

    @RequestMapping(value = "/city", method = RequestMethod.POST)
    public String list(Location search_location, Model model) throws URISyntaxException, IOException {
        System.out.println("|_-_-_TESTING_-_-_|");

        //Inicializada a variável da nova localidade
        Location location = new Location();

        String TOKEN = "/?token=3c955c3676946b481be9519656dd86960b566462";
        String url = "https://api.waqi.info/feed/";
        String name_teste = search_location.getName();
        location.setName(name_teste);

        URLConnection connection = new URL(url + name_teste + TOKEN).openConnection();
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        InputStream response = connection.getInputStream();

        try (Scanner scanner = new Scanner(response)) {
            String responseBody = scanner.useDelimiter("\\A").next();

            System.out.print("JSON Response from de API");
            JsonParser parser = new JsonParser();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            JsonElement el = parser.parse(responseBody);
            responseBody = gson.toJson(el);
            System.out.println(responseBody);

            try {
                JSONObject jsonObject = new JSONObject(responseBody);
                System.out.println(jsonObject.getClass().getName());
                System.out.println("Trying to separate elements");

                String status = jsonObject.getString("status");
                System.out.println("STATUS: _______________________ " + status);

                if (!status.equals("ok")) {
                    System.out.println("If statement is working");
                    return "no_location_found";
                }

                JSONObject data = jsonObject.getJSONObject("data");
                JSONObject iaqi = data.getJSONObject("iaqi");
                JSONObject time = data.getJSONObject("time");
                JSONObject city = data.getJSONObject("city");

                Integer id = data.getInt("idx");
                if (locationService.getLocationById(id) != null){
                    location = locationService.getLocationById(id);
                    model.addAttribute("location", location);
                    return "location_info";
                }

                location.setId(id);

                try{
                    JSONArray geo = city.getJSONArray("geo");
                    location.setLatitude(Double.parseDouble(geo.get(0) + ""));
                    location.setLongitude(Double.parseDouble(geo.get(1) + ""));
                    System.out.println("Latitude: " + geo.get(0) + "Longitude: " + geo.get(1));
                }catch (JSONException e){
                    System.out.println("Error: " + e);
                    location.setLatitude(0.0);
                    location.setLongitude(0.0);
                }

                try{
                    Integer aqi = data.getInt("aqi");
                    location.setAqi(aqi);
                    System.out.println("AQI: " + aqi);
                }catch (JSONException e){
                    System.out.println("Error: " + e);
                    location.setAqi(0);
                }

                try{
                    String t = time.getString("s");
                    System.out.println("Time: " + t);
                    String tz = time.getString("tz");
                    System.out.println("Timezone: " + tz);
                    location.setTime(t);
                    location.setTimezone(tz);
                } catch(JSONException e) {
                    System.out.println("Error: " + e );
                    location.setTime("Undefined");
                    location.setTimezone("Undefined");
                }

                try{
                    JSONObject no2 = iaqi.getJSONObject("no2");
                    Double no2_value = no2.getDouble("v");
                    location.setNo2(no2_value);
                    System.out.println("no2: " + no2_value);
                } catch(JSONException e) {
                    System.out.println("Error: " + e );
                    location.setNo2(0.0);
                }
                try{
                    JSONObject p = iaqi.getJSONObject("p");
                    Double p_value = p.getDouble("v");
                    location.setP(p_value);
                    System.out.println("p: " + p_value);
                } catch(JSONException e) {
                    System.out.println("Error: " + e );
                    location.setP(0.0);
                }
                try{
                    JSONObject o3 = iaqi.getJSONObject("o3");
                    Double o3_value = o3.getDouble("v");
                    location.setO3(o3_value);
                    System.out.println("o3: " + o3_value);
                } catch(JSONException e) {
                    System.out.println("Error: " + e );
                    location.setO3(0.0);
                }
                try{
                    JSONObject pm25 = iaqi.getJSONObject("pm25");
                    Double pm25_value = pm25.getDouble("v");
                    location.setPm25(pm25_value);
                    System.out.println("pm25: " + pm25_value);
                } catch(JSONException e) {
                    System.out.println("Error: " + e );
                    location.setPm25(0.0);
                }
                try{
                    JSONObject so2 = iaqi.getJSONObject("so2");
                    Double so2_value = so2.getDouble("v");
                    location.setSo2(so2_value);
                    System.out.println("so2: " + so2_value);
                } catch(JSONException e) {
                    System.out.println("Error: " + e );
                    location.setSo2(0.0);
                }
                try{
                    JSONObject pm10 = iaqi.getJSONObject("pm10");
                    Double pm10_value = pm10.getDouble("v");
                    location.setPm10(pm10_value);
                    System.out.println("pm10: " + pm10_value);
                } catch(JSONException e) {
                    System.out.println("Error: " + e );
                    location.setPm10(0.0);
                }

            } catch (JSONException err) {
                System.out.println("Error" + err.toString());
            }
        }catch (Exception e){
            System.out.println("Error" + e);
        }
        locationService.saveLocation(location);
        model.addAttribute("location", location);
        return "location_info";
    }
}
