package parking_lot.strategies.pricing;

import parking_lot.models.VehicleType;

import java.util.Date;

public interface CalculateFeesStrategy {
    public double calculate(Date entryTime, Date exitTime, VehicleType vehicleType);
}
