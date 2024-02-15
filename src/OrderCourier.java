public class OrderCourier {

    private Order order;
    private Courier courier;

    public OrderCourier(Order order, Courier courier) {
        this.order = order;
        this.courier = courier;
    }

    public OrderCourier() {
    }


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }
}
