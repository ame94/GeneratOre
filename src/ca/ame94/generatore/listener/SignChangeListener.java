package ca.ame94.generatore.listener;

import ca.ame94.generatore.util.Config;
import ca.ame94.generatore.util.Logger;
import ca.ame94.generatore.util.OreGenerator;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.material.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignChangeListener implements Listener {

    @EventHandler
    public void OnSignEdit(SignChangeEvent event) {
        String line1 = event.getLine(0);

        // First get the block for the sign itself
        Block signBlock = event.getBlock();

        // Make sure this is a wall-mounted sign, not sign post
        if (signBlock.getType() == Material.WALL_SIGN) {
            // Get the block the sign is attached to.
            Sign sign = (Sign)signBlock.getState().getData();
            Block attachedBlock = signBlock.getRelative(sign.getAttachedFace());
            if (attachedBlock.getType() == Config.getGeneratorBlock()) {
                // If the attached block is a generator block
                if (line1.equalsIgnoreCase("[generatore]")) {
                    Player player = event.getPlayer();
                    // Now check permissions
                    if (player.hasPermission("generatore.create")) {
                        // Now check if generators are allowed on this map
                        Logger.Info("Checking world: " + player.getWorld().getName());
                        if (Config.isMapPermitted(player.getWorld().getName())) {
                            player.sendMessage(ChatColor.GREEN + "Created ore generator successfully.");
                            // Now pop a new ore block on top
                            attachedBlock.getRelative(BlockFace.UP).setType(OreGenerator.get());
                        } else {
                            player.sendMessage(ChatColor.RED + "Sorry you cannot create ore generators on this world.");
                            event.setCancelled(true);
                            return;
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "You do not have permission to create ore generators.");
                        event.setCancelled(true);
                        return;
                    }
                }
            }

        } else if (signBlock.getType() == Material.SIGN_POST) {
            // wrong type, check if player is using this as generator later
        }

    }

}
