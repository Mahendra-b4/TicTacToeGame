package tictactoe.models;

import java.util.Scanner;

public class Player {
    private int id;
    private String name;
    private char symbol;
    private PlayerType playerType;
    private Scanner scanner;

    public Player(int id, String name, char symbol, PlayerType playerType) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
        scanner = new Scanner(System.in);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Cell makeMove(Board board) {
        System.out.println("Hi "+getName()+" it's your turn make a move, by entering row and col");
        int row = scanner.nextInt();
        int col = scanner.nextInt();

        while(!validateMove(row, col, board)){
            System.out.println("Invalid move, kindly make move again");
            row = scanner.nextInt();
            col = scanner.nextInt();
        }

        Cell cell = board.getBoard().get(row).get(col);

        cell.setCellState(CellState.FILLED);
        cell.setPlayer(this);

        return cell;
    }

    private boolean validateMove(int row, int col, Board board) {
        if(row < 0 || row > 3){
            return false;
        }
        if(col < 0 || col > 3){
            return false;
        }
        if(CellState.FILLED.equals(board.getBoard().get(row).get(col))){
            return false;
        }
        return true;
    }
}
