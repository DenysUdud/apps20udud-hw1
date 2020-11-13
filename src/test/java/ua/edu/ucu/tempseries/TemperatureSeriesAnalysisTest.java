package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void testConstructorWithoutArguments() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();

        double[] exp = new double[2];
        assertEquals(2, seriesAnalysis.getTempSeries().length);
        assertEquals(0, seriesAnalysis.getTempsSize());
        assert(Arrays.equals(exp, seriesAnalysis.getTempSeries()));
    }

    @Test
    public void testConstructor() {
        // define with 1 element
        double[] temperatureSeries = {0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        assertEquals(2, seriesAnalysis.getTempSeries().length);
        assertEquals(1, seriesAnalysis.getTempsSize());

        // define series with 2 elements
        temperatureSeries = new double[]{1,2};
        seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        assertEquals(4, seriesAnalysis.getTempSeries().length);
        assertEquals(2, seriesAnalysis.getTempsSize());
    }

    @Test(expected = InputMismatchException.class)
    public void testConstructorWithImpoliteTemp() {
        // define with -500 degrees
        double[] temperatureSeries = new double[]{1, 2, 5, 6, -5000};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
    }

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();
        
        assertEquals(expResult, actualResult, 0.00001);        
    }

    @Test
    public void testDeviation() {
        double[] temperatureSeries = {1.0, 1.0, 1.0, 1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.0;
        double actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.00001);

        double[] temperatureSeries1 = {11.27, 11.36, 11.09, 11.16, 11.47};
        seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries1);
        expResult = 0.13608;
        actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMin() {
        double[] temperatureSeries = {1.0, 1.0, 1.0, 1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        assertEquals(1.0, seriesAnalysis.min(), 0.00001);

        double[] temperatureSeries1 = {9.0, -200.0, 10.0, 3.0};
        seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries1);

        assertEquals(-200.0, seriesAnalysis.min(), 0.00001);
    }

    @Test
    public void testMax() {
        double[] temperatureSeries = {1.0, 1.0, 1.0, 1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        assertEquals(1.0, seriesAnalysis.max(), 0.00001);

        double[] temperatureSeries1 = {9.0, -200.0, 10.0, 3.0};
        seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries1);

        assertEquals(10.0, seriesAnalysis.max(), 0.00001);
    }

    @Test
    public void testTempClosestToZero() {
        // normal situation
        double[] temperatureSeries = {-3, -4, -5, -1, 1, 2, 3};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1;

        double actualResult = seriesAnalysis.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);

        // only negative
        double[] temperatureSeries1 = {-3, -4, -5, -1};
        TemperatureSeriesAnalysis seriesAnalysis1 = new TemperatureSeriesAnalysis(temperatureSeries1);
        expResult = -1;

        double actualResult1 = seriesAnalysis1.findTempClosestToZero();

        assertEquals(expResult, actualResult1, 0.00001);

        // only zero
        double[] temperatureSeries2 = {0};
        TemperatureSeriesAnalysis seriesAnalysis2 = new TemperatureSeriesAnalysis(temperatureSeries2);
        expResult = 0;

        double actualResult2 = seriesAnalysis2.findTempClosestToZero();

        assertEquals(expResult, actualResult2, 0.00001);
    }

    @Test
    public void testTempClosestToValue() {
        // normal situation
        double[] temperatureSeries = {-4, -5, -1, 1, 2, 3};
        double value = -3.0;

        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -4;

        double actualResult = seriesAnalysis.findTempClosestToValue(value);

        assertEquals(expResult, actualResult, 0.00001);

        // situation when there is two of them
        value = 0;
        double expResult2 = 1;
        TemperatureSeriesAnalysis seriesAnalysis2 = new TemperatureSeriesAnalysis(temperatureSeries);

        double actualResult2 = seriesAnalysis2.findTempClosestToValue(value);
        assertEquals(expResult2, actualResult2, 0.00001);
    }

    @Test
    public void testFindTempsLessThen() {
        double[] temperatureSeries = {1.0, 2.0, 3.0, 4.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        double[] smallerTemps = {1.0, 2.0};
        double[] resultTemps = seriesAnalysis.findTempsLessThen(3.0);

        assertArrayEquals(smallerTemps, resultTemps);

        double[] temperatureSeries1 = {1.0, 2.0, 3.0, 4.0, 5.0};
        seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries1);

        double[] smallerTemps1 = {};
        resultTemps = seriesAnalysis.findTempsLessThen(1.0);

        assertArrayEquals(smallerTemps1, resultTemps);
    }

    @Test
    public void testFindTempsGreaterThen() {
        double[] temperatureSeries = {1.0, 2.0, 3.0, 4.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        double[] biggerTemps = {3.0, 4.0, 5.0};
        double[] resultTemps = seriesAnalysis.findTempsGreaterThen(2.0);

        assertArrayEquals(biggerTemps, resultTemps);

        double[] temperatureSeries1 = {1.0, 2.0, 3.0, 4.0, 5.0};
        seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries1);

        double[] biggerTemps1 = {};
        resultTemps = seriesAnalysis.findTempsGreaterThen(5.0);

        assertArrayEquals(biggerTemps1, resultTemps);

        double[] temperatureSeries2 = {1.0, 2.0, 3.0, 4.0, 5.0};
        seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries2);

        double[] smallerTemps2 = {};
        resultTemps = seriesAnalysis.findTempsGreaterThen(5.5);

        assertEquals(resultTemps.length, 0, 0.000001);
    }

    private void assertArrayEquals(double[] temps, double[] resultTemps) {
        for (int i = 0; i < resultTemps.length; i++) {
            assertEquals(temps[i], resultTemps[i], 0.00001);
        }
    }

    @Test
    public void testAddTemps() {
        double[] temperatureSeries = {1.0, 2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        assertEquals(2, seriesAnalysis.getTempsSize());
        assertEquals(4, seriesAnalysis.getTempSeries().length);

        assertEquals(3, seriesAnalysis.addTemps(5.0));

        assertEquals(3, seriesAnalysis.getTempsSize());
        assertEquals(4, seriesAnalysis.getTempSeries().length);

        assertEquals(9, seriesAnalysis.addTemps(6.0, 7.0, 8.0, 9.0, 10.0, 11.0));

        assertEquals(9, seriesAnalysis.getTempsSize());
        assertEquals(16, seriesAnalysis.getTempSeries().length);
    }

    @Test(expected = InputMismatchException.class)
    public void testAddTempsError() {
        double[] temperatureSeries = {1.0, 2.0, 3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.addTemps(-275.0);
    }

    @Test
    public void testSummaryStatistics() {
        double[] temperatureSeries = {1, 3, 6, 3, 4, 7};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expAverage = seriesAnalysis.average();
        double expDev = seriesAnalysis.deviation();
        double expMin = seriesAnalysis.min();
        double expMax = seriesAnalysis.max();

        TempSummaryStatistics resultStatistics = seriesAnalysis.summaryStatistics();

        assertEquals(expAverage, resultStatistics.getAverageTemp(), 0.00001);
        assertEquals(expDev, resultStatistics.getDevTemp(), 0.00001);
        assertEquals(expMin, resultStatistics.getMinTemp(), 0.00001);
        assertEquals(expMax, resultStatistics.getMaxTempFin(), 0.00001);
    }
}
