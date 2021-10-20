package snapje.canetop.GUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import snapje.canetop.API.GUIAPI.GUI;
import snapje.canetop.Core.Main;
import snapje.canetop.Utils.Chat;

import java.util.ArrayList;

public class GUIManager implements Listener {

/**
 * Class created by David Out (Snapje), do not remove this from the class.
 * For any errors please contact: davidoutdevloper@gmail.com
 */

  private static GUIManager instance;
  public static GUIManager getInstance() { return instance;}

  public ArrayList<GUI> guis = new ArrayList<>();

  public GUIManager() {
      instance = this;
      guis.add(new GUI_Messages());
      guis.add(new GUI_CaneTop());
      guis.add(new GUI_Settings());
      guis.add(new GUI_CaneTopGUIEditor());
  }

  @EventHandler
    public void onClick(InventoryClickEvent e) {
      if(e.getClickedInventory() == null) return;
      if(e.getClickedInventory() == e.getWhoClicked().getInventory()) return;
      if(!(e.getWhoClicked() instanceof Player)) return;
      if(e.getCurrentItem() == null || e.getCurrentItem().getType().equals(Material.AIR)) return;

      for(GUI gui : guis) {
          if(Chat.format(gui.getTitle()).equals(e.getView().getTitle())) {
              gui.clickEvent(e);
          }
      }
  }

  @EventHandler
    public void onClose(InventoryCloseEvent e) {
      if(e.getView() == null) return;
      if(e.getView().getTitle() == null || e.getView().getTitle().isEmpty()) return;

      for(GUI gui : guis) {
          if(Chat.format(gui.getTitle()).equalsIgnoreCase(e.getView().getTitle())) {
              if(gui.canCloseInventory() == false) {
                  Bukkit.getServer().getScheduler().runTaskLater(Main.getPlugin(Main.class),new Runnable() {

                      @Override
                      public void run() {
                          gui.openGUI((Player) e.getPlayer());
                      }
                  },10);
              }
          }
      }
  }


}
