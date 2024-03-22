package parking_lot.repository;

import parking_lot.models.Slab;
import parking_lot.models.VehicleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlabRepository {
    private Map<Integer, Slab> map;

    public SlabRepository(Map<Integer, Slab> map) {
        this.map = map;
    }
    public SlabRepository() {
        this.map = new HashMap<>();
    }

    public List <Slab> getSlabsByVehicleType(VehicleType vehicleType){
        List <Slab> slabs=new ArrayList<>();
        for (Map.Entry<Integer, Slab> entry : map.entrySet()) {
            Slab slab=entry.getValue();
            if (slab.getVehicleType().equals(vehicleType)) {
                slabs.add(slab);
            }

        }
        slabs.sort((o1,o2)->o1.getStartHour()-o2.getStartHour());
        return slabs;
    }

}
