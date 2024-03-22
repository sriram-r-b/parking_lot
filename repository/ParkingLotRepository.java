package parking_lot.repository;

import parking_lot.models.Gate;
import parking_lot.models.ParkingLot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLotRepository {
    private Map<Integer,ParkingLot> map;

    public ParkingLotRepository(Map<Integer, ParkingLot> map) {
        this.map = map;
    }
    public ParkingLotRepository() {
        this.map = new HashMap<>();
    }

    public ParkingLot getParkingLotByGateId(int gateid){
        for (Map.Entry<Integer, ParkingLot> entry : map.entrySet()) {
            ParkingLot parkingLot=entry.getValue();
            for (Gate gate : parkingLot.getGates()) {
                if(gate.getId()==gateid){
                    return parkingLot;
                }

            }
        }
        return null;

    }
}
