package com.zikkey.ulearnhelper.console.commands;

import com.zikkey.ulearnhelper.application.interfaces.command.ICommand;
import com.zikkey.ulearnhelper.application.utils.CommandRegistry;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
public class HelpCommand implements ICommand {
    private final CommandRegistry registry;
    private final Level APP = Level.getLevel("APP");

    public HelpCommand(CommandRegistry registry) {
        this.registry = registry;
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Выводит список команд";
    }

    @Override
    public String getUsage() {
        return null;
    }

    @Override
    public void run(List<String> args) {
       for(var command : registry.registeredCommands) {
           var usage = command.getUsage();
           var usageString = usage != null ? " " + usage : "";
           print(command.getName() + usageString + " - " + command.getDescription());
       }
    }

    private void print(String msg) {
        log.log(APP, msg);
    }
}
