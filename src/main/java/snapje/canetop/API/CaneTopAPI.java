package snapje.canetop.API;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import snapje.canetop.Core.Main;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class CaneTopAPI {
	
	public static HashMap<String, Integer> caneScore = new HashMap<String, Integer>();
	public static HashMap<Integer, String> caneTop = new HashMap<>();

	public Integer getScore(UUID uuid) {
		return (caneScore.containsKey(uuid.toString()) ? caneScore.get(uuid.toString()) : 0);
	}
	public void setScore(UUID uuid, int score) {caneScore.put(uuid.toString(), score);}

	public void add(UUID uuid, int score) {
		if(!caneScore.containsKey(uuid.toString())) {
			caneScore.put(uuid.toString(), score);
			return;
		}
			int newScore = caneScore.get(uuid.toString()) + score;
			caneScore.put(uuid.toString(), newScore);
	}

	public void remove(UUID uuid, int score) {
		if(!caneScore.containsKey(uuid.toString()) || score > caneScore.get(uuid.toString())) {
			caneScore.put(uuid.toString(), 0);
			return;
		}
			int newScore = caneScore.get(uuid.toString()) - score;
			caneScore.put(uuid.toString(), newScore);
	}

	public void reset(UUID uuid) {
		caneScore.put(uuid.toString(), 0);
	}
	
	public static Player getNumberOne() {
		int highestvalue = 0;
		Player numberone = null;

		for(String uuid : caneScore.keySet()) {
			if(caneScore.get(uuid) <= highestvalue) continue;
				highestvalue = caneScore.get(uuid);
				Player winner = Bukkit.getPlayer(uuid);
				numberone = winner;
		}

		return numberone;
	}

	public static HashMap<Integer, UUID> getTop() {
		HashMap<Integer, UUID> caneTop = new HashMap<Integer, UUID>();

		for(String uuid : caneScore.keySet()) {
			for(int place = 1; place < caneScore.size(); place++) {
				if(caneScore.get(caneTop.get(place)) == null) {
					caneTop.put(place, UUID.fromString(uuid));
					break;
				}

				if(caneScore.get(caneTop.get(place)) > caneScore.get(UUID.fromString(uuid))) continue;
					caneTop.put(place, UUID.fromString(uuid));
					break;
			}
		}
		return caneTop;
	}

	public static HashMap<Integer, UUID> getTop10() {
		HashMap<Integer, UUID> caneTop = new HashMap<Integer, UUID>();
		int one = 0;
		int two = 0;
		int three = 0;
		int four = 0;
		int five = 0;
		int six = 0;
		int seven = 0;
		int eight = 0;
		int nine = 0;
		int ten = 0;
		
		for(String d : caneScore.keySet()) {
			UUID uuid = UUID.fromString(d);
			if(caneScore.get(uuid.toString()) >= one) {
				one = caneScore.get(uuid.toString());
				caneTop.put(1, uuid);
			} else if(caneScore.get(uuid.toString()) >= two) {
				two = caneScore.get(uuid.toString());
				caneTop.put(2, uuid);
			} else if(caneScore.get(uuid.toString()) >= three) {
				three = caneScore.get(uuid.toString());
				caneTop.put(3, uuid);
			} else if(caneScore.get(uuid.toString()) >= four) {
				four = caneScore.get(uuid.toString());
				caneTop.put(4, uuid);
			} else if(caneScore.get(uuid.toString()) >= five) {
				five = caneScore.get(uuid.toString());
				caneTop.put(5, uuid);
			} else if(caneScore.get(uuid.toString()) >= six) {
				six = caneScore.get(uuid.toString());
				caneTop.put(6, uuid);
			} else if(caneScore.get(uuid.toString()) >= seven) {
				seven = caneScore.get(uuid.toString());
				caneTop.put(7, uuid);
			} else if(caneScore.get(uuid.toString()) >= eight) {
				eight = caneScore.get(uuid.toString());
				caneTop.put(8, uuid);
			} else if(caneScore.get(uuid.toString()) >= nine) { 
				nine = caneScore.get(uuid.toString());
				caneTop.put(9, uuid);
			} else if(caneScore.get(uuid.toString()) >= ten) {
				ten = caneScore.get(uuid.toString());
				caneTop.put(10, uuid);
			}
		}
		return caneTop;
	}
	
	public static Integer getPlace(UUID uuid) {
		HashMap<Integer, UUID> top = new HashMap<Integer, UUID>();
		HashMap<UUID, Integer> d = new HashMap<UUID, Integer>();
		
		for(int place = 1; place < caneScore.size(); place++) {
			UUID holder = top.get(place);
			for(String currentuuid : caneScore.keySet()) {
				if(caneScore.get(uuid.toString()) < caneScore.get(holder.toString())) continue;
				UUID cu = UUID.fromString(currentuuid);
				top.put(place, cu);
			}
		}
		
		for(Integer current : top.keySet()) {
			d.put(top.get(current), current);
		}
		return d.get(uuid);
	}
	
	public static File playerdata = new File(Main.getPlugin(Main.class).getDataFolder() + "//database//playerdata");
	public static YamlConfiguration yaml = YamlConfiguration.loadConfiguration(playerdata);
	
	public static void saveToDatabase(UUID uuid) {
		if(caneScore.get(uuid.toString()) == null) return;
		try {
			
			OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
			yaml.set("Player." + uuid + ".playername", player.getName());
			yaml.set("Player." + uuid + ".caneScore", caneScore.get(uuid.toString()));
			
		yaml.save(playerdata);
		caneScore.remove(uuid.toString());
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
	public static void getFromDatabase(UUID uuid) {
			if(uuid != null) {
				int score = yaml.getInt("Player." + uuid.toString() + ".caneScore");
				if(isInteger(yaml.getString("Player." + uuid.toString() + ".caneScore")) == true) {
				caneScore.put(uuid.toString(), score);
					yaml.set("Player." + uuid, null);
				
				}
			}
	try {
		yaml.save(playerdata);
	} catch (IOException ex) {
		ex.printStackTrace();
	
	}
	}
	
	public static HashMap<String, Integer> getAllScores() {
		HashMap<String, Integer> CaneScore = caneScore;
		
		if(!playerdata.exists() || yaml.getConfigurationSection("Player") == null) return CaneScore;
				for(String uuid : yaml.getConfigurationSection("Player").getKeys(false)) {
					String score = yaml.getString("Player." + uuid + ".caneScore");
					if(!isInteger(score) ) continue;
					int finalScore = Integer.parseInt(score);
					CaneScore.put(UUID.fromString(uuid).toString(), finalScore);
				}
		return CaneScore;
	}
	
	private static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}
	
	
	
}
