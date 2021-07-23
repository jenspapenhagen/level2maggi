package de.papenhagen.service;

import de.papenhagen.entities.Root;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.net.URL;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * InfoCrawler to crawl a given URL
 *
 * @author jpapenhagen
 */
@ApplicationScoped
public class InfoCrawler {

    @ConfigProperty(name = "weather.url", defaultValue = "https://www.pegelonline.wsv.de")
    URL url;

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public CompletionStage<Root> levleSanktArnual() {
        Client client = ClientBuilder
                .newBuilder()
                .executorService(executorService)
                .build();

        // Pegels Sankt Arnual
        String station = "SANKT%20ARNUAL";
        String enpoint = "/W.json";

        // complete URL: "https://www.pegelonline.wsv.de/webservices/rest-api/v2/stations/SANKT%20ARNUAL/W.json?includeCurrentMeasurement=true");
        return client.target(url + station + enpoint)
                .queryParam("includeCurrentMeasurement", true)
                .request()
                .rx()
                .get(Root.class);
    }


}
