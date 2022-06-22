package smily.copypose.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import smily.copypose.animation.AnimatedBukkitSchedule;
import smily.copypose.core.pastepose.IPastePose;
import smily.copypose.core.record.RecordPose;
import smily.copypose.core.record.RecordPoseSelector;
import smily.copypose.nms.NMSInstances;
import smily.copypose.util.*;
import smily.copyposev1_18_1.core.TestLerp;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class PastePoseCommand implements CommandExecutor {
    private Player player;
    private PluginMessager messager;
    private RecordPose selectedRecordPose;
    private final IPastePose pastePose = NMSInstances.getNewPastePose();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            player =  ((Player) sender);
        }

        messager = new PluginMessager(sender);
        selectedRecordPose = RecordPoseSelector.getRecordPose(this.player);


        if(!Optional.ofNullable(selectedRecordPose).isPresent()){
            messager.chatOrLog(ErrorText.NULL_SELECTED, MessageLevel.ERROR);
            return false;
        }

        switch (args.length){
            case 0:
                messager.chatOrLog( ErrorText.MISSING_ARGS, MessageLevel.ERROR);
                messager.chatOrLog(ErrorText.Resolve.LOOK_FOWARD + "github page to learn this plugin", MessageLevel.ERROR);
                break;
            case 1:
                pasteToPlayer(Bukkit.getPlayer(args[0]), selectedRecordPose, new AtomicInteger(3));
                break;
            case 2:
                TestLerp.lerpPlayer(this.player, PluginProperties.PLUGIN.getServer());
                break;
            default:
                messager.chatOrLog(ErrorText.TOO_MANY_ARGS, MessageLevel.ERROR);
                break;
        }
        return false;
    }

    private void pasteToPlayer(Player target, RecordPose recordPose, AtomicInteger countdown){
        if(!Optional.ofNullable(player).isPresent()) messager.chatOrLog(ErrorText.ACTIVITY + "Targeted player either offline or doesn't exist", MessageLevel.ERROR);

        AnimatedBukkitSchedule.animate(()-> {
            try {
                MinecraftTitle.setActionBar(this.player, "Started pasting in " + countdown.get());
                if(countdown.get() == 0) {
                    MinecraftTitle.setActionBar(this.player, "Pasting started");
                    pastePose.pasteToPlayer(target, recordPose);
                }
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            countdown.getAndDecrement();
        }, TickConvert.fromSecond(1), TickConvert.fromSecond(countdown.get()+1), 0);
    }
}
