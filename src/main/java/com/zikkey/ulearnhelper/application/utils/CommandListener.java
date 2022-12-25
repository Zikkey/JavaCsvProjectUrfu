package com.zikkey.ulearnhelper.application.utils;

import com.zikkey.ulearnhelper.application.interfaces.command.ICommand;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Log4j2
public class CommandListener {
    private boolean isRunning;
    private boolean reload;
    private final Level APP = Level.getLevel("APP");
    private final CommandRegistry registry;

    public CommandListener(CommandRegistry registry) {
        this.registry = registry;
    }

    public void listen() throws IOException {
        reload = true;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String commandLine;
        String commandName;
        List<String> args;
        ICommand command;

        while (reload) {
            reload = false;
            isRunning = true;
            while (isRunning) {
                boolean commandFound = true;

                System.out.print(" > ");
                commandLine = reader.readLine();
                String[] commandLineAsArray = commandLine.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                commandName = commandLineAsArray[0];


                if (commandLineAsArray.length > 1) {
                    args = Arrays.asList(commandLineAsArray).subList(1, commandLineAsArray.length);
                } else {
                    args = new ArrayList<>();
                }

                command = registry.getByName(commandName);
                if (command == null) {
                    print("Введена неизвестная команда, напишите 'help', чтобы узнать существующие.");
                    commandFound = false;
                }

                if (commandFound) {
                    try {
                        command.run(args);
                    } catch(Exception e) {
                        log.error("Не удалось выполнить команду " + command.getName());
                    }
                }
            }
        }
    }

    public void stop() {
        isRunning = false;
    }

    public void reload() {
        isRunning = false;
        reload = true;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean isReload() {
        return reload;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public void setReload(boolean reload) {
        this.reload = reload;
    }

    private void print(String msg) {
        log.log(APP, msg);
    }

}
