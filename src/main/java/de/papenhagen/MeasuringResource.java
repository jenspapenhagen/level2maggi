package de.papenhagen;

import de.papenhagen.entities.BottleCount;
import de.papenhagen.service.ConvertMeasuringUnit;
import de.papenhagen.service.InfoCrawler;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.ExecutionException;

/**
 * Endpoint to Convert the Level of the Saar in Sankt Arnual
 * into count of "Maggi Würze (250g)" - Bottles
 */
@Path("/saar")
@Slf4j
public class MeasuringResource {

    @Inject
    InfoCrawler infoCrawler;

    @Inject
    ConvertMeasuringUnit convertMeasuringUnit;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public BottleCount endpoint() {
        double convertLvl = 1;
        try {
            final double levelOfSanktArnual = infoCrawler.levelSanktArnual()
                    .toCompletableFuture()
                    .get()
                    .getCurrentMeasurement()
                    .getValue();

            convertLvl = convertMeasuringUnit.convert(levelOfSanktArnual);
        } catch (InterruptedException | ExecutionException e) {
            log.info("InterruptedException or ExecutionException on endpoint: {}", e.getLocalizedMessage());
        }

        return new BottleCount(convertLvl);
    }
}