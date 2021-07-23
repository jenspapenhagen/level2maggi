package de.papenhagen.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Root
 *
 * @author jpapenhagen
 */
@Data
@NoArgsConstructor
public class Root {
    private String shortname;
    private String longname;
    private String unit;
    private int equidistance;
    private CurrentMeasurement currentMeasurement;
    private GaugeZero gaugeZero;


}
