package de.papenhagen.entities;


import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Root
 *
 * @author jpapenhagen
 */
@XmlRootElement
public class Root {
    private String shortname;
    private String longname;
    private String unit;
    private int equidistance;
    private CurrentMeasurement currentMeasurement;
    private GaugeZero gaugeZero;

    public Root(String shortname, String longname, String unit, int equidistance, CurrentMeasurement currentMeasurement, GaugeZero gaugeZero) {
        this.shortname = shortname;
        this.longname = longname;
        this.unit = unit;
        this.equidistance = equidistance;
        this.currentMeasurement = currentMeasurement;
        this.gaugeZero = gaugeZero;
    }

    public Root() {
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getLongname() {
        return longname;
    }

    public void setLongname(String longname) {
        this.longname = longname;
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
