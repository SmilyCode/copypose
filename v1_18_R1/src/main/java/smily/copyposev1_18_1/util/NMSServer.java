package smily.copyposev1_18_1.util;

import net.minecraft.server.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_18_R1.CraftServer;

public class NMSServer {
    public static final MinecraftServer minecraft_server = ((CraftServer) Bukkit.getServer()).getServer();
}
