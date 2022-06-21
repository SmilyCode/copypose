package smily.copypose.nms;

import org.bukkit.Server;

public class VersionChecker {
    private final Server server;
    private BukkitVersionCompatible bukkitVersion;
    private boolean isCompatible = false;

    public VersionChecker(Server server){
        this.server = server;
    }

    public void check(){
        for(BukkitVersionCompatible version: BukkitVersionCompatible.values()){
            if(server.getBukkitVersion().equals(version.toString())) {
                bukkitVersion = version;
                isCompatible = true;
            }
        }
    }

    public BukkitVersionCompatible getBukkitVersion(){
        return bukkitVersion;
    }

    public boolean isVersionCompatible(){
        return isCompatible;
    }
}