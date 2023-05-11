package snapje.canetop.Data.Messages;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import snapje.canetop.Core.Main;
import snapje.canetop.Data.DataHandler;
import snapje.canetop.Utils.Chat;

import java.io.File;
import java.util.ArrayList;

public class Messages {
	
	 
	    
	    private static Messages instance = new Messages();
	    public static Messages getInstance() {return instance;}

	    private boolean loaded = false;
	    public String Prefix;
	    public String onEnable;
	    public String onDisable;
	    public String reloadedConfig;
	    
	    public String noPermission;
	    public String resetPlayerScore;
	    public String setPlayerScore;
	    public String addCanesToScore;
	    public String removeCanesFromScore;
		public String getYourScore;
		public String getPlayerScore;
		public String scoreCannotBeUnderZero;

		public String playerNotFound;
		public String getPlayersPlace;


		public String failedToRegisterCommand;
		public String failedToRegisterEvent;
		public String isNotANumber;

		public String setNewMessage;
		public String typeNewMessage;
		public String canceledMessageEditor;

		public String editGUITitle;
	    public String editGUIDisplayname;
	    public String editGUILore;
	    public String setGUITitle;
	    public String setGUIDisplayname;
	    public String cancelledGUIEditor;

	    public String addedNewLineToGUILore;
	    public String addLineToGuiLore;
	    public String setGUILore;




	   

	    public void loadMessages() {
	            this.loaded = true;
	            this.Prefix = Main.getInstance().getConfig().getString("Prefix");
	            
	            this.onEnable = MessagesAPI.getMessage("onEnable", "{PREFIX} &7Turned {PLUGIN-NAME} &aON&7.");
	            this.onDisable = MessagesAPI.getMessage("onDisable", "{PREFIX} &7Turned {PLUGIN-NAME} &cOFF&7.");
	            this.reloadedConfig = MessagesAPI.getMessage("reloadedConfig", "{PREFIX} &7Successfully reloaded the plugin settings.");

	            this.noPermission = MessagesAPI.getMessage("nopermission", "&cSorry, but you don't have permision for this.");
	            this.resetPlayerScore = MessagesAPI.getMessage("resetplayerscore", "{PREFIX} &fYou have reset &b{PLAYER}'s &fscore.");
	            this.setPlayerScore = MessagesAPI.getMessage("setplayerscore", "{PREFIX} &fYou have set &3{PLAYER}'s &fscore to &b{CANES}&f.");
	            this.addCanesToScore = MessagesAPI.getMessage("addcanestoscore", "{PREFIX} &fYou added &b{CANES} cane's &fto &3{PLAYER}'s &fscore.");
	            this.removeCanesFromScore = MessagesAPI.getMessage("removecanesfromscore", "{PREFIX} &fYou removed &b{CANES} cane's &ffrom &3{PLAYER}'s &fscore.");
	            this.getPlayerScore = MessagesAPI.getMessage("getyourscore", "{PREFIX} &fThe canescore from &3{PLAYER}&f is &b{SCORE}&f.");
	            this.getYourScore = MessagesAPI.getMessage("getplayerscore", "{PREFIX} &fYoure canescore is &b{SCORE}&f.");
	            this.getPlayersPlace = MessagesAPI.getMessage("getPlayersPlace", "{PREFIX} &f{PLAYER}'s position in the canetop is &b{PLACE}&f.");
	            this.scoreCannotBeUnderZero = MessagesAPI.getMessage("scoreCannotBeUnderZero", "&cCanescores cannot be less then zero.");

	            this.playerNotFound = MessagesAPI.getMessage("playerNotFound", "&c{Player} never joined the server.");

	            this.isNotANumber = MessagesAPI.getMessage("isNotANumber", "&c{NUMBER} is not a number.");
	            this.failedToRegisterCommand = MessagesAPI.getMessage("failedToRegisterCommand","&cFailed to register the {COMMAND} command, Exception: {EXCEPTION}");
	            this.failedToRegisterEvent = MessagesAPI.getMessage("failedToRegisterEvent","&cFailed to register the{EVENT} event, Exception: {EXCEPTION}");

	            this.typeNewMessage = MessagesAPI.getMessage("typeNewMessage", "&7Type in the chat the new message. Type -c to cancel.");
	            this.setNewMessage = MessagesAPI.getMessage("setNewMessage", "&7You set the new message to: '{MESSAGE}&7'.");
	            this.canceledMessageEditor = MessagesAPI.getMessage("canceledMessageEditor", "&cYou cancelled editing the message {MESSAGE}");

	            this.editGUITitle = MessagesAPI.getMessage("editGUITitle", "&7Type in the chat the gui title. Type -c to cancel.");
			    this.editGUIDisplayname = MessagesAPI.getMessage("editGUIDisplayname", "&7Type in the chat the gui item displayname. Type -c to cancel.");

			    this.setGUITitle = MessagesAPI.getMessage("setGUITitle", "&7You have successfully set the GUI title to '{TITLE}&7'.");
			    this.setGUIDisplayname = MessagesAPI.getMessage("setGUIDisplayname", "&7You have successfully set the GUI title to '{DISPLAYNAME}&7'.");

			    this.addedNewLineToGUILore = MessagesAPI.getMessage("addedNewLineToGUILore", "&7You have successfully added the line '{LINE}&7' to the gui item lore.");
			    this.addLineToGuiLore = MessagesAPI.getMessage("addLineToGuiLore", "&7Type in the chat a new line you want to add, or type -s to save the new lore.");
			    this.setGUILore = MessagesAPI.getMessage("setGUILore", "&7You have changed the gui lore.");
			    this.editGUILore = MessagesAPI.getMessage("editGUILore", "&7Type in the chat the gui lore. Type -c to cancel.");
			    this.cancelledGUIEditor = MessagesAPI.getMessage("cancelledGUIEditor", "&cYou successfully cancelled the editing.");

	            
	        	
	            
	           
	    }

