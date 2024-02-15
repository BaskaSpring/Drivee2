import java.util.*;

public class Main {
    private static final double RADIUS_EARTH = 6371.0;
    private static final double AVERAGE_SPEED = 45.0;
    private static Map<OrderCourier,Double> orderCourierDistanceMap = new HashMap<>();

    private static double calculateDistanceOrder(Order order){
        return calculateDistance(order.getFromLat(),order.getFromLon(),order.getToLat(),order.getToLon());
    }

    private static double calculateDistanceByOrderAndCourier(OrderCourier orderCourier){
        return calculateDistance(orderCourier.getOrder().getFromLat(),orderCourier.getOrder().getFromLon(),orderCourier.getCourier().getCurrentLat(),orderCourier.getCourier().getCurrentLon());
    }

    private static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return RADIUS_EARTH * c;
    }

    public static HashMap<Order,Courier> distributeOrders(List<Order> orders, List<Courier> couriers) {
        HashMap<Order,Courier> response = new HashMap<>();

        for (Order order : orders) {
            for (Courier courier : couriers) {
                OrderCourier orderCourier = new OrderCourier(order, courier);
                orderCourierDistanceMap.put(orderCourier, calculateDistanceByOrderAndCourier(orderCourier));
            }
        }
        while (!orderCourierDistanceMap.isEmpty()) {
            double minTime = Double.MAX_VALUE;
            OrderCourier minOrderCourier = new OrderCourier();
            for (OrderCourier orderCourier:orderCourierDistanceMap.keySet()){
                double dist = orderCourierDistanceMap.get(orderCourier);
                double time = orderCourier.getCourier().getReleaseTime();
                if (minTime>dist/AVERAGE_SPEED+time){
                    minTime = dist/AVERAGE_SPEED+time;
                    minOrderCourier = orderCourier;
                }
            }
            Courier courier = minOrderCourier.getCourier();
            Order order = minOrderCourier.getOrder();
            double orderTime = calculateDistanceOrder(order)/AVERAGE_SPEED;
            courier.setReleaseTime(courier.getReleaseTime()+minTime+orderTime);
            courier.setCurrentLat(order.getToLat());
            courier.setCurrentLon(order.getToLon());
            List<OrderCourier> orderCouriers = new ArrayList<>();
            for (OrderCourier orderCourier:orderCourierDistanceMap.keySet()){
                if (orderCourier.getOrder() == order){
                    orderCouriers.add(orderCourier);
                } else {
                    if (orderCourier.getCourier() == courier) {
                        orderCourierDistanceMap.put(orderCourier, calculateDistanceByOrderAndCourier(orderCourier));
                    }
                }
            }
            for (OrderCourier orderCourier: orderCouriers){
                orderCourierDistanceMap.remove(orderCourier);
            }
            response.put(order,courier);
        }
        return response;
    }


    public static void main(String[] args) {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(52.52, 13.40, 48.85, 2.35, 20.0));
        orders.add(new Order(51.51, 9.93, 53.55, 10.00, 15.0));
        orders.add(new Order(40.71, -74.01, 34.05, -118.25, 25.0));
        orders.add(new Order(53.55, 10.00, 52.52, 13.40, 10.0));
        orders.add(new Order(48.85, 2.35, 51.51, 9.93, 30.0));
        orders.add(new Order(55.75, 37.62, 59.93, 30.33, 18.0));
        orders.add(new Order(41.89, 12.49, 55.75, 37.62, 22.0));
        orders.add(new Order(35.68, 139.76, 37.77, -122.41, 28.0));
        orders.add(new Order(37.77, -122.41, 51.51, -0.13, 35.0));
        orders.add(new Order(48.85, 2.35, 35.68, 139.76, 17.0));

        List<Courier> couriers = new ArrayList<>();
        couriers.add(new Courier(52.52, 13.40));
        couriers.add(new Courier(48.85, 2.35));
        couriers.add(new Courier(40.71, -74.01));

        couriers.forEach(x->System.out.println(x.toString()));

        HashMap<Order,Courier> orderCourierMap = distributeOrders(orders,couriers);

        for (Map.Entry<Order, Courier> entry : orderCourierMap.entrySet()) {
            System.out.println("Заказ: " + entry.getKey().toString() + ", Курьер: " + entry.getValue().getId());
        }
    }
}