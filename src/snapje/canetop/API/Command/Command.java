package snapje.canetop.API.Command;

import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class Command {

/**
 * Class created by David Out (Snapje), do not remove this from the class.
 * For any errors please contact: davidoutdevloper@gmail.com
 */

  public abstract String getCommandName();
  public abstract String getDescription();
  public abstract String getUsage();
  public abstract List<String> getAliases();
  public abstract List<SubCommand> getSubCommands();
  public abstract String[] getArguments();

  public abstract void sendHelp(CommandSender sender);
  public abstract void onCommand(CommandSender sender, String[] args);


}
