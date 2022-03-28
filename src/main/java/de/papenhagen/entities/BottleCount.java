package de.papenhagen.entities;

/**
 * BottleCount
 *
 * @author jpapenhagen
 */
public class BottleCount {
    int count;
    String unit;
    String timeStamp;

    public BottleCount(int count, String unit, String timeStamp) {
        this.count = count;
        this.unit = unit;
        this.timeStamp = timeStamp;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
