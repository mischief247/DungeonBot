package com.mischief247.dungeonbot.commands;

import net.dv8tion.jda.core.entities.Message;

public abstract class Command {

    public abstract String invoke(String[] var1, Message var2);

    public CommandList getCommand() {
        return null;
    }

    public static String getHelp() {
        return "";
    }
}
