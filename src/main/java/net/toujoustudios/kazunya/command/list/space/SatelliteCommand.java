package net.toujoustudios.kazunya.command.list.space;

import com.fasterxml.jackson.databind.JsonNode;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.config.Keys;

import java.util.List;

/**
 * This file was created by IanToujou.
 * Date: 18/05/2021
 * Time: 18:14
 * Project: Kazunya
 */
public class SatelliteCommand implements ICommand {

    @Override
    public void handle(CommandContext context) {

        EmbedBuilder embedBuilder = new EmbedBuilder();
        TextChannel channel = context.getChannel();
        List<String> args = context.getArgs();

        embedBuilder.setColor(Config.DEFAULT_EMBED_COLOR);
        embedBuilder.setTitle("**__SATELLITE INFORMATION__**");

        if(args.size() != 1) {

            embedBuilder.setTitle("**__ERROR__**");
            embedBuilder.setColor(Config.ERROR_EMBED_COLOR);
            embedBuilder.setDescription(":x: The command syntax is not correct.\nPlease use **" + Config.DEFAULT_PREFIX + getUsage() + "**");
            channel.sendMessage(embedBuilder.build()).queue();
            return;

        }

        WebUtils.ins.getJSONObject("https://api.n2yo.com/rest/v1/satellite/tle/" + args.get(0) + "?apiKey=" + Keys.N2YO_API_KEY).async((json) -> {

            JsonNode info = json.get("info");
            String id = info.get("satid").asText();
            String name = info.get("satname").asText();
            String tle = json.get("tle").asText();

            if(tle.length() > 0) {

                String[] tleParts = tle.split("\r\n2 ");
                String lineOne = tleParts[0];

                String designator = lineOne.substring(9, 15);

                StringBuilder launchYearBuilder = new StringBuilder();
                if(Integer.parseInt(designator.substring(0, 2)) > 40) {
                    launchYearBuilder.append(19);
                } else {
                    launchYearBuilder.append(20);
                }
                launchYearBuilder.append(designator, 0, 2);
                String launchYear = launchYearBuilder.toString();

                embedBuilder.addField(":information_source: General Information:", "**ID:** " + id + "\n**Name:** " + name + "\n**International Designator:** " + designator + "\n**Launch Year:** " + launchYear, false);

            } else {

                embedBuilder.addField(":information_source: General Information:", "**ID:** " + id + "\n**Name:** " + name, false);

            }

            channel.sendMessage(embedBuilder.build()).queue();

        });

    }

    @Override
    public String getName() {
        return "satellite";
    }

    @Override
    public String getHelp() {
        return "Shows detailed information about any given satellite by using the norad id.";
    }

    @Override
    public String getUsage() {
        return "satellite [norad_id]";
    }

    @Override
    public boolean isNSFW() {
        return false;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.SPACE;
    }

}
