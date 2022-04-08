package de.papenhagen.entities;

import java.util.Arrays;

public enum BottleSize {
    SMALL("Maggi Würze 125g", 100), MIDDLE("Maggi Würze 250g", 177), BIG("Maggi Würze 1000g", 270);

    private final String name;
    private final int sizeInMM;

    BottleSize(String name, int sizeInMM) {
        this.name = name;
        this.sizeInMM = sizeInMM;
    }

    public static BottleSize of(final int size) {
        return Arrays.stream(BottleSize.values())
                .filter(b -> b.sizeInMM == size)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Bottle Size unknown"));
    }

    public String getName() {
        return name;
    }

    public int getSizeInMM() {
        return sizeInMM;
    }
}
