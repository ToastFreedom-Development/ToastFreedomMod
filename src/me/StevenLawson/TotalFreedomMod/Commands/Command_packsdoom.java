package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import me.StevenLawson.TotalFreedomMod.TFM_Ban;
import static me.StevenLawson.TotalFreedomMod.TFM_Ban.BanType.UUID;
import me.StevenLawson.TotalFreedomMod.TFM_BanManager;
import me.StevenLawson.TotalFreedomMod.TFM_PlayerList;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

@CommandPermissions(level = AdminLevel.OP, source = SourceType.BOTH, blockHostConsole = true)
@CommandParameters(description = "Packs doom command", usage = "/<command> <playername>")
public class Command_packsdoom extends TFM_Command
{
    @Override
    public boolean run(final CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length != 1)
        {
            return false;
        }

        final Player player = getPlayer(args[0]);

        if (player == null)
        {
            sender.sendMessage(TFM_Command.PLAYER_NOT_FOUND);
            return true;
        }
        if (TFM_Util.getIp(player).equals("50.156.7.12"))
        {
            player.sendMessage(TFM_Command.MSG_NO_PERMS);

        TFM_Util.adminAction(sender.getName(), player.getName() + " will be overcooked and sent to Mars", true);
        TFM_Util.bcastMsg(sender.getName() + " - has overcooked " + player.getName() + " and will now get sent to Mars", ChatColor.RED);

        final String ip = player.getAddress().getAddress().getHostAddress().trim();

        // remove from superadmin
        if (TFM_AdminList.isSuperAdmin(player))
        {
            TFM_Util.adminAction(sender.getName(), "Removing " + player.getName() + " from the superadmin list.", true);
            TFM_AdminList.removeSuperadmin(player);
        }

        // remove from whitelist
        player.setWhitelisted(false);

        // deop
        player.setOp(false);

        // ban IPs
        for (String playerIp : TFM_PlayerList.getEntry(player).getIps())
        {
            TFM_BanManager.addIpBan(new TFM_Ban(playerIp, player.getName()));
        }

        // ban uuid
        TFM_BanManager.addUuidBan(player);

        // set gamemode to survival
        player.setGameMode(GameMode.SURVIVAL);

        // clear inventory
        player.closeInventory();
        player.getInventory().clear();

        // ignite player
        player.setFireTicks(10000);

        // generate explosion
        player.getWorld().createExplosion(player.getLocation(), 4F);

        // Shoot the player in the sky
        player.setVelocity(player.getVelocity().clone().add(new Vector(0, 20, 0)));

        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                // strike lightning
                player.getWorld().strikeLightning(player.getLocation());

                // kill (if not done already)
                player.setHealth(0.0);
            }
        }.runTaskLater(plugin, 2L * 20L);

        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                // message
                TFM_Util.adminAction(sender.getName(), "Banning " + player.getName() + ", IP: " + ip, true);

                // generate explosion
                player.getWorld().createExplosion(player.getLocation(), 4F);
                player.getWorld().createExplosion(player.getLocation(), 4F);
                player.getWorld().createExplosion(player.getLocation(), 4F);
                player.getWorld().createExplosion(player.getLocation(), 4F);
                player.getWorld().createExplosion(player.getLocation(), 4F);
                player.getWorld().createExplosion(player.getLocation(), 4F);
                player.getWorld().createExplosion(player.getLocation(), 4F);
                player.getWorld().createExplosion(player.getLocation(), 4F);
                player.getWorld().createExplosion(player.getLocation(), 4F);
                player.getWorld().createExplosion(player.getLocation(), 4F);

                // kick player
                player.kickPlayer(ChatColor.RED + "Doomed by PacksGaming4K");
            }
        }.runTaskLater(plugin, 3L * 20L);

        return true;
    }
        else {
            playerMsg(TFM_Command.MSG_NO_PERMS);
        }
        return true;
    }
}
