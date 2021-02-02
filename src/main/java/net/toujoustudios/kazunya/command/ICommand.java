package net.toujoustudios.kazunya.command;

import java.util.Arrays;
import java.util.List;

/**
 * This file was created by IanToujou.
 * Date: 02.02.2021
 * Time: 08:45
 * Project: Kazunya
 */
public interface ICommand {

    void handle(CommandContext context);

    String getName();

    String getHelp();

    String getUsage();

    CommandCategory getCategory();

    default List<String> getAliases() { return Arrays.asList(); }

}
