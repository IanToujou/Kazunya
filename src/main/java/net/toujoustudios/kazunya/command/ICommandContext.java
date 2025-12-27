package net.toujoustudios.kazunya.command;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.sharding.ShardManager;

public interface ICommandContext {

    Guild guild();

    SlashCommandInteraction interaction();

    default MessageChannelUnion channel() {
        return this.interaction().getChannel();
    }

    default User author() {
        return this.interaction().getUser();
    }

    default Member member() {
        return this.interaction().getMember();
    }

    default JDA jda() {
        return this.interaction().getJDA();
    }

    default ShardManager shardManager() {
        return this.jda().getShardManager();
    }

    default User selfUser() {
        return this.jda().getSelfUser();
    }

    default Member selfMember() {
        return this.guild().getSelfMember();
    }

}
