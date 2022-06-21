package smily.copypose;

import org.bukkit.plugin.java.JavaPlugin;
import smily.copypose.command.PastePoseCommand;
import smily.copypose.command.RecordPoseCommand;
import smily.copypose.nms.NMSInitiate;

public class CopyPose extends JavaPlugin {
    @Override
    public void onLoad() {
        this.getLogger().info("Checking is version compatible");

        NMSInitiate nmsInitiate = new NMSInitiate(this.getServer());
        nmsInitiate.initiate();
    }

    @Override
    public void onEnable() {
        getCommand("recordpose").setExecutor(new RecordPoseCommand());
        getCommand("pastepose").setExecutor(new PastePoseCommand());
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
