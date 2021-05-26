package net.toujoustudios.kazunya.command.list.general;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.CommandManager;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;

import java.util.List;

/**
 * This file was created by IanToujou.
 * Date: 02.02.2021
 * Time: 08:50
 * Project: Kazunya
 */
public class HelpCommand implements ICommand {

    private final CommandManager manager;

    public HelpCommand(CommandManager manager) {

        this.manager = manager;

    }

    @Override
    @SuppressWarnings("all")
    public void handle(CommandContext context) {

        List<String> args = context.getArgs();
        TextChannel channel = context.getChannel();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setColor(Config.DEFAULT_EMBED_COLOR);
        embedBuilder.setTitle("**__HELP__**");

        if(args.isEmpty()) {

            StringBuilder builderGeneral = new StringBuilder();
            StringBuilder builderRoleplay = new StringBuilder();
            StringBuilder builderFun = new StringBuilder();
            StringBuilder builderMusic = new StringBuilder();
            StringBuilder builderStats = new StringBuilder();
            StringBuilder builderEconomy = new StringBuilder();
            StringBuilder builderSpace = new StringBuilder();

            int totalNSFW = 0;

            for(ICommand command : manager.getCommands()) {

                if(command.isNSFW() && !channel.isNSFW()) {

                    totalNSFW++;

                } else {

                    if(command.getCategory() == CommandCategory.GENERAL) {
                        builderGeneral.append("**" + Config.DEFAULT_PREFIX + command.getName() + "** - " + command.getHelp() + "\n");
                    }
                    if(command.getCategory() == CommandCategory.ROLEPLAY) {
                        builderRoleplay.append("**" + Config.DEFAULT_PREFIX + command.getName() + "** - " + command.getHelp() + "\n");
                    }
                    if(command.getCategory() == CommandCategory.FUN) {
                        builderFun.append("**" + Config.DEFAULT_PREFIX + command.getName() + "** - " + command.getHelp() + "\n");
                    }
                    if(command.getCategory() == CommandCategory.MUSIC) {
                        builderMusic.append("**" + Config.DEFAULT_PREFIX + command.getName() + "** - " + command.getHelp() + "\n");
                    }
                    if(command.getCategory() == CommandCategory.STATS) {
                        builderStats.append("**" + Config.DEFAULT_PREFIX + command.getName() + "** - " + command.getHelp() + "\n");
                    }
                    if(command.getCategory() == CommandCategory.ECONOMY) {
                        builderEconomy.append("**" + Config.DEFAULT_PREFIX + command.getName() + "** - " + command.getHelp() + "\n");
                    }
                    if(command.getCategory() == CommandCategory.SPACE) {
                        builderSpace.append("**" + Config.DEFAULT_PREFIX + command.getName() + "** - " + command.getHelp() + "\n");
                    }

                }

            }

            embedBuilder.addField(":satellite_orbital: General:", builderGeneral.toString(), false);
            embedBuilder.addField(":smile: Roleplay:", builderRoleplay.toString(), false);
            embedBuilder.addField(":tada: Fun:", builderFun.toString(), false);
            embedBuilder.addField(":musical_note: Music:", builderMusic.toString(), false);
            embedBuilder.addField(":chart_with_upwards_trend: Stats:", builderStats.toString(), false);
            embedBuilder.addField(":coin: Economy:", builderEconomy.toString(), false);
            embedBuilder.addField(":rocket: Space:", builderSpace.toString(), false);

            boolean isOwnerOnServer = false;
            for(Member member : context.getGuild().getMembers()) {

                String id = member.getId();

                if(id.equals(Config.DEFAULT_ADMIN_USER)) {

                    isOwnerOnServer = true;
                    break;

                }

            }

            if(isOwnerOnServer) {
                embedBuilder.addField(":bookmark_tabs: Credits:", context.getGuild().getMemberById(Config.DEFAULT_ADMIN_USER).getAsMention() + " - Toujou Studios", false);
            } else {
                embedBuilder.addField(":bookmark_tabs: Credits:", "IanToujou - Toujou Studios", false);
            }

            if(!channel.isNSFW()) {

                if(totalNSFW == 1) {
                    embedBuilder.setDescription("*Note: " + totalNSFW + " NSFW command will only show up in NSFW channels.*");
                } else {
                    embedBuilder.setDescription("*Note: " + totalNSFW + " NSFW commands will only show up in NSFW channels.*");
                }


            }

            channel.sendMessage(embedBuilder.build()).queue();

            return;

        }

        if(args.size() > 1) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: The command syntax is not correct.\nPlease use **" + Config.DEFAULT_PREFIX + getUsage() + "**");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        String search = args.get(0);
        ICommand command = manager.getCommand(search);

        if(command == null) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: Nothing found for `" + search + "`.");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        embedBuilder.setDescription("**Description:** " + command.getHelp() + "\n**Usage:** " + Config.DEFAULT_PREFIX + command.getUsage());
        channel.sendMessage(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getHelp() { return "Shows a list with all my features and commands."; }

    @Override
    public String getUsage() { return "help [(command)]"; }

    @Override
    public boolean isNSFW() { return false; }

    @Override
    public CommandCategory getCategory() { return CommandCategory.GENERAL; }

}
