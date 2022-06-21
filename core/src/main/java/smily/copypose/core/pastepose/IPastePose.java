package smily.copypose.core.pastepose;

import org.bukkit.entity.Player;
import smily.copypose.core.record.RecordPose;

public interface IPastePose {
    void pasteToPlayer(Player target, RecordPose recordPose);
}
