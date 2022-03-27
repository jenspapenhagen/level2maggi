package de.papenhagen.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * BottleCount
 *
 * @author jpapenhagen
 */
public class BottleCount {
    int count;

    public BottleCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
