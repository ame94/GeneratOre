package ca.ame94.generatore;

import ca.ame94.generatore.listener.BlockChangeListener;
import ca.ame94.generatore.util.Logger;
import ca.ame94.generatore.util.PluginMgr;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginMain extends JavaPlugin {

    @Override
    public void onEnable() {
        Logger.Info("Starting up.");
        // Register event handlers
        PluginMgr.Init(this);
        PluginMgr.RegisterEvent(new BlockChangeListener());
    }

    @Override
    public void onDisable() {
        Logger.Info("Shutting down.");

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        int numArgs = args.length;

        switch (numArgs) {
            case 0:
                break;

            case 1:
                String subCommand = args[0];
                if (subCommand.equalsIgnoreCase("reload")) {
                    if (sender.hasPermission("generatore.reload")) {
                        sender.sendMessage("Reloading plugin configuration...");
                        Logger.Info("Reloading plugin configuration...");
                        PluginMgr.Reload();
                    } else {
                        sender.sendMessage(ChatColor.RED + "You do not have permission to do that.");
                    }
                    return true;
                }

                sender.sendMessage("Unknown subcommand!");
                break;

            default:
                break;
        }

        return false;
    }

}
