package smily.copyposev1_18_1.core;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.Server;
import org.bukkit.craftbukkit.v1_18_R1.CraftServer;
import org.bukkit.entity.Player;
import smily.copypose.util.MessageLevel;
import smily.copypose.util.PluginMessager;
import smily.copypose.util.PluginProperties;

import java.lang.reflect.InvocationTargetException;

public class TestLerp {
    public static void lerpPlayer(Player player, Server server) {
        PluginMessager.chatAllPlayer("Lerp you", MessageLevel.INFO);
        ServerPlayer serverPlayer =  ((CraftServer) server).getServer().getPlayerList().getPlayer(player.getUniqueId());
        PacketContainer packetContainer =  new PacketContainer(PacketType.Play.Server.REL_ENTITY_MOVE_LOOK);
//
//        try {
////            PluginProperties.PROTOCOL_MANAGER.sendServerPacket(player,);
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
    }

}
