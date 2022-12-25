package com.zikkey.ulearnhelper.application.utils;

import com.zikkey.ulearnhelper.UlearnHelperApplication;
import com.zikkey.ulearnhelper.application.interfaces.command.ICommand;
import org.reflections.Reflections;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import static org.reflections.scanners.Scanners.SubTypes;

@Component
public class CommandRegistry {
    public final List<ICommand> registeredCommands = new ArrayList<>();
    private final ApplicationContext context;

    public CommandRegistry(ApplicationContext context) {
        this.context = context;
    }

    public ICommand getByName(String name) {
        AtomicReference<ICommand> tmpCommand = new AtomicReference<>(null);
        registeredCommands.forEach(command -> {
            if (command.getName().equalsIgnoreCase(name))
                tmpCommand.set(command);
        });
        return tmpCommand.get();
    }

    public boolean isRegistered(ICommand command) {
        return registeredCommands.contains(command);
    }

    public void unregister(ICommand command) {
        if (isRegistered(command))
            registeredCommands.remove(command);
    }

    public void handleAndRegisterAll() {
        var reflections = new Reflections(UlearnHelperApplication.class);
        Set<Class<?>> subTypes = reflections.get(SubTypes.of(ICommand.class).asClass());
        for (Class<?> subType : subTypes) {
            registeredCommands.add((ICommand)context.getBean(subType));
        }
    }
}
