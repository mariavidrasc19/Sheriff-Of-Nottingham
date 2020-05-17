package main;

import angels.Angels;
import angels.DamageAngel;
import angels.DarkAngel;
import angels.Dracula;
import angels.GoodBoy;
import angels.LevelUpAngel;
import angels.LifeGiver;
import angels.SmallAngel;
import angels.Spawner;
import angels.TheDoomer;
import angels.XPAngel;

import jucatori.Wizard;
import jucatori.Pyromancer;
import jucatori.Rogue;
import jucatori.Knight;
import jucatori.Players;
import jucatori.Status;
import jucatori.Ground;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class Main {
    public static void main(final String[] args) throws IOException {
        // dau ca argumente fisierele de intrare si iesire
        GameInputLoader gameInputLoader =
                new GameInputLoader(args[Constants.ZERO], args[Constants.ONE]);
        GameInput gameInput = gameInputLoader.load();

        // directionez scrierea in fisier catre System.out.print()
        PrintStream o = new PrintStream(new File(args[Constants.ONE]));
        PrintStream console = System.out;
        System.setOut(o);
        //System.out.println(Arrays.deepToString(gameInput.getGameAngels().clone()));

        // am creat o matrice care retine tipurile de terenuri folosindu-ne
        // de matricea de char-uri citita in fisier
        // matricea va avea alemente de tip ground (adica Land/Volcanic/Woods/Desert)
        Ground[][] gamePlatform = new Ground[gameInput.getGamePlatform().
                length][gameInput.getGamePlatform()[Constants.ZERO].length];
        for (int i = Constants.ZERO; i < gameInput.getGamePlatform().length; ++i) {
            for (int j = Constants.ZERO; j < gameInput.getGamePlatform()
                    [Constants.ZERO].length; ++j) {
                char ground = gameInput.getGamePlatform()[i][j];
                switch (ground) {
                    case 'W':
                        gamePlatform[i][j] = Ground.Woods;
                        break;
                    case 'V':
                        gamePlatform[i][j] = Ground.Volcanic;
                        break;
                    case 'L':
                        gamePlatform[i][j] = Ground.Land;
                        break;
                    case 'D':
                        gamePlatform[i][j] = Ground.Desert;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + ground);
                }
            }
        }
        // System.out.println(Arrays.deepToString(gamePlatform.clone()));

        // am creat o lista care retine jucatorii si informatiile despre ei
        // folosindu-ma de matricea de String-uri citita in fisier
        ArrayList<Players> gamePlayers = new ArrayList<Players>();
        for (int i = Constants.ZERO; i < gameInput.getGamePlayers().length; ++i) {
            Players player = new Players();
            Ground ground = gamePlatform[Integer.parseInt(gameInput.getGamePlayers()[i]
                    [Constants.ONE])][Integer.parseInt(gameInput.getGamePlayers()[i][2])];
            switch (gameInput.getGamePlayers()[i][Constants.ZERO]) {
                // la fiecare jucator se apeleaza constructorul aferent tipului
                // sau/si apoi se adauga in lista de jucatori
                case "K":
                    player = new Knight(Integer.parseInt(gameInput.getGamePlayers()
                            [i][Constants.ONE]),
                            Integer.parseInt(gameInput.getGamePlayers()[i][Constants.TWO]),
                            ground, i);
                    gamePlayers.add(player);
                    break;
                case "P":
                    player = new Pyromancer(Integer.parseInt(gameInput.
                            getGamePlayers()[i][Constants.ONE]),
                            Integer.parseInt(gameInput.getGamePlayers()[i][Constants.TWO]),
                            ground, i);
                    gamePlayers.add(player);
                    break;
                case "R":
                    player = new Rogue(Integer.parseInt(gameInput.getGamePlayers()
                            [i][Constants.ONE]),
                            Integer.parseInt(gameInput.getGamePlayers()[i][Constants.TWO]),
                            ground, i);
                    gamePlayers.add(player);
                    break;
                case "W":
                    player = new Wizard(Integer.parseInt(gameInput.getGamePlayers()
                            [i][Constants.ONE]),
                            Integer.parseInt(gameInput.getGamePlayers()[i][Constants.TWO]),
                            ground, i);
                    gamePlayers.add(player);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: "
                            + gameInput.getGamePlayers()[i][Constants.ZERO]);
            }
        }

        // logica jocului
        char[][] gameMove = gameInput.getGameMoves();

        // se creaza o matricea de ingeri implicati in batalie
        Angels[][] gameAngels = new Angels[gameMove.length][Constants.TEN];
        for (int i = Constants.ZERO; i < gameInput.getGameAngels().length; i++) {
            for (int j = Constants.ZERO; j < gameInput.getGameAngels()[Constants.ZERO].length; j++) {
                if (gameInput.getGameAngels()[i][j] == null) {
                    j = gameInput.getGameAngels()[Constants.ZERO].length;
                } else {
                    int positionInCol = Constants.MINUS_ONE;
                    int positionInRow = Constants.MINUS_ONE;
                    String typeOfAngel = null;
                    String word = gameInput.getGameAngels()[i][j];
                    for (int k = Constants.ZERO; k < word.length(); k++) {
                        if (word.charAt(k) == ',') {
                            k++;
                            int nextComma = k + Constants.ONE;
                            if (word.charAt(nextComma) != ',') {
                                nextComma++;
                            }
                            positionInRow = Integer.parseInt(word.substring(k, nextComma));
                            positionInCol = Integer.parseInt(word.substring(nextComma + 1));
                            k = word.length();

                            switch (typeOfAngel) {
                                case "DamageAngel":
                                    gameAngels[i][j] = new DamageAngel(positionInRow,
                                            positionInCol);
                                    break;
                                case "DarkAngel":
                                    gameAngels[i][j] = new DarkAngel(positionInRow,
                                            positionInCol);
                                    break;
                                case "Dracula":
                                    gameAngels[i][j] = new Dracula(positionInRow,
                                            positionInCol);
                                    break;
                                case "GoodBoy":
                                    gameAngels[i][j] = new GoodBoy(positionInRow,
                                            positionInCol);
                                    break;
                                case "LevelUpAngel":
                                    gameAngels[i][j] = new LevelUpAngel(positionInRow,
                                            positionInCol);
                                    break;
                                case "LifeGiver":
                                    gameAngels[i][j] = new LifeGiver(positionInRow,
                                            positionInCol);
                                    break;
                                case "SmallAngel":
                                    gameAngels[i][j] = new SmallAngel(positionInRow,
                                            positionInCol);
                                    break;
                                case "Spawner":
                                    gameAngels[i][j] = new Spawner(positionInRow,
                                            positionInCol);
                                    break;
                                case "TheDoomer":
                                    gameAngels[i][j] = new TheDoomer(positionInRow,
                                            positionInCol);
                                    break;
                                case "XPAngel":
                                    gameAngels[i][j] = new XPAngel(positionInRow,
                                            positionInCol);
                                    break;
                            }
                        } else {
                            typeOfAngel = word.substring(Constants.ZERO, k + Constants.ONE);
                        }
                    }
                }
            }
        }
        //System.out.println(Arrays.deepToString(gameAngels.clone()));

        for (int i = Constants.ZERO; i < gameMove.length; ++i) {
            // pentru fiecare runda i:

            //verific daca avem ingeri spawnati in runda si ii pun intr-un ArrayList
            ArrayList<Angels> angelsInRound = new ArrayList<Angels>();
            for (int k = Constants.ZERO; k < gameAngels[Constants.ZERO].length; k++) {
                if (gameAngels[i][k] == null) {
                    k = gameAngels[Constants.ZERO].length;
                } else {
                    angelsInRound.add(gameAngels[i][k]);
                }
            }

            // aici se citesc din fisier ingerii si sunt implementati in joc
            int num = i + 1;
            System.out.println("~~ Round " + num + " ~~");

            // jucatorii isi schimba pozitia la inceputul rundei
            for (int j = Constants.ZERO; j < gameMove[i].length; ++j) {
                // fiecare jucator isi schimba pozitia in functie de semnul indicat in matrice
                // daca nu este intepenit
                if (!gamePlayers.get(j).isStun()) {
                    switch (gameMove[i][j]) {
                        case '_':
                            break;
                        case 'U':
                            gamePlayers.get(j).setPositionInTheRow(
                                    gamePlayers.get(j).getPositionInTheRow() - Constants.ONE);
                            break;
                        case 'D':
                            gamePlayers.get(j).setPositionInTheRow(
                                    gamePlayers.get(j).getPositionInTheRow() + Constants.ONE);
                            break;
                        case 'L':
                            gamePlayers.get(j).setPositionInTheCol(
                                    gamePlayers.get(j).getPositionInTheCol() - Constants.ONE);
                            break;
                        case 'R':
                            gamePlayers.get(j).setPositionInTheCol(
                                    gamePlayers.get(j).getPositionInTheCol() + Constants.ONE);
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + gameMove[i][j]);
                    }
                }
            }

            // acum se executa batalia dupa mutarea tuturor jucatorilor
            // se merge pe fiecare platforma de lupta si se realizeaza, daca este cazul, batalia
            for (int l = Constants.ZERO; l < gamePlatform.length; ++l) {
                for (int j = Constants.ZERO; j < gamePlatform[l].length; ++j) {
                    /* se cauta jucatorii ce au fost repartizati sau mutati pe
                    platforma gamePlatform[l][j] */
                    ArrayList<Integer> playersInSquare = new ArrayList<Integer>();
                    ArrayList<Integer> allPlayersInSquare = new ArrayList<Integer>();
                    for (int k = Constants.ZERO; k < gamePlayers.size(); ++k) {
                        if ((gamePlayers.get(k).getPositionInTheCol() == j)
                                && (gamePlayers.get(k).getPositionInTheRow() == l)) {
                            allPlayersInSquare.add(k);
                            if (gamePlayers.get(k).getStatus().equals(Status.Alive)) {
                                playersInSquare.add(k);
                            }
                        }
                    }

                    // se vor bate daca sunt 2 competitori pe acelasi teren
                    if (playersInSquare.size() == 2) {
                        Players player1 = gamePlayers.get(playersInSquare.get(Constants.ZERO));
                        Players player2 = gamePlayers.get(playersInSquare.get(Constants.ONE));
                        // aici se ataca intre ei

                        // variabilele vor retine punctajele jucatorilor inainte de batalie
                        float player1Hp;
                        float player2Hp;

                        // in cazul in care un jucator va fi Wizard se va da la functia accep()
                        // un al treilea parametru
                        if (player1 instanceof Wizard) {
                            if (player1.getTemporalDamage().size() != Constants.ZERO) {
                                player1.setHp(player1.getHp()
                                        - player1.getTemporalDamage().get(Constants.ZERO));
                                player1.getTemporalDamage().remove(Constants.ZERO);
                            }

                            //jucatorul isi face strategia
                            if (!player2.isStun()) {
                                player2.makeStrategy();
                            }
                            player2.setStun(false);

                            player1Hp = player1.getHp();
                            player1.accept(player2, Constants.ZERO);

                            float totalDamage = player2.getDamageWithoutRaceModifiers();
                            player2.setDamageWithoutRaceModifiers(Constants.ZERO);

                            if (player2.getTemporalDamage().size() != Constants.ZERO) {
                                player2.setHp(player2.getHp() - player2.getTemporalDamage().
                                        get(Constants.ZERO));
                                player2.getTemporalDamage().remove(Constants.ZERO);
                            }

                            // jucatorul isi pregateste strategia
                            if (!player1.isStun()) {
                                player1.makeStrategy();
                                if (i == Constants.ZERO) {
                                    player1.setStrategy(Constants.ZERO);
                                }
                            }
                            player1.setStun(false);

                            player2Hp = player2.getHp();
                            player2.accept(player1, totalDamage);
                        } else if (player2 instanceof Wizard) {
                            if (player2.getTemporalDamage().size() != Constants.ZERO) {
                                player2.setHp(player2.getHp() - player2.getTemporalDamage().
                                        get(Constants.ZERO));
                                player2.getTemporalDamage().remove(Constants.ZERO);
                            }

                            // jucatorul isi pregateste strategia
                            if (!player1.isStun()) {
                                player1.makeStrategy();
                            }
                            player1.setStun(false);

                            player2Hp = player2.getHp();
                            player2.accept(player1, Constants.ZERO);

                            float totalDamage = player1.getDamageWithoutRaceModifiers();
                            player1.setDamageWithoutRaceModifiers(Constants.ZERO);

                            if (player1.getTemporalDamage().size() != Constants.ZERO) {
                                player1.setHp(player1.getHp() - player1.getTemporalDamage().
                                        get(Constants.ZERO));
                                player1.getTemporalDamage().remove(Constants.ZERO);
                            }

                            //jucatorul isi face strategia
                            if (!player2.isStun()) {
                                player2.makeStrategy();
                                if (i == Constants.ZERO) {
                                    player2.setStrategy(Constants.ZERO);
                                }
                            }
                            player2.setStun(false);

                            player1Hp = player1.getHp();
                            player1.accept(player2, totalDamage);
                        } else {
                            /* jucatorul, inainte sa fie atacat, isi ia damage-ul temporal,
                            daca are din rundele trecute */
                            if (player1.getTemporalDamage().size() != Constants.ZERO) {
                                player1.setHp(player1.getHp() - player1.getTemporalDamage().
                                        get(Constants.ZERO));
                                player1.getTemporalDamage().remove(Constants.ZERO);
                            }
                            if (player2.getTemporalDamage().size() != Constants.ZERO) {
                                player2.setHp(player2.getHp() - player2.getTemporalDamage().
                                        get(Constants.ZERO));
                                player2.getTemporalDamage().remove(Constants.ZERO);
                            }
                            // fiecare jucator isi pregateste strategia
                            if (!player1.isStun()) {
                                player1.makeStrategy();
                            }
                            if (!player2.isStun()) {
                                player2.makeStrategy();
                            }
                            player1.setStun(false);
                            player2.setStun(false);

                            // inainte sa i se aplica damage-ul pt batalie,
                            // jucatorului i se retine Hp-ul vechi
                            player1Hp = player1.getHp();
                            player1.accept(player2, Constants.ZERO);

                            player2Hp = player2.getHp();
                            player2.accept(player1, Constants.ZERO);
                        }

                        // daca unul din jucatori a murit de la dama-ul temporal,
                        // inainte de batalie, celuilalt i se acorda punctajul
                        // pe care l-a pierdut in urma bataliei
                        if ((player1Hp <= Constants.ZERO) && (player2Hp
                                > Constants.ZERO)) {
                            player2.setHp(player2Hp);
                        }
                        if ((player2Hp <= Constants.ZERO) && (player1Hp
                                > Constants.ZERO)) {
                            player1.setHp(player1Hp);
                        }

                        // batalia s-a terminat si
                        // se seteaza statusul de mort daca unul din+ jucatori are hp <= 0
                        if (player1.getHp() <= Constants.ZERO) {
                            player1.setStatus(Status.Dead);
                        }
                        if (player2.getHp() <= Constants.ZERO) {
                            player2.setStatus(Status.Dead);
                        }
                        // se verifica daca a castigat cineva
                        if (player1.getStatus().equals(Status.Dead)
                                && (player2.getStatus().equals(Status.Dead))) {
                            System.out.println("Player "
                                    + gamePlayers.get(playersInSquare.get(Constants.ONE))
                                    .getName()
                                    + " " + gamePlayers.get(playersInSquare.get(Constants.ONE))
                                    .getId()
                                    + " was killed by "
                                    + gamePlayers.get(playersInSquare.get(Constants.ZERO))
                                    .getName()
                                    + " " + gamePlayers.get(playersInSquare.get(Constants.ZERO))
                                    .getId());
                            System.out.println("Player "
                                    + gamePlayers.get(playersInSquare.get(Constants.ZERO))
                                    .getName()
                                    + " " + gamePlayers.get(playersInSquare.get(Constants.ZERO))
                                    .getId()
                                    + " was killed by "
                                    + gamePlayers.get(playersInSquare.get(Constants.ONE)).getName()
                                    + " " + gamePlayers.get(playersInSquare.get(Constants.ONE))
                                    .getId());
                        } else if (player1.getStatus().equals(Status.Dead)) {
                            System.out.println("Player "
                                    + gamePlayers.get(playersInSquare.get(Constants.ZERO))
                                    .getName()
                                    + " " + gamePlayers.get(playersInSquare.get(Constants.ZERO))
                                    .getId()
                                    + " was killed by "
                                    + gamePlayers.get(playersInSquare.get(Constants.ONE)).getName()
                                    + " " + gamePlayers.get(playersInSquare.get(Constants.ONE))
                                    .getId());
                            // i se acorda castigatorului puncte de experienta
                            if (player2.getHp() - player2Hp < Constants.ZERO) {
                                if (Constants.TWO * Constants.ONE_HUNDRED
                                        - (player2.getLevel()
                                        - player1.getLevel()) * Constants.FORTY
                                        > Constants.ZERO) {
                                    player2.setXp(player2.getXp() + Constants.TWO
                                            * Constants.ONE_HUNDRED - (player2.getLevel()
                                            - player1.getLevel()) * Constants.FORTY);
                                }
                            }
                            gamePlayers.get(playersInSquare.get(Constants.ONE)).setLevel();
                        } else if (player2.getStatus().equals(Status.Dead)) {
                            System.out.println("Player "
                                    + gamePlayers.get(playersInSquare.get(Constants.ONE)).getName()
                                    + " " + gamePlayers.get(playersInSquare.get(Constants.ONE))
                                    .getId()
                                    + " was killed by "
                                    + gamePlayers.get(playersInSquare.get(Constants.ZERO))
                                    .getName()
                                    + " " + gamePlayers.get(playersInSquare.get(Constants.ZERO))
                                    .getId());
                            // i se acorda castigatorului puncte de experienta
                            if (player1.getHp() - player1Hp < Constants.ZERO) {
                                if (Constants.TWO * Constants.ONE_HUNDRED
                                        - (player1.getLevel() - player2.getLevel())
                                        * Constants.FORTY > Constants.ZERO) {
                                    player1.setXp(player1.getXp() + Constants.TWO
                                            * Constants.ONE_HUNDRED - (player1.getLevel()
                                            - player2.getLevel()) * Constants.FORTY);
                                }
                            }
                            gamePlayers.get(playersInSquare.get(Constants.ZERO)).setLevel();
                        }
                        // se acorda punctajele jucatorilor in gamePlayers
                        gamePlayers.get(playersInSquare.get(Constants.ZERO)).
                                setStatus(player1.getStatus());
                        gamePlayers.get(playersInSquare.get(Constants.ZERO)).
                                setHp(player1.getHp());
                        gamePlayers.get(playersInSquare.get(Constants.ZERO)).
                                setXp(player1.getXp());

                        gamePlayers.get(playersInSquare.get(Constants.ONE)).
                                setStatus(player2.getStatus());
                        gamePlayers.get(playersInSquare.get(Constants.ONE)).
                                setHp(player2.getHp());
                        gamePlayers.get(playersInSquare.get(Constants.ONE)).
                                setXp(player2.getXp());
                    }
                }
            }

            for (int l = Constants.ZERO; l < gamePlatform.length; ++l) {
                for (int j = Constants.ZERO; j < gamePlatform[l].length; ++j) {
                    /* se cauta jucatorii ce au fost repartizati sau mutati pe
                    platforma gamePlatform[l][j] */
                    ArrayList<Integer> playersInSquare = new ArrayList<Integer>();
                    ArrayList<Integer> allPlayersInSquare = new ArrayList<Integer>();
                    for (int k = Constants.ZERO; k < gamePlayers.size(); ++k) {
                        if ((gamePlayers.get(k).getPositionInTheCol() == j)
                                && (gamePlayers.get(k).getPositionInTheRow() == l)) {
                            allPlayersInSquare.add(k);
                            if (gamePlayers.get(k).getStatus().equals(Status.Alive)) {
                                playersInSquare.add(k);
                            }
                        }
                    }
                    // intra in actiune ingerii, daca exista
                    for (Angels angel : angelsInRound) {
                        if ((angel.getPositionInTheRow() == l)
                                && (angel.getPositionInTheCol() == j)) {
                            System.out.println("Angel " + angel.getName() + " was spawned at "
                                    + angel.getPositionInTheRow() + " "
                                    + angel.getPositionInTheCol());
                            /*daca s-a spawnat un inger pe aceasta platforma actioneaza asupra
                            jucatorilor double dispatch intre ingeri si eroi
                            jucatorul accepta sa interactionezee cu ingerul
                            iar acesta interactioneaza cu el */

                            for (int p = 0; p < allPlayersInSquare.size(); p++) {
                                float oldHp = gamePlayers.get(allPlayersInSquare.get(p)).getHp();
                                Status oldStatus = gamePlayers.get(allPlayersInSquare.get(p))
                                        .getStatus();
                                gamePlayers.get(allPlayersInSquare.get(p)).accept(angel);
                                float newHp = gamePlayers.get(allPlayersInSquare.get(p)).getHp();
                                if ((oldStatus.equals(Status.Alive))
                                        && (angel instanceof Spawner)) {
                                } else if (gamePlayers.get(allPlayersInSquare.get(p)).getStatus()
                                        .equals(Status.Alive)) {
                                    if (oldHp <= newHp) {
                                        System.out.println(angel.getName() + " helped "
                                                + gamePlayers.get(allPlayersInSquare.get(p))
                                                .getName()
                                                + " " + gamePlayers.get(allPlayersInSquare.get(p))
                                                .getId());
                                    } else {
                                        System.out.println(angel.getName() + " hit "
                                                + gamePlayers.get(allPlayersInSquare.get(p))
                                                .getName()
                                                + " " + gamePlayers.get(allPlayersInSquare.get(p))
                                                .getId());
                                    }
                                }
                                if (gamePlayers.get(allPlayersInSquare.get(p)).getStatus()
                                        .equals(Status.Alive)) {
                                    gamePlayers.get(allPlayersInSquare.get(p)).setLevel();
                                }
                                if (gamePlayers.get(allPlayersInSquare.get(p)).getHp()
                                        <= Constants.ZERO) {
                                    if (oldStatus.equals(Status.Alive)) {
                                        gamePlayers.get(allPlayersInSquare.get(p))
                                                .setStatus(Status.Dead);
                                        System.out.println("Player "
                                                + gamePlayers.get(allPlayersInSquare.get(p))
                                                .getName()
                                                + " " + gamePlayers.get(allPlayersInSquare
                                                .get(p)).getId()
                                                + " was killed by an angel");
                                    }
                                } else if ((oldStatus.equals(Status.Dead))
                                        && (gamePlayers.get(allPlayersInSquare.get(p)).
                                        getStatus().equals(Status.Alive))) {
                                    System.out.println("Player "
                                            + gamePlayers.get(allPlayersInSquare.get(p)).getName()
                                            + " " + gamePlayers.get(allPlayersInSquare.get(p))
                                            .getId()
                                            + " was brought to life by an angel");
                                }
                            }
                        }
                    }
                }
            }
            // aici se v-a printa rezultatul fiecarei runde, conform cerintei
            System.out.println();
        }

        // se printeaza situatia jucatorilor
        System.out.println("~~ Results ~~");
        for (int i = Constants.ZERO; i < gamePlayers.size(); ++i) {
            char player = '\0';
            if (gamePlayers.get(i) instanceof Knight) {
                player = 'K';
            } else if (gamePlayers.get(i) instanceof Pyromancer) {
                player = 'P';
            } else if (gamePlayers.get(i) instanceof Rogue) {
                player = 'R';
            } else if (gamePlayers.get(i) instanceof Wizard) {
                player = 'W';
            }
            // se printeaza un anumit patern in functie de statusul Dead/Alive al jucatorului
            if (gamePlayers.get(i).getStatus().equals(Status.Alive)) {
                int hp = Math.round(gamePlayers.get(i).getHp());
                System.out.println(player + " " + gamePlayers.get(i).getLevel() + " "
                        + gamePlayers.get(i).getXp() + " " + hp + " "
                        + gamePlayers.get(i).getPositionInTheRow() + " "
                        + gamePlayers.get(i).getPositionInTheCol());
            } else {
                System.out.println(player + " dead");
            }
        }
    }
}
