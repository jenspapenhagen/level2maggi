package de.papenhagen;

import de.papenhagen.entities.Root;
import de.papenhagen.service.ConvertMeasuringUnit;
import de.papenhagen.service.InfoCrawler;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletionStage;


@QuarkusTest
@TestProfile(MineProfile.class)
public class MeasuringResourceTest {

    @Test
    public void testInfoCrawlerLevleSanktArnual() {
        // given
        final InfoCrawler infoCrawler = new InfoCrawler();


        // when
        final CompletionStage<Root> rootCompletionStage = infoCrawler.levelSanktArnual();

        // then
        Assertions.assertNotNull(rootCompletionStage);
    }

    @Test
    public void testConvertMeasuringUnit() {
        // given
        final ConvertMeasuringUnit convertMeasuringUnit = new ConvertMeasuringUnit();


        // when
        final double convert = convertMeasuringUnit.convert(202.0);

        // then
        Assertions.assertEquals(12, convert);
    }


}