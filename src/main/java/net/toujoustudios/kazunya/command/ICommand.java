package net.toujoustudios.kazunya.command;

import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;

import java.util.Collections;
import java.util.List;

public interface ICommand {

    void handle(CommandContext context);
    String name();
    String description();
    String emoji();
    List<OptionData> options();
    CommandCategory category();

    default List<SubcommandData> subCommandData() {
        return Collections.emptyList();
    }

    default String syntax() {
        StringBuilder builder = new StringBuilder();
        builder.append(name());
        for(OptionData data : options()) {
            builder.append(" ");
            if(data.isRequired()) {
                builder.append("<");
                builder.append(data.getName());
                builder.append(">");
            } else {
                builder.append("[<");
                builder.append(data.getName());
                builder.append(">]");
            }
        }
        return builder.toString();
    }

}
