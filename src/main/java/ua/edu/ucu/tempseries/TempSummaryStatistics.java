package ua.edu.ucu.tempseries;

public class TempSummaryStatistics {
    final private double avgTemp;
    final private double devTemp;
    final private double minTemp;
    final private double maxTemp;

    public TempSummaryStatistics(double avgTemp, double devTemp, double minTemp, double maxTemp) {
        this.avgTemp = avgTemp;
        this.devTemp = devTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }
}
