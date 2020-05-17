package main;

import fileio.FileSystem;

class GameInputLoader {
    private final String inputPath;
    private final String outputPath;

    GameInputLoader(final String inputPath, final String outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    final GameInput load() {
        int n;
        int m;
        char[][] gamePlatform = new char[Constants.ONE][Constants.ONE];
        int nrPlayers;
        String[][] gamePlayers = new String[Constants.ONE][Constants.ONE];
        int nrRounds;
        char[][] gameMoves = new char[Constants.ONE][Constants.ONE];
        String[][] gameAngels = new String[Constants.ONE][Constants.ONE];

        try {
            FileSystem fs = new FileSystem(inputPath, outputPath);
            n = fs.nextInt();
            m = fs.nextInt();
            gamePlatform = new char[n][m];
            for (int i = Constants.ZERO; i < n; ++i) {
                String line = fs.nextWord();
                for (int j = Constants.ZERO; j < m; ++j) {
                    gamePlatform[i][j] = line.charAt(j);
                }
            }
            nrPlayers = fs.nextInt();
            gamePlayers = new String[nrPlayers][Constants.THREE];
            for (int i = Constants.ZERO; i < nrPlayers; ++i) {
                for (int j = Constants.ZERO; j < Constants.THREE; ++j) {
                    gamePlayers[i][j] = fs.nextWord();
                }
            }

            nrRounds = fs.nextInt();
            gameMoves = new char[nrRounds][nrPlayers];
            for (int i = Constants.ZERO; i < nrRounds; ++i) {
                String line = fs.nextWord();
                for (int j = Constants.ZERO; j < nrPlayers; ++j) {
                    gameMoves[i][j] = line.charAt(j);
                }
            }

            gameAngels = new String[nrRounds][Constants.TEN];
            for (int i = Constants.ZERO; i < nrRounds; ++i) {
                int nrAngels = fs.nextInt();
                for (int j = Constants.ZERO; j < nrAngels; j++) {
                    String angels = fs.nextWord();
                    gameAngels[i][j] = angels;
                }
            }
            fs.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return new GameInput(gamePlatform, gamePlayers, gameMoves, gameAngels);
    }
}
