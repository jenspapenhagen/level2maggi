package de.papenhagen.service;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;

/**
 * Service to Convert form Meter into
 * "Maggi Würze" - Bottle
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
     * info: this methode is rouding up the count of
     *
     * @param centimeter of the orignal size
     * @return the count of bottles
     */
    public double convert(final double centimeter) {
        final int bottleSizeInMM = 177;
        return Math.ceil((centimeter * 10) / bottleSize);
    }

}
