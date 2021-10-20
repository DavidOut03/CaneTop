package snapje.canetop.Events.CustomEvents;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import snapje.canetop.API.CaneScore;

public class BreakCaneEvent extends Event {

/**
 * Class created by David Out (Snapje), do not remove this from the class.
 * For any errors please contact: davidoutdevloper@gmail.com
 */

private static final HandlerList handlers = new HandlerList();
    public HandlerList getHandlers() {return handlers;}
    public static HandlerList getHandlerList() {return handlers;}

    private Player player;
    private CaneScore score;
    private int amount;

    public Player getPlayer() {return this.player;}
    public CaneScore getScore() {return this.score;}
    public int getAmountToAdd() {return this.amount;}

    public BreakCaneEvent(Player player, int amount) {
            this.player = player;
        if(CaneScore.getScore(player.getUniqueId()) != null) {
            this.score = CaneScore.getScore(player.getUniqueId());
        } else {
            this.score = new CaneScore(player.getUniqueId(), 0);
        }
            this.amount = amount;

    }
}
