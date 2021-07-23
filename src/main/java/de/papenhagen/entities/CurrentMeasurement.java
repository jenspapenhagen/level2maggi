package de.papenhagen.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * CurrentMeasurement
 *
 * @author jpapenhagen
 */
@Data
@NoArgsConstructor
public class CurrentMeasurement {
    // will work in later version of Quarkus
    //    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ssZ")
    //    private LocalDateTime timestamp;
    private Date timestamp;
    private double value;
    private int trend;
    private String stateMnwMhw;
    private String stateNswHsw;
}
