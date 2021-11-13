package gamebot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.InlineQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineQueryResultArticle;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;

public class Bot {
    // Create your bot passing the token received from @BotFather
    private final TelegramBot bot = new TelegramBot(System.getenv("BOT_TOKEN"));

    public void serve() {
        // Register for updates
        bot.setUpdatesListener(updates -> {
            // ... process updates
            updates.forEach(this::process);
            // return id of last processed update or confirm them all
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    private void process(Update update) {
        Message message = update.message();
        CallbackQuery callbackQuery = update.callbackQuery();
        InlineQuery inlineQuery = update.inlineQuery();

        BaseRequest request = null;

        if (inlineQuery != null) {

            InlineQueryResultArticle pipka = new InlineQueryResultArticle("stone", "Stone", "I'm ready to fight!");

        } else if (message != null) {
            // Send messages
            long chatId = message.chat().id();
            request = new SendMessage(chatId, "Hello!");
        }

        if (request != null) {
            bot.execute(request);
        }
    }
}
