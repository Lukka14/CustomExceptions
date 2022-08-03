package me.chalmano.commandeffects;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadCMD implements CommandExecutor {
    CommandEffects plugin;
    public ReloadCMD(CommandEffects plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length==0) {
                return false;
            }
            if (args[0].equalsIgnoreCase("reload")) {
                if ((player.hasPermission("CommandEffects.reload") || player.isOp())) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aCommandEffects has " +
                            "been successfully reloaded."));
                    plugin.reloadConfig();
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou do not have permission."));
                }
            }
        }
        else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&aCommandEffects has " +
                    "been successfully reloaded."));
            plugin.reloadConfig();
        }
        return true;
    }

}
