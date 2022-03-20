package de.papenhagen.entities;

/**
 * CurrentMeasurement
 *
 * @author jpapenhagen
 */
public class CurrentMeasurement {
    private String timestamp;
    private double value;
    private int trend;
    private String stateMnwMhw;
    private String stateNswHsw;

    public CurrentMeasurement() {
    }

    public CurrentMeasurement(String timestamp, double value, int trend, String stateMnwMhw, String stateNswHsw) {
        this.timestamp = timestamp;
        this.value = value;
        this.trend = trend;
        this.stateMnwMhw = stateMnwMhw;
        this.stateNswHsw = stateNswHsw;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getTrend() {
        return trend;
    }

    public void setTrend(int trend) {
        this.trend = trend;
    }

    public String getStateMnwMhw() {
        return stateMnwMhw;
    }

    public void setStateMnwMhw(String stateMnwMhw) {
        this.stateMnwMhw = stateMnwMhw;
    }

    public String getStateNswHsw() {
        return stateNswHsw;
    }

    public void setStateNswHsw(String stateNswHsw) {
        this.stateNswHsw = stateNswHsw;
    }
}
