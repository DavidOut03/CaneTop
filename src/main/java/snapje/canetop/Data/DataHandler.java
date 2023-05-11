package snapje.canetop.Data;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import snapje.canetop.API.CaneScore;
import snapje.canetop.Core.Main;
import snapje.canetop.Utils.Chat;
import snapje.canetop.Utils.Check;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class DataHandler {

/**
 * Class created by David Out (Snapje), do not remove this from the class.
 * For any errors please contact: davidoutdevloper@gmail.com
 */

    private static DataHandler instance;
    public static DataHandler getInstance() {return instance;}

    private File configFile;
    private FileConfiguration config;
    public FileConfiguration getConfig() {return config;}


    private File messagesFile;
    private FileConfiguration messagesConfig;
    public FileConfiguration getMessagesConfig() {return messagesConfig;}
    public File getMessagesFile() {return messagesFile;}

    private File playerData;
    private FileConfiguration playerDataConfig;
    public FileConfiguration getPlayerDataConfig() {return playerDataConfig;}
    public File getPlayerDataFile() {return playerData;}

    public DataHandler() {
        createFiles();
        loadFiles();

        instance = this;
    }

    public void createFiles() {
        if(!new File(Main.getInstance().getDataFolder(), "config.yml").exists()) {
            Main.getInstance().getConfig().options().copyDefaults(true);
            Main.getInstance().getConfig().options().copyHeader(true);
            Main.getInstance().saveDefaultConfig();
        }



        if(!new File(Main.getInstance().getDataFolder(), "messages.yml").exists()) {
            File mfile = new File(Main.getInstance().getDataFolder(), "messages.yml");
            YamlConfiguration myaml = YamlConfiguration.loadConfiguration(mfile);
            try {
               mfile.createNewFile();
               myaml.createSection("Message");
               myaml.save(mfile);
            } catch (Exception ex) {
                Bukkit.getConsoleSender().sendMessage(Chat.format("&cCould not create file, Exception: " + ex));
            }
        }

        if(!new File(Main.getPlugin(Main.class).getDataFolder() + "//database//playerdata").exists()) {
            File folder = new File(Main.getPlugin(Main.class).getDataFolder() + "//database");
            if(!folder.exists()) {
                folder.mkdir();

            }
            File pfile = new File(Main.getPlugin(Main.class).getDataFolder() + "//database//playerdata");
            YamlConfiguration pyaml = YamlConfiguration.loadConfiguration(pfile);
            try {
                pfile.createNewFile();
                pyaml.createSection("Player");
                pyaml.save(pfile);
            } catch (Exception ex) {
                Bukkit.getConsoleSender().sendMessage(Chat.format("&cCould not create file, Exception: " + ex));
            }
        }

        playerData = new File(Main.getPlugin(Main.class).getDataFolder() + "//database//playerdata");
        messagesFile = new File(Main.getInstance().getDataFolder(), "messages.yml");
        config = Main.getInstance().getConfig();
    }
    public void loadFiles() {
        playerDataConfig = YamlConfiguration.loadConfiguration(playerData);
        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
    }



    public void reloadConfig() {
        new DataHandler();
    }

    public void savePlayerData() {
        if(new File(Main.getPlugin(Main.class).getDataFolder() + "//database//playerdata").exists()) return;
        if(CaneScore.getScores() == null) return;
        for(String key : CaneScore.getScores()) {
            if(UUID.fromString(key) != null) {
                UUID uuid = UUID.fromString(key);
                CaneScore score = CaneScore.getScore(uuid);
                if(score != null) {
                    score.saveToConfig();
                }
            }
        }
    }

    public void loadPlayerData() {
        if(playerData.exists() && playerDataConfig.getConfigurationSection("Player") == null) return;
            for(String key : playerDataConfig.getConfigurationSection("Player").getKeys(false)) {
                UUID uuid = UUID.fromString(key);
                if(uuid == null) return;
                    String s = playerDataConfig.getString("Player." + key + ".score");
                    if(s == null && !Check.isInteger(s)) return;
                        int points = Integer.parseInt(s);
                        CaneScore score = new CaneScore(uuid, points);
            }

    }
}
