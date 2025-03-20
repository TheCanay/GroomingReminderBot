package com.liubomur.bot.enums.commands;

public enum Commands {

    START("/start"),
    INVALID_COMMAND(""),
    ADDNOTIFICATION("/addNotification"),
    REMOVENOTIFICATION("/removeNotification"),
    FEEDBACK("/feedback");

    private String command;

    Commands(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
