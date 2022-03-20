package de.papenhagen;

import de.papenhagen.entities.CurrentMeasurement;
import de.papenhagen.entities.GaugeZero;
import de.papenhagen.entities.Root;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.adapter.JsonbAdapter;

public class RootAdapter implements JsonbAdapter<Root, JsonObject> {

    @Override
    public JsonObject adaptToJson(Root root) throws Exception {
        final CurrentMeasurement currentMeasurement = root.getCurrentMeasurement();
        final GaugeZero gaugeZero = root.getGaugeZero();
        return Json.createObjectBuilder()
                .add("shortname", root.getShortname())
                .add("longname", root.getLongname())
                .add("unit", root.getUnit())
                .add("equidistance", root.getEquidistance())
                .add("currentMeasurement", Json.createObjectBuilder()
                        .add("timestamp", currentMeasurement.getTimestamp())
                        .add("value", currentMeasurement.getValue())
                        .add("trend", currentMeasurement.getTrend())
                        .add("stateMnwMhw", currentMeasurement.getStateMnwMhw())
                        .add("stateNswHsw", currentMeasurement.getStateNswHsw())
                        .build())
                .add("gaugeZero", Json.createObjectBuilder()
                        .add("unit", gaugeZero.getUnit())
                        .add("value", gaugeZero.getValue())
                        .add("validForm", gaugeZero.getValidFrom())
                        .build())
                .build();
    }

    @Override
    public Root adaptFromJson(JsonObject jsonObject) throws Exception {
        final Root root = new Root();
        root.setShortname(jsonObject.getString("shortname"));
        root.setLongname(jsonObject.getString("longname"));
        root.setUnit(jsonObject.getString("unit"));
        root.setEquidistance(jsonObject.getInt("equidistance"));

        final JsonObject currentMeasurement = jsonObject.getJsonObject("currentMeasurement");
        root.setCurrentMeasurement(new CurrentMeasurement(currentMeasurement.getString("timestamp"),
                currentMeasurement.getJsonNumber("value").doubleValue(),
                currentMeasurement.getInt("trend"),
                currentMeasurement.getString("stateMnwMhw"),
                currentMeasurement.getString("stateNswHsw")
        ));
        final JsonObject gaugeZero = jsonObject.getJsonObject("gaugeZero");
        root.setGaugeZero(new GaugeZero(gaugeZero.getString("unit"),
                gaugeZero.getJsonNumber("value").doubleValue(),
                gaugeZero.getString("validForm")));
        return root;
    }
}
