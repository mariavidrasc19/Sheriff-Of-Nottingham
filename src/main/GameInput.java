package main;

public class GameInput {
    private final char[][] gamePlatform;
    private final String[][] gamePlayers;
    private final char[][] gameMoves;
    private final String[][] gameAngels;

    GameInput() {
        this.gamePlatform = new char[Constants.ZERO][];
        this.gamePlayers = new String[Constants.ZERO][];
        this.gameMoves = new char[Constants.ZERO][];
        this.gameAngels = new String[Constants.ZERO][];
    }

    public GameInput(final char[][] gamePlatform, final String[][] gamePlayers,
                     final char[][] gameMoves, final String[][] gameAngels) {
        this.gamePlatform = gamePlatform;
        this.gamePlayers = gamePlayers;
        this.gameMoves = gameMoves;
        this.gameAngels = gameAngels;
    }

    final char[][] getGamePlatform() {
        return gamePlatform;
    }

    final String[][] getGamePlayers() {
        return gamePlayers;
    }

    final char[][] getGameMoves() {
        return gameMoves;
    }

    public String[][] getGameAngels() {
        return gameAngels;
    }
}
