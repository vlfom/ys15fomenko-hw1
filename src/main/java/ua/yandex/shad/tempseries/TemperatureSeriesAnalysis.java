package ua.yandex.shad.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {

    private static final int DEFAULT_CAPACITY = 10;
    private static final double BOTTOM_BORDER = -273;
    private static final double COMPARE_BORDER = 0.00001;
    private double[] temperatureSeries;
    private int capacity = DEFAULT_CAPACITY;
    private int size;

    public TemperatureSeriesAnalysis() {
        size = 0;
        temperatureSeries = new double[DEFAULT_CAPACITY];
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        while (capacity < temperatureSeries.length) {
            capacity *= 2;
        }
        size = temperatureSeries.length;
        this.temperatureSeries = Arrays.copyOf(temperatureSeries, capacity);
    }

    public int getSize() {
        return size;
    }

    private void assertNotEmpty() throws IllegalArgumentException {
        if (size == 0) {
            throw new IllegalArgumentException();
        }
    }

    public double average() throws IllegalArgumentException {
        assertNotEmpty();
        double sum = 0;
        for (int i = 0; i < size; ++i) {
            sum += temperatureSeries[i];
        }
        return sum / size;
    }

    public double deviation() throws IllegalArgumentException {
        assertNotEmpty();
        double avg = average();
        double sum = 0;
        for (int i = 0; i < size; ++i) {
            sum += (temperatureSeries[i] - avg) * (temperatureSeries[i] - avg);
        }
        sum = Math.sqrt(sum / size);
        return sum;
    }

    public double min() throws IllegalArgumentException {
        assertNotEmpty();
        double min = temperatureSeries[0];
        for (int i = 1; i < size; ++i) {
            min = Math.min(min, temperatureSeries[i]);
        }
        return min;
    }

    public double max() throws IllegalArgumentException {
        assertNotEmpty();
        double max = temperatureSeries[0];
        for (int i = 1; i < size; ++i) {
            max = Math.max(max, temperatureSeries[i]);
        }
        return max;
    }

    public double findTempClosestToZero() throws IllegalArgumentException {
        assertNotEmpty();
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) throws
            IllegalArgumentException {
        assertNotEmpty();
        double best = temperatureSeries[0];
        for (int i = 1; i < size; ++i) {
            if (Math.abs(best - tempValue) > Math.abs(temperatureSeries[i]
                    - tempValue) || Math.abs(Math.abs(best - tempValue) - Math
                    .abs(temperatureSeries[i] - tempValue)) < COMPARE_BORDER
                    && best < 0) {
                best = temperatureSeries[i];
            }
        }
        return best;
    }

    private double[] findTemps(double tempValue, int compareToValue) throws
            IllegalArgumentException {
        assertNotEmpty();
        int count = 0;
        double[] result = new double[]{};
        for (int j = 0; j < 2; ++j) {
            count = 0;
            for (int i = 0; i < size; ++i) {
                if (Double.compare(temperatureSeries[i], tempValue)
                        == compareToValue) {
                    if (j == 0) {
                        ++count;
                    } else {
                        result[count++] = temperatureSeries[i];
                    }
                }
            }
            if (j == 0) {
                result = new double[count];
            }
        }
        return result;
    }

    public double[] findTempsLessThen(double tempValue) throws
            IllegalArgumentException {
        return findTemps(tempValue, -1);
    }

    public double[] findTempsGreaterThen(double tempValue) throws
            IllegalArgumentException {
        return findTemps(tempValue, 1);
    }

    public TempSummaryStatistics summaryStatistics() throws
            IllegalArgumentException {
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) throws InputMismatchException {
        for (double temp : temps) {
            if (temp < BOTTOM_BORDER) {
                throw new InputMismatchException();
            }
        }
        while (capacity < size + temps.length) {
            capacity *= 2;
        }
        temperatureSeries = Arrays.copyOf(temperatureSeries, capacity);
        for (int i = size; i < size + temps.length; ++i) {
            temperatureSeries[i] = temps[i - size];
        }
        size += temps.length;
        return size;
    }
}