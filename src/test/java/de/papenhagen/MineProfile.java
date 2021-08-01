package de.papenhagen;

import io.quarkus.test.junit.QuarkusTestProfile;

import java.util.Map;

public class MineProfile implements QuarkusTestProfile {
    @Override
    public Map<String, String> getConfigOverrides() {
        return Map.of(
                "weather.url", "https://www.pegelonline.wsv.de/webservices/rest-api/v2/stations/",
                "weather.bottle.size", "177"
        );
    }

    @Override
    public String getConfigProfile() {
        return "mine";
    }

}