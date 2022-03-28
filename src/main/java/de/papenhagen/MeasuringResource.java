package de.papenhagen;

import de.papenhagen.entities.BottleCount;
import de.papenhagen.entities.CurrentMeasurement;
import de.papenhagen.service.ConvertMeasuringUnit;
import de.papenhagen.service.InfoCrawler;
import de.papenhagen.service.MeasuringService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;

/**
 * Endpoint to Convert the Level of the Saar in Sankt Arnual
 * into count of "Maggi Würze (250g)" - Bottles
 */
@Path("/saar")
@Slf4j
public class MeasuringResource {

    @Inject
    MeasuringService measuringService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public BottleCount endpoint() {
        final BottleCount bottleCount = measuringService.calualteMeasuring();
        log.info("BottleCount: {}", bottleCount.getCount());

        return bottleCount;
    }

    @GET
    @Path("clear")
    @Produces(MediaType.TEXT_PLAIN)
    public String clear() {
        return measuringService.clear();
    }
}