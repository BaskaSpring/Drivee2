public class Order {
    private double fromLat;
    private double fromLon;
    private double toLat;
    private double toLon;
    private double cost;

    public Order(double fromLat, double fromLon, double toLat, double toLon, double cost) {
        this.fromLat = fromLat;
        this.fromLon = fromLon;
        this.toLat = toLat;
        this.toLon = toLon;
        this.cost = cost;
    }

    public Order() {
    }

    public double getFromLat() {
        return fromLat;
    }

    public void setFromLat(double fromLat) {
        this.fromLat = fromLat;
    }

    public double getFromLon() {
        return fromLon;
    }

    public void setFromLon(double fromLon) {
        this.fromLon = fromLon;
    }

    public double getToLat() {
        return toLat;
    }

    public void setToLat(double toLat) {
        this.toLat = toLat;
    }

    public double getToLon() {
        return toLon;
    }

    public void setToLon(double toLon) {
        this.toLon = toLon;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Заказ{" +
                "fromLat=" + fromLat +
                ", fromLon=" + fromLon +
                ", toLat=" + toLat +
                ", toLon=" + toLon +
                ", cost=" + cost +
                '}';
    }
}
