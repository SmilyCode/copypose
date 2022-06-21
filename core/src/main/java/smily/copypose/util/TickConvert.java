package smily.copypose.util;

public class TickConvert {
    public static int fromHour(int hour){
        return fromMinute(60)*hour;
    }

    public static int fromMinute(int minute){
        return fromSecond(60)*minute;
    }

    public static int fromSecond(int second){
        return second*20;
    }
}
