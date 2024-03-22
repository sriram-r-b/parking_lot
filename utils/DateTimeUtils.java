package parking_lot.utils;

import java.util.Date;

public class DateTimeUtils {
    public static int calcHours(Date start,Date end){
        long time=end.getTime()- start.getTime();
        long timeInSeconds=time/1000;
        double hours=Math.ceil((double)timeInSeconds/3600);
        return (int)hours;
    }
}
