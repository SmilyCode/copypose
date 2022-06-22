package smily.copypose.core.record;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import smily.copypose.data.PlayerMove;
import smily.copypose.nms.NMSInstances;
import smily.copypose.util.PluginProperties;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

//for starting a record or adding new records

public class RecordPose implements Record {
    private final RecordData recordData;
    private final String name;
    private boolean isPlaying = false;
    private int taskId = 0;
    AtomicInteger tick;
    private IPathRecorder iPathRecorder = NMSInstances.getNewPathRecorder();

    private RecordPose(String name, RecordData recordData){
        this.name = name;
        this.recordData = recordData;
        RecordDatabase.storeRecord(name, this);
    }

    @Override
    public void record() {
        if(isPlaying) throw new RuntimeException("Cannot record 2 times");
        Player player = recordData.getPlayer();

        tick = new AtomicInteger(1);

        isPlaying = true;

        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(PluginProperties.PLUGIN,()-> {
            Location location = player.getLocation();

            recordData.addTimestamp(tick.get(), new PlayerMove(
                    location.getX(),
                    location.getY(),
                    location.getZ(),
                    location.getYaw(),
                    location.getPitch()
            ));

            tick.getAndIncrement();
            }, 0, 1);

        if(!(this.recordData.getDurationData()==0)){
            Bukkit.getScheduler().scheduleSyncDelayedTask(PluginProperties.PLUGIN,
                    ()-> Bukkit.getScheduler().cancelTask(taskId),
                    recordData.getDurationData());
            RecordDatabase.replaceValue(name, this);
        }
    }

    @Override
    public void save(File file) {}

    @Override
    public void stopRecord() {
        if(!isPlaying&&taskId==0) return;

        Bukkit.getScheduler().cancelTask(taskId);
        recordData.setDurationData(tick.get());
        RecordDatabase.replaceValue(name, this);
        isPlaying = false;
    }

    @Override
    public boolean isPlaying() {
        return isPlaying;
    }

    @SuppressWarnings("null")
    public RecordData getRecordData(){
        return this.recordData;
    }

    public static RecordPose create(String name, RecordData recordData){
        if(!RecordDatabase.containRecord(name)){
            return new RecordPose(name, recordData);
        } else return null;
    }

    public void clearDuration(){
        recordData.setDurationData(0);
    }

    public void clearRecord(){
        recordData.clearTimestamp();
    }

    public String getName() {
        return name;
    }
}