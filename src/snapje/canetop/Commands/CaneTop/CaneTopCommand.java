package snapje.canetop.Commands.CaneTop;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import snapje.canetop.API.CaneScore;
import snapje.canetop.API.Command.Command;
import snapje.canetop.API.Command.SubCommand;
import snapje.canetop.API.Top;
import snapje.canetop.Data.Messages.Messages;
import snapje.canetop.Data.Settings;
import snapje.canetop.GUI.GUI_CaneTop;
import snapje.canetop.Utils.Chat;

import java.util.*;

public class CaneTopCommand extends Command {
    /**
     * Class created by David Out (Snapje), do not remove this from the class.
     * For any errors please contact: davidoutdevloper@gmail.com
     */
    @Override
    public String getCommandName() {
        return "canetop";
    }

    @Override
    public String getDescription() {
        return "The main command for the plugin.";
    }

    @Override
    public String getUsage() {
        return null;
    }

    @Override
    public List<String> getAliases() {
        ArrayList<String> s = new ArrayList<>();
        s.add("ct");
        return s;
    }

    @Override
    public List<SubCommand> getSubCommands() {
        ArrayList<SubCommand> commands = new ArrayList<>();
        commands.add(new ScoreSubCommand());
        commands.add(new SettingsSubCommand());
        commands.add(new TopSubCommand());
        return commands;
    }

    @Override
    public String[] getArguments() {
        return new String[0];
    }

    @Override
    public void sendHelp(CommandSender sender) {
        for(String s : Messages.getHelpCommand()) {
            sender.sendMessage(Chat.format(s));
        }
    }

    /*
  - "&9&m---------------------------------------------------"
  - "&b/canetop score get [player]: &fget a players canescore."
  - "&b/canetop score set [player]: &fset a players canescore."
  - "&b/canetop score add [player]: &fadd canes to someones canescore."
  - "&b/canetop score remove [player]: &fremove canes from someones canescore."
  - "&b/canetop score reset [player]: &freset a players canescore."
  - "&b/canetop settings: &fedit the plugin settings."
  - "&b/canetop info: &fget the plugin info."
  -  "&b/canetop top: &fget the canetop."
  - "&9&m---------------------------------------------------"
     */

    @Override
    public void onCommand(CommandSender sender, String[] args) {
            if(sender instanceof Player) {
                if(sender.hasPermission("canetop.manage")) {
                    sendHelp(sender);
                } else {
                    if(Settings.getInstance().caneTopGuiEnabled() == true) {
                        GUI_CaneTop gui = new GUI_CaneTop();
                        gui.openGUI((Player) sender);
                    } else {
                        Top.sendTop10(sender);
                    }
                }
            } else {
                sendHelp(sender);
            }
    }






/**
 * Class created by David Out (Snapje), do not remove this from the class.
 * For any errors please contact: davidoutdevloper@gmail.com
 */

}
