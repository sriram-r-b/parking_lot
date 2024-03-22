package parking_lot.models;

public class Spot extends BaseModel{
    private  String name;
    private SpotStatus status;
    private VehicleType vehicleType;

    public String getName() {
        return name;
    }

    public Spot(String name, SpotStatus status, VehicleType vehicleType) {

        this.name = name;
        this.status = status;
        this.vehicleType = vehicleType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SpotStatus getStatus() {
        return status;
    }

    public void setStatus(SpotStatus status) {
        this.status = status;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
