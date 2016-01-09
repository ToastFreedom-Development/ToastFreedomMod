package me.StevenLawson.TotalFreedomMod.Commands;

import java.util.Random;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH)
@CommandParameters(description = "Toast command", usage = "/<command>")
public class Command_toast extends TFM_Command
{
    public static final String CAKE_LYRICS = "The hottest toast you'll ever have";
    private final Random random = new Random();

    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        final StringBuilder output = new StringBuilder();

        final String[] words = CAKE_LYRICS.split(" ");
        for (final String word : words)
        {
            output.append(ChatColor.COLOR_CHAR).append(Integer.toHexString(1 + random.nextInt(14))).append(word).append(" ");
        }

        final ItemStack heldItem = new ItemStack(Material.BREAD);
        final ItemMeta heldItemMeta = heldItem.getItemMeta();
        heldItemMeta.setDisplayName((new StringBuilder()).append(ChatColor.YELLOW).append("Toast").append(ChatColor.WHITE).append("").toString());
        heldItem.setItemMeta(heldItemMeta);

        for (final Player player : server.getOnlinePlayers())
        {
            final int firstEmpty = player.getInventory().firstEmpty();
            if (firstEmpty >= 0)
            {
                player.getInventory().setItem(firstEmpty, heldItem);
            }
        }

        TFM_Util.bcastMsg(output.toString());

        return true;
    }
}
