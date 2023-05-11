package snapje.canetop.API;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import snapje.canetop.Data.Messages.Messages;
import snapje.canetop.Utils.Chat;

import java.util.*;

public class CaneTop {

/**
 * Class created by David Out (Snapje), do not remove this from the class.
 * For any errors please contact: davidoutdevloper@gmail.com
 */


  private static CaneTop instance;
  public static CaneTop getInstance() {return instance;}

    private HashMap<Integer, CaneScore> caneTop = new HashMap<>();


    public CaneTop() {
      instance = this;
      updateTop();
    }

    public void updateTop() {
        HashMap<Integer, CaneScore> newCaneTop = new HashMap();
        this.caneTop = newCaneTop;
    }

    public boolean spotIsEmpty(HashMap<Integer, CaneScore> map, int place) {
        return (map.isEmpty() && map.get(place) != null)? false : true;
    }

    public boolean playerAlreadyInTop(HashMap<Integer, CaneScore> top, CaneScore score) {
        boolean is = false;

        for(int place : caneTop.keySet()) {
            if(!caneTop.get(place).equals(score)) continue;
               return true;
        }

        return is;
    }


    public CaneScore getScoreFromPlace(int place) {
        return caneTop.get(place);
    }


    public Integer getPlace(CaneScore score) {
        int currentPlace = 1;

        if(caneTop.isEmpty()) return currentPlace;
        for (int place : caneTop.keySet()) {
            if (!caneTop.get(place).equals(score)) continue;
            currentPlace = place;
        }

        return currentPlace;
    }

    public HashMap<Integer, CaneScore> getCaneTop() {
        return this.caneTop;
    }

    public void sendTopMessage(CommandSender sender, int starter, int max) {
        updateTop();

        for(String currentLine : Messages.getCaneTop()) {

            if(!currentLine.contains("{PLACE}") && !currentLine.contains("{PLAYER}") && !currentLine.contains("SCORE")) {
                sender.sendMessage(Chat.format(currentLine));
                continue;
            }

                for(int place : getCaneTop().keySet()) {
                    if(place < starter) continue;
                    if(place > max) break;
                    CaneScore score = caneTop.get(place);
                    if(score == null) continue;
                      sender.sendMessage(Chat.format(currentLine).replace("{PLACE}", place + "").replace("{PLAYER}", Bukkit.getOfflinePlayer(score.getPlayerUUID()).getName()).replace("{SCORE}", score.getScore() + ""));
                }
        }
    }


}
