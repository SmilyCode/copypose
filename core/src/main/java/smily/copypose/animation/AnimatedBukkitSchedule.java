package smily.copypose.animation;

import org.bukkit.Bukkit;
import smily.copypose.util.PluginProperties;

public class AnimatedBukkitSchedule {
    private int id;
    private boolean isAnimated = false;

    public AnimatedBukkitSchedule(){

    }

    public void animate(Runnable animate, int runPerTick, int delay){
        if(isAnimated) throw new RuntimeException("it's currently animating");

        this.id = Bukkit.getScheduler().scheduleSyncRepeatingTask(PluginProperties.PLUGIN, () -> animate.run(), delay, runPerTick);
    }

    public void stop(){
        if(!isAnimated) return;

        Bukkit.getScheduler().cancelTask(id);
    }


    public static void animate(Runnable animate, int runPerTick, int duration, int delay){
        int id = Bukkit.getScheduler().scheduleSyncRepeatingTask(PluginProperties.PLUGIN, () -> animate.run(), delay, runPerTick);

        Bukkit.getScheduler().scheduleSyncDelayedTask(PluginProperties.PLUGIN, ()-> Bukkit.getScheduler().cancelTask(id), duration);
    }

    public static void animateWithEnd(Runnable animate, Runnable endTask, int runPerTick, int duration, int delay){
        int id = Bukkit.getScheduler().scheduleSyncRepeatingTask(PluginProperties.PLUGIN, () -> animate.run(), delay, runPerTick);

        Bukkit.getScheduler().scheduleSyncDelayedTask(PluginProperties.PLUGIN, ()-> {
            endTask.run();
            Bukkit.getScheduler().cancelTask(id);
        }, duration);
    }
}
