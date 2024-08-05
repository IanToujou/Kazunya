package net.toujoustudios.kazunya.command;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.sharding.ShardManager;

public interface ICommandContext {

    Guild getGuild();

    SlashCommandInteraction getInteraction();

    default MessageChannelUnion getChannel() {
        return this.getInteraction().getChannel();
    }

    default User getAuthor() {
        return this.getInteraction().getUser();
    }

    default Member getMember() {
        return this.getInteraction().getMember();
    }

    default JDA getJDA() {
        return this.getInteraction().getJDA();
    }

    default ShardManager getShardManager() {
        return this.getJDA().getShardManager();
    }

    default User getSelfUser() {
        return this.getJDA().getSelfUser();
    }

    default Member getSelfMember() {
        return this.getGuild().getSelfMember();
    }

}
