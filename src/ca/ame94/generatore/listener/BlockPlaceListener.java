package ca.ame94.generatore.listener;

import ca.ame94.generatore.util.Config;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    @EventHandler
    public void OnBlockPlaceEvent(BlockPlaceEvent event) {

        // Check to see if a sign was placed
        if (event.getBlock().getType() == Material.WALL_SIGN) {

        }


    }

}
