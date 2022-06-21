package smily.copyposev1_18_2.core;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.phys.Vec3;
import org.bukkit.entity.Player;
import smily.copypose.animation.AnimatedBukkitSchedule;
import smily.copypose.core.pastepose.IPastePose;
import smily.copypose.core.record.RecordPose;
import smily.copypose.data.PlayerMove;
import smily.copyposev1_18_2.util.NMSPlayer;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class PastePoseV1_18_2  implements IPastePose {

    @Override
    public void pasteToPlayer(Player target, RecordPose recordPose){
        Map<Integer, PlayerMove> moveData = recordPose.getRecordData().getTimestamp();
        AtomicInteger indexTick = new AtomicInteger(1);

        ServerPlayer player = NMSPlayer.getServerPlayer(target);



        AnimatedBukkitSchedule.animate(()-> {
            PlayerMove playerMove = moveData.get(indexTick.get());

            player.move(MoverType.SELF, new Vec3(
                    playerMove.getLocationX(),
                    playerMove.getLocationY(),
                    playerMove.getLocationZ()
            ));

//            serverPlayer.move(MoverType.SELF, new Vec3(
//                    playerMove.getLocationX(),
//                    playerMove.getLocationY(),
//                    playerMove.getLocationZ())
//            );

//            Location location = new Location(
//                    target.getWorld(),
//                    playerMove.getLocationX(),
//                    playerMove.getLocationY(),
//                    playerMove.getLocationZ());
//            location.setYaw(playerMove.getYaw());
//            location.setPitch(playerMove.getPitch());
//
//            target.teleport(location);

            indexTick.getAndIncrement();

        }, 1, recordPose.getRecordData().getDurationData(), 0);
    }
}
