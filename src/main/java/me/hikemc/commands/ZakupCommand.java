package me.hikemc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ZakupCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = (Player) sender;
        if(args.length != 1) {
            player.sendMessage("§cPoprawne użycie: /zakup VIP/SVIP/SPONSOR");
            return true;
        }
        if(args[0].contains("vip")) {
            player.sendMessage("§cPrzejebałeś kasę na vipa");
        }
        if(args[0].contains("svip")) {
            player.sendMessage("§cPrzjebeałes kase na SVIPA");
        }
        if(!args[0].contains("vip") && !args[0].contains("svip")) {
            player.sendMessage("§cNie lol");
            return true;
        }







        return true;
    }
}
