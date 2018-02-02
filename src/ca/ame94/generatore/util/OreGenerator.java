package ca.ame94.generatore.util;

import org.bukkit.Material;

public class OreGenerator {

    public static Material get() {
        Material result = Material.STONE;
        switch (Rand.getInt(1, 8)) {
            case 1:
                result = Material.COAL_ORE;
                break;
            case 2:
                result = Material.IRON_ORE;
                break;
            case 3:
                result = Material.GOLD_ORE;
                break;
            case 4:
                result = Material.REDSTONE_ORE;
                break;
            case 5:
                result = Material.LAPIS_ORE;
                break;
            case 6:
                result = Material.QUARTZ_ORE;
                break;
            case 7:
                result = Material.EMERALD_ORE;
                break;
            case 8:
                result = Material.DIAMOND_ORE;
                break;
            default:
                break;
        }

        return result;
    }
}
