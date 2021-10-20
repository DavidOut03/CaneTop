package snapje.canetop.GUI;

import com.sun.xml.internal.bind.v2.TODO;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import snapje.canetop.API.GUIAPI.GUI;
import snapje.canetop.API.GUIAPI.InventoryItem;
import snapje.canetop.Data.Messages.Messages;
import snapje.canetop.Data.Settings;
import snapje.canetop.Utils.Chat;

import java.util.*;

public class GUI_CaneTopGUIEditor extends GUI implements Listener {

    private static ArrayList<Player> editTitle = new ArrayList<>();
    private static ArrayList<Player> editDisplayName = new ArrayList<>();
    private static ArrayList<Player> editLore = new ArrayList<>();


    @Override
    public int getSize() {
        return 45;
    }

    @Override
    public String getTitle() {
        return "&8CaneTopGUI Editor";
    }

    @Override
    public void setUpInventory() {
        for(int slot = 0; slot < getSize(); slot++) {
            if(Material.matchMaterial("STAINED_GLASS_PANE") != null) {
                set(slot, InventoryItem.createItemStack(Material.STAINED_GLASS_PANE, 7, " "));
            } else {
                set(slot, InventoryItem.createItemStack(Material.matchMaterial("GRAY_STAINED_GLASS_PANE"), " "));
            }
        }

        if(Settings.getInstance().caneTopGuiEnabled() == true) {
            ArrayList<String> enabled = new ArrayList<>();
            enabled.add("&7Click to &cDisable &7the CaneTop GUI.");
            set(20, InventoryItem.createItemStack(Material.REDSTONE_TORCH_ON, "&aGUI Enabled", enabled));
        } else {
            ArrayList<String> disabled = new ArrayList<>();
            disabled.add("&7Click to &aEnable &7the CaneTop GUI.");
            set(20, InventoryItem.createItemStack(Material.REDSTONE_TORCH_ON, "&cGUI Disabled", disabled));
        }

        int size = Settings.getInstance().getTopSize();
        ArrayList<String> sizeLore = new ArrayList<>();
        if(size == 4) {
            sizeLore.add("&7Top 4 <");
            sizeLore.add("&7Top 9");
            sizeLore.add("&7Top 16");
        } else if(size == 9) {
            sizeLore.add("&7Top 4");
            sizeLore.add("&7Top 9 <");
            sizeLore.add("&7Top 16");
        } else {
            sizeLore.add("&7Top 4");
            sizeLore.add("&7Top 9");
            sizeLore.add("&7Top 16 <");
        }
        set(21, InventoryItem.createItemStack(Material.CHEST, "&6GUI Size", sizeLore));

        ArrayList<String> title = new ArrayList<>();
        title.add("&7Click to edit the gui title.");
        title.add(" ");
        title.add("&7Current Title: ");
        title.add(" ");
        title.add(Settings.getInstance().getCaneTopGUITitle());
        set(22, InventoryItem.createItemStack(Material.BOOK_AND_QUILL, "&dEdit the title", title));


        ArrayList<String> displayname = new ArrayList<>();
        displayname.add("&7Click to edit the skull heads displayname.");
        displayname.add(" ");
        displayname.add("&7Current Displayname: ");
        displayname.add(" ");
        displayname.add(Settings.getInstance().getSkullDisplayName());
        set(23, InventoryItem.createItemStack(Material.PAPER, "&bEdit Displayname", displayname));


        ArrayList<String> lore = new ArrayList<>();
        lore.add("&7Click to edit the skull heads lore.");
        lore.add(" ");
        lore.add("&7Current Lore: ");
        lore.add(" ");
        for(String line : Settings.getInstance().getSkullLore()) {
            lore.add(line);
        }

        set(24, InventoryItem.createItemStack(Material.NAME_TAG, "&eEdit Lore", lore));

    }

    @Override
    public boolean canCloseInventory() {
        return true;
    }

