package com.mischief247.dungeonbot;

import com.mischief247.dungeonbot.commands.CommandList;
import com.mischief247.dungeonbot.util.MessageWithState;
import com.mischief247.dungeonbot.util.PropertiesManager;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;


class DungeonListener extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getAuthor().isBot() && event.getChannelType().equals(ChannelType.TEXT) && event.getMessage().getContentDisplay().startsWith(PropertiesManager.token)) {
            String[] commandWithArgs = event.getMessage().getContentDisplay().substring(1).split(" ");

            boolean success = false;

            for (CommandList command: CommandList.values()){
                if (commandWithArgs[0].equals(command.name)) {
                    MessageWithState output = command.commandHandler.invoke(commandWithArgs,event.getMessage());

                    if(output.isError()){
                        StringBuilder sb = new StringBuilder(output.getMsg());
                        sb.insert(0,"The Command has errored please report this to the github link provided along with the stacktrace that follows\nhttps://github.com/mischief247/DungeonBot/issues\nbegin stack trace\n");
                        output.setMsg(sb.toString());
                    }
                    if (output.getMsg().length()>2000) {
                        event.getChannel().sendMessage(output.getMsg().substring(0, 2000)).queue();
                    }
                    else {
                        event.getChannel().sendMessage(output.getMsg()).queue();
                    }
                    success = true;
                }
            }

            if (!success) {
                event.getChannel().sendMessage("invalid command type !help for a list of commands").queue();
            }
        }

    }

}
