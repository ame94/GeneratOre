package ca.ame94.generatore.listener;

import ca.ame94.generatore.util.PluginMgr;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class BlockChangeListener implements Listener {

    @EventHandler
    public void onBlockFromToEvent(BlockFromToEvent event) {

        boolean isLiquid = (!event.getBlock().getType().isSolid());
        if (isLiquid) {
            Block toBlock = event.getToBlock();
            Material toType = toBlock.getType();



            if (toType == Material.AIR) {
                toBlock.setType(Material.DIRT);
            }

        }
    }
}
