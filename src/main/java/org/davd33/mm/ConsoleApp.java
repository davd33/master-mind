package org.davd33.mm;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
@Log4j2
public class ConsoleApp implements CommandLineRunner {

    public static void main(String[] args) {
        log.debug("STARTING APP");
        SpringApplication.run(ConsoleApp.class, args);
        log.debug("APP FINISHED");
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new GameImpl();
        while (!game.isVictorious()) {
            game.newTry(scanner.next("[0-9]{4}"));
            System.out.println(game.dequeueOutput());
        }
    }
}
