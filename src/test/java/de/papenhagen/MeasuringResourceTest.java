package de.papenhagen;

import de.papenhagen.entities.Root;
import de.papenhagen.service.ConvertMeasuringUnit;
import de.papenhagen.service.InfoCrawler;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(MineProfile.class)
public class MeasuringResourceTest {

    @Test
    public void testInfoCrawlerLevleSanktArnual() {
        // given
        final InfoCrawler infoCrawler = new InfoCrawler();


        // when
        Root rootCompletionStage =  infoCrawler.levelSanktArnual();
        // then
        Assertions.assertNotNull(rootCompletionStage);
        Assertions.assertEquals(rootCompletionStage.getCurrentMeasurement().getValue(), 1.0);
    }

    @Test
    public void testConvertMeasuringUnit() {
        // given
        final ConvertMeasuringUnit convertMeasuringUnit = new ConvertMeasuringUnit();


        // when
        final double convert = convertMeasuringUnit.convert(202.0);

        // then
        Assertions.assertEquals(18, convert);
    }


}