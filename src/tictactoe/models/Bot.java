package tictactoe.models;

import tictactoe.strategies.botPlayingStragies.BotPlayingStrategy;
import tictactoe.strategies.botPlayingStragies.BotPlayingStrategyFactory;

public class Bot extends Player{

    private BotPlayingLevel botPlayingLevel;
    private BotPlayingStrategy botPlayingStrategy;
    public Bot(int id, String name, char symbol, PlayerType playerType, BotPlayingLevel botPlayingLevel) {
        super(id, name, symbol, playerType);
        this.botPlayingLevel = botPlayingLevel;
        botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategyForLevel(botPlayingLevel);
    }

    public BotPlayingLevel getBotPlayingLevel() {
        return botPlayingLevel;
    }

    public void setBotPlayingLevel(BotPlayingLevel botPlayingLevel) {
        this.botPlayingLevel = botPlayingLevel;
    }

    @Override
    public Cell makeMove(Board board){
        System.out.println("It's Bot turn, wait for the move");
        Cell cell = botPlayingStrategy.makeMove(board);

        cell.setCellState(CellState.FILLED);
        cell.setPlayer(this);
        return cell;
    }
}
