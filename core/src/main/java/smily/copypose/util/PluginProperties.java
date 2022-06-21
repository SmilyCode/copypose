package smily.copypose.util;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.logging.Logger;

public class PluginProperties {
    public final static Plugin PLUGIN = Bukkit.getPluginManager().getPlugin("Copypose");
    public final static ProtocolManager PROTOCOL_MANAGER = ProtocolLibrary.getProtocolManager();
    public final static File PLUGIN_FOLDER = PLUGIN.getDataFolder();
    public final static Logger PLUGIN_LOGGER = PLUGIN.getLogger();
}
