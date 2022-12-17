package com.zikkey.ulearnhelper.application.interfaces.command;

import com.sun.istack.NotNull;
import com.zikkey.ulearnhelper.application.utils.CommandRegistry;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public interface ICommand {
    @NotNull
    String getName();

    @NotNull
    String getDescription();

    String getUsage();

    void run(@NotNull List<String> args);

}
