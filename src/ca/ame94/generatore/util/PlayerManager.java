package ca.ame94.generatore.util;

import org.bukkit.Chunk;
import org.bukkit.entity.Player;

import java.util.Collection;

public class PlayerManager {

    /**
     * Return true if a player was found in a particular chunk
     * @param chunk The chunk in question
     * @return True if a player is in that chunk
     */
    public static boolean isPlayerInChunk(Chunk chunk) {
        Collection<? extends Player> a = PluginMgr.getPlugin().getServer().getOnlinePlayers();

        int chunkCoordX = chunk.getX();
        int chunkCoordZ = chunk.getZ();

        for (Player p : a) {
            int pChunkCoordX = p.getLocation().getChunk().getX();
            int pChunkCoordZ = p.getLocation().getChunk().getZ();

            if (chunkCoordX == pChunkCoordX && chunkCoordZ == pChunkCoordZ) {
                return true;
            }
        }

        return false;
    }
}
