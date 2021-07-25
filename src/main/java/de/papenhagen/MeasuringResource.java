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
import java.time.LocalDateTime;
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
        final double levelOfSanktArnual = infoCrawler.levelSanktArnual()
                .getCurrentMeasurement()
                .getValue();

        double convertLvl = convertMeasuringUnit.convert(levelOfSanktArnual);

        return new BottleCount(convertLvl);
    }

    @GET
    @Path("clear")
    @Produces(MediaType.TEXT_PLAIN)
    public String clear(){
        infoCrawler.invalidateAll();
        log.info("clear the level-cache at: {}", LocalDateTime.now());

        return "done";
    }
}