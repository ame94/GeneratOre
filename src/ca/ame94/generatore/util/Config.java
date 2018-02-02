package ca.ame94.generatore.util;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Config {

    private static Material genMaterialBlock = null;
    private static int generationRateTicks = 0;
    private static List<String> mapWhitelist = null;
    private static HashMap<Material, Integer> materialDB = null;


    public static void Init() {
        JavaPlugin p = PluginMgr.getPlugin();

        if (!p.getDataFolder().exists()) {
            Logger.Info("Creating default configuration...");
            p.getConfig().options().copyDefaults(true);
            p.saveConfig();
        }

        genMaterialBlock = getGeneratorBlock();
        generationRateTicks = getGenerationRateTicks();
        mapWhitelist = getMapWhitelist();
        loadMaterialList();
    }


    public static void loadMaterialList() {
        Map<String, Object> temp = new HashMap<>();

        temp = PluginMgr.getPlugin().getConfig().getValues(true);
        int occurance = 0;
        Logger.Info("Materials used:");
        for (String key : temp.keySet()) {
            Logger.Info("  " + key);
            //occurance = Integer.parseInt((String)temp.get(key));
        }

    }

    /**
     * Find out which block is used for the generator.
     * @return The block type used for the generator
     */
    public static Material getGeneratorBlock() {

        if (genMaterialBlock == null) {
            String blockName = PluginMgr.getPlugin().getConfig().getString("GeneratorBlock");
            Material newMat = Material.getMaterial(blockName);
            if (newMat == null) {
                Logger.Info("Error: \"" + blockName + "\" is an invalid block name.");
            } else {
                Logger.Info("Using " + blockName + " for generator block.");
                genMaterialBlock = newMat;
            }
        }

        return genMaterialBlock;
    }

    public static int getGenerationRateTicks() {

        if (generationRateTicks == 0) {
            int numTicks = 20; // safe default
            double rateSeconds = PluginMgr.getPlugin().getConfig().getDouble("GenerationRate");
            if (rateSeconds > 0.0) {
                numTicks = (int)(20.0 * rateSeconds);
                if (numTicks < 1) {
                    numTicks = 1;
                }
            }
            generationRateTicks = numTicks;
            Logger.Info("Ore generation rate: " + rateSeconds + " seconds; using " + numTicks + " ticks.");
        }

        return generationRateTicks;
    }

    public static void addWorldToWhitelist(String mapName) {
        if (!mapWhitelist.contains(mapName.toLowerCase())) {
            mapWhitelist.add(mapName);
        }
        PluginMgr.getPlugin().getConfig().set("MapWhitelist", mapWhitelist);
        PluginMgr.getPlugin().saveConfig();
    }


    public static List<String> getMapWhitelist() {
        List<String> whitelist = null;

        if (mapWhitelist == null) {
            whitelist = PluginMgr.getPlugin().getConfig().getStringList("MapWhitelist");
            if (whitelist != null) {
                Logger.Info("Whitelisted worlds:");
                for (String world : whitelist) {
                    Logger.Info("  * " + world);
                }
                mapWhitelist = whitelist;
            }
        }

        return mapWhitelist;
    }

    public static boolean isMapPermitted(String mapName) {
        boolean result = false;
        if (mapWhitelist.contains(mapName.toLowerCase())) {
            result = true;
        }
        return result;
    }
}
