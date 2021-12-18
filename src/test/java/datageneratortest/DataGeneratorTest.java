package datageneratortest;

import datagenerator.DataGenerator;
import datasciencealgorithms.utils.Point;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataGeneratorTest {

    DataGenerator dataGenerator;
    @BeforeEach
    void setUp(){
        dataGenerator = new DataGenerator();
    }

    @Test
    void shouldGenerateDataWithTrend(){

        BigDecimal trend = new BigDecimal(".2");
        List<BigDecimal> actualDecimals = new ArrayList<>();
        List<BigDecimal> expectedDecimals = dataGenerator.generateDataWithTrend(10, trend)
                .stream()
                .map(Point::getY).collect(Collectors.toList());

        actualDecimals.add(BigDecimal.ONE);
        // Generate each point of data
        for (int i = 1; i < expectedDecimals.size(); i++) {
            actualDecimals.add(actualDecimals.get(i - 1).add(trend));
        }

        // Assert that the expected data is equal to generated data
        Assertions.assertAll(
                actualDecimals.stream().map(x ->
                        (() -> Assertions.assertEquals(x.doubleValue(), expectedDecimals.get(actualDecimals.indexOf(x)).doubleValue(), 0))));

    }

}
