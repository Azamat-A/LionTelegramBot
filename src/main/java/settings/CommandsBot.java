package settings;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
@Service
public
class CommandsBot extends TelegramLongPollingBot {

//    @Override
//    public void onUpdatesReceived(List<Update> updates) {
//        super.onUpdatesReceived(updates);
//    }
    //    @Override
//    public void onRegister() {
//        super.onRegister();
//    }
    @Override
    public String getBotUsername() {
        return "lionTestingSpringBot";
    }
    @Override
    public String getBotToken(){
        return "6242458367:AAGT6S6y8OSa2CabiE-mWgVNFCt-4PtfHyU";
    }


    private void sendWithOutUrl(Message message){
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        InlineKeyboardButton button = new InlineKeyboardButton();

        button.setText("Тык");
        button.setCallbackData("start0");

        List<InlineKeyboardButton>keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(button);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow);

        keyboard.setKeyboard(rowList);
        try{

            execute(
                    SendMessage.builder()
                            .chatId(message.getChatId())
                            .parseMode("Markdown")
                            .text("Ошибка ввода текста.Повторите попытку!")
                            .replyMarkup(keyboard)
                            .build());
        }
        catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Message command = update.getMessage();
            long chatId = update.getMessage().getChatId();

            switch (messageText){

                case "/start" :
                    startAnswer (command);
                    break;

                default:
                        try{
                        execute(
                                SendMessage.builder()
                                        .chatId(command.getChatId())
                                        .parseMode("Markdown")
                                        .text("Ошибка ввода текста.Повторите попытку!")
                                        .build());
                    }
                    catch (TelegramApiException e){
                        e.printStackTrace();
                    }
                    break;
            }

        }
        else if (update.hasCallbackQuery())
        {
            if (update.getCallbackQuery().getData().equals("start0")) {
                try{
                    execute(
                            SendMessage.builder()
                                    .chatId(update.getCallbackQuery().getMessage().getChatId())
                                    .parseMode("Markdown")
                                    .text("Hello world !")
                                    .build());
                }
                catch (TelegramApiException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public void startAnswer(Message command){
        sendWithOutUrl(command);
    }
}
