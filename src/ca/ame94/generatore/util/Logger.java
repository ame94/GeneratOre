package ca.ame94.generatore.util;

import org.bukkit.Bukkit;

import java.util.logging.Level;

public class Logger {

    public static void Info(String msg) {
        Bukkit.getLogger().log(Level.INFO, "[GeneratOre] " + msg);
    }

    public static void BroadcastAndLog(String msg) {
        PluginMgr.getPlugin().getServer().broadcastMessage(msg);
        Info(msg);
    }

}
