package de.papenhagen.service;

import javax.enterprise.context.ApplicationScoped;

/**
 * Service to Convert form Meter into
 * "Maggi Würze (250g)" - Bottle
 *
 * @author jpapenhagen
 */
@ApplicationScoped
public class ConvertMeasuringUnit {

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
        return Math.ceil((centimeter * 10) / bottleSizeInMM);
    }

}
