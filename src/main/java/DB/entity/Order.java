package DB.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Order {
    Long id;
    Long userId;
    String cargoName;
    int cargoMass;
    Long tariffId;
    Long routeId;
    String deliveryAddress;
    Date dateOfArrival;
    Date pickUpDate;
    Timestamp orderPlacementTime;
    String orderStatus;

    public Order() {
    }

    public Order(Long id, Long userId, String cargoName, int cargoMass, Long tariffId, Long routeId, String deliveryAddress, Date dateOfArrival, Date pickUpDate, Timestamp orderPlacementTime, String orderStatus) {
        this.id = id;
        this.userId = userId;
        this.cargoName = cargoName;
        this.cargoMass = cargoMass;
        this.tariffId = tariffId;
        this.routeId = routeId;
        this.deliveryAddress = deliveryAddress;
        this.dateOfArrival = dateOfArrival;
        this.pickUpDate = pickUpDate;
        this.orderPlacementTime = orderPlacementTime;
        this.orderStatus = orderStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(Date pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public double getCargoMass() {
        return cargoMass;
    }

    public void setCargoMass(int cargoMass) {
        this.cargoMass = cargoMass;
    }

    public Long getTariffId() {
        return tariffId;
    }

    public void setTariffId(Long tariffId) {
        this.tariffId = tariffId;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Date getDateOfArrival() {
        return dateOfArrival;
    }

    public void setDateOfArrival(Date dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }

    public Timestamp getOrderPlacementTime() {
        return orderPlacementTime;
    }

    public void setOrderPlacementTime(Timestamp orderPlacementTime) {
        this.orderPlacementTime = orderPlacementTime;
    }
}
