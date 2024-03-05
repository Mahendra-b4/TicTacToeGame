package tictactoe.strategies.winnerCheck;

import tictactoe.models.Board;
import tictactoe.models.Move;

public interface WinnerCheck {

    public boolean checkWinner(Board board, Move move);

}
