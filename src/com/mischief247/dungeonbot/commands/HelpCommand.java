package com.mischief247.dungeonbot.commands;

import net.dv8tion.jda.core.entities.Message;

public class HelpCommand extends Command {

    public String invoke(String[] args, Message message) {
        StringBuilder sb = new StringBuilder();
        sb.append("DMBot by mischief247 please report all bugs here https://github.com/mischief247/DungeonBot/issues\n");
        sb.append("----------------------------------------------------\n");

        for (CommandList cl : CommandList.values()) {
            sb.append(cl.name);
            sb.append(": ");
            sb.append(cl.help);
            sb.append("\n");
        }

        return sb.toString();
    }

    public CommandList getCommand() {
        return CommandList.HELP;
    }
}
