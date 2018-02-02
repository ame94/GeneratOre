package ca.ame94.generatore.scheduler;

import ca.ame94.generatore.util.Logger;
import ca.ame94.generatore.util.PluginMgr;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ReplaceOreTask extends BukkitRunnable {

    private Block where;
    private Material what;

    public ReplaceOreTask(Block block, Material mat) {
        where = block;
        what = mat;
    }

    @Override
    public void run() {
        where.setType(what);
    }
}
