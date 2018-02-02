package ca.ame94.generatore.listener;

import ca.ame94.generatore.scheduler.ReplaceOreTask;
import ca.ame94.generatore.util.*;
import ca.ame94.generatore.util.types.Materials;
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
        if (Blocks.isOreBlock(minedBlock) || minedBlock.getType() == Material.COBBLESTONE) {
            if (Blocks.isGenerator(minedBlock.getRelative(BlockFace.DOWN))) {
                Player miningPlayer = event.getPlayer();
                if (!(miningPlayer.hasPermission("generatore.use") || miningPlayer.hasPermission("generatore.admin")) ) {
                    miningPlayer.sendMessage(ChatColor.RED + "Hey! You're not allowed to use that!");
                    event.setCancelled(true);
                    return;
                } else {
                    Material newMat = Config.getMaterials().getRandomMaterial();
                    new ReplaceOreTask(minedBlock, newMat).runTaskLater(PluginMgr.getPlugin(), Config.getGenerationRateTicks());
                }
            }
        }

        // Is this a generator block the player is trying to break?
        if (Blocks.isGenerator(minedBlock)) {
            Player miningPlayer = event.getPlayer();
            if (!(miningPlayer.hasPermission("generatore.use") || miningPlayer.hasPermission("generatore.admin")) ) {
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
                    if (!(miningPlayer.hasPermission("generatore.create") || miningPlayer.hasPermission("generatore.admin")) ) {
                        miningPlayer.sendMessage(ChatColor.RED + "Hey! You're not allowed to break that!");
                        event.setCancelled(true);
                        return;
                    }
                }
            }
        }
    }
}
