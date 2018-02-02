package ca.ame94.generatore.util;

import ca.ame94.generatore.anno.TestedWorking;
import ca.ame94.generatore.anno.Untested;
import ca.ame94.generatore.util.types.SignData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import static org.bukkit.Material.COAL_ORE;

public class Blocks {


    /**
     * Useful for checking if a generator block is near a sign
     * @param block Usually a sign
     * @return true if there is a geneartor block nearby
     */
    public static boolean isGeneratorBlockNear(Block block) {
        boolean result = false;
        BlockFace[] directionList = {BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST};
        for (BlockFace side : directionList) {
            Block signBlock = block.getRelative(side);
            // If this block is wall sign,
            if (signBlock.getType() == Config.getGeneratorBlock()) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Return number of signs attached to a block
     * @param block The block in question
     * @return number of signs
     */
    @TestedWorking
    public static int getBlockSignCount(Block block) {
        int count = 0;
        BlockFace[] directionList = {BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST};
        for (BlockFace side : directionList) {
            Block signBlock = block.getRelative(side);
            // If this block is wall sign,
            if (signBlock.getType() == Material.WALL_SIGN) {
                ++count;
            }
        }
        return count;
    }

    /**
     * Check to see if the given block is a generator
     * @param block The block in question
     * @return true if is generator
     */
    @TestedWorking
    public static boolean isGenerator(Block block) {
        boolean result = false;

        if (block.getType() == Config.getGeneratorBlock()) {
            int signCount = Blocks.getBlockSignCount(block);
            if (signCount == 1) {
                SignData sign = Signs.getAttachedWallsign(block);
                if (sign != null) {
                    if (sign.getLine1().equalsIgnoreCase("[generatore]")) {
                        result = true;
                    }
                }
            }
        }

        return result;
    }

    @Untested
    public static boolean isGeneratorSign(Block block) {
        boolean result = false;

        if (block.getType() == Material.WALL_SIGN) {

        }

        return result;

    }


    @TestedWorking
    public static boolean isOreBlock(Block block) {
        boolean result = false;
        switch (block.getType()) {
            case COAL_ORE:
                result = true;
                break;
            case IRON_ORE:
                result = true;
                break;
            case GOLD_ORE:
                result = true;
                break;
            case REDSTONE_ORE:
                result = true;
                break;
            case GLOWING_REDSTONE_ORE:
                result = true;
                break;
            case LAPIS_ORE:
                result = true;
                break;
            case QUARTZ_ORE:
                result = true;
                break;
            case EMERALD_ORE:
                result = true;
                break;
            case DIAMOND_ORE:
                result = true;
                break;
            default:
                break;
        }
        return result;
    }

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
