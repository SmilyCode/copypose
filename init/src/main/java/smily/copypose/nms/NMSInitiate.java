package smily.copypose.nms;

import org.bukkit.Server;
import smily.copyposev1_18_1.core.PastePoseV1_18_1;
import smily.copyposev1_18_2.core.PastePoseV1_18_2;
import smily.copypose.util.PluginProperties;
import smily.copyposev1_18_2.core.record.PathRecorderv1_18_2;

public class NMSInitiate {
    private final Server server;
    private final VersionChecker versionChecker;

    public NMSInitiate(Server server){
        this.server = server;
        versionChecker = new VersionChecker(this.server);

    }

    public void initiate(){
        versionChecker.check();

        if(!versionChecker.isVersionCompatible()) throw new RuntimeException("This plugin doesn't support your server version.");
        else PluginProperties.PLUGIN_LOGGER.info("Nice! It is compatible.");

        switch (versionChecker.getBukkitVersion()){
            case V1_18:
                break;

            case V1_18_1:
//                new NMSInstances(new PastePoseV1_18_1());
                break;

            case V1_18_2:
                new NMSInstances(new PastePoseV1_18_2(), new PathRecorderv1_18_2());
                break;
        }
    }

    public VersionChecker getVersionChecker() {
        return versionChecker;
    }
}
