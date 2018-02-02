package ca.ame94.generatore;

import ca.ame94.generatore.listener.BlockBreakListener;
import ca.ame94.generatore.listener.BlockChangeListener;
import ca.ame94.generatore.listener.BlockPlaceListener;
import ca.ame94.generatore.listener.SignChangeListener;
import ca.ame94.generatore.util.Config;
import ca.ame94.generatore.util.Logger;
import ca.ame94.generatore.util.PluginMgr;
import ca.ame94.generatore.util.Rand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginMain extends JavaPlugin {

    @Override
    public void onEnable() {
        Logger.Info("Starting up.");

        // Init basic plugin stuff.
        PluginMgr.Init(this);

        // Load config
        Config.Init();
        Rand.init();

        // Register event handlers
        //PluginMgr.RegisterEvent(new BlockChangeListener());
        PluginMgr.RegisterEvent(new BlockPlaceListener());
        PluginMgr.RegisterEvent(new SignChangeListener());
        PluginMgr.RegisterEvent(new BlockBreakListener());
    }

    @Override
    public void onDisable() {
        Logger.Info("Shutting down.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        int numArgs = args.length;
        String subCommand;
        String arg1;

        switch (numArgs) {
            case 0:
                break;

            case 1:
                subCommand = args[0];
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

            case 2:
                subCommand = args[0];
                arg1 = args[1];

                if (subCommand.equalsIgnoreCase("addworld")) {
                    if (sender.hasPermission("generatore.admin")) {
                        String newWorld = arg1;
                        sender.sendMessage("Adding " + arg1 + " to map whitelist.");
                        Logger.Info("Adding " + arg1 + " to map whitelist.");
                        Config.addWorldToWhitelist(arg1);
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
