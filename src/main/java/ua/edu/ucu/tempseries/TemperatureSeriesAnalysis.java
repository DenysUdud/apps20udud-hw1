package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    /**
     * Class contains the methods for working with
     * range of temperatures.
     */
    private double[] tempSeries;
    private int tempsSize;

    private static final double MINIMUM_TEMP_VALUE = -273.0;
    private static final int DEFAULT_TEMPS_CAPACITY = 2;

    public TemperatureSeriesAnalysis() {
        tempSeries = new double[DEFAULT_TEMPS_CAPACITY];
        tempsSize = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        checkTempsValues(temperatureSeries);

        tempSeries = new double[temperatureSeries.length * 2];
        tempsSize = temperatureSeries.length;

        //copying array to new one
        System.arraycopy(
                temperatureSeries,
                0,
                tempSeries,
                0,
                temperatureSeries.length
        );

    }

    public double average() {
        /*
          A method used to calculate average temperature.
         */
        checkIsEmpty();
        double sum = 0;
        for (int i = 0; i < tempsSize; i++) {
            sum += tempSeries[i];
        }
        return sum / tempsSize;
    }

    public double deviation() {
        /*
        A method used to search standard deviation.
        More https://studfiles.net/preview/4267350/page:18/
         */
        checkIsEmpty();
        double averageTemp = average(), quadraticSum = 0;

        for (int i = 0; i < tempsSize; i++) {
            double diff = tempSeries[i] - averageTemp;
            quadraticSum += diff*diff;
        }
        return Math.sqrt(quadraticSum / tempSeries.length);
    }

    public double min() {
        /*
        A method used to return min value in the range.
         */
        checkIsEmpty();

        double minimum = tempSeries[0];
        for (int i = 1; i < tempsSize; i++) {
            minimum = Math.min(minimum, tempSeries[i]);
        }
        return minimum;
    }

    public double max() {
        /*
        A method used to return max value in the range.
         */
        checkIsEmpty();

        double maximum = tempSeries[0];

        for (int i = 1; i < tempsSize; i++) {
            maximum = Math.max(maximum, tempSeries[i]);
        }
        return maximum;
    }

    public double findTempClosestToZero() {
        /*
        A method used to return the closest value to 0 in the range.
        If there are two values, equally close to zero
        (ex: 0.2 and -0.2) returns 0.2.
         */
        checkIsEmpty();

        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        /*
        A method used to return the closest value to tempValue
        in the range.
        If there are two values, equally close to tempValue
        (ex: 0.2 and -0.2 to 0) returns 0.2.
         */
        checkIsEmpty();

        double closest = tempSeries[0];
        double minDiff = Math.abs(closest - tempValue);

        for (int i = 1; i < tempsSize; i++) {
            double newDiff = Math.abs(tempSeries[i] - tempValue);
            if (newDiff < minDiff) {
                closest = tempSeries[i];
                minDiff = newDiff;
            }
            else if (minDiff == newDiff) {
                closest = Math.max(tempSeries[i], closest);
            }
        }
        return closest;
    }

    public double[] findTempsLessThen(double tempValue) {
        /*
        A method used to return Array with temperatures with smaller
        value than tempValue.
         */
        checkIsEmpty();

        int size = 0;

        // count size of array with temperature less than...
        for (int i = 0; i < tempsSize; i++) {
            if (tempSeries[i] < tempValue) {
                size++;
            }
        }

        double[] smallerTemperatures = new double[size];
        int i = 0;
        for (int j = 0; j < tempsSize; j++) {
            if (tempSeries[j] < tempValue) {
                smallerTemperatures[i] = tempSeries[j];
                i++;
            }
        }
        return smallerTemperatures;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        /*
        A method used to return Array with temperatures with greater
        value than tempValue.
         */
        checkIsEmpty();

        int size = 0;

        // count size
        for (int i = 0; i < tempsSize; i++) {
            if (tempSeries[i] > tempValue) {
                size++;
            }
        }

        double[] greaterTemperatures = new double[size];
        int i = 0;
        for (int j = 0; j < tempsSize; j++) {
            if (tempSeries[j] > tempValue) {
                greaterTemperatures[i] = tempSeries[j];
                i++;
            }
        }

        return greaterTemperatures;
    }

    public TempSummaryStatistics summaryStatistics() {
        /*
        A method used to return immutable TempSummaryStatistics
        object with following information:
        average temp;
        deviation of temp;
        min temp;
        max temp;
         */
        checkIsEmpty();

        return new TempSummaryStatistics(
                average(),
                deviation(),
                min(),
                max()
        );
    }

    public int addTemps(double... temps) {
        /*
        A method used to add new values to the temperatureSeries.
        If there is not any place in array - make it two times
        bigger.
         */
        checkTempsValues(temps);

        int newSize = tempSeries.length;
        while (newSize < (tempsSize + temps.length)) {
            newSize *= 2;
        }

        double[] newTemperatures = new double[newSize];

        // reformat old array
        System.arraycopy(
                tempSeries,0,
                newTemperatures,0,
                tempSeries.length
        );

        tempSeries = newTemperatures;
        //copying new array to old

        System.arraycopy(
                temps, 0,
                tempSeries,
                tempsSize,
                temps.length
        );

        tempsSize += temps.length;
        return tempsSize;
    }

    private void checkIsEmpty() {
        /*
        A method used to check whether temperature series is empty.
         */
        if (tempsSize == 0) {
            throw new IllegalArgumentException();
        }
    }

    private void checkTempsValues(double[] temps) {
        /*
        A method used to check whether whether all values in
        temps are greater than -273.0.
         */
        for (double temp: temps) {
            if (temp < MINIMUM_TEMP_VALUE) {
                throw new InputMismatchException();
            }
        }
    }
}
