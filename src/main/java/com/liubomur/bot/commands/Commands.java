package com.liubomur.bot.commands;

public enum Commands {

    START("/start"),
    INVALID_COMMAND(""),
    FREQUENCY("/frequency"),
    PET_NAME("/name"),
    FEEDBACK("/feedback");

    private String command;

    Commands(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
