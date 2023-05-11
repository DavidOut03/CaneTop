package snapje.canetop.Events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import snapje.canetop.API.CaneScore;

public class JoinAndQuit implements Listener {

/**
 * Class created by David Out (Snapje), do not remove this from the class.
 * For any errors please contact: davidoutdevloper@gmail.com
 */

  @EventHandler
    public void onJoin(PlayerJoinEvent e) {
      if(CaneScore.getScore(e.getPlayer().getUniqueId()) == null) {
          CaneScore score = new CaneScore(e.getPlayer().getUniqueId(), 0);
          score.reset();
      }
  }

  @EventHandler
    public void onQuit(PlayerQuitEvent e) {
      if(CaneScore.getScore(e.getPlayer().getUniqueId()) != null) {
          CaneScore score = CaneScore.getScore(e.getPlayer().getUniqueId());
          score.saveToConfig();
      } else {
          CaneScore score = new CaneScore(e.getPlayer().getUniqueId(), 0);
          score.saveToConfig();
      }
  }

}
