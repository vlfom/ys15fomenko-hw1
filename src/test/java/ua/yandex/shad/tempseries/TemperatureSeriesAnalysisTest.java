package ua.yandex.shad.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void testAverage() {
        double[] temperatureSeries = {
                1.0, -5.0, 1.0, 5.0, -20.5, 13.5, 2.0, 0.0, 0.5, 11.3, 10.0, -10.0, 15.0, 16.0,
                -17.0, 29.0, -88.0, -19.0, 13.5, 14.1, -11.0, -15.0, 29.0, -23.4, -2.7
        };
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -2.028;
        double actualResult = seriesAnalysis.average();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testDeviation() {
        double[] temperatureSeries = {
                1.0, -5.0, 1.0, 5.0, -20.5, 13.5, 2.0, 0.0, 0.5, 11.3, 10.0, -10.0, 15.0, 16.0,
                -17.0, 29.0, -88.0, -19.0, 13.5, 14.1, -11.0, -15.0, 29.0, -23.4, -2.7
        };
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 22.558838;
        double actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMin() {
        double[] temperatureSeries = {
                1.0, -5.0, 1.0, 5.0, -20.5, 13.5, 2.0, 0.0, 0.5, 11.3, 10.0, -10.0, 15.0, 16.0,
                -17.0, 29.0, -88.0, -19.0, 13.5, 14.1, -11.0, -15.0, 29.0, -23.4, -2.7
        };
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -88.0;
        double actualResult = seriesAnalysis.min();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMax() {
        double[] temperatureSeries = {
                1.0, -5.0, 1.0, 5.0, -20.5, 13.5, 2.0, 0.0, 0.5, 11.3, 10.0,
                -10.0, 15.0, 16.0, -17.0, 29.0, -88.0, -19.0, 13.5, 14.1,
                -11.0, -15.0, 29.0, -23.4, -2.7
        };
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 29.0;
        double actualResult = seriesAnalysis.max();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToZero() {
        double[] temperatureSeries = {
                1.0, -5.0, 1.0, 5.0, -20.5, 13.5, 2.0, 0.1, 0.5, 11.3, 10.0,
                -10.0, 15.0, 16.0, -17.0, 29.0, -88.0, -19.0, 13.5, 14.1,
                -11.0, -15.0, 29.0, -23.4, -2.7
        };
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.1;
        double actualResult = seriesAnalysis.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToValue() {
        double[] temperatureSeries = {
                1.0, -5.0, 1.0, 5.0, -20.5, 13.5, 2.0, 0.0, 0.5, 11.3, 10.0,
                -10.0, 15.0, 16.0, -17.0, 29.0, -88.0, -19.0, 13.5, 14.1,
                -11.0, -15.0, 29.0, -23.4, -2.7
        };
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 16.0;
        double actualResult = seriesAnalysis.findTempClosestToValue(19.3);

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempLessThan() {
        double[] temperatureSeries = {
                1.0, -5.0, 1.0, 5.0, -20.5, 13.5, 2.0, 0.0, 0.5, 11.3, 10.0,
                -10.0, 15.0, 16.0, -17.0, 29.0, -88.0, -19.0, 13.5, 14.1,
                -11.0, -15.0, 29.0, -23.4, -2.7
        };

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {
                1.0, -5.0, 1.0, 5.0, -20.5, 13.5, 2.0, 0.0, 0.5, 11.3, 10.0, -10.0,
                -17.0, -88.0, -19.0, 13.5, 14.1, -11.0, -15.0, -23.4, -2.7
        };
        double[] actualResult = seriesAnalysis.findTempsLessThen(14.3);

        assertEquals(expResult.length, actualResult.length);
        for (int i = 0; i < expResult.length; ++i)
            assertEquals(expResult[i], actualResult[i], 0.00001);
    }

    @Test
    public void testFindTempGreaterThan() {
        double[] temperatureSeries = {
                1.0, -5.0, 1.0, 5.0, -20.5, 13.5, 2.0, 0.0, 0.5, 11.3, 10.0,
                -10.0, 15.0, 16.0, -17.0, 29.0, -88.0, -19.0, 13.5, 14.1,
                -11.0, -15.0, 29.0, -23.4, -2.7
        };
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {15.0, 16.0, 29.0, 29.0};
        double[] actualResult = seriesAnalysis.findTempsGreaterThen(14.3);

        assertEquals(expResult.length, actualResult.length);
        for (int i = 0; i < expResult.length; ++i)
            assertEquals(expResult[i], actualResult[i], 0.00001);
    }
}
