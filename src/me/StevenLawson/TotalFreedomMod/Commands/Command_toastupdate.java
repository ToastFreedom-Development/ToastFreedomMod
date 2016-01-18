package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_Util;
import me.StevenLawson.TotalFreedomMod.TotalFreedomMod;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH)
@CommandParameters(description = "Updates ToastFreedomMod", usage = "/<command>")
public class Command_toastupdate extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length != 0)
        {
            return false;
        }
        TFM_Util.bcastMsg(ChatColor.RED + sender.getName() + " - Loading the latest ToastFreedomMod on to the server");
        server.reload();
        
        TFM_Util.bcastMsg(ChatColor.RED + sender.getName() + " - Successfully loaded the latest ToastFreedomMod on the server");
        final String message = String.format("%s v%s.%s loaded.",
        TotalFreedomMod.pluginName,
        TotalFreedomMod.pluginVersion,
        TotalFreedomMod.buildNumber);
        playerMsg(message);

        return true;
    }
}