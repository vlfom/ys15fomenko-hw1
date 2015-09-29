package ua.yandex.shad.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {

    private double[] temperatureSeries;
    private int capacity = DEFAULT_CAPACITY;
    private int size;

    static private final int DEFAULT_CAPACITY = 10;

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
        double best = temperatureSeries[0];
        for (int i = 1; i < size; ++i) {
            if (Math.abs(best) > Math.abs(temperatureSeries[i]) ||
                    (Math.abs(best) == Math.abs(temperatureSeries[i]) && best < 0)) {
                best = temperatureSeries[i];
            }
        }
        return best;
    }

    public double findTempClosestToValue(double tempValue) throws IllegalArgumentException {
        assertNotEmpty();
        double best = temperatureSeries[0];
        for (int i = 1; i < size; ++i) {
            if (Math.abs(best - tempValue) > Math.abs(temperatureSeries[i] - tempValue) ||
                    (Math.abs(best - tempValue) == Math.abs(temperatureSeries[i] - tempValue) && best < 0)) {
                best = temperatureSeries[i];
            }
        }
        return best;
    }

    public double[] findTempsLessThen(double tempValue) throws IllegalArgumentException {
        assertNotEmpty();
        int count = 0;
        for (int i = 0; i < size; ++i) {
            if (temperatureSeries[i] < tempValue) {
                ++count;
            }
        }
        double[] result = new double[count];
        count = 0;
        for (int i = 0; i < size; ++i) {
            if (temperatureSeries[i] < tempValue) {
                result[count++] = temperatureSeries[i];
            }
        }
        return result;
    }

    public double[] findTempsGreaterThen(double tempValue) throws IllegalArgumentException {
        assertNotEmpty();
        int count = 0;
        for (int i = 0; i < size; ++i) {
            if (temperatureSeries[i] > tempValue) {
                ++count;
            }
        }
        double[] result = new double[count];
        count = 0;
        for (int i = 0; i < size; ++i) {
            if (temperatureSeries[i] > tempValue) {
                result[count++] = temperatureSeries[i];
            }
        }
        return result;
    }

    public TempSummaryStatistics summaryStatistics() throws IllegalArgumentException {
        assertNotEmpty();
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) throws InputMismatchException {
        for (double temp : temps) {
            if (temp < -273) {
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