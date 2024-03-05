package tictactoe.strategies.botPlayingStragies;

import tictactoe.models.BotPlayingLevel;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy getBotPlayingStrategyForLevel(BotPlayingLevel botPlayingLevel){
        if(BotPlayingLevel.EASY.equals(botPlayingLevel)){
            return new EasyBotPlayingStrategy();
        }
        else if(BotPlayingLevel.MEDIUM.equals(botPlayingLevel)){
            return new MediumBotPlayingStrategy();
        }
        return new HardBotPlayingStrategy();
    }
}
