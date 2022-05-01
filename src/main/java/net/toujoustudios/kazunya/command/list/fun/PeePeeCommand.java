package net.toujoustudios.kazunya.command.list.fun;

import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;

import java.util.ArrayList;
import java.util.List;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 01/05/2022
 * Time: 23:21
 */
public class PeePeeCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {


    }

    @Override
    public String getName() {
        return "peepee";
    }

    @Override
    public String getDescription() {
        return "Shows the size of a persons pp.";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.FUN;
    }

    @Override
    public List<String> getAliases() {
        ArrayList<String> aliases = new ArrayList<>();
        aliases.add("pp");
        return aliases;
    }

}
