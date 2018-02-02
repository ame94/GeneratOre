package ca.ame94.generatore.util.types;

import ca.ame94.generatore.util.Logger;
import ca.ame94.generatore.util.PluginMgr;
import ca.ame94.generatore.util.Rand;
import org.bukkit.Material;

import java.util.*;

public class Materials {

    private HashMap<Material, Integer> materialDB = null;

    public Materials() {
        materialDB = new HashMap<>();
        List<?> entries = PluginMgr.getPlugin().getConfig().getList("OreSelection");
        Logger.Info("Ore Selection:");
        for (Object element : entries) {
            LinkedHashMap<Object, Object> entry = (LinkedHashMap<Object,Object>)element;
            for (Object key : entry.keySet()) {
                int value = (int) entry.get(key);
                Logger.Info("  " + (String)key + " : " + value);
                materialDB.put(Material.getMaterial((String)key), value);
            }
        }
    }

    public Material getRandomMaterial() {
        Material result = Material.COBBLESTONE; // default material
        // Randomize the materials in a list.
        List<Material> matList = new ArrayList<>();
        for (Material mat : materialDB.keySet()) {
            matList.add(mat);
        }
        Collections.shuffle(matList);

        // Now pick the first one that beats chance.
        double r = Rand.getDouble();
        for (Material mat : matList) {
            double chance = 1.0 / (double)materialDB.get(mat);
            if (r < chance) {
                result = mat;
                break;
            }
        }
        
        return result;
    }
}
