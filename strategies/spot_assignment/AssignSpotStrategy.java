package parking_lot.strategies.spot_assignment;

import parking_lot.models.ParkingLot;
import parking_lot.models.Spot;
import parking_lot.models.VehicleType;
import parking_lot.exceptions.NoSpotAvailableException;

public interface AssignSpotStrategy {
    public Spot assignSpot (VehicleType vehicleType, ParkingLot parkingLot) throws NoSpotAvailableException ;

}
