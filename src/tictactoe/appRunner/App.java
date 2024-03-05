package tictactoe.appRunner;

import tictactoe.controller.GameController;
import tictactoe.exceptions.DuplicateSymbolFoundException;
import tictactoe.exceptions.MoreThanOneBotException;
import tictactoe.models.*;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) throws MoreThanOneBotException, DuplicateSymbolFoundException {
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player(1, "MB", 'X', PlayerType.HUMAN));
        playerList.add(new Bot(2, "BOT", 'O', PlayerType.BOT, BotPlayingLevel.EASY));

        int tournament = 1;

        GameController gameController = new GameController();
        Game game = gameController.startGame(playerList, tournament);
//        game.printBoard();

        while(game.getGameStatus().equals(GameStatus.IN_PROG)){
            gameController.printBoard(game);

            gameController.makeMove(game);
        }
        if(game.getGameStatus().equals(GameStatus.SUCCESS)){
            System.out.println(game.getWinningPlayer().getName() + " Congratulations!!, you are a winner");
        }
        if(game.getGameStatus().equals(GameStatus.DRAW)){
            System.out.println("Game Draw");;
        }
    }
}