	    public boolean messagesAreLoaded() {return loaded;}
	
	public static ArrayList<String> getHelpCommand() {
		ArrayList<String> lines = new ArrayList<String>();
			if(Main.getInstance().getConfig().getStringList("Command-Help") != null) {
				ArrayList<String> stringList = (ArrayList<String>) Main.getInstance().getConfig().getStringList("Command-Help");
				if(stringList != null && !stringList.isEmpty()) {
					for(String currentLine : stringList) {
						lines.add(Chat.format(currentLine));
					}
				} else {
					lines.add(Chat.format("&9&m---------------------------------------------------"));
					lines.add(Chat.format("&b/canescore get [player]: &fget a players canescore."));
					lines.add(Chat.format("&b/canescore set [player]: &fset a players canescore."));
					lines.add(Chat.format("&b/canescore add [player]: &fadd canes to someones canescore."));
					lines.add(Chat.format("&b/canescore remove [player]: &fremove canes from someones canescore."));
					lines.add(Chat.format("&b/canescore reset [player]: &freset a players canescore."));
					lines.add(Chat.format("&9&m---------------------------------------------------"));
					Bukkit.getConsoleSender().sendMessage("stringlist is empty");
				}
			} else {
				lines.add(Chat.format("&9&m---------------------------------------------------"));
				lines.add(Chat.format("&b/canescore get [player]: &fget a players canescore."));
				lines.add(Chat.format("&b/canescore set [player]: &fset a players canescore."));
				lines.add(Chat.format("&b/canescore add [player]: &fadd canes to someones canescore."));
				lines.add(Chat.format("&b/canescore remove [player]: &fremove canes from someones canescore."));
				lines.add(Chat.format("&b/canescore reset [player]: &freset a players canescore."));
				lines.add(Chat.format("&9&m---------------------------------------------------"));
				Bukkit.getConsoleSender().sendMessage("stringlist is null");
			}
		return lines;
	}

	public static ArrayList<String> getCaneTop() {
		ArrayList<String> lines = new ArrayList<String>();
		
			if(Main.getInstance().getConfig().getStringList("CaneTop.Message") != null) {
				ArrayList<String> stringList = (ArrayList<String>) Main.getInstance().getConfig().getStringList("CaneTop");
				if(stringList != null && !stringList.isEmpty()) {
					for(String currentLine : stringList) {
						lines.add(Chat.format(currentLine));
					}
				} else {
					lines.add(Chat.format("&9&m---------------------------------------------------"));
					lines.add(Chat.format("&b&lCaneTop"));
					lines.add(Chat.format("&3{PLACE}) &b {PLAYER}&f: {SCORE} canes."));
					lines.add(Chat.format("&9&m---------------------------------------------------"));
				}
			} else {
				lines.add(Chat.format("&9&m---------------------------------------------------"));
				lines.add(Chat.format("&b&lCaneTop"));
				lines.add(Chat.format("&3{PLACE}) &b {PLAYER}&f: {SCORE} canes."));
				lines.add(Chat.format("&9&m---------------------------------------------------"));
			}
		
		return lines;
	}
	

}
