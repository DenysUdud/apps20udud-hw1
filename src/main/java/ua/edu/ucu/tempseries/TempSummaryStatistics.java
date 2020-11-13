package ua.edu.ucu.tempseries;

public class TempSummaryStatistics {
    private final double avgTempFin;
    private final double devTempFin;
    private final double minTempFin;
    private final double maxTempFin;

    public TempSummaryStatistics(double avgTemp,
                                 double devTemp,
                                 double minTemp,
                                 double maxTemp) {
        avgTempFin = avgTemp;
        devTempFin = devTemp;
        minTempFin = minTemp;
        maxTempFin = maxTemp;
    }

    public double getDevTemp() {
        return devTempFin;
    }

    public double getMinTemp() {
        return minTempFin;
    }

    public double getMaxTempFin() {
        return maxTempFin;
    }

    public double getAverageTemp() {
        return avgTempFin;
    }
}
