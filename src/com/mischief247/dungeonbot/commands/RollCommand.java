package com.mischief247.dungeonbot.commands;

import com.mischief247.dungeonbot.util.PropertiesManager;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.PrivateChannel;

public class RollCommand extends Command {

    public String invoke(String[] args, Message message) {
        if (args.length < 3) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            Random dice = new Random();

            for(int i = 0; i < Integer.parseInt(args[1]); ++i) {
                sb.append("roll ");
                sb.append(i);
                sb.append(": ");
                sb.append(dice.nextInt(Integer.parseInt(args[2]) + 1));
                sb.append("\n");
            }

            if (!args[0].equals("droll")) {
                return sb.toString();
            } else {
                message.getMember().getUser().openPrivateChannel().queue();

                try {
                    TimeUnit.MILLISECONDS.sleep(200L);
                } catch (InterruptedException IE) {
                    IE.printStackTrace();
                }

                List<PrivateChannel> list = message.getJDA().getPrivateChannels();
                for (PrivateChannel p:list) {
                    if (p.getUser().equals(message.getMember().getUser())){
                        p.sendMessage(sb.toString()).queue();
                    }
                }

                return "*you hear the sound of tumbling dice*";
            }
        }
    }

    public CommandList getCommand() {
        return CommandList.ROLL;
    }

    public static String getHelp() {
        return PropertiesManager.token;
    }
}
