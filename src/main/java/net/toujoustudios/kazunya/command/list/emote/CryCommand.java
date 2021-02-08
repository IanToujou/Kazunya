package net.toujoustudios.kazunya.command.list.emote;

import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;

/**
 * This file was created by IanToujou.
 * Date: 08.02.2021
 * Time: 03:37
 * Project: Kazunya
 */
public class CryCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {

        

    }

    @Override
    public String getName() {
        return "cry";
    }

    @Override
    public String getHelp() {
        return "Make yourself cry.";
    }

    @Override
    public String getUsage() {
        return "cry";
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.EMOTE;
    }

}
