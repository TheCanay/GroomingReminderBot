package com.liubomur.bot.constants;

import static com.liubomur.bot.constants.Commands.*;

public final class CommandsResponse {

    private CommandsResponse() {
    }

    public static final String START_COMMAND_INIT_RESPONSE = STR."""
            Привіт! Це бот для нагадування про те,
            що ваш улюбленець уже потребує грумінгу 😊
            Для того щоб почати роботу з ботом, та створити своє перше регулярне нагадування,
            просто введи команду:
            \{ADDNOTIFICATION}
            та дотримуйся інструкцій!
            Для видалення нагадування використовуй команду:
            \{REMOVENOTIFICATION}
            Поки це увесь функціонал, та ми будемо раді почути ваші побажання!
            Для того щоб залишити відгук чи побажання, використайте команду:
            \{FEEDBACK}""";

    public static final String START_COMMAND_DEFAULT_RESPONSE = STR."""
            Для того щоб побачити список команд напиши:
            \{HELP}""";

    public static final String ADDNOTIFICATION_COMMAND_RESPONSE_PET_NAME = STR."""
            Гайда створімо нове нагадування! Спершу, введіть ім'я вашого улюбленця!""";

    public static final String ADDNOTIFICATION_COMMAND_RESPONSE_FREQUENCY = STR."""
            Тепер нам потрібно знати як часто потрібно робити нагадування. Який проміжок у днях вам підходить?""";

    public static final String ADDNOTIFICATION_COMMAND_RESPONSE_FINISHED = STR."""
            Нагадування створено!""";

    public static final String ADDNOTIFICATION_COMMAND_RESPONSE_FAILED_TO_CREATE = STR."""
            Нагадування створено!""";

    public static final String ADDNOTIFICATION_COMMAND_RESPONSE_INCORRECT_USER_INPUT = STR."""
            Ой! Схоже ви надсилаєте нам щось не те, спробуйте знову!""";

    public static final String INVALID_COMMAND_RESPONSE = STR."""
            Вибач, та схоже я не знаю такої команди, використай /help щоб отримати список моїх команд""";
}