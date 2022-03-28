package de.papenhagen.service;

import de.papenhagen.entities.BottleCount;
import de.papenhagen.entities.BottleSize;
import de.papenhagen.entities.CurrentMeasurement;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;

@ApplicationScoped
@Slf4j
public class MeasuringService {

    @Inject
    InfoCrawler infoCrawler;

    @Inject
    ConvertMeasuringUnit convertMeasuringUnit;

    @ConfigProperty(name = "weather.bottle.size", defaultValue = "177")
    public int bottleSize;

    public BottleCount calualteMeasuring() {
        //getting the accurate measurements form the cache
        final CurrentMeasurement currentMeasurement = infoCrawler.levelSanktArnual().getCurrentMeasurement();

        //location
        final int levelOfSanktArnual = currentMeasurement.getValue();
        final String timestamp = currentMeasurement.getTimestamp();

        log.debug("convertLvl: {}", levelOfSanktArnual);

        final int convertLvl = convertMeasuringUnit.convert(levelOfSanktArnual);
        final BottleSize bottle = BottleSize.of(this.bottleSize);
        final String unit = "An " + bottle.getName() + " Bottle with " + bottle.getSizeInMM() + " mm height.";
        log.debug("convertLvl: {}", convertLvl);

        return new BottleCount(convertLvl, unit, timestamp);
    }

    public String clear() {
        infoCrawler.invalidateAll();
        log.info("clear the level-cache at: {}", LocalDateTime.now());

        return "done";
    }
}
