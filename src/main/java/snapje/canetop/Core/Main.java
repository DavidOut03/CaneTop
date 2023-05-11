package snapje.canetop.Core;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import snapje.canetop.API.CaneTop;
import snapje.canetop.API.Command.CommandManager;
import snapje.canetop.API.GUIAPI.GUI;
import snapje.canetop.Data.DataHandler;
import snapje.canetop.Data.Messages.Messages;
import snapje.canetop.Data.Settings;
import snapje.canetop.Events.EventManager;
import snapje.canetop.GUI.GUIManager;
import snapje.canetop.GUI.GUI_CaneTopGUIEditor;
import snapje.canetop.GUI.GUI_Messages;

public class Main extends JavaPlugin {

/**
 * Class created by David Out (Snapje), do not remove this from the class.
 * For any errors please contact: davidoutdevloper@gmail.com
 */

  private static DataHandler handler;
  private static CaneTop top;
  private static Main instance;
  private static EventManager eventManager;
  private static Settings settings;
  public static Main getInstance() {return instance;}

    @Override
    public void onEnable() {
    getInstances();

    DataHandler.getInstance().loadPlayerData();
    Messages.getInstance().loadMessages();

    loadCommands();
    loadEvents();
    registerGUIS();

      Bukkit.getConsoleSender().sendMessage(Messages.getInstance().onEnable.replace("{PLUGIN-NAME}", getName()));
  }

  @Override
    public void onDisable() {
    DataHandler.getInstance().savePlayerData();
    Bukkit.getConsoleSender().sendMessage(Messages.getInstance().onDisable.replace("{PLUGIN-NAME}", getName()));
  }

  public void loadCommands() {
    CommandManager.getInstance().setUp();
  }
  public void loadEvents() {
    EventManager.getInstance().setUp();
  }

  private void getInstances() {
    instance = this;

    handler = new DataHandler();
    eventManager = new EventManager(this);
    top = new CaneTop();
    settings = new Settings();
  }

  public void registerGUIS() {
   Bukkit.getPluginManager().registerEvents(new GUIManager(), this);
   Bukkit.getPluginManager().registerEvents(new GUI_Messages(), this);
    Bukkit.getPluginManager().registerEvents(new GUI_CaneTopGUIEditor(), this);
  }

  public void update() {
    handler = new DataHandler();
    top = new CaneTop();
    settings = new Settings();
    Messages.getInstance().loadMessages();
  }





}