    @Override
    public void clickEvent(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        event.setCancelled(true);

        if(event.getCurrentItem().getType().toString().contains("REDSTONE_TORCH")) {
            if(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).toLowerCase().contains("enabled")) {
                ArrayList<String> disabled = new ArrayList<>();
                disabled.add(Chat.format("&7Click to &aEnable &7the CaneTop GUI."));
                String displayName = "&cGUI Disabled";

                ItemMeta meta = event.getCurrentItem().getItemMeta();
                meta.setDisplayName(Chat.format(displayName));
                meta.setLore(disabled);
                event.getCurrentItem().setItemMeta(meta);
                Settings.getInstance().setGUIEnabled(false);
            } else {
                ArrayList<String> enabled = new ArrayList<>();
                enabled.add(Chat.format("&7Click to &cDisable &7the CaneTop GUI."));
                String displayName = "&aGUI Enabled";

                ItemMeta meta = event.getCurrentItem().getItemMeta();
                meta.setDisplayName(Chat.format(displayName));
                meta.setLore(enabled);
                event.getCurrentItem().setItemMeta(meta);
                Settings.getInstance().setGUIEnabled(true);
            }
        } else if(event.getCurrentItem().getType() == Material.CHEST) {
            ArrayList<String> sizeLore = new ArrayList<>();
            int size = Settings.getInstance().getTopSize();

            if(size == 4) {
                sizeLore.add("&7Top 4");
                sizeLore.add("&7Top 9 <");
                sizeLore.add("&7Top 16");
                Settings.getInstance().setTopSize(9);
            } else if(size == 9) {
                sizeLore.add("&7Top 4");
                sizeLore.add("&7Top 9");
                sizeLore.add("&7Top 16 <");
                Settings.getInstance().setTopSize(16);
            } else {
                sizeLore.add("&7Top 4 <");
                sizeLore.add("&7Top 9");
                sizeLore.add("&7Top 16");
                Settings.getInstance().setTopSize(4);
            }

            ItemMeta meta = event.getCurrentItem().getItemMeta();
            meta.setLore(Chat.formatLore(sizeLore));
            event.getCurrentItem().setItemMeta(meta);
        } else if(event.getCurrentItem().getType() == Material.BOOK_AND_QUILL) {
            editTitle.add(p);
            p.closeInventory();
            p.sendMessage(Messages.getInstance().editGUITitle);
        } else if(event.getCurrentItem().getType() == Material.PAPER) {
            editDisplayName.add(p);
            p.closeInventory();
            p.sendMessage(Messages.getInstance().editGUIDisplayname);
        } else if(event.getCurrentItem().getType() == Material.NAME_TAG) {
            Settings.getInstance().setSkullLore(new ArrayList<>());
            editLore.add(p);
            p.closeInventory();
            p.sendMessage(Messages.getInstance().editGUILore);
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if(editTitle.contains(e.getPlayer())) {
            e.setCancelled(true);

            if(!e.getMessage().contains("-c")) {
                Settings.getInstance().setGUITitle(e.getMessage());
                e.getPlayer().sendMessage(Messages.getInstance().setGUITitle.replace("{TITLE}", Chat.format(e.getMessage())));
                editTitle.remove(e.getPlayer());
            } else {
                editTitle.remove(e.getPlayer());
                e.getPlayer().sendMessage(Messages.getInstance().cancelledGUIEditor);
            }
        } else if(editDisplayName.contains(e.getPlayer())) {
            e.setCancelled(true);

            if(!e.getMessage().contains("-c")) {
                Settings.getInstance().setSkulldisplayName(e.getMessage());
                e.getPlayer().sendMessage(Messages.getInstance().setGUIDisplayname.replace("{DISPLAYNAME}", Chat.format(e.getMessage())));
                editDisplayName.remove(e.getPlayer());
            } else {
                editDisplayName.remove(e.getPlayer());
                e.getPlayer().sendMessage(Messages.getInstance().cancelledGUIEditor);
            }

        } else if(editLore.contains(e.getPlayer())) {
            e.setCancelled(true);

            if(!e.getMessage().contains("-c")) {
               if(e.getMessage().contains("-s")) {
                   e.getPlayer().sendMessage(Messages.getInstance().setGUILore);
                   editLore.remove(e.getPlayer());
               } else {
                   List<String> lore = Settings.getInstance().getSkullLore();
                   lore.add(e.getMessage());
                   Settings.getInstance().setSkullLore(lore);
                   e.getPlayer().sendMessage(Messages.getInstance().addedNewLineToGUILore.replace("{LINE}", Chat.format(e.getMessage())));
                   e.getPlayer().sendMessage(Messages.getInstance().addLineToGuiLore);
               }
            } else {
                editLore.remove(e.getPlayer());
                e.getPlayer().sendMessage(Messages.getInstance().cancelledGUIEditor);
            }

        }
    }

/**
 * Class created by David Out (Snapje), do not remove this from the class.
 * For any errors please contact: davidoutdevloper@gmail.com
 */

}
