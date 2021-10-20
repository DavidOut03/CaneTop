package snapje.canetop.Commands.CaneTop;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import snapje.canetop.API.Command.SubCommand;
import snapje.canetop.API.Top;
import snapje.canetop.Data.Settings;
import snapje.canetop.GUI.GUI_CaneTop;

public class TopSubCommand extends SubCommand {
    /**
     * Class created by David Out (Snapje), do not remove this from the class.
     * For any errors please contact: davidoutdevloper@gmail.com
     */
    @Override
    public String getCommandName() {
        return "top";
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getUsage() {
        return null;
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public String[] getArguments() {
        return new String[0];
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if(sender instanceof Player) {
            if(Settings.getInstance().caneTopGuiEnabled() == true) {
                GUI_CaneTop gui = new GUI_CaneTop();
                gui.openGUI((Player) sender);
            } else {
                Top.sendTop10(sender);
            }
        } else {
            Top.sendTop10(sender);
        }
    }

/**
 * Class created by David Out (Snapje), do not remove this from the class.
 * For any errors please contact: davidoutdevloper@gmail.com
 */

}
