package me.StevenLawson.TotalFreedomMod.Commands;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@CommandPermissions(level = AdminLevel.OP, source = SourceType.BOTH, blockHostConsole = true)
@CommandParameters(description = "Sets gamemode to survival and gives diamond armor", usage = "/<command> <playername>")
public class Command_pvp extends TFM_Command
{

    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length != 0)
        {
            return false;
        }
            Player player;
            player = sender_p;

        ItemStack dhelmet = new ItemStack(Material.DIAMOND_HELMET);
        ItemStack dchestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemStack dleggings = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemStack dboots = new ItemStack(Material.DIAMOND_BOOTS);
        ItemStack dsword = new ItemStack(Material.DIAMOND_SWORD);
        ItemStack gapple = new ItemStack(Material.GOLDEN_APPLE);
        player.getInventory().addItem(new ItemStack[] { 
            dhelmet, dchestplate, dleggings, dboots, dsword, gapple 
        });
        player.setGameMode(GameMode.SURVIVAL);
        playerMsg("You have been given PVP items and your gamemode was set to survival");
        
        return true;
                
    }
}
