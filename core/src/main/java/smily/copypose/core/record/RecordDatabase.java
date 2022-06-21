package smily.copypose.core.record;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
* Keep in mind this database methods doesn't throw anything when you either
* put null value or return null value
*
* So to be safe make sure to put containName method in an if statement
* before proceeding certain method for error handling
*/

public class RecordDatabase {
    private final static Map<String, RecordPose> database = new HashMap<>();

    public static void storeRecord(String name, RecordPose records) throws RuntimeException{
        if (!containRecord(name)) {
            database.put(name, records);
        }
    }

    // It's best to put value in a variable for more use
    public static RecordPose getRecord(String name){
        return database.get(name);
    }

    public static boolean containRecord(String name){
        return database.containsKey(name);
    }

    public static void deleteRecord(String name){
        database.remove(name);
    }

    public static Map<String, RecordPose> getDatabase(){
        return database;
    }

    public static Set<String> getNames(){
        return database.keySet();
    }

    public static void replaceValue(String name, RecordPose recordPose){
        if(!database.containsKey(name)) throw new RuntimeException("No record of that name can be found.");

        database.remove(name);
        database.put(name, recordPose);
    }
}