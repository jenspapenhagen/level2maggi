package de.papenhagen;

import de.papenhagen.entities.BottleCount;
import de.papenhagen.service.MeasuringService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;

/**
 * Endpoint to Convert the Level of the Saar in Sankt Arnual
 * default into count of "Maggi Würze (250g)" - Bottles
 */
@Path("/")
@Slf4j
public class MeasuringResource {

    @Inject
    MeasuringService measuringService;

    @GET
    @Path("{size}")
    @Produces(MediaType.APPLICATION_JSON)
    public BottleCount endpoint(@PathParam("size") @DefaultValue("177") final String size) {
        try {
            final int parseLong = Integer.parseInt(size);

            final BottleCount bottleCount = measuringService.calualteMeasuring(parseLong);
            log.info("BottleCount: {}", bottleCount.getCount());
            return bottleCount;
        } catch (IllegalArgumentException exception) {
            log.warn("wrong input or");
        }

        return new BottleCount(0, "wrong input", LocalDateTime.now().toString());
    }

    @DELETE
    @Path("clear")
    @Produces(MediaType.TEXT_PLAIN)
    public String clear() {
        return measuringService.clear();
    }
}