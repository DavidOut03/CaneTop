package snapje.canetop.GUI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import snapje.canetop.API.GUIAPI.GUI;
import snapje.canetop.API.GUIAPI.InventoryItem;
import snapje.canetop.Data.Messages.Messages;
import snapje.canetop.Data.Messages.MessagesAPI;
import snapje.canetop.Utils.Chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GUI_Messages extends GUI implements Listener {

    private static HashMap<Player, String> messageEditor = new HashMap<>();

    /**
     * Class created by David Out (Snapje), do not remove this from the class.
     * For any errors please contact: davidoutdevloper@gmail.com
     */

    @Override
    public int getSize() {
        return 54;
    }

    @Override
    public String getTitle() {
        return "&dMessage Editor";
    }

    @Override
    public boolean canCloseInventory() {
        return true;
    }

    @Override
    public void setUpInventory() {
        if(Material.matchMaterial("STAINED_GLASS_PANE") != null) {
            InventoryItem.fillGUIWithItem(this, 0, 8, InventoryItem.createItemStack(Material.STAINED_GLASS_PANE, 7, " "));
            InventoryItem.fillGUIWithItem(this, 45, 54, InventoryItem.createItemStack(Material.STAINED_GLASS_PANE, 7, " "));
        } else {
            InventoryItem.fillGUIWithItem(this, 0, 8, InventoryItem.createItemStack(Material.matchMaterial("GRAY_STAINED_GLASS_PANE"), " "));
            InventoryItem.fillGUIWithItem(this, 45, 54, InventoryItem.createItemStack(Material.matchMaterial("GRAY_STAINED_GLASS_PANE")," "));
        }


        if(MessagesAPI.getMessages().isEmpty()) return;
        for(String key : MessagesAPI.getMessages().keySet()) {
            List<String> lore = new ArrayList<>();
            lore.add( MessagesAPI.getMessages().get(key) );
            add(InventoryItem.createItemStack(Material.PAPER, "&b&l" + key, lore));
        }
    }



    @Override
    public void clickEvent(InventoryClickEvent event) {
        event.setCancelled(true);
        if(event.getCurrentItem().getType() != Material.PAPER) return;
        Player player = (Player) event.getWhoClicked();

        String message = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
        messageEditor.put(player, message);
        player.sendMessage(Messages.getInstance().typeNewMessage);

        player.closeInventory();
    }

    @EventHandler
    public void onType(AsyncPlayerChatEvent e) {
        if(messageEditor.containsKey(e.getPlayer())) {
            if(e.getMessage().equalsIgnoreCase("-c")) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(Messages.getInstance().canceledMessageEditor.replace("{MESSAGE}", messageEditor.get(e.getPlayer())));
                messageEditor.remove(e.getPlayer());
            } else {
                e.setCancelled(true);
                MessagesAPI.setMessage(messageEditor.get(e.getPlayer()), e.getMessage());
                e.getPlayer().sendMessage(Messages.getInstance().setNewMessage.replace("{MESSAGE}", Chat.format(e.getMessage())));
                messageEditor.remove(e.getPlayer());
                openAndUpdateInventory(e.getPlayer());
            }
        }
    }
}
