package smily.copyposev1_18_2.util;

import net.minecraft.server.level.ServerPlayer;
import org.bukkit.entity.Player;

public class NMSPlayer {
    public static ServerPlayer getServerPlayer(Player player){
        return NMSServer.minecraft_server.getPlayerList().getPlayer(player.getUniqueId());
    }
}
