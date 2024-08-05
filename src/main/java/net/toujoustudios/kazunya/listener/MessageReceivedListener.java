package net.toujoustudios.kazunya.listener;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.log.LogLevel;
import net.toujoustudios.kazunya.log.Logger;
import net.toujoustudios.kazunya.main.Main;
import net.toujoustudios.kazunya.model.UserManager;
import net.toujoustudios.kazunya.util.ColorUtil;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

public class MessageReceivedListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        if(!event.isFromGuild()) {
            Logger.log(LogLevel.INFORMATION, "DM " + event.getAuthor().getEffectiveName() + " : " + event.getMessage().getContentDisplay());
            return;
        }
        if(!event.getGuild().getId().equals("1166790471985680457")) return;
        if(event.getMember() == null) return;
        if(event.getMember().getUser().isBot()) return;
        Member member = event.getMember();
        Guild guild = event.getGuild();

        List<String> blacklistedChannels = List.of(
                "1166792272021553253",
                "1168338171881345064",
                "1190029818331402350",
                "1191079819006460007",
                "1178427138781753344",
                "1190028754576216095",
                "1184568784862855291",
                "1166792367324540939",
                "1166792398509191179",
                "1166792414401417226"
        );

        if(blacklistedChannels.contains(event.getChannel().getId())) return;

        if(event.getChannelType() == ChannelType.TEXT || event.getChannelType() == ChannelType.FORUM) {

            List<String> levelRoles = List.of(
                    "1166793803471007935",
                    "1190337225599234119",
                    "1190337395023937576",
                    "1190337415789948999",
                    "1190337662826061966",
                    "1190337689241788527",
                    "1190337714512466002",
                    "1190337768837091530"
            );

            UserManager manager = UserManager.getUser(member.getId());
            int oldLevel = manager.getLevel(guild.getId());
            manager.setExperience(guild.getId(), manager.getExperience(guild.getId())+1);

            int level = manager.getLevel(guild.getId());
            int roleToSelect = level/10;
            Role role = Main.getBot().getJDA().getRoleById(levelRoles.get(roleToSelect));
            if(role == null) return;

            event.getGuild().addRoleToMember(member, role).queue();

            int newLevel = manager.getLevel(guild.getId());
            if(newLevel > oldLevel) {
                TextChannel channel = event.getGuild().getTextChannelById("1191079819006460007");
                if(channel == null) return;
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setTitle("**:sparkles: Level Up**");
                embedBuilder.setDescription(member.getAsMention() + " just reached **Level " + newLevel + "**!");
                embedBuilder.setColor(ColorUtil.getFromRGBString(Config.getDefault().getString("format.color.default")));
                channel.sendMessageEmbeds(embedBuilder.build()).queue();
            }

        }

    }

}
