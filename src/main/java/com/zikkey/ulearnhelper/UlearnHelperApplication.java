package com.zikkey.ulearnhelper;

import com.zikkey.ulearnhelper.application.interfaces.ICsvParseHelper;
import com.zikkey.ulearnhelper.application.repository.*;
import com.zikkey.ulearnhelper.application.services.ChartService;
import com.zikkey.ulearnhelper.application.utils.CommandListener;
import com.zikkey.ulearnhelper.application.utils.CommandRegistry;
import com.zikkey.ulearnhelper.domain.entities.*;
import com.zikkey.ulearnhelper.domain.entities.Module;
import com.zikkey.ulearnhelper.domain.enums.ExerciseType;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static com.zikkey.ulearnhelper.domain.enums.ExerciseType.Additional;
import static com.zikkey.ulearnhelper.domain.enums.ExerciseType.Task;

@Log4j2
@SpringBootApplication
public class UlearnHelperApplication implements CommandLineRunner {
    private final CommandListener listener;
    private final CommandRegistry registry;
    private final Level APP = Level.getLevel("APP");

    public UlearnHelperApplication(CommandListener listener, CommandRegistry registry) {
        this.listener = listener;
        this.registry = registry;
    }

    public static void main(String[] args) {
        SpringApplication.run(UlearnHelperApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        registry.handleAndRegisterAll();
        print("Введите 'help', чтобы узнать список команд");
        listener.listen();
    }

    private void print(String msg) {
        log.log(APP, msg);
    }
}
