package net.toujoustudios.kazunya.log;

import java.util.ArrayList;
import java.util.Date;

/**
 * This file has been created by Ian Toujou.
 * Project: Kazunya
 * Date: 03/03/2022
 * Time: 19:21
 */
public class CommandLog {

    private static final ArrayList<CommandLog> commandLogs = new ArrayList<>();

    private String commandId;
    private boolean executionSuccessful;
    private Date executionTime;
    private String userId;
    private String serverId;
    private String fullCommand;

    public CommandLog(String commandId, boolean executionSuccessful, Date executionTime, String userId, String serverId, String fullCommand) {
        this.commandId = commandId;
        this.executionSuccessful = executionSuccessful;
        this.executionTime = executionTime;
        this.userId = userId;
        this.serverId = serverId;
        this.fullCommand = fullCommand;
        commandLogs.add(this);
    }

    public static void saveAll() {
        if (commandLogs.size() == 0) return;
        for (CommandLog all : commandLogs) {
            all.save();
        }
    }

    public static void unloadAll() {
        saveAll();
        commandLogs.clear();
    }

    public void save() {
        DatabaseLogger.logCommand(this);
    }

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public boolean isExecutionSuccessful() {
        return executionSuccessful;
    }

    public void setExecutionSuccessful(boolean executionSuccessful) {
        this.executionSuccessful = executionSuccessful;
    }

    public Date getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Date executionTime) {
        this.executionTime = executionTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getFullCommand() {
        return fullCommand;
    }

    public void setFullCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

}
