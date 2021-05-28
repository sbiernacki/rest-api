package pl.biernat.week7.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.biernat.week7.integration.model.Response;

@Service
public class CallExternalNewsApi {

    private final RestTemplate restTemplate;

    @Value("${news.apiKey}")
    private String apiKey;

    @Autowired
    public CallExternalNewsApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Response fetchNews() {
        return restTemplate.getForObject("https://newsapi.org/v2/everything?q=apple&from=2021-05-20&to=2021-05-20&apiKey=" + apiKey, Response.class);
    }
}
