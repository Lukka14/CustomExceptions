package me.chalmano.commandeffects;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;

import java.util.List;

public final class CommandEffects extends JavaPlugin implements Listener{

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this,this);
        getCommand("ce").setExecutor(new ReloadCMD(this));
        getLogger().info("CommandEffects has enabled.");

    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
//        player.sendMessage("contains = "+getConfig().contains(event.getMessage().replaceFirst("/", ""))+"\nin config = "+getConfig().getString(event.getMessage().replaceFirst("/", "")+".sound"));
        if (getConfig().contains(event.getMessage().replaceFirst("/", ""))){
            Location location = player.getLocation();
            Sound sound = Sound.valueOf(getConfig().getString(event.getMessage().replaceFirst("/", "") + ".sound"));
            List<String> message;

            for(int i=0;i<getConfig().getStringList(event.getMessage().replaceFirst("/", "") + ".message").size();i++){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        getConfig().getStringList(event.getMessage().replaceFirst("/", "") + ".message").get(i)));
            }
            Particle particle = Particle.valueOf(getConfig().getString(event.getMessage().replaceFirst("/", "") + ".particles"));
            try {
                player.playSound(location, sound, 1.0F, 1.0F);
                player.spawnParticle(particle, location, 100);
                player.sendMessage();
            } catch (Exception e) {
                player.sendMessage("Something went wrong...\n");
                player.sendMessage(getConfig().getString(event.getMessage().replaceFirst("/", "") + ".sound"));
                player.sendMessage(getConfig().getString(event.getMessage().replaceFirst("/", "") + ".particles"));
            }
        }
    }
    @Override
    public void onDisable() {
        getLogger().info("CommandEffects has disabled.");
        // Plugin shutdown logic
    }
}
