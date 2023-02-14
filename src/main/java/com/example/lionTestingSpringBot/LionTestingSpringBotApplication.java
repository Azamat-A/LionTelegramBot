package com.example.lionTestingSpringBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import settings.CommandsBot;

@SpringBootApplication
public class LionTestingSpringBotApplication {

	public static void main(String[] args) throws TelegramApiException {
		SpringApplication.run(LionTestingSpringBotApplication.class, args);

        CommandsBot bot = new CommandsBot();

        TelegramBotsApi telegramBot = new TelegramBotsApi(DefaultBotSession.class);
		telegramBot.registerBot(bot);
	}

}
