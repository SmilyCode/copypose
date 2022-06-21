package smily.copyposev1_18_2.core.record;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.pathfinder.Path;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import smily.copypose.animation.AnimatedBukkitSchedule;
import smily.copypose.core.record.IPathRecorder;

import java.util.ArrayList;
import java.util.List;

public class PathRecorderv1_18_2 implements IPathRecorder {
    private Path pathResult;
    private boolean isRecording = false;
    private List<Node> nodes = new ArrayList<>();
    private AnimatedBukkitSchedule animation;
    private Player player;

    // throw RuntimeException if already recording
    @Override
    public void record(Player player, int duration) {
        if(isRecording) throw new RuntimeException("You cannot record twice");
        this.player = player;

        if(duration != 0){
            AnimatedBukkitSchedule.animateWithEnd(()-> {
                Location playerLocation = player.getLocation();
                nodes.add(new Node(playerLocation.getBlockX(), playerLocation.getBlockY(), playerLocation.getBlockZ()));
            }, this::stop, 1, duration, 0);
        } else {
            animation.animate(()-> {
                Location playerLocation = player.getLocation();
                nodes.add(new Node(playerLocation.getBlockX(), playerLocation.getBlockY(), playerLocation.getBlockZ()));
            }, 1,  0);
        }

        isRecording = true;
    }

    @Override
    public void stop() {
        if(!isRecording) return;

        Location location = player.getLocation();

        pathResult = new Path(nodes, new BlockPos(location.getBlockX(), location.getBlockY(), location.getBlockZ()), true);
        animation.stop();
        isRecording = false;
    }

    @Override
    public boolean isRecording() {
        return isRecording;
    }

    public Path getPathResult() {
        return pathResult;
    }
}
