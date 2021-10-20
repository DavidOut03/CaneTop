package snapje.canetop.API.Command;

import org.bukkit.command.CommandSender;

public abstract  class SubCommand {

/**
 * Class created by David Out (Snapje), do not remove this from the class.
 * For any errors please contact: davidoutdevloper@gmail.com
 */

        public abstract String getCommandName();
        public abstract String getDescription();
        public abstract String getUsage();
        public abstract String[] getAliases();
        public abstract String[] getArguments();

        public abstract void onCommand(CommandSender sender, String[] args);



}
