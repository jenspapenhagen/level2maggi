package de.papenhagen.service;

import de.papenhagen.entities.BottleSize;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Service to Convert form Meter into
 * "Maggi Würze" - Bottle Count
 *
 * @author jpapenhagen
 */
@ApplicationScoped
@Slf4j
public class ConvertMeasuringUnit {

    /**
     * Table of Bootle Sizes:
     * "Maggi Würze 125g" - Bottle size of 100 mm (need test measuring)
     * "Maggi Würze 250g" - Bottle size of 177 mm
     * "Maggi Würze 1000g" - Bottle size of 270 mm
     */
    @ConfigProperty(name = "weather.bottle.size", defaultValue = "177")
    public int bottleSize;

    /**
     * Convert meter into bottle count
     * <p>
     * info: this methode is rounding up the count
     *
     * @param centimeter of the orignal size
     * @return the count of bottles
     */
    public int convert(final int centimeter) {
        //checking against divided by zero
        final int defaultBottleSizeInMM = 117;
        final int bottleSizeInMM = (bottleSize <= 0) ? defaultBottleSizeInMM : bottleSize;

        final BottleSize bottle = BottleSize.of(bottleSizeInMM);
        final double sizeInMM = (centimeter * 10);

        return new BigDecimal(sizeInMM / bottle.getSizeInMM())
                .setScale(0, RoundingMode.UP)
                .intValue();
    }


}


