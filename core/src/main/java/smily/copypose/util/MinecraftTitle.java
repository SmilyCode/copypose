package smily.copypose.util;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class MinecraftTitle {
    private static PacketContainer titlePacket = new PacketContainer(PacketType.Play.Server.SET_TITLE_TEXT);
    private static PacketContainer titleAnimationPacket = new PacketContainer(PacketType.Play.Server.SET_TITLES_ANIMATION);
    private static PacketContainer actionBarPacket = new PacketContainer(PacketType.Play.Server.SET_ACTION_BAR_TEXT);

    public static void sendTitle(Player player, String message){

    }

    public static void setActionBar(Player player, String message) throws InvocationTargetException{
        String jsonFormat = "{\"text\": \"" + message + "\"}";
        actionBarPacket.getChatComponents().write(0, WrappedChatComponent.fromJson(jsonFormat));
        PluginProperties.PROTOCOL_MANAGER.sendServerPacket(player,actionBarPacket);
    }
}
