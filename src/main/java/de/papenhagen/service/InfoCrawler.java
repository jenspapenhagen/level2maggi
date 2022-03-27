package de.papenhagen.service;

import de.papenhagen.SerializerRegistrationCustomizer;
import de.papenhagen.entities.CurrentMeasurement;
import de.papenhagen.entities.GaugeZero;
import de.papenhagen.entities.Root;
import io.quarkus.cache.CacheInvalidateAll;
import io.quarkus.cache.CacheResult;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
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

    @Inject
    SerializerRegistrationCustomizer jsonp;

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public Root levelSanktArnual() {
        final CurrentMeasurement currentMeasurement = new CurrentMeasurement();
        currentMeasurement.setValue(1);
        currentMeasurement.setStateMnwMhw("test");
        currentMeasurement.setStateNswHsw("test");
        currentMeasurement.setTimestamp("test time");

        GaugeZero gaugeZero = new GaugeZero();
        final Root fallback = new Root(
                "shortname",
                "LongName",
                "cm",
                0,
                currentMeasurement,
                gaugeZero);


        try {
            final Response response = callRemote();
            String output = response.readEntity(String.class);

            return jsonp.customize().fromJson(output, Root.class);
        } catch (Exception e) {
            log.warn("An Exception get thrown: {}, sending the Fallback", e.getLocalizedMessage());
            log.error("stacktrace: " + e.getStackTrace());
            return fallback;
        }
    }

    /**
     * This remote Call can take some Time, therefore we cache the result.
     * (this has to be public because the @CacheResult Annotation is not allowed on private methods)
     *
     * @return the CompletionStage of the remote Call
     */
    @CacheResult(cacheName = "level-cache")
    public Response callRemote() {
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
                .get();
    }

    /**
     * mostly for debugging
     */
    @CacheInvalidateAll(cacheName = "level-cache")
    public void invalidateAll() {
    }


}
