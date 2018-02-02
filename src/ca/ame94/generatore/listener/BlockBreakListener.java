package ca.ame94.generatore.listener;

import ca.ame94.generatore.scheduler.ReplaceOreTask;
import ca.ame94.generatore.util.*;
import ca.ame94.generatore.util.types.SignData;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.block.Sign;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block minedBlock = event.getBlock();

        // Is this an ore block produced by the generator?
        if (Blocks.isOreBlock(minedBlock)) {
            if (Blocks.isGenerator(minedBlock.getRelative(BlockFace.DOWN))) {
                Player miningPlayer = event.getPlayer();
                if (!miningPlayer.hasPermission("generatore.use")) {
                    miningPlayer.sendMessage(ChatColor.RED + "Hey! You're not allowed to use that!");
                    event.setCancelled(true);
                    return;
                } else {
                    Material newMat = OreGenerator.get();
                    new ReplaceOreTask(minedBlock, newMat).runTaskLater(PluginMgr.getPlugin(), Config.getGenerationRateTicks());
                }
            }
        }

        // Is this a generator block the player is trying to break?
        if (Blocks.isGenerator(minedBlock)) {
            Player miningPlayer = event.getPlayer();
            if (!miningPlayer.hasPermission("generatore.use")) {
                miningPlayer.sendMessage(ChatColor.RED + "Hey! You're not allowed to break that!");
                event.setCancelled(true);
                return;
            }
        }

        // Is this a generator sign block the player is trying to break?
        SignData sign = Signs.getSignDataFromBlock(minedBlock);
        if (sign != null) {
            if (sign.getLine1().equalsIgnoreCase("[generatore]")) {
                if (Blocks.isGeneratorBlockNear(minedBlock)) {
                    Player miningPlayer = event.getPlayer();
                    if (!miningPlayer.hasPermission("generatore.create")) {
                        miningPlayer.sendMessage(ChatColor.RED + "Hey! You're not allowed to break that!");
                        event.setCancelled(true);
                        return;
                    }
                }
            }
        }

        /*
        // ## First check if this is an ore block
        // If an ore block is mined,
        if (Blocks.isOreBlock(minedBlock)) {
            // Check if the block below it is the one used for the generator
            Block GeneratorBlock = minedBlock.getRelative(BlockFace.DOWN);
            if (GeneratorBlock.getType() == Config.getGeneratorBlock()) {
                // Now check all four sides for a sign
                BlockFace[] bfList = {BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST};
                for (BlockFace side : bfList) {
                    Block signBlock = GeneratorBlock.getRelative(side);
                    // If this block is wall sign,
                    if (signBlock.getType() == Material.WALL_SIGN) {
                        // read its data
                        String line1 = ((Sign)signBlock.getState()).getLine(0);
                        if (line1.equalsIgnoreCase("[generatore]")) {
                            Player miningPlayer = event.getPlayer();
                            if (miningPlayer.hasPermission("generatore.use")) {
                                Material newMat = OreGenerator.get();
                                new ReplaceOreTask(minedBlock, newMat).runTaskLater(PluginMgr.getPlugin(), Config.getGenerationRateTicks());
                            } else {
                                miningPlayer.sendMessage(ChatColor.RED + "Hey! You're not allowed to use this!");
                                return;
                            }
                        }
                    }
                }
            }
        }
        */
    }
}
