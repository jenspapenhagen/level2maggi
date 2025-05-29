package de.papenhagen.entities;


import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Root
 *
 * @author jpapenhagen
 */
@XmlRootElement
public class Root {
    private String shortName;
    private String fullName;
    private String unit;
    private int equidistance;
    private CurrentMeasurement currentMeasurement;
    private GaugeZero gaugeZero;

    public Root(String shortName, String fullName, String unit, int equidistance, CurrentMeasurement currentMeasurement, GaugeZero gaugeZero) {
        this.shortName = shortName;
        this.fullName = fullName;
        this.unit = unit;
        this.equidistance = equidistance;
        this.currentMeasurement = currentMeasurement;
        this.gaugeZero = gaugeZero;
    }

    public Root() {
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getEquidistance() {
        return equidistance;
    }

    public void setEquidistance(int equidistance) {
        this.equidistance = equidistance;
    }

    public CurrentMeasurement getCurrentMeasurement() {
        return currentMeasurement;
    }

    public void setCurrentMeasurement(CurrentMeasurement currentMeasurement) {
        this.currentMeasurement = currentMeasurement;
    }

    public GaugeZero getGaugeZero() {
        return gaugeZero;
    }

    public void setGaugeZero(GaugeZero gaugeZero) {
        this.gaugeZero = gaugeZero;
    }
}
