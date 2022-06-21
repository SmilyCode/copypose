package smily.copypose.core.record;

import org.bukkit.entity.Player;
import smily.copypose.data.PlayerMove;

import java.util.HashMap;
import java.util.Map;

public class RecordData {
    private final Map<Integer, PlayerMove> timestamp;
    private int durationData;
    private Player player;

    public RecordData(Player player){
        this(0, player);
    }

    public RecordData(int durationData, Player player){
        this.durationData = durationData;
        this.player = player;
//        player.isSl
        timestamp = new HashMap<>();
    }

    public void addTimestamp(int tick, PlayerMove move){
        timestamp.put(tick, move);
    }

    public Map<Integer, PlayerMove> getTimestamp() {
        return timestamp;
    }

    public void clearTimestamp(){
        timestamp.clear();
    }

    public int getDurationData() {
        return durationData;
    }

    public void setDurationData(int durationData) {
        this.durationData = durationData;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


}
