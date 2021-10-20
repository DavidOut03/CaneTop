package snapje.canetop.API;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import snapje.canetop.Data.Messages.Messages;
import snapje.canetop.Utils.Chat;
import snapje.canetop.Utils.Check;

import java.util.*;

public class Top {

/**
 * Class created by David Out (Snapje), do not remove this from the class.
 * For any errors please contact: davidoutdevloper@gmail.com
 * @return
 */

  public static void sendTop10(CommandSender sender) {
    HashMap<Integer,String> top10 = Top.getTop(10);

    int currentPosition = 1;
    for(String currentLine : Messages.getCaneTop()) {
        if(currentLine.contains("{PLACE}") || currentLine.contains("{PLAYER}") || currentLine.contains("SCORE")) {
            String id = top10.get(currentPosition);
            UUID uuid = UUID.fromString(id);
            sender.sendMessage(Chat.format(currentLine).replace("{PLACE}", currentPosition + "").replace("{PLAYER}", Bukkit.getOfflinePlayer(UUID.fromString(top10.get(currentPosition))).getName()).replace("{SCORE}", CaneScore.getScore(uuid).getScore() + ""));
            currentPosition++;
        } else {
            sender.sendMessage(Chat.format(currentLine));
        }
    }
  }


  public static HashMap<Integer, String> getTop(int top) {
      HashMap<Integer, String> returned = new HashMap();

      HashMap<String, Integer> scores = getScores();
      Map<String, Integer> map = sortByValue(scores);

     int current = map.size();
      for(Map.Entry<String, Integer> me : map.entrySet()) {
          if(current <= top) {
              returned.put(current, me.getKey());
          }
          current--;
      }

      return returned;
  }

  public static int getPlacersPlace(CaneScore score) {
      int returned = 0;

      for(Map.Entry<Integer, String> top : getTop(getScores().size()).entrySet()) {
          if(top.getValue().equalsIgnoreCase(score.getPlayerUUID().toString())) {
              return top.getKey();
          }
      }

      return returned;
  }

  private static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
    // creating list from elements of HashMap
    List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());
    // sorting list
    Collections.sort(list, new Comparator<Map.Entry<String, Integer>>()
    {
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
        {
            return (o1.getValue()).compareTo(o2.getValue());
        }
    });
    HashMap<String, Integer> ha = new LinkedHashMap<String, Integer>();
    for(Map.Entry<String, Integer> me : list)
    {
        ha.put(me.getKey(), me.getValue());
    }
    return ha;
}

    private static HashMap<String, Integer> getScores() {
        HashMap<String, Integer> score = new HashMap<>();

        for(String s : CaneScore.getScores()) {
            score.put(s, CaneScore.getScore(UUID.fromString(s)).getScore());
        }

        return score;
    }



}
