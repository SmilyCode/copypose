package smily.copypose.core.pastepose;

import org.bukkit.entity.Player;
import smily.copypose.core.record.RecordPose;
import smily.copypose.util.Clonable;

public interface IPastePose extends Clonable<IPastePose> {
    void pasteToPlayer(Player target, RecordPose recordPose);
}
