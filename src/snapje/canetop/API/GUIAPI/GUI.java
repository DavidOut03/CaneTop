package snapje.canetop.API.GUIAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import snapje.canetop.Utils.Chat;

import java.util.HashMap;

public abstract class GUI {

    /**
     * Class created by David Out (Snapje), do not remove this from the class.
     * For any errors please contact: davidoutdevloper@gmail.com
     */

    private final HashMap<Integer, ItemStack> itemStackHashMap = new HashMap<>();
    private final Inventory inv;
    protected String Title;

    public abstract int getSize();
    public abstract String getTitle();
    public abstract void setUpInventory();
    public abstract boolean canCloseInventory();
    public abstract void clickEvent(InventoryClickEvent event);

    public GUI() {
        inv = Bukkit.createInventory(null, getSize(), ChatColor.translateAlternateColorCodes('&', getTitle()));
    }

    private void fillHashMap() {
        for(int i = 0; i < getSize(); i++) {
            itemStackHashMap.put(i, new ItemStack(Material.AIR));
        }
    }
    private void generateInventory() {
        for(int i = 0; i < getSize(); i++) {
            if(itemStackHashMap.get(i) != null) {
                inv.setItem(i, itemStackHashMap.get(i));
            }
        }
    }

    protected void clear() {
        for(int i = 0; i < getSize(); i++) {
            if(itemStackHashMap.get(i) != null) {
                inv.setItem(i, new ItemStack(Material.AIR));
            }
        }
    }
    protected void set(int slot, ItemStack item) {
        itemStackHashMap.put(slot, item);
    }

    protected void add(ItemStack itemStack) {
        for(int i = 0; i < getSize(); i++) {
            if(itemStackHashMap.get(i) == null) {
               itemStackHashMap.put(i, itemStack);
               break;
            } else  if (itemStackHashMap.get(i).getType() == Material.AIR) {
                itemStackHashMap.put(i, itemStack);
                break;
            }
            }
    }
    protected void remove(int slot) {
        itemStackHashMap.remove(slot);
    }
    protected void removeItemStack(ItemStack item) {
        for(int slot : itemStackHashMap.keySet()) {
            if(itemStackHashMap.get(slot).equals(item)) {
                remove(slot);
            }
        }
    }
    protected void removeMaterial(Material mat) {
        for(int slot : itemStackHashMap.keySet()) {
            if(itemStackHashMap.get(slot).getType().equals(mat)) {
                remove(slot);
            }
        }
    }

    protected void setTitle(String title) {
        Title = title;
    }

    protected void openAndUpdateInventory(Player player) {
        setUpInventory();
        openGUI(player);
    }
    public void openGUI(Player player) {
        if(itemStackHashMap.isEmpty()) {
            setUpInventory();
        }
        generateInventory();
        player.openInventory(inv);
    }


    public static boolean isSameGUI(Inventory inv , GUI gui) {
        if(inv.getTitle().equals(Chat.format(gui.getTitle()))) {
            return true;
        } else {
            return false;
        }
    }

}
