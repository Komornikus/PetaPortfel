package me.hikemc.tab;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class tabCompleterA implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length == 2){
            List<String> arguments = new ArrayList<>();
            arguments.add("set");
            arguments.add("remove");
            arguments.add("add");

            return arguments;
        }
        if (args.length == 3){
            List<String> arguments = new ArrayList<>();
            arguments.add("<kwota>");

            return arguments;
        }



        return null;
    }
}
