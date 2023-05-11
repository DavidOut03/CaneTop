package snapje.canetop.API.Command;


import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import snapje.canetop.Commands.CaneTop.CaneTopCommand;
import snapje.canetop.Core.Main;
import snapje.canetop.Data.Messages.Messages;
import snapje.canetop.Utils.Chat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CommandManager implements CommandExecutor {

    /**
     * Class created by David Out (Snapje), do not remove this from the class.
     * For any errors please contact: davidoutdevloper@gmail.com
     */

    private static CommandManager instance = new CommandManager();
    public static CommandManager getInstance() {return instance;}

    private Main plugin = Main.getPlugin(Main.class);

    private List<Command> commands = new ArrayList<Command>();

    public CommandManager() {
        commands.add(new CaneTopCommand());
    }

    public void setUp() {
        if(commands.isEmpty()) return;

            for(Command cmd : commands) {
                if(cmd.getCommandName() == null) continue;

                    try {
                        plugin.getCommand(cmd.getCommandName()).setExecutor(this);
                        if(cmd.getAliases() != null) plugin.getCommand(cmd.getCommandName()).setAliases(cmd.getAliases());
                        if(cmd.getDescription() != null) plugin.getCommand(cmd.getCommandName()).setDescription(cmd.getDescription());
                        // if(cmd.getUsage() != null) { plugin.getCommand(cmd.getCommandName()).setUsage(cmd.getUsage());}
                    } catch (NullPointerException ex) {
                        Bukkit.getConsoleSender().sendMessage(Messages.getInstance().failedToRegisterCommand.replace("{COMMAND}", cmd.getCommandName()).replace("{EXCEPTION}", "Command is not in plugin.yml.") );
                    } catch (Exception ex) {
                        Bukkit.getConsoleSender().sendMessage(Messages.getInstance().failedToRegisterCommand.replace("{COMMAND}", cmd.getCommandName()).replace("{EXCEPTION}", ex.toString()) );
                    }

            }
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String s, String[] args) {

        for(Command cmd : commands) {
            if (cmd.getCommandName() == null || !command.getName().equalsIgnoreCase(cmd.getCommandName())) continue;

                    if (args.length == 0) {
                        cmd.onCommand(sender, args);
                        return false;
                    }

                    SubCommand target = getSubCommand(cmd, args[0]);


                    if (target == null) {
                        cmd.sendHelp(sender);
                        return true;
                    }

                    ArrayList<String> arrayList = new ArrayList<>();
                    arrayList.addAll(Arrays.asList(args));
                    arrayList.remove(0);

                    try {
                        target.onCommand(sender, args);
                    } catch (Exception ex) {
                        cmd.sendHelp(sender);
                        ex.printStackTrace();
                        return true;
                    }


        }
        return false;
    }

    private SubCommand getSubCommand(Command cmd, String name) {
        if(cmd.getSubCommands() == null) return null;

        Iterator<SubCommand> commands = cmd.getSubCommands().iterator();

        while (commands.hasNext()) {
            SubCommand sc = (SubCommand) commands.next();

            if (sc.getCommandName() == null) continue;
                if (sc.getCommandName().equalsIgnoreCase(name))   return sc;

                String[] aliases;
                int length = (aliases = sc.getAliases()).length;
                for (int var = 0; var < length; var++) {
                    String alias = aliases[var];
                    if (!name.equalsIgnoreCase(alias)) continue;
                    return sc;
                }
        }

        return null;
    }


}
