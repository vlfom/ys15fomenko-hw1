package ua.yandex.shad.tempseries;

import org.junit.Test;

import java.util.InputMismatchException;

import static org.junit.Assert.assertEquals;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void testDefaultConstructor() {
        TemperatureSeriesAnalysis seriesAnalysis = new
                TemperatureSeriesAnalysis();

        assertEquals(seriesAnalysis.getSize(), 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIllegalArgument() {
        TemperatureSeriesAnalysis seriesAnalysis = new
                TemperatureSeriesAnalysis();

        seriesAnalysis.average();
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {1.0, -5.0, 1.0, 5.0, -20.5, 13.5, 2.0,
                0.0, 0.5, 11.3, 10.0, -10.0, 15.0, 16.0, -17.0, 29.0, -88.0,
                -19.0, 13.5, 14.1, -11.0, -15.0, 29.0, -23.4, -2.7};
        TemperatureSeriesAnalysis seriesAnalysis = new
                TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -2.028;
        double actualResult = seriesAnalysis.average();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testDeviation() {
        double[] temperatureSeries = {1.0, -5.0, 1.0, 5.0, -20.5, 13.5, 2.0,
                0.0, 0.5, 11.3, 10.0, -10.0, 15.0, 16.0, -17.0, 29.0, -88.0,
                -19.0, 13.5, 14.1, -11.0, -15.0, 29.0, -23.4, -2.7};
        TemperatureSeriesAnalysis seriesAnalysis = new
                TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 22.558838;
        double actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMin() {
        double[] temperatureSeries = {1.0, -5.0, 1.0, 5.0, -20.5, 13.5, 2.0,
                0.0, 0.5, 11.3, 10.0, -10.0, 15.0, 16.0, -17.0, 29.0, -88.0,
                -19.0, 13.5, 14.1, -11.0, -15.0, 29.0, -23.4, -2.7};
        TemperatureSeriesAnalysis seriesAnalysis = new
                TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -88.0;
        double actualResult = seriesAnalysis.min();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMax() {
        double[] temperatureSeries = {1.0, -5.0, 1.0, 5.0, -20.5, 13.5, 2.0,
                0.0, 0.5, 11.3, 10.0, -10.0, 15.0, 16.0, -17.0, 29.0, -88.0,
                -19.0, 13.5, 14.1, -11.0, -15.0, 29.0, -23.4, -2.7};
        TemperatureSeriesAnalysis seriesAnalysis = new
                TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 29.0;
        double actualResult = seriesAnalysis.max();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToZero() {
        double[] temperatureSeries = {-1.0, -5.0, 1.0, 5.0, -20.5, 13.5, 2.0,
                0.0, 0.5, 11.3, 10.0, -10.0, 15.0, 16.0, -17.0, 29.0, -88.0,
                -19.0, 13.5, 14.1, -11.0, -15.0, 29.0, -23.4, -2.7};
        TemperatureSeriesAnalysis seriesAnalysis = new
                TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.0;
        double actualResult = seriesAnalysis.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindAnotherTempClosestToZero() {
        double[] temperatureSeries = {1.0, -5.0, -1.0, 5.0, -20.5, 13.5, 2.0,
                0.0, 0.5, 11.3, 10.0, -10.0, 15.0, 16.0, -17.0, 29.0, -88.0,
                -19.0, 13.5, 14.1, -11.0, -15.0, 29.0, -23.4, -2.7};
        TemperatureSeriesAnalysis seriesAnalysis = new
                TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.0;
        double actualResult = seriesAnalysis.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToValue() {
        double[] temperatureSeries = {-1.0, -5.0, 1.0, 5.0, -20.5, 13.5, 2.0,
                0.0, 0.5, 11.3, 10.0, -10.0, 15.0, 16.0, -17.0, 29.0, -88.0,
                -19.0, 13.5, 14.1, -11.0, -15.0, 29.0, -23.4, -2.7};
        TemperatureSeriesAnalysis seriesAnalysis = new
                TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 16.0;
        double actualResult = seriesAnalysis.findTempClosestToValue(19.3);

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempLessThan() {
        double[] temperatureSeries = {1.0, -5.0, 1.0, 5.0, -20.5, 13.5, 2.0,
                0.0, 0.5, 11.3, 10.0, -10.0, 15.0, 16.0, -17.0, 29.0, -88.0,
                -19.0, 13.5, 14.1, -11.0, -15.0, 29.0, -23.4, -2.7};
        TemperatureSeriesAnalysis seriesAnalysis = new
                TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {1.0, -5.0, 1.0, 5.0, -20.5, 13.5, 2.0, 0.0,
                0.5, 11.3, 10.0, -10.0, -17.0, -88.0, -19.0, 13.5, 14.1,
                -11.0, -15.0, -23.4, -2.7};
        double[] actualResult = seriesAnalysis.findTempsLessThen(14.3);

        assertEquals(expResult.length, actualResult.length);
        for (int i = 0; i < expResult.length; ++i) {
            assertEquals(expResult[i], actualResult[i], 0.00001);
        }
    }

    @Test
    public void testFindTempGreaterThan() {
        double[] temperatureSeries = {1.0, -5.0, 1.0, 5.0, -20.5, 13.5, 2.0,
                0.0, 0.5, 11.3, 10.0, -10.0, 15.0, 16.0, -17.0, 29.0, -88.0,
                -19.0, 13.5, 14.1, -11.0, -15.0, 29.0, -23.4, -2.7};
        TemperatureSeriesAnalysis seriesAnalysis = new
                TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {15.0, 16.0, 29.0, 29.0};
        double[] actualResult = seriesAnalysis.findTempsGreaterThen(14.3);

        assertEquals(expResult.length, actualResult.length);
        for (int i = 0; i < expResult.length; ++i) {
            assertEquals(expResult[i], actualResult[i], 0.00001);
        }
    }

    @Test
    public void testTempSummaryStatisticsEquals() {
        double[] temperatureSeries = {1.0, -5.0, 1.0, 5.0, -20.5, 13.5, 2.0,
                0.0, 0.5, 11.3, 10.0, -10.0, 15.0, 16.0, -17.0, 29.0, -88.0,
                -19.0, 13.5, 14.1, -11.0, -15.0, 29.0, -23.4, -2.7};
        TemperatureSeriesAnalysis seriesAnalysis = new
                TemperatureSeriesAnalysis(temperatureSeries);
        TempSummaryStatistics expResult = new TempSummaryStatistics(-2.028,
                22.558838, -88.0, 29.0);
        TempSummaryStatistics actualResult = seriesAnalysis.summaryStatistics();

        assertEquals(expResult.getAvgTemp(), actualResult.getAvgTemp(),
                0.00001);
        assertEquals(expResult.getDevTemp(), actualResult.getDevTemp(),
                0.00001);
        assertEquals(expResult.getMinTemp(), actualResult.getMinTemp(),
                0.00001);
        assertEquals(expResult.getMaxTemp(), actualResult.getMaxTemp(),
                0.00001);
    }

    @Test
    public void testAddTemps() {
        double[] temperatureSeries = {1.0, -5.0, 1.0, 5.0, -20.5, 13.5, 2.0,
                0.0, 0.5, 11.3, 10.0, -10.0, 15.0, 16.0, -17.0, 29.0, -88.0,
                -19.0, 13.5, 14.1, -11.0, -15.0, 29.0, -23.4, -2.7};
        TemperatureSeriesAnalysis seriesAnalysis = new
                TemperatureSeriesAnalysis(temperatureSeries);
        double[] addTemps = {-2.5, 0.2, 3.4, 11.6, 12.8, -172.4, 199.0,
                235.10, -88.2, 33.4, -11.3, -44.6, 35.1, 68.4, -36.2, -111.2,
                33.4};

        int expectedNewSize = temperatureSeries.length + addTemps.length;
        int newSize = seriesAnalysis.addTemps(addTemps);

        assertEquals(expectedNewSize, newSize);
    }

    @Test(expected = InputMismatchException.class)
    public void testAddTempsInputMismatch() {
        double[] temperatureSeries = {1.0, -5.0, 1.0, 5.0, -20.5, 13.5, 2.0,
                0.0, 0.5, 11.3, 10.0, -10.0, 15.0, 16.0, -17.0, 29.0, -88.0,
                -19.0, 13.5, 14.1, -11.0, -15.0, 29.0, -23.4, -2.7};
        TemperatureSeriesAnalysis seriesAnalysis = new
                TemperatureSeriesAnalysis(temperatureSeries);
        double[] addTemps = {-2.5, 0.2, 3.4, 11.6, 12.8, -172.4, 199.0,
                235.10, -88.2, 33.4, -11.3, -44.6, 35.1, 68.4, -36.2,
                -111.2, 33.4, -278};

        seriesAnalysis.addTemps(addTemps);
    }
}