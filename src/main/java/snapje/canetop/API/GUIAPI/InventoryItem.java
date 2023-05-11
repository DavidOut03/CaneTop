package snapje.canetop.API.GUIAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import snapje.canetop.Utils.Chat;
import snapje.canetop.Utils.Check;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InventoryItem {

/**
 * Class created by David Out (Snapje), do not remove this from the class.
 * For any errors please contact: davidoutdevloper@gmail.com
 */

   /**public ItemStack createItemStack(Material mat, String displayName) {
    ItemStack returnedItemStack = new ItemStack(mat);
    ItemMeta meta = returnedItemStack.getItemMeta();
    meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));

     return returnedItemStack;
   }*/

   public static ItemStack createItemStack(Material mat) {
       ItemStack returnedItemStack = new ItemStack(mat);
       return returnedItemStack;
   }

   public static ItemStack createItemStack(Material mat, String displayName) {
       ItemStack returnedItemStack = new ItemStack(mat);
       ItemMeta meta = returnedItemStack.getItemMeta();
       meta.setDisplayName(format(displayName));
       returnedItemStack.setItemMeta(meta);
       return returnedItemStack;
   }

    public static ItemStack createItemStack(Material mat, String displayName, List<String> lore) {
        ItemStack returnedItemStack = new ItemStack(mat);
        ItemMeta meta = returnedItemStack.getItemMeta();
        meta.setDisplayName(format(displayName));
        meta.setLore(formatLore(lore));

        returnedItemStack.setItemMeta(meta);
        return returnedItemStack;
    }

    public static ItemStack createItemStack(Material mat, int type) {
        //Material type, int amount, short damage, Byte data
        ItemStack returnedItemStack = new ItemStack(mat, 1, (short) 0, (byte) type);
        return returnedItemStack;
    }

    public static ItemStack createItemStack(Material mat,int type,  String displayName) {
        ItemStack returnedItemStack = new ItemStack(mat, 1, (short) 0, (byte) type);
        ItemMeta meta = returnedItemStack.getItemMeta();
        meta.setDisplayName(format(displayName));
        returnedItemStack.setItemMeta(meta);
        return returnedItemStack;
    }

    public static ItemStack createItemStack(Material mat,int type, String displayName, List<String> lore) {
        ItemStack returnedItemStack = new ItemStack(mat, 1, (short) 0, (byte) type);
        ItemMeta meta = returnedItemStack.getItemMeta();
        meta.setDisplayName(format(displayName));
        meta.setLore(formatLore(lore));

        returnedItemStack.setItemMeta(meta);
        return returnedItemStack;
    }

    private static String format(String string) {
       return ChatColor.translateAlternateColorCodes('&', string);
    }

    private static List<String> formatLore(List<String> lore) {
       List<String> returnedLore = new ArrayList<>();

       for(String currentLine : lore) {
           returnedLore.add(format(currentLine));
        }

       return returnedLore;
    }

    public static void fillInventoryWithItem(Inventory inv, int starterSlot, int endingSlot, ItemStack item) {
       for(int i = starterSlot; i <= endingSlot; i++) {
           inv.setItem(i, item);
       }
    }

    public static void fillGUIWithItem(GUI gui, int starterSlot, int endingSlot, ItemStack item) {
        for(int i = starterSlot; i <= endingSlot; i++) {
            gui.set(i, item);
        }
    }

    public static ItemStack getPlayerSkull(String playerName, String displayName) {
       String name = playerName;
       if(Check.isUUID(playerName)) {
           name = Bukkit.getOfflinePlayer(UUID.fromString(playerName)).getName();
           if(ChatColor.stripColor(displayName).contains(playerName)) {
               Bukkit.broadcastMessage("replace");
               displayName.replace(playerName, name);
           }
       }

       ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
       SkullMeta meta = (SkullMeta) skull.getItemMeta();
       meta.setOwner(name);
       meta.setDisplayName(Chat.format(displayName));
       skull.setItemMeta(meta);

       return skull;
    }

    public static ItemStack getPlayerSkull(String playerName, String displayName, List<String> lore) {
        String name = playerName;
        if(Check.isUUID(playerName)) {
            name = Bukkit.getOfflinePlayer(UUID.fromString(playerName)).getName();
            if(ChatColor.stripColor(displayName).contains(playerName)) {
                Bukkit.broadcastMessage("replace");
                displayName.replace(playerName, name);
            }
        }

        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwner(name);
        meta.setDisplayName(Chat.format(displayName));
        meta.setLore(formatLore(lore));
        skull.setItemMeta(meta);

        return skull;
    }

}
