package net.toujoustudios.kazunya.command.list.roleplay;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.toujoustudios.kazunya.command.CommandCategory;
import net.toujoustudios.kazunya.command.CommandContext;
import net.toujoustudios.kazunya.command.ICommand;
import net.toujoustudios.kazunya.config.Config;
import net.toujoustudios.kazunya.error.ErrorEmbed;
import net.toujoustudios.kazunya.error.ErrorType;
import net.toujoustudios.kazunya.util.ColorUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 05/11/2021
 * Time: 22:21
 */
public class KillCommand implements ICommand {

    private final Config config;

    public KillCommand() {
        config = Config.getDefault();
    }

    @Override
    public void handle(CommandContext context) {

        List<OptionMapping> args = context.getArgs();
        Member member = context.getMember();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        Member target = args.get(0).getAsMember();
        assert target != null;

        if(target.getId().equals(member.getId())) {
            ErrorEmbed.sendError(context, ErrorType.COMMAND_INVALID_USER_SELF);
            return;
        }

        List<String> images = config.getStringList("gif.command.kill");

        ArrayList<String> messages = new ArrayList<>();
        int random = new Random().nextInt(5);

        if(random < 3) {
            embedBuilder.setTitle("**:skull: Kill**");
            messages.add("{Victim} got killed by {Killer}.");
            messages.add("{Killer} used a nuke on {Victim}.");
            messages.add("{Victim} stepped on a landmine and died.");
            messages.add("{Killer} hugged {Victim} a little too hard...");
            messages.add("{Victim} got crushed by {Killer}'s fat ass. Whoops...");
            messages.add("{Victim} died due to a cuddle overdose.");
            messages.add("{Victim} ate too many hamburgers.");
            messages.add("{Killer} threw a bottle of highly concentrated sulfuric acid on {Victim}.");
            messages.add("{Victim} was used as a fuel source for {Killer}'s car.");
            messages.add("{Victim} got sandwiched by {Killer}.");
            messages.add("{Victim} was too beautiful for this world.");
            messages.add("{Victim} was too ugly for this world.");
            messages.add("{Victim} got dominated by {Killer}.");
            messages.add("{Victim} watched too much Anime.");
            messages.add("{Victim} died in a car crash.");
            messages.add("{Killer} used kinetic bombardment on {Victim}.");
            messages.add("{Victim} was too horny and died.");
            messages.add("{Killer} yeeted {Victim} out of the window.");
            messages.add("{Killer} was hungry and ate {Victim}.");
            messages.add("{Victim} played too much Genshin Impact and starved to death.");
            messages.add("{Killer} stole all the food from {Victim}.");
            messages.add("{Victim} watched certain Ankha videos and died.");
            messages.add("{Killer} threw {Victim} into an abandoned nuclear missile shaft.");
            messages.add("{Victim} got killed by SCP-173.");
            messages.add("{Victim} was sent to space by {Killer}");
        } else {
            embedBuilder.setTitle("**:boom: Kill Failed**");
            messages.add("{Victim} escaped {Killer}'s knife and ran away.");
            messages.add("{Victim} used the UNO reverse card on {Killer}.");
            messages.add("{Victim} was about to die but replied \"No u\".");
            messages.add("Ricardo Milos saved {Victim} and killed {Killer}.");
            messages.add("{Killer} slipped over a Banana and broke the neck while trying to get near {Victim}.");
            messages.add("{Killer} died due to a heart attack before he could kill {Victim}. Death Note moment lmao.");
        }

        embedBuilder.setDescription(messages.get(new Random().nextInt(messages.size())).replace("{Victim}", target.getAsMention()).replace("{Killer}", member.getAsMention()));
        embedBuilder.setImage(images.get(new Random().nextInt(images.size())));
        embedBuilder.setColor(ColorUtil.getFromRGBString(config.getString("format.color.default")));
        context.getInteraction().reply(target.getAsMention()).addEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "kill";
    }

    @Override
    public String getDescription() {
        return "Kill someone else and commit a very bad thing.";
    }

    @Override
    public String getEmoji() {
        return "🔫";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.USER, "user", "The person you want to kill.", true));
        return optionData;
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ROLEPLAY;
    }

}
