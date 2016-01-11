package me.StevenLawson.TotalFreedomMod.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.ALL, source = SourceType.BOTH)
@CommandParameters(description = "Shows you how to become a admin.", usage = "/<command>", aliases = "ai")
public class Command_admininfo extends TFM_Command {

    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole) {

        playerMsg(ChatColor.AQUA + "Want to be an admin?");
        playerMsg(ChatColor.AQUA + "Apply on the forums at: " + ChatColor.GOLD + "http://toastfreedomforums.boards.net");
        return true;

    }
}