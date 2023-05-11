package snapje.canetop.Commands.CaneTop;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import snapje.canetop.API.CaneScore;
import snapje.canetop.API.Command.SubCommand;
import snapje.canetop.Data.Messages.Messages;
import snapje.canetop.Utils.Chat;
import snapje.canetop.Utils.Check;

import java.util.UUID;

public class ScoreSubCommand extends SubCommand {

    /**
     * Class created by David Out (Snapje), do not remove this from the class.
     * For any errors please contact: davidoutdevloper@gmail.com
     */

    @Override
    public String getCommandName() {
        return "score";
    }

    @Override
    public String getDescription() {
        return "edit the plugin scores.";
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

    	/*
	canetop

	canetop score get {player}
	canetop score reset {player}
	canetop score set {player} {points}
	canetop score add {player} {points}
	canetop score remove {player} {points}
	canetop settings reload
	canetop settings edit
	canetop info
	 */

    @Override
    public void onCommand(CommandSender sender, String[] args) {
      if(args.length == 1) {

          if(!(sender instanceof Player)) {
              sendHelp(sender);
              return;
          }
              Player p = (Player) sender;
              CaneScore score = CaneScore.getScore(p.getUniqueId());;
              sender.sendMessage(Messages.getInstance().getYourScore.replace("{PLAYER}", p.getName()).replace("{SCORE}", score.getScore() + ""));
          return;
      }

      if(args.length == 2) {
          if(!args[1].equalsIgnoreCase("get") || !(sender instanceof Player)) {
                sendHelp(sender);
                return;
          }

              Player p = (Player) sender;
              CaneScore score = CaneScore.getScore(p.getUniqueId());;
              sender.sendMessage(Messages.getInstance().getYourScore.replace("{PLAYER}", p.getName()).replace("{SCORE}", score.getScore() + ""));
              return;
      }

      if(args.length == 3) {
          String playerName = args[2];
          OfflinePlayer player = Bukkit.getOfflinePlayer(playerName);
          CaneScore score = CaneScore.getScore(player.getUniqueId());

          if (args[1].equalsIgnoreCase("get")) {
              if (!sender.hasPermission("canetop.manage")) {
                  sender.sendMessage(Messages.getInstance().noPermission);
                  return;
              }
              if (!isLegit(player)) {
                      sender.sendMessage(Messages.getInstance().playerNotFound.replace("{PLAYER}", playerName));
                      return;
              }
                  sender.sendMessage(Messages.getInstance().getPlayerScore.replace("{PLAYER}", player.getName()).replace("{SCORE}", score.getScore(getUUID(playerName)).getScore() + ""));
                  return;
          }

           if (args[1].equalsIgnoreCase("reset")) {
              if (!sender.hasPermission("canetop.manage"))  {
                  sender.sendMessage(Messages.getInstance().noPermission);
                  return;
              }
              if (!isLegit(player)) {
                      sender.sendMessage(Messages.getInstance().playerNotFound.replace("{PLAYER}", playerName));
                      return;
              }
                  sender.sendMessage(Messages.getInstance().resetPlayerScore.replace("{PLAYER}", Bukkit.getOfflinePlayer(getUUID(playerName)).getName()));
                  score.reset();
          }
           sendHelp(sender);
           return;
      }


      if(args.length == 4) {
          String playerName = args[2];
          OfflinePlayer player = Bukkit.getOfflinePlayer(playerName);
          CaneScore score = CaneScore.getScore(player.getUniqueId());;
          int number = Integer.parseInt(args[3]);

          if(!sender.hasPermission("canetop.manage")) {
              sender.sendMessage(Messages.getInstance().noPermission);
              return;
          }
          if (isLegit(player) == false) {
              sender.sendMessage(Messages.getInstance().playerNotFound.replace("{PLAYER}", playerName));
              return;
          }
          if(!Check.isInteger(args[3])) {
              sender.sendMessage(Messages.getInstance().isNotANumber.replace("{NUMBER}", args[3]));
              return;
          }


              if(args[1].equalsIgnoreCase("set")) {
                  if(number < 0) {
                      sender.sendMessage(Messages.getInstance().scoreCannotBeUnderZero.replace("{PLAYER}", playerName).replace("{POINT}", number + ""));
                      return;
                  }
                  score.set(number);
                  sender.sendMessage(Messages.getInstance().setPlayerScore.replace("{PLAYER}", Bukkit.getOfflinePlayer(getUUID(playerName)).getName()).replace("{CANES}", number + ""));
                  return;
              }
              if(args[1].equalsIgnoreCase("add")) {
                  score.add(number);
                  sender.sendMessage(Messages.getInstance().addCanesToScore.replace("{PLAYER}", Bukkit.getOfflinePlayer(getUUID(playerName)).getName()).replace("{CANES}",  number + ""));
                  return;
              }

              if(args[1].equalsIgnoreCase("remove")) {
                  if(number > score.getScore()) {
                      sender.sendMessage(Messages.getInstance().scoreCannotBeUnderZero.replace("{PLAYER}", playerName).replace("{POINT}", number + ""));
                      return;
                  }
                  score.remove(number);
                  sender.sendMessage(Messages.getInstance().removeCanesFromScore.replace("{PLAYER}", Bukkit.getOfflinePlayer(getUUID(playerName)).getName()).replace("{CANES}",  number + ""));
                  return;
              }


            sendHelp(sender);
              return;
      }

      sendHelp(sender);
    }

    public UUID getUUID(String name) {
        Player p = Bukkit.getPlayer(name);
        OfflinePlayer op = Bukkit.getOfflinePlayer(name);
        return (p == null ? op.getUniqueId() : p.getUniqueId());
    }

    public void sendHelp(CommandSender sender) {
        for(String currentline : Messages.getHelpCommand()) {
            sender.sendMessage(Chat.format(currentline));
        }
    }

    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private boolean isLegit(OfflinePlayer player) {
        CaneScore score = CaneScore.getScore(player.getUniqueId());
        if(player == null) return false;
        if(score == null) return false;
        return true;
    }


/**
 * Class created by David Out (Snapje), do not remove this from the class.
 * For any errors please contact: davidoutdevloper@gmail.com
 */

}
