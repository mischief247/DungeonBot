package com.mischief247.dungeonbot.commands;



public enum CommandList {
    ROLL("roll", new RollCommand(), RollCommand.getHelp()),
    CREATE_GROUP("creategame", new CreateGroupCommand(), CreateGroupCommand.getHelp()),
    DROLL("droll", new RollCommand(), RollCommand.getHelp()),
    HELP("help", new HelpCommand(), "this command");

    public final String name;
    public final Command commandHandler;
    public final String help;

     CommandList(String name, Command command, String help) {
        this.name = name;
        commandHandler = command;
        this.help = help;
    }
}
