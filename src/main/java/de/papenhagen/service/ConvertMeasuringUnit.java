package de.papenhagen.service;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Service to Convert form Meter into
 * "Maggi Würze" - Bottle Count
 *
 * @author jpapenhagen
 */
@ApplicationScoped
public class ConvertMeasuringUnit {

    /**
     * Table of Bootle Sizes:
     *  "Maggi Würze 125g" - Bottle size of 100 mm (need test measuring)
     *  "Maggi Würze 250g" - Bottle size of 177 mm
     *  "Maggi Würze 1000g" - Bottle size of 270 mm
     */
    @ConfigProperty(name = "weather.bottle.size", defaultValue = "177")
    int bottleSize;

    /**
     * Convert meter into bottle count
     * <p>
     * info: this methode is rounding up the count
     *
     * @param centimeter of the orignal size
     * @return the count of bottles
     */
    public int convert(final double centimeter) {
        //checking against divided by zero
        final int defaultBottleSizeInMM = 117;
        final int bottleSizeInMM =  1 < bottleSize ? defaultBottleSizeInMM : bottleSize;
        return new BigDecimal((centimeter * 10) / bottleSizeInMM)
                .setScale(0, RoundingMode.UP)
                .intValue();
    }

}
