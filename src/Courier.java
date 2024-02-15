import java.util.UUID;

public class Courier {

    private double currentLat;
    private double currentLon;
    private double releaseTime;

    private String id = UUID.randomUUID().toString();

    public String getId() {
        return id;
    }

    public Courier(double currentLat, double currentLon) {
        this.currentLat = currentLat;
        this.currentLon = currentLon;
        this.releaseTime = 0;
    }

    public Courier(double currentLat, double currentLon, double releaseTime) {
        this.currentLat = currentLat;
        this.currentLon = currentLon;
        this.releaseTime = releaseTime;
    }

    public double getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(double releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Courier() {
    }

    public double getCurrentLat() {
        return currentLat;
    }

    public void setCurrentLat(double currentLat) {
        this.currentLat = currentLat;
    }

    public double getCurrentLon() {
        return currentLon;
    }

    public void setCurrentLon(double currentLon) {
        this.currentLon = currentLon;
    }

    @Override
    public String toString() {
        return "Курьер{" +
                "currentLat=" + currentLat +
                ", currentLon=" + currentLon +
                ", id='" + id + '\'' +
                '}';
    }
}
