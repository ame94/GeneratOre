package ca.ame94.generatore.util;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class Blocks {

    public static boolean isBlockLiquid(Block block) {
        Material mat = block.getType();
        if (mat == Material.LAVA || mat == Material.STATIONARY_LAVA || mat == Material.WATER || mat == Material.STATIONARY_WATER) {
            return true;
        }
        return false;
    }

    public static boolean isBlockLava(Block block) {
        Material mat = block.getType();
        if (mat == Material.LAVA || mat == Material.STATIONARY_LAVA) {
            return true;
        }
        return false;
    }

    public static boolean isBlockWater(Block block) {
        Material mat = block.getType();
        if (mat == Material.WATER || mat == Material.STATIONARY_WATER) {
            return true;
        }
        return false;
    }

    public static String getLocation(Block block) {
        return "(" + block.getLocation().getBlockX() + ", " + block.getLocation().getBlockY() + ", " + block.getLocation().getBlockZ() + ")";
    }


}
