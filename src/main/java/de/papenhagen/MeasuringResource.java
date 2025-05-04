package de.papenhagen;

import de.papenhagen.entities.BottleCount;
import de.papenhagen.service.MeasuringService;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

/**
 * Endpoint to Convert the Level of the Saar in Sankt Arnual
 * default into count of "Maggi Würze (250g)" - Bottles
 */
@Path("/")
public class MeasuringResource {

    private static final Logger log = LoggerFactory.getLogger(MeasuringResource.class);

    @Inject
    MeasuringService measuringService;

    @GET
    @Path("{size}")
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
    public String clear() {
        return measuringService.clear();
    }
}