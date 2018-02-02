package ca.ame94.generatore.util;

import ca.ame94.generatore.anno.Broken;
import ca.ame94.generatore.anno.TestedWorking;
import ca.ame94.generatore.anno.Untested;
import ca.ame94.generatore.util.types.SignData;
import org.bukkit.Material;

import org.bukkit.Warning;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class Signs {

    /**
     * Returns the SignData for a given block
     * @param block The block to check for sign data
     * @return null if no sign, or more than 1 sign, otherwise SignData
     */
    @TestedWorking
    public static SignData getAttachedWallsign(Block block) {
        SignData signData = null;
        int numSigns = Blocks.getBlockSignCount(block);
        if (numSigns == 1) {
            // Now check all four sides for a sign
            BlockFace[] bfList = {BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST};
            for (BlockFace side : bfList) {
                Block signBlock = block.getRelative(side);
                // If this block is wall sign,
                if (signBlock.getType() == Material.WALL_SIGN) {
                    signData = getSignDataFromBlock(signBlock);
                    if (signData != null) {
                        break;
                    }
                }
            }
        }
        return signData;
    }

    /**
     * Assumes the given block is a sign and returns a SignData object
     * @param block The block assumed to be a sign
     * @return null if not a sign, otherwise SignData object
     */
    @TestedWorking
    public static SignData getSignDataFromBlock(Block block) {
        SignData signData = null;
        if (block.getType() == Material.WALL_SIGN || block.getType() == Material.SIGN_POST) {
            signData = new SignData(block);
        }
        return signData;
    }

}
