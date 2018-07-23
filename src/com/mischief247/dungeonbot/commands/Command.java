package com.mischief247.dungeonbot.commands;

import com.mischief247.dungeonbot.util.MessageWithState;
import net.dv8tion.jda.core.entities.Message;

public abstract class Command {

    public abstract MessageWithState invoke(String[] args, Message message);

    public abstract CommandList getCommand();

    public static String getHelp() {
        return "";
    }
}
