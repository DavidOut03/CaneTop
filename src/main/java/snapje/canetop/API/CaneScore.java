package snapje.canetop.API;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import snapje.canetop.Data.DataHandler;
import snapje.canetop.Utils.Chat;
import snapje.canetop.Utils.Check;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class CaneScore {

/**
 * Class created by David Out (Snapje), do not remove this from the class.
 * For any errors please contact: davidoutdevloper@gmail.com
 */

  private static HashMap<String, CaneScore> scores = new HashMap<>();
  private String playerName;
  private String uuid;
  private int brokenCanes;
  private int place;

  public CaneScore(UUID uuid, int score) {
      if(uuid == null) return;

      if (!scores.containsKey(uuid.toString())) {
          this.playerName = Bukkit.getOfflinePlayer(uuid).getName();
          this.uuid = uuid.toString();
          this.brokenCanes = score;
          scores.put(uuid.toString(), this);
          return;
      }
            this.playerName = Bukkit.getOfflinePlayer(uuid).getName();
            this.uuid = uuid.toString();
            this.brokenCanes = scores.get(uuid.toString()).getScore();
            scores.put(uuid.toString(), this);

  }

  public String getPlayerName() {return this.playerName;}
  public UUID getPlayerUUID() {return UUID.fromString(this.uuid);}
  public int getScore() {return  this.brokenCanes;}

  public void set(int amount) {brokenCanes = amount;}
  public void add(int amount) {brokenCanes = (brokenCanes + amount);}
  public void remove(int amount) {brokenCanes = (brokenCanes - amount);}
  public void reset() {brokenCanes = 0;}

  public void deleteData() {
      scores.remove(uuid);
  }

  public boolean saveToConfig() {
      if(!DataHandler.getInstance().getPlayerDataFile().exists()) return false;
          YamlConfiguration config = YamlConfiguration.loadConfiguration(DataHandler.getInstance().getPlayerDataFile());
          try {
              config.set("Player." + uuid + ".playerName", playerName);
              config.set("Player." + uuid + ".score", brokenCanes);
              config.save(DataHandler.getInstance().getPlayerDataFile());
              return true;
          } catch (Exception ex) {
              Bukkit.getConsoleSender().sendMessage(Chat.format("&cCould not save file, Exception: " + ex));
              return false;
      }
  }

    /*
     static methods
     */

    public static CaneScore getScore(UUID uuid) {
        if(scores.get(uuid.toString()) == null) return new CaneScore(uuid, 0);
        return scores.get(uuid.toString());
    }

    public static CaneScore loadScoreFromConfig(UUID uuid) {
        CaneScore score = new CaneScore(uuid, 0);
        YamlConfiguration config = YamlConfiguration.loadConfiguration(DataHandler.getInstance().getPlayerDataFile());
        String s = config.getString("Player." + uuid.toString() + ".score");

        if(!DataHandler.getInstance().getPlayerDataFile().exists() || !config.getConfigurationSection("Player").contains(uuid.toString())) return score;
        if(s == null || !Check.isInteger(s)) return score;
            int canes = Integer.parseInt(s);
            score.set(canes);
        return score;
    }

    public static Set<String> getScores() {
        return scores.keySet();
    }

}
