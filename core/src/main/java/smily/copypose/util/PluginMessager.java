package smily.copypose.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.logging.Level;

/*Provide static method that handle common exception when sending message to player*/

public class PluginMessager {
    private Object instance;
    private Player player;

    //create new PluginMessager based on the instance (player or other else)
    public PluginMessager(Object instance){
        this.instance = instance;

        if(isPlayerInstance()){
            this.player = (Player) instance;
        }
    }

    public void chatOrLog(String text, MessageLevel level){
        if(isPlayerInstance()){
            this.player.sendMessage(chatColor(level) + text);
        } else {
            PluginProperties.PLUGIN_LOGGER.log(Level.INFO,chatColor(level) + text);
        }
    }

    public static void chatAllPlayer(String text, MessageLevel level){
        Bukkit.getOnlinePlayers();
        for(Player player:Bukkit.getOnlinePlayers()){
            player.sendMessage(chatColor(level) + text);
        }
    }

    public void chatAndLog(String text, MessageLevel level){
        if(isPlayerInstance()) {
            this.player.sendMessage(chatColor(level) + text);
        }
        PluginProperties.PLUGIN_LOGGER.log(Level.INFO,chatColor(level)+text);
    }

    public void log(String text, MessageLevel level){
        PluginProperties.PLUGIN_LOGGER.log(Level.INFO, chatColor(level)+text);
    }

    private static ChatColor chatColor(MessageLevel level){
        switch (level){
            case INFO:
                return ChatColor.AQUA;
            case WARN:
                return ChatColor.YELLOW;
            case ERROR:
                return ChatColor.RED;
            default:
                return ChatColor.WHITE;
        }
    }

    public boolean isPlayerInstance(){
        return this.instance instanceof Player;
    }

    public void setInstance(Object instance) {
        this.instance = instance;
    }

    public Object getInstance() {
        return instance;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}

