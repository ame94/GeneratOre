package ca.ame94.generatore.listener;

import ca.ame94.generatore.util.Blocks;
import ca.ame94.generatore.util.Logger;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class BlockChangeListener implements Listener {

    public static int count = 0;

    @EventHandler
    public void onBlockFromToEvent(BlockFromToEvent event) {


        Block block = event.getBlock();
        Block toBlock = event.getToBlock();

        Logger.BroadcastAndLog("FromTo #" + count + " " + block.getType().toString() + " Going " + Blocks.getLocation(block) + " to " + Blocks.getLocation(toBlock));
        ++count;

        if (Blocks.isBlockWater(block)) {
            if (Blocks.isBlockLava(toBlock)) {
                block.setType(Material.DIRT);
                Logger.Info("Triggered");
                //event.setCancelled(true);
            }
        }
    }
}
