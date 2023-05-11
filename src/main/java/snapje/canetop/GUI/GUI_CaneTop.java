package snapje.canetop.GUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import snapje.canetop.API.CaneScore;
import snapje.canetop.API.GUIAPI.GUI;
import snapje.canetop.API.GUIAPI.InventoryItem;
import snapje.canetop.API.Top;
import snapje.canetop.Data.Messages.Messages;
import snapje.canetop.Data.Settings;
import snapje.canetop.Utils.Chat;
import snapje.canetop.Utils.Check;

import java.util.*;

public class GUI_CaneTop extends GUI {

    private int size = Settings.getInstance().getTopSize();

    @Override
    public int getSize() {
        if(Settings.getInstance().getTopSize() == 16) {
            return 54;
        } else if(Settings.getInstance().getTopSize() == 9) {
            return 45;
        } else {
            return 4*9;
        }
    }

    @Override
    public String getTitle() {
        return Settings.getInstance().getCaneTopGUITitle();
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

        HashMap<Integer, Integer> placeAndSlot = new HashMap<>();
        placeAndSlot.put(1, 13);
        placeAndSlot.put(2, 21);
        placeAndSlot.put(3, 22);
        placeAndSlot.put(4, 23);
        if(Settings.getInstance().getTopSize() >= 9) {
            placeAndSlot.put(5, 29);
            placeAndSlot.put(6, 30);
            placeAndSlot.put(7, 31);
            placeAndSlot.put(8, 32);
            placeAndSlot.put(9, 33);
            if(Settings.getInstance().getTopSize() == 16) {
                placeAndSlot.put(10, 37);
                placeAndSlot.put(11, 38);
                placeAndSlot.put(12, 39);
                placeAndSlot.put(13, 40);
                placeAndSlot.put(14, 41);
                placeAndSlot.put(15, 42);
                placeAndSlot.put(16, 43);
            }
        }

        int top = Settings.getInstance().getTopSize();
        for(Map.Entry<Integer, Integer> me : placeAndSlot.entrySet()) {
            if(Top.getTop(top).get(me.getKey()) != null) {
                int place = me.getKey();
                String name = Top.getTop(top).get(me.getKey());
                List<String> lore = new ArrayList<String>();

                if(Chat.getNameFromUUID(name) != null) {
                    name = Chat.getNameFromUUID(Top.getTop(top).get(me.getKey()));
                    int score = CaneScore.getScore(UUID.fromString(Top.getTop(top).get(me.getKey()))).getScore();
                    lore = Chat.replaceInLore(Settings.getInstance().getSkullLore(), "{SCORE}", score + "");
                }

                String displayName = Settings.getInstance().getSkullDisplayName().replace("{PLAYER}", name).replace("{PLACE}", place + "");
                set(me.getValue(), InventoryItem.getPlayerSkull(name, displayName, lore));
            } else {
                String displayName = Settings.getInstance().getSkullDisplayName().replace("{PLACE}", me.getKey() + "").replace("{PLAYER}", " ");
                set(me.getValue(), InventoryItem.getPlayerSkull("Alex", displayName));
            }
        }





    }

    /*

    if(Chat.getNameFromUUID(name) != null) {
                    name = Chat.getNameFromUUID(Top.getTop(top).get(me.getKey()));
                }

      int top = Settings.getInstance().getTopSize();
        for(Map.Entry<Integer, Integer> me : placeAndSlot.entrySet()) {
            if(Top.getTop(top).get(me.getKey()) != null) {
                String name = Top.getTop(top).get(me.getKey());
                if(name != null) {
                    List<String> lore = new ArrayList<String>();
                    if (Check.isUUID(name)) {
                        name = Bukkit.getOfflinePlayer(UUID.fromString(name)).getName();
                        int score = CaneScore.getScore(UUID.fromString(Top.getTop(top).get(me.getKey()))).getScore();

                        lore = Chat.replaceInLore(Settings.getInstance().getSkullLore(), "{SCORE}", score + "");
                    }
                    String displayName = Settings.getInstance().getSkullDisplayName().replace("{PLACE}", me.getKey() + "").replace("{PLAYER}", name);
                    set(me.getValue(), InventoryItem.getPlayerSkull(name, displayName, lore));
                }
            } else {
                String displayName = Settings.getInstance().getSkullDisplayName().replace("{PLACE}", me.getKey() + "").replace("{PLAYER}", " ");
                set(me.getValue(), InventoryItem.getPlayerSkull("Alex", displayName));
            }
        }
     */


    @Override
    public boolean canCloseInventory() {
        return true;
    }

    @Override
    public void clickEvent(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        event.setCancelled(true);

        /*event.getWhoClicked().closeInventory();
        p.sendMessage(Messages.getInstance().getPlayersPlace.replace("{PLAYER}", p.getName()).replace("{PLACE}", Top.getPlacersPlace(CaneScore.getScore(p.getUniqueId())) + "")); */
    }

/**
 * Class created by David Out (Snapje), do not remove this from the class.
 * For any errors please contact: davidoutdevloper@gmail.com
 */



}
