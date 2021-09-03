package de.papenhagen.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
     * "Maggi Würze 125g" - Bottle size of 100 mm (need test measuring)
     * "Maggi Würze 250g" - Bottle size of 177 mm
     * "Maggi Würze 1000g" - Bottle size of 270 mm
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
        final int bottleSizeInMM = 1 < bottleSize ? defaultBottleSizeInMM : bottleSize;
        return new BigDecimal((centimeter * 10) / bottleSizeInMM)
                .setScale(0, RoundingMode.UP)
                .intValue();
    }

    public InputStream filterBlacklist(final InputStream csvInputSteam, final List<Integer> blackList) {
        List<String> inputList =  new ArrayList<>();
        // use auto closing of the stream, again IOException
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(csvInputSteam))) {
            int lineCount = 0;
            while (reader.ready()) {
                if (!blackList.contains(lineCount)) {
                    inputList.add(reader.readLine());
                }
                lineCount++;
            }

        } catch(IOException ex) {
            ex.printStackTrace();
        }

        // convert ArrayList of String back to an InputSteam
        byte[] bytes = inputList.stream().collect(Collectors.joining("\n", "", "\n")).getBytes();

        List<Score> scoreValues = new ArrayList<>();
        Optional<Score> score = scoreValues.stream()
                .filter(Objects::nonNull)
                .findFirst();

        score.isEmpty()

        return new ByteArrayInputStream(bytes);
    }


}


