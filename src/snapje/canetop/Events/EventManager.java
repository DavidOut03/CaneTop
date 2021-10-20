package snapje.canetop.Events;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import snapje.canetop.API.Command.Command;
import snapje.canetop.API.Command.CommandManager;
import snapje.canetop.Commands.CaneTop.CaneTopCommand;
import snapje.canetop.Core.Main;
import snapje.canetop.Data.Messages.Messages;
import snapje.canetop.Events.CustomEvents.BreakCaneEvent;
import snapje.canetop.Events.Listeners.BreakCaneListener;
import snapje.canetop.Events.Listeners.TestListener;

import java.util.ArrayList;
import java.util.List;

public class EventManager {

/**
 * Class created by David Out (Snapje), do not remove this from the class.
 * For any errors please contact: davidoutdevloper@gmail.com
 */


    private static EventManager instance;
    public static EventManager getInstance() {return instance;}

    private Plugin plugin;

    private List<Listener> events = new ArrayList<>();

    public EventManager(Plugin main) {

        events.add(new JoinAndQuit());
        events.add(new BreakCaneListener());
        events.add(new TestListener());

        plugin = main;
        instance = this;
    }

    public void setUp() {
        if(!events.isEmpty()) {
            for(Listener event : events) {
                PluginManager pm = Bukkit.getPluginManager();
                    try {
                       pm.registerEvents(event, plugin);
                    } catch (Exception ex) {
                        Bukkit.getConsoleSender().sendMessage(Messages.getInstance().failedToRegisterEvent.replace("{EVENT}", event.getClass().getName()).replace("{EXCEPTION}", ex.toString()) );
                    }

            }
        }

    }

}
