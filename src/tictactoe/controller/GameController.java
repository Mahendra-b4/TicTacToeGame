package tictactoe.controller;


import tictactoe.exceptions.DuplicateSymbolFoundException;
import tictactoe.exceptions.MoreThanOneBotException;
import tictactoe.models.Game;
import tictactoe.models.Player;

import java.util.List;

public class GameController {

    public Game startGame(List<Player> players, int tournment)
            throws MoreThanOneBotException, DuplicateSymbolFoundException {

        return Game.getBuilder()
                   .setPlayers(players)
                   .setTournament(tournment)
                   .build();
    }

    public void printBoard(Game game){
        game.printBoard();
    }

    public void makeMove(Game game){
        game.makeMove();
    }
}
