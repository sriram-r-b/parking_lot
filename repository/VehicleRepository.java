package parking_lot.repository;

import parking_lot.models.Gate;
import parking_lot.models.Vehicle;
import parking_lot.models.VehicleType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class VehicleRepository {
    private Map<Integer, Vehicle> map;
    public VehicleRepository(){
        this.map=new HashMap<>();
    }
    public Vehicle getVehicleById(int gateId){
        return map.get(gateId);
    }
    private static int indusable=0;

    public Vehicle createIfNotExists(String vehicleNumber, VehicleType vehicleType){
        for (Map.Entry<Integer,Vehicle> entry:map.entrySet()){
                Vehicle vehicle=entry.getValue();
                if (vehicle.getVehicleNumber().equals(vehicleNumber)){
                    return vehicle;
                }
        }
        Vehicle vehicle=new Vehicle();
        vehicle.setVehicleNumber(vehicleNumber);
        vehicle.setVehicleType(vehicleType);
        vehicle.setId(indusable++);
        map.put(vehicle.getId(),vehicle);
        return vehicle;
    }

}

