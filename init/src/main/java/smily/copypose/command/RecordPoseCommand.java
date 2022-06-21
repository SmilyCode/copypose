package smily.copypose.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import smily.copypose.util.PluginProperties;
import smily.copypose.core.record.RecordData;
import smily.copypose.core.record.RecordDatabase;
import smily.copypose.core.record.RecordPose;
import smily.copypose.core.record.RecordPoseSelector;
import smily.copypose.util.*;

import java.util.Optional;

public class RecordPoseCommand implements CommandExecutor {
    private Player player;
    private PluginMessager messager;
    private RecordPose recordPose;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        messager = new PluginMessager(sender);

        if(sender instanceof Player){
            this.player = ((Player) sender);
        }

        switch (args.length){
            case 0:
                messager.chatOrLog(ErrorText.ACTIVITY + "missing needed information.", MessageLevel.ERROR);
                messager.chatOrLog(ErrorText.Resolve.ACTIVITY + "look at github page for how to use this plugin.", MessageLevel.ERROR);
                break;
            case 1:
                switch (args[0]) {
                    case "record":
                        startRecord(new TimeFormat("3s"));
                        break;
                    case "stop":
                        stopRecord();
                        break;
                    case "clear":
                        recordPose.clearRecord();
                        recordPose.clearDuration();
                        messager.chatOrLog("Timestamp cleared for " + recordPose.getName(), MessageLevel.INFO);
                        break;
                    default:
                        messager.chatOrLog(ErrorText.INVALID_COMMAND + "wrong syntax", MessageLevel.ERROR);
                        break;
                }
                break;
            case 2:
                if(args[0].equals("select")){
                    selectRecord(args[1]);
                } else messager.chatOrLog(ErrorText.INVALID_COMMAND + "wrong syntax", MessageLevel.ERROR);
                break;

            case 3:
                if(args[0].equals("new")){
                    newRecord(args[1], Bukkit.getPlayer(args[2]), null);
                } else messager.chatOrLog(ErrorText.INVALID_COMMAND + "wrong syntax", MessageLevel.ERROR);
                break;

            case 4:
                if(args[0].equals("new")){
                    newRecord(args[1], Bukkit.getPlayer(args[2]), new TimeFormat(args[3]));
                } else messager.chatOrLog(ErrorText.INVALID_COMMAND + "wrong syntax", MessageLevel.ERROR);
                break;

            default:
                messager.chatOrLog(ErrorText.TOO_MANY_ARGS, MessageLevel.ERROR);
                break;
        }

        return false;
    }

    private void newRecord(String name, Player target, TimeFormat duration){
        if(!name.equals("new")) {
            if(Optional.ofNullable(target).isPresent()) {
                if(!RecordDatabase.containRecord(name)) {
                    if(!Optional.ofNullable(duration).isPresent()){
                        duration = new TimeFormat("0t");
                    }
                    duration.define();
                    if (duration.isValid()) {
                        recordPose = RecordPose.create(name, new RecordData(duration.convertToTick(), target));
                        if (duration.getNumber() != 0){
                            messager.chatOrLog("created a record named \""
                                    + name
                                    + "\" with duration for "
                                    + duration.getNumber()
                                    + " "
                                    + duration.getUnit().toString(), MessageLevel.INFO);
                        } else messager.chatOrLog("Created a record named \""
                                + name
                                + "\".", MessageLevel.INFO);
                        RecordPoseSelector.storeSelected(this.player, this.recordPose);
                    } else {
                        messager.chatOrLog(ErrorText.WRONG_FORMAT + "duration time format is invalid.", MessageLevel.ERROR);
                        messager.chatOrLog(ErrorText.Resolve.FORMAT + "[number][t,s,m,h]", MessageLevel.ERROR);
                    }
                } else messager.chatOrLog(ErrorText.ACTIVITY + "a record with that name already exist.", MessageLevel.ERROR);
            } else messager.chatOrLog(ErrorText.ACTIVITY + "targeted player either offline or doesn't exist.", MessageLevel.ERROR);
        } else messager.chatOrLog(ErrorText.ACTIVITY + "record name cannot be named same as existing plugin syntax.", MessageLevel.ERROR);
    }

    private void startRecord(TimeFormat countdown){
        if(!recordPose.isPlaying()) {
            countdown.define();
            messager.chatOrLog("Record start in " + countdown.getNumber() + " " + countdown.getUnit().toString(), MessageLevel.INFO);
            Bukkit.getScheduler().scheduleSyncDelayedTask(PluginProperties.PLUGIN, () -> {
                recordPose.record();
                messager.chatOrLog("Record started.", MessageLevel.INFO);
            }, countdown.convertToTick());
        } else messager.chatOrLog(ErrorText.START + "it's already started", MessageLevel.ERROR);
    }

    private void stopRecord(){
        if(recordPose.isPlaying()){
            recordPose.stopRecord();
            messager.chatOrLog("Stopped recording at tick " + recordPose.getRecordData().getDurationData(), MessageLevel.INFO);
        } else messager.chatOrLog(ErrorText.STOP + "nothing has started.", MessageLevel.ERROR);
    }

    private void selectRecord(String name){
        if (RecordDatabase.containRecord(name)) {
            if(!recordPose.isPlaying()) {
                recordPose = RecordDatabase.getRecord(name);
                RecordPoseSelector.replaceSelected(this.player, recordPose);

                messager.chatOrLog("Selected record named \"" + recordPose.getName() + "\".", MessageLevel.INFO);
            } else messager.chatOrLog(ErrorText.ACTIVITY + "there's an activity in the current selected.", MessageLevel.ERROR);
        } else messager.chatOrLog(ErrorText.ACTIVITY + "the specified record name is null or cannot be found.", MessageLevel.ERROR);
    }
}
