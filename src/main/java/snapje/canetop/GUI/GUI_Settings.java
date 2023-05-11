package snapje.canetop.GUI;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import snapje.canetop.API.GUIAPI.GUI;
import snapje.canetop.API.GUIAPI.InventoryItem;
import snapje.canetop.Core.Main;
import snapje.canetop.Data.Messages.Messages;

import java.util.ArrayList;

public class GUI_Settings extends GUI {

    @Override
    public int getSize() {
        return 45;
    }

    @Override
    public String getTitle() {
        return "&8Settings";
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

        ArrayList<String> message = new ArrayList<>();
        message.add("&7Click to edit the plugin messages.");
        set(21, InventoryItem.createItemStack(Material.NAME_TAG, "&dMessage Editor", message));

        ArrayList<String> guiEditor = new ArrayList<>();
        guiEditor.add("&7Click to edit the canetop gui settings.");
        set(22, InventoryItem.createItemStack(Material.ITEM_FRAME, "&bCaneTop GUI Editor", guiEditor));

        ArrayList<String> reload = new ArrayList<>();
        reload.add("&7Click to reload the plugin settings.");
        set(23, InventoryItem.createItemStack(Material.REDSTONE_TORCH_ON, "&cReload Config", reload));

    }

    @Override
    public boolean canCloseInventory() {
        return true;
    }

    @Override
    public void clickEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        event.setCancelled(true);
        if(event.getCurrentItem().getType() == Material.NAME_TAG) {
            GUI_Messages gui = new GUI_Messages();
            gui.openGUI(player);
        } else if(event.getCurrentItem().getType() == Material.ITEM_FRAME) {
            GUI_CaneTopGUIEditor gui = new GUI_CaneTopGUIEditor();
            gui.openGUI(player);
        } else if(event.getCurrentItem().getType() == Material.REDSTONE_TORCH_ON) {
            player.closeInventory();
            Main.getInstance().update();
            player.sendMessage(Messages.getInstance().reloadedConfig);
        }
     }

/**
 * Class created by David Out (Snapje), do not remove this from the class.
 * For any errors please contact: davidoutdevloper@gmail.com
 */

}
