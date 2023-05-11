package snapje.canetop.Events.Listeners;

import com.bgsoftware.wildtools.api.events.HarvesterHoeUseEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import snapje.canetop.API.CaneTop;
import snapje.canetop.Core.Main;
import snapje.canetop.Events.CustomEvents.BreakCaneEvent;

import java.util.ArrayList;

public class BreakCaneListener implements Listener {

/**
 * Class created by David Out (Snapje), do not remove this from the class.
 * For any errors please contact: davidoutdevloper@gmail.com
 */

    private ArrayList<Block> b = new ArrayList<Block>();
    private int blockCount = 0;

    @EventHandler
    public void onBreak(BlockBreakEvent e) {

        if(e.getBlock().getType() == Material.SUGAR_CANE || e.getBlock().getType() == Material.matchMaterial("SUGAR_CANE_BLOCK")) {

            blockCount = 0;
            b.clear();

            for(int i = e.getBlock().getLocation().getBlockY(); i < 255; i++) {
                if(e.getBlock().getWorld().getBlockAt(e.getBlock().getX(), i, e.getBlock().getZ()).getType() == Material.SUGAR_CANE || e.getBlock().getWorld().getBlockAt(e.getBlock().getX(), i, e.getBlock().getZ()).getType() == Material.matchMaterial("SUGAR_CANE_BLOCK")) {
                    blockCount++;
                    b.add(e.getBlock());
                } else {
                    break;
                }
            }
            Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {

                @Override
                public void run() {
                    if(e.getBlock().getType() == Material.AIR) {
                        Bukkit.getPluginManager().callEvent(new BreakCaneEvent(e.getPlayer(), blockCount));
                    }
                }

            }, 5L);


        } else if(Bukkit.getVersion().contains("1.13") || Bukkit.getVersion().contains("1.14")  || Bukkit.getVersion().contains("1.15") || Bukkit.getVersion().contains("1.16") && e.getBlock().getType() == Material.matchMaterial("LEGACY_SUGAR_CANE_BLOCK")) {
            if(e.getBlock().getType() == Material.matchMaterial("LEGACY_SUGAR_CANE_BLOCK")) {

                blockCount = 0;
                b.clear();
                for(int i = e.getBlock().getLocation().getBlockY(); i < 255; i++) {
                    if(e.getBlock().getWorld().getBlockAt(e.getBlock().getX(), i, e.getBlock().getZ()).getType() == Material.matchMaterial("LEGACY_SUGAR_CANE_BLOCK")) {
                        blockCount++;
                        b.add(e.getBlock());
                    } else {
                        break;
                    }
                }

                Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {

                    @Override
                    public void run() {
                        if(e.getBlock().getType() == Material.AIR) {
                            Bukkit.getPluginManager().callEvent(new BreakCaneEvent(e.getPlayer(), blockCount));
                        }
                    }

                }, 5L);




            }
        }
    }

    @EventHandler
    public void onHoeUse(HarvesterHoeUseEvent e) {
        CaneTop top = new CaneTop();
        int blockCount = 0;

        for(Location b : e.getBlocks()) {
            if(b.getBlock().getType() == Material.SUGAR_CANE || b.getBlock().getType() == Material.matchMaterial("SUGAR_CANE_BLOCK")) {
                blockCount++;
            } else if(b.getBlock().getType() == Material.matchMaterial("LEGACY_SUGAR_CANE_BLOCK")) {
                blockCount++;
            }
        }

        if(blockCount != 0) {
            Bukkit.getPluginManager().callEvent(new BreakCaneEvent(e.getPlayer(), blockCount));
        }

    }


    @EventHandler
    public void onBreak(BreakCaneEvent e) {
        if(e.getScore() != null) {
            e.getScore().add(e.getAmountToAdd());
        }
    }

}
