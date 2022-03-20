package de.papenhagen.entities;

/**
 * GaugeZero
 *
 * @author jpapenhagen
 */
public class GaugeZero {
    private String unit;
    private double value;
    private String validFrom;

    public GaugeZero() {
    }

    public GaugeZero(String unit, double value, String validFrom) {
        this.unit = unit;
        this.value = value;
        this.validFrom = validFrom;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }
}
