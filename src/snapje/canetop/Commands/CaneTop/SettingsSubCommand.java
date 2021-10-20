package snapje.canetop.Commands.CaneTop;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import snapje.canetop.API.Command.SubCommand;
import snapje.canetop.Core.Main;
import snapje.canetop.Data.Messages.Messages;
import snapje.canetop.GUI.GUI_Settings;
import snapje.canetop.Utils.Chat;

public class SettingsSubCommand extends SubCommand {
    /**
     * Class created by David Out (Snapje), do not remove this from the class.
     * For any errors please contact: davidoutdevloper@gmail.com
     */
    @Override
    public String getCommandName() {
        return "settings";
    }

    @Override
    public String getDescription() {
        return "A command to edit the settings.";
    }

    @Override
    public String getUsage() {
        return "null";
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public String[] getArguments() {
        return new String[0];
    }

    /*
    	canetop settings reload
	    canetop settings edit
     */

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if(args.length == 1) {
            sendHelp(sender);
        } else if(args.length == 2) {
            if(args[1].equalsIgnoreCase("reload")) {
                Main.getInstance().update();
                sender.sendMessage(Messages.getInstance().reloadedConfig);
            } else if(args[1].equalsIgnoreCase("edit") && sender instanceof Player) {
                GUI_Settings gui = new GUI_Settings();
                gui.openGUI((Player) sender);
            }
        }
    }

    public void sendHelp(CommandSender sender) {
        for(String currentline : Messages.getHelpCommand()) {
            sender.sendMessage(Chat.format(currentline));
        }
    }


}
