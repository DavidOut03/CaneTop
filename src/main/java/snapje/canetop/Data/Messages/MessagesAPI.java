package snapje.canetop.Data.Messages;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import snapje.canetop.Core.Main;
import snapje.canetop.Data.DataHandler;
import snapje.canetop.Utils.Chat;

import java.util.HashMap;

public class MessagesAPI {
	
	
	 public static String getMessage(String name, String message) {
	      String returned = Chat.format(message);

	      if(DataHandler.getInstance().getMessagesConfig() != null) {
	          YamlConfiguration yaml = YamlConfiguration.loadConfiguration(DataHandler.getInstance().getMessagesFile());
	          if(yaml.getConfigurationSection("Message") != null) {
	              if(yaml.getString("Message." + name) != null) {
	                  returned = Chat.format(DataHandler.getInstance().getMessagesConfig().getString("Message." + name));
	              } else {
	                  if(yaml.getString("Message." + name) == null) {
	                     setMessage(name, message);
	                  } else {
	                      Main.getInstance().getLogger().warning("message exists.");
	                  }
	              }
	          }
	      } else {
	          Main.getInstance().getLogger().warning("file does not exist.");
	      }
	     return returned.replace("%prefix%", getPrefix()).replace("{PREFIX}", getPrefix());
	  }

	  public static HashMap<String, String> getMessages() {
	      HashMap<String, String> messages = new HashMap<>();

	      if(DataHandler.getInstance().getMessagesConfig() != null) {
	          YamlConfiguration yaml = YamlConfiguration.loadConfiguration(DataHandler.getInstance().getMessagesFile());
	          if(yaml.getConfigurationSection("Message") != null) {
	              for(String s : yaml.getConfigurationSection("Message").getKeys(false)) {
	                  String key = s;
	                  String message = yaml.getString("Message." + s);
	                  messages.put(key, getMessage(s, message));
	              }
	          }
	      }
	      return messages;
	  }

	  public static void setMessage(String messageName, String newMessage) {
	      if(DataHandler.getInstance().getMessagesConfig() != null) {
	          YamlConfiguration yaml = YamlConfiguration.loadConfiguration(DataHandler.getInstance().getMessagesFile());
	                      try {
	                          yaml.set("Message." + messageName, newMessage);
	                          yaml.save(DataHandler.getInstance().getMessagesFile());
	                      } catch (Exception ex) {
	                          ex.printStackTrace();
	                      }
	      } else {
	          Main.getInstance().getLogger().warning("file does not exist.");
	      }
	  }

	
	  
	  public static String getPrefix() {
		  String prefix = ChatColor.translateAlternateColorCodes('&', "&b&lCaneTop");
		  
		  if(Main.getInstance().getConfig().getString("Prefix") != null) {
			  prefix = ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("Prefix"));
		  }
		  
		  
		  return prefix;
	  }

}
