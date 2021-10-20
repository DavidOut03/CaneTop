package snapje.canetop.Utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import snapje.canetop.Data.Messages.MessagesAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Chat {

/**
 * Class created by David Out (Snapje), do not remove this from the class.
 * For any errors please contact: davidoutdevloper@gmail.com
 */

  public static String format(String s) {
    return ChatColor.translateAlternateColorCodes('&', s).replace("{PREFIX}", MessagesAPI.getPrefix());
  }

  public static List<String> formatLore(List<String> lore) {
        List<String> returnedLore = new ArrayList<>();

        for(String currentLine : lore) {
            returnedLore.add(format(currentLine));
        }

        return returnedLore;
    }

  public static List<String> replaceInLore(List<String> lore, String s1, String s2) {
      List<String> returnedLore = new ArrayList<>();

      for(String currentLine : lore) {
          if(currentLine.contains(s1)) {
              returnedLore.add(currentLine.replace(s1, s2));
          } else {
              returnedLore.add(currentLine);
          }
      }

      return returnedLore;
  }

  public static String getNameFromUUID(String s) {
      if(Check.isUUID(s) == false) return null;
      UUID uuid = UUID.fromString(s);
      return Bukkit.getOfflinePlayer(uuid).getName();
  }

}
