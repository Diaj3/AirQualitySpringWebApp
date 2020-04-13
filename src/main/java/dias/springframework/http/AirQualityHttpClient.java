package dias.springframework.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class AirQualityHttpClient implements HttpClient {

    private CloseableHttpClient client;
    private String TOKEN = "3c955c3676946b481be9519656dd86960b566462";

    public AirQualityHttpClient() {
        this.client = HttpClients.createDefault();
    }

    @Override
    public String get(String url) throws IOException {
        HttpGet request = new HttpGet(url + "/?token=" + this.TOKEN);
        CloseableHttpResponse response = this.client.execute(request);
        try {
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Erro na HttpResponse ");
        }
        return null;
    }
}
