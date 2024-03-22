package parking_lot.factories;

import parking_lot.repository.SlabRepository;
import parking_lot.strategies.pricing.CalculateFeesStrategy;
import parking_lot.strategies.pricing.WeekdayStrategy;
import parking_lot.strategies.pricing.WeekendStrategy;

import java.util.Calendar;
import java.util.Date;

public class CalculateFeesStrategyFactory {
    private SlabRepository slabRepository;

    public CalculateFeesStrategyFactory(SlabRepository slabRepository) {
        this.slabRepository = slabRepository;
    }

    public CalculateFeesStrategy getCalculateFeesStrategy(Date exitTime)
    {
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(exitTime);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        boolean isWeekend = ((day == Calendar.SATURDAY) || (day == Calendar.SUNDAY));
        CalculateFeesStrategy calculateFeesStrategy;
        if (isWeekend) {
            calculateFeesStrategy = new WeekendStrategy(slabRepository);
        } else {
            calculateFeesStrategy = new WeekdayStrategy();
        }
        return calculateFeesStrategy;

    }
}