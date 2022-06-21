package smily.copypose.core.record;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class RecordPoseSelector {
    private final static Map<Player, RecordPose> databaseSelector = new HashMap<>();

    public static RecordPose getRecordPose(Player player){
        // to update, it might have the same name stored here but different value that's why checks in database for new value
        return RecordDatabase.getRecord(databaseSelector.get(player).getName());

    }

    public static void storeSelected(Player player, RecordPose recordPose){
        databaseSelector.put(player, recordPose);
    }

    public static void replaceSelected(Player player, RecordPose recordPose){
        if(databaseSelector.containsKey(player)){
           databaseSelector.remove(player);
           databaseSelector.put(player,recordPose);
        }
    }
}
