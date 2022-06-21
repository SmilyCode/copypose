package smily.copypose.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeFormat {
    private final Pattern pattern = Pattern.compile("(\\d+)([hmts])");
    private String timeFormat;
    private Unit unit;
    private int number = 0;

    public TimeFormat(String timeFormat){
        this.timeFormat = timeFormat;
    }

    public enum Unit{
        TICK,
        SECOND,
        MINUTE,
        HOUR;

        @Override
        public String toString(){
            switch (this){
                case HOUR:
                    return "hour";
                case MINUTE:
                    return "minute";
                case SECOND:
                    return "second";
                case TICK:
                    return "TICK";
                default:
                    return null;
            }
        }

        public Unit getUnit(){
            return this;
        }
    }

    public void define(){
        Matcher matcher = pattern.matcher(timeFormat);

        if(isValid()){
            if(matcher.find()){
                this.number = Integer.parseInt(matcher.group(1));
                String time = matcher.group(2);

                switch (time){
                    case "h":
                        this.unit = Unit.HOUR;
                        break;
                    case "m":
                        this.unit = Unit.MINUTE;
                        break;
                    case "s":
                        this.unit = Unit.SECOND;
                        break;
                    case "t":
                        this.unit = Unit.TICK;
                }
            }
        }
    }

    public Integer convertToTick(){
        switch (getUnit()){
            case HOUR:
                return TickConvert.fromHour(number);
            case MINUTE:
                return TickConvert.fromMinute(number);
            case SECOND:
                return TickConvert.fromSecond(number);
            case TICK:
                return number;
            default:
                return null;
        }
    }

    public boolean isValid(){
        Matcher matcher = pattern.matcher(timeFormat);
        return matcher.matches();
    }


    public Pattern getPattern() {
        return pattern;
    }

    public String getTimeFormat(){
        return this.timeFormat;
    }

    public Unit getUnit(){
        return this.unit.getUnit();
    }

    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    public int getNumber() {
        return number;
    }
}
