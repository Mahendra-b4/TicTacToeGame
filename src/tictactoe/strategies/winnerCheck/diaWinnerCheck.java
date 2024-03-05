package tictactoe.strategies.winnerCheck;

import tictactoe.models.Board;
import tictactoe.models.Move;
import tictactoe.models.Player;

import java.util.HashMap;
import java.util.Map;

public class diaWinnerCheck implements WinnerCheck{
    Map<Character, Integer> left_dia = new HashMap<>();
    Map<Character, Integer> right_dia = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        Player cur_player = move.getPlayer();
        char symbol = cur_player.getSymbol();
        int row_number = move.getCell().getRow();
        int col_number = move.getCell().getCol();
        if(row_number == col_number){
            if(!left_dia.containsKey(symbol)){
                left_dia.put(symbol, 0);
            }
            left_dia.put(symbol, left_dia.get(symbol)+1);
            if(left_dia.get(symbol).equals(board.getBoard().size())){
                return true;
            }
        }
        if((row_number+col_number) == (board.getBoard().size()-1)){
            if(!right_dia.containsKey(symbol)){
                right_dia.put(symbol, 0);
            }
            right_dia.put(symbol, right_dia.get(symbol)+1);
            if(right_dia.get(symbol).equals(board.getBoard().size())){
                return true;
            }
        }
        return false;
    }
}
