package parking_lot.models;

public enum VehicleType {
    CAR,
    BIKE,
    EV_CAR,
    TRUCK;
    public static VehicleType getTypeFromString(String type){
        for (VehicleType value : VehicleType.values()) {
            if (type.equalsIgnoreCase(value.toString())){
                return value;
            }

        }
        throw new IllegalArgumentException("vehicle type not supported");
    }


}
