package com.mischief247.dungeonbot.commands;



public enum CommandList {
    ROLL("roll", new RollCommand(), RollCommand.getHelp()),
    CREATE_GROUP("creategroup", new CreateGroupCommand(), CreateGroupCommand.getHelp()),
    DROLL("droll", new RollCommand(), RollCommand.getHelp()),
    HELP("help", new HelpCommand(), "this command");

    public String name;
    public Command commandHandler;
    public String help;

     CommandList(String name, Command command, String help) {
        this.name = name;
        commandHandler = command;
        this.help = help;
    }
}
