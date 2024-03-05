package tictactoe.models;

import tictactoe.exceptions.DuplicateSymbolFoundException;
import tictactoe.exceptions.MoreThanOneBotException;
import tictactoe.strategies.winnerCheck.WinnerCheck;
import tictactoe.strategies.winnerCheck.colWinnerCheck;
import tictactoe.strategies.winnerCheck.diaWinnerCheck;
import tictactoe.strategies.winnerCheck.rowWinnerCheck;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private Board board;
    private List<Player> players;
    private int tournament;
    private List<Move> moves;
    private GameStatus gameStatus;
    private Player winningPlayer;
    private int nextPlayerIndex;
    private List<WinnerCheck> winnerCheckList;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getTournament() {
        return tournament;
    }

    public void setTournament(int tournament) {
        this.tournament = tournament;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Player getWinningPlayer() {
        return winningPlayer;
    }

    public void setWinningPlayer(Player winningPlayer) {
        this.winningPlayer = winningPlayer;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public List<WinnerCheck> getWinnerCheckList() {
        return winnerCheckList;
    }

    public void setWinnerCheckList(List<WinnerCheck> winnerCheckList) {
        this.winnerCheckList = winnerCheckList;
    }

    private Game(List<Player> players, int tournament) {
        this.board = new Board();
        this.players = players;
        this.tournament = tournament;
        this.moves = new ArrayList<>();
        this.gameStatus = GameStatus.IN_PROG;
        this.nextPlayerIndex = 0;
        winnerCheckList = new ArrayList<>();
        winnerCheckList.add(new rowWinnerCheck());
        winnerCheckList.add(new colWinnerCheck());
        winnerCheckList.add(new diaWinnerCheck());
    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public void printBoard() {
        board.printBoard();
    }

    public void makeMove() {
        Player cur_player = players.get(nextPlayerIndex);
        Cell cell = cur_player.makeMove(board);

        Move move = new Move(cell, cur_player);
        moves.add(move);

        if(checkWinner(board, move)){
            gameStatus = GameStatus.SUCCESS;
            winningPlayer = cur_player;
            System.out.println("The winner is "+cur_player.getName());
            printBoard();
            return;
        }
        if(moves.size() == board.getBoard().size()*board.getBoard().size()){
            gameStatus = GameStatus.DRAW;
            return;
        }
        nextPlayerIndex++;
        nextPlayerIndex %= players.size();

    }

    private boolean checkWinner(Board board, Move move) {
// should implement this
        for(WinnerCheck winnerCheck:winnerCheckList){
            if(winnerCheck.checkWinner(board, move)){
                return true;
            }
        }
        return false;
    }

    public static class Builder{
        private List<Player> players;
        private int tournament;


        public Game build() throws MoreThanOneBotException, DuplicateSymbolFoundException {
//            Validate for only 1 bot and unique symbol for players
            validateBot(players);
            validateUniqueSymbolForPlayers(players);
            return new Game(players, tournament);
        }

        private void validateUniqueSymbolForPlayers(List<Player> players) throws DuplicateSymbolFoundException {
            Set<Character> symbols = new HashSet<>();
            for(Player player:players){
                if(symbols.contains(player.getSymbol())){
                    throw new DuplicateSymbolFoundException();
                }
            }
        }

        private void validateBot(List<Player> players) throws MoreThanOneBotException {
            int botCount = 0;
            for(Player player:players){
                if(PlayerType.BOT.equals(player.getPlayerType())){
                    botCount++;
                }
            }
            if(botCount > 1){
                throw new MoreThanOneBotException();
            }
        }

        public List<Player> getPlayers() {
            return players;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public int getTournament() {
            return tournament;
        }

        public Builder setTournament(int tournament) {
            this.tournament = tournament;
            return this;
        }
    }
}
