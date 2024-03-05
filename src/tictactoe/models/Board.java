package tictactoe.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }

    private List<List<Cell>> board;

    public Board(){
        board = new ArrayList<>();

        for(int i=0 ; i<3 ; i++){
            board.add(new ArrayList<>());

            for(int j=0 ; j<3 ; j++){
                board.get(i).add(new Cell(i, j));
            }
        }
    }

    public void printBoard() {
        for(List<Cell> row:board){
            for(Cell cell:row){
                cell.display();
            }
            System.out.println();
        }

    }
}
