package de.papenhagen.service;

import de.papenhagen.entities.CurrentMeasurement;
import de.papenhagen.entities.Root;
import io.quarkus.cache.CacheInvalidateAll;
import io.quarkus.cache.CacheResult;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class InfoCrawler {

    @ConfigProperty(name = "weather.url", defaultValue = "https://www.pegelonline.wsv.de")
    URL url;

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public Root levelSanktArnual() {
        final Root fallback = new Root();
        final CurrentMeasurement currentMeasurement = new CurrentMeasurement();
        currentMeasurement.setValue(1.0);
        fallback.setCurrentMeasurement(currentMeasurement);

        try {
            return callRemote()
                    .toCompletableFuture()
                    .get();
        } catch (Exception e) {
            log.warn("An Exception get thrown: {}, sending the Fallback", e.getLocalizedMessage());
            return fallback;
        }
    }

    /**
     * This remote Call can take some Time, therefore we cache the result.
     * (this have to be public because the @CacheResult Annotation is not allowed on private methods)
     * @return the CompletionStage of the remote Call
     */
    @CacheResult(cacheName = "level-cache")
    public CompletionStage<Root> callRemote() {
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

    /**
     * mostly for debugging
     */
    @CacheInvalidateAll(cacheName = "level-cache")
    public void invalidateAll() {
    }


}
