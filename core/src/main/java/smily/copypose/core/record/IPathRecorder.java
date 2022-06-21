package smily.copypose.core.record;

import org.bukkit.entity.Player;

public interface IPathRecorder {
    //For recording a player position
    void record(Player player, int duration);

    void stop();

    boolean isRecording();
}
