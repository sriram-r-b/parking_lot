package parking_lot.strategies.pricing;

import parking_lot.models.VehicleType;
import parking_lot.utils.DateTimeUtils;

import java.util.Date;

public class WeekdayStrategy implements CalculateFeesStrategy{
    @Override
    public double calculate(Date entryTime, Date exitTime, VehicleType vehicleType){
        double hours=DateTimeUtils.calcHours(entryTime,exitTime);
        return hours*10;
    }
}
