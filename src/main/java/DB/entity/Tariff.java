package DB.entity;

public class Tariff {
    Long id;
    String name;
    double cargoHoldHeight;
    double cargoHoldLength;
    double cargoHoldWidth;
    int cargoMassCap;
    String deliveryRange;
    int pricePerKm;

    public String getDeliveryRange() {
        return deliveryRange;
    }

    public Tariff(Long id, String name, double cargoHoldHeight, double cargoHoldLength, double cargoHoldWidth, int cargoMassCap, String deliveryRange, int pricePerKm) {
        this.id = id;
        this.name = name;
        this.cargoHoldHeight = cargoHoldHeight;
        this.cargoHoldLength = cargoHoldLength;
        this.cargoHoldWidth = cargoHoldWidth;
        this.cargoMassCap = cargoMassCap;
        this.deliveryRange = deliveryRange;
        this.pricePerKm = pricePerKm;
    }

    public double getCargoHoldHeight() {
        return cargoHoldHeight;
    }

    public void setCargoHoldHeight(double cargoHoldHeight) {
        this.cargoHoldHeight = cargoHoldHeight;
    }

    public double getCargoHoldLength() {
        return cargoHoldLength;
    }

    public void setCargoHoldLength(double cargoHoldLength) {
        this.cargoHoldLength = cargoHoldLength;
    }

    public double getCargoHoldWidth() {
        return cargoHoldWidth;
    }

    public void setCargoHoldWidth(double cargoHoldWidth) {
        this.cargoHoldWidth = cargoHoldWidth;
    }

    public void setDeliveryRange(String deliveryRange) {
        this.deliveryRange = deliveryRange;
    }

    public Tariff() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getCargoMassCap() {
        return cargoMassCap;
    }

    public void setCargoMassCap(int cargoMassCap) {
        this.cargoMassCap = cargoMassCap;
    }

    public int getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(int pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cargoHoldHeight=" + cargoHoldHeight +
                ", cargoHoldLength=" + cargoHoldLength +
                ", cargoHoldWidth=" + cargoHoldWidth +
                ", cargoMassCap=" + cargoMassCap +
                ", deliveryRange='" + deliveryRange + '\'' +
                ", pricePerKm=" + pricePerKm +
                '}';
    }
}
