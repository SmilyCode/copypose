package smily.copypose.core.record;

import org.bukkit.entity.Player;
import smily.copypose.util.Clonable;

public interface IPathRecorder extends Clonable<IPathRecorder> {

    //For recording a player position
    void record(Player player, int duration);

    void stop();

    boolean isRecording();


}
