package de.papenhagen.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Score {
    private String product;
    private Integer numberOfRatings;
    private Integer accumulatedScore;
}
