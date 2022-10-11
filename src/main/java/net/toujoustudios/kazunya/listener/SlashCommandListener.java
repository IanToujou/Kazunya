package net.toujoustudios.kazunya.listener;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.toujoustudios.kazunya.data.user.UserAccount;
import net.toujoustudios.kazunya.data.user.UserManager;
import net.toujoustudios.kazunya.error.ErrorEmbed;
import net.toujoustudios.kazunya.error.ErrorType;
import net.toujoustudios.kazunya.main.Main;
import org.jetbrains.annotations.NotNull;

public class SlashCommandListener extends ListenerAdapter {

    @Override
    public void onSlashCommand(@NotNull SlashCommandEvent event) {

        if(event.getGuild() == null) return;
        if(event.getUser().isBot()) return;

        UserManager userManager = UserManager.getUser(event.getUser().getId());

        if(userManager.getAccount() == null) {
            userManager.setAccount(new UserAccount(event.getUser().getName(), event.getUser().getEffectiveAvatarUrl()));
        }

        if(userManager.isBanned()) {
            ErrorEmbed.sendError(event, ErrorType.GENERAL_BANNED);
            return;
        }

        Main.getBot().getCommandManager().handle(event);

    }

}
