package com.zikkey.ulearnhelper.console.commands;

import com.zikkey.ulearnhelper.application.interfaces.command.ICommand;
import com.zikkey.ulearnhelper.application.services.VkEnrichmentService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
public class ChartCommand implements ICommand {
    private final Level APP = Level.getLevel("APP");

    @Override
    public String getName() {
        return "chart";
    }

    @Override
    public String getDescription() {
        return "сохраняет диаграмму по названию курса";
    }

    @Override
    public String getUsage() {
        return "course path";
    }

    @Override
    public void run(List<String> args) {

    }

    private void print(String msg) {
        log.log(APP, msg);
    }
}
