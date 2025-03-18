package com.liubomur.bot.constants;

import static com.liubomur.bot.commands.Commands.*;
import static java.lang.StringTemplate.STR;

public final class CommandsResponse {

    private CommandsResponse() {
    }

    public static final String START_COMMAND_RESPONSE = STR."""
            Привіт! Це бот для нагадування про те,
            що ваш улюбленець уже потребує грумінгу 😊
            Для того щоб налаштувати частоту нагадувань використовуйте команду:
            \{FREQUENCY.getCommand()}
            Для встановлення імені улюбленця використовуйте команду:
            \{PET_NAME.getCommand()}
            Поки це увесь функціонал, та ми будемо раді почути ваші побажання!
            Для того щоб залишити відгук чи побажання, використайте команду:
            \{FEEDBACK.getCommand()}""";

    public static final String FREQUENCY_COMMAND_RESPONSE = STR."""
            dummy""";

    public static final String INVALID_COMMAND_RESPONSE = STR."""
            Вибач, та схоже я не знаю такої команди, використай /help щоб отримати список моїх команд""";
}