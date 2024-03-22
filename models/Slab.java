package parking_lot.models;

public class Slab extends BaseModel{
    private VehicleType vehicleType;
    private int startHour;
    private int endHour;

    private Double pricePerHour;

    public Slab(VehicleType vehicleType, int startHour, int endHour, int pricePerHour) {
        this.vehicleType = vehicleType;
        this.startHour = startHour;
        this.endHour = endHour;
        this.pricePerHour = (double)pricePerHour;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public Double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }
}
