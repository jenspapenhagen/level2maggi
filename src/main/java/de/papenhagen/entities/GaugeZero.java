package de.papenhagen.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * GaugeZero
 *
 * @author jpapenhagen
 */
@Data
@NoArgsConstructor
public class GaugeZero {
    private String unit;
    private double value;
    private String validFrom;
}
