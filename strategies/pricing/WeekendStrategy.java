package parking_lot.strategies.pricing;

import parking_lot.models.Slab;
import parking_lot.models.VehicleType;
import parking_lot.repository.SlabRepository;
import parking_lot.utils.DateTimeUtils;

import java.util.Date;
import java.util.List;

public class WeekendStrategy implements CalculateFeesStrategy{
    private SlabRepository slabRepository;

    public WeekendStrategy(SlabRepository slabRepository) {
        this.slabRepository = slabRepository;
    }

    @Override
    public double calculate(Date entryTime, Date exitTime, VehicleType vehicleType){
        List<Slab> slabs= this.slabRepository.getSlabsByVehicleType(vehicleType);
        int hours= DateTimeUtils.calcHours(entryTime,exitTime);
        double totalAmount=0;
        for (Slab slab : slabs) {
            if(hours>= slab.getStartHour()){
                if (slab.getEndHour()==-1){
                    totalAmount+= slab.getPricePerHour()*(hours- slab.getStartHour());
                    break;
                }
                if (hours>= slab.getEndHour()){
                    totalAmount+= (slab.getEndHour()-slab.getStartHour())*(slab.getPricePerHour());
                }
                else {
                    totalAmount+= (hours-slab.getStartHour())*(slab.getPricePerHour());
                }
            }
            else {
                break;
            }
        }
        return hours*10;
    }
}
