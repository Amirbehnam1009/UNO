package uno;

import uno.card.*;
import uno.color.Color;
import uno.player.ComputerPlayer;
import uno.player.HumanPlayer;
import uno.player.Player;
import uno.rotation.Anticlockwise;
import uno.rotation.Clockwise;
import uno.rotation.GameRotation;

import java.util.*;

public class UnoGame {
    /**
     * clockwise instance of the rotation
     */
    private final static GameRotation clockwise = new Clockwise();
    /**
     * anti-clockwise instance of the rotation
     */
    private final static GameRotation antiClockWise = new Anticlockwise();
    /**
     * determines end of the game
     */
    private boolean end = false;
    /**
     * store instance of the game
     */
    private Store store;
    /**
     * ground instance of the game
     */
    private Ground ground;
    /**
     * current rotation of the game
     */
    private GameRotation gameRotation;
    /**
     * holds all players of te game
     */
    private List<Player> players;
    /**
     * turn of the of the game
     */
    private Player turn;

    /**
     * Main method of the Uno game.
     *
     * @param args program arguments
     */
    public static void main(String[] args) {
        UnoGame unoGame = new UnoGame();
        unoGame.play();
    }

    /**
     * Gets ground instance of the game.
     *
     * @return ground instance of the game
     */
    public Ground getGround() {
        return ground;
    }

    /**
     * Gets current rotation of the game
     *
     * @return rotation instance of the game
     */
    public GameRotation getGameRotation() {
        return gameRotation;
    }

    /**
     * Gets turn of the game.
     *
     * @return turn
     */
    public Player getTurn() {
        return turn;
    }

    /**
     * Sets turn of the game
     *
     * @param skip determines skip next player
     */
    public void setTurn(boolean skip) {
        turn = gameRotation.getNextPlayer(turn, players, skip);
    }

    /**
     * Gets store of the game
     *
     * @return instance of store
     */
    public Store getStore() {
        return store;
    }

    /**
     * Gets all players of the game
     *
     * @return list of all players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Operates as the playing process of the Uno game is implemented here. It asks for determining the game type by
     * selecting from single player (play with computer players) and multiple player (play with human players) and handles moves and board
     * state and game rules and regulations and finally determines the winner.
     */
    private void play() {
        String game = selectGameMode();
        while (!game.equals("0")) {
            this.store = new Store();
            gameRotation = clockwise;
            ground = new Ground();
            switch (game) {
                case "2":
                    System.out.println();
                    System.out.println("Game started!");
                    playMultipleMode();
                    break;
                case "1":
                    System.out.println();
                    System.out.println("Game started!");
                    playSingleMode();
                    break;
                default:
                    System.out.println("Invalid game mode, try again");
            }
            game = selectGameMode();
        }
    }

    /**
     * Gets game mode from user and returns it.
     *
     * @return game mode
     */
    private String selectGameMode() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select game mode,");
        System.out.println("0. Exit");
        System.out.println("1. Single player (play with computer players)");
        System.out.println("2. Multiple players (play with opponents)");
        return scanner.nextLine().trim();
    }

    /**
     * play with human players
     */
    private void playMultipleMode() {
        System.out.println("Please enter number of players (2-10): ");
        String numberOfPlayerStr = getNumberOfPlayer();
        while (!validateNumberOfPlayer(numberOfPlayerStr, false)) {
            System.out.println("Number of players must be between 2 and 10, try again:");
            numberOfPlayerStr = getNumberOfPlayer();
        }
        initializePlayers(numberOfPlayerStr, false);
        System.out.println();
        System.out.println();
        doPlay();
    }

    /**
     * play with computer players
     */
    private void playSingleMode() {
        System.out.println("Please enter number of players (3-5): ");
        String numberOfPlayerStr = getNumberOfPlayer();
        while (!validateNumberOfPlayer(numberOfPlayerStr, true)) {
            System.out.println("Number of players must be between 3 and 5, try again:");
            numberOfPlayerStr = getNumberOfPlayer();
        }
        initializePlayers(numberOfPlayerStr, true);
        doPlay();
    }

    /**
     * The game cycle will be handled here.
     */
    private void doPlay() {
        end = false;
        ground.putCard(store.pickColoredCard());

        if (ground.getCard() instanceof ActionCard) {
            cycleStartActions();
            if (ground.getCard() instanceof Skip) {
                setTurn(false);
            } else if (ground.getCard() instanceof Reverse) {
                changRotation();
                setTurn(false);
            } else if (ground.getCard() instanceof Draw2) {
                turn.addCard(store.pick());
                turn.addCard(store.pick());
                setTurn(false);
            }
            cycleEndActions();
        }

        while (!end) {
            cycleStartActions();
            boolean validCard = false;
            while (!validCard) {
                Card choice = turn.chooseCard(this);
                if (choice == null) {
                    validCard = true;
                    Card card = store.pick();
                    if (card.validateApplyCard(this)) {
                        card.print(0);
                        card.applyCard(this);
                    } else {
                        turn.addCard(card);
                        turn = gameRotation.getNextPlayer(turn, players, false);
                    }
                } else {
                    validCard = true;
                    choice.print(0);
                    choice.applyCard(this);
                }
            }
            cycleEndActions();
        }
    }

    /**
     * Determines end game and print winner and al scores
     */
    private void cycleEndActions() {
        printScore();
        if (isGameFinished()) {
            endGame();
        }
        System.out.println();
        System.out.println();
    }

    /**
     * Prints rotation, turn, and cards of player, card number of players and print ground card.
     */
    private void cycleStartActions() {
        System.out.println("=========================================================================================");
        System.out.println("=======================================CYCLE START=======================================");
        System.out.println("=========================================================================================");
        for (int i = 0; i < (players.size() + 1) / 2; i++) {
            System.out.print("\t\t\t");
        }
        System.out.println(gameRotation.toString());
        Player current = turn;
        for (int i = 0; i < players.size() - 1; i++) {
            current = gameRotation.getNextPlayer(current, players, false);
            current.printPlayer();
            System.out.print("\t\t\t");
        }
        System.out.println();
        System.out.println();
        ground.printGround((players.size() + 1) / 2);
        System.out.println();
        for (int i = 0; i < (players.size() + 1) / 2; i++) {
            System.out.print("\t\t\t");
        }
        System.out.print("(((");
        turn.printPlayer();
        System.out.print(")))");

        System.out.println();
        turn.printCards();
        System.out.println();
    }

    /**
     * Ends the game and prints the winner.
     */
    private void endGame() {
        end = true;
        printWinner();
    }

    /**
     * Prints the winner that is the player with zero score.
     */
    private void printWinner() {
        for (Player player : players) {
            if (player.getScore() == 0) {
                System.out.println(player.toString() + " Wins!");
            }
        }
    }

    /**
     * Initializes specified number of players and chooses turn randomly.
     *
     * @param numberOfPlayerStr number of players
     * @param single            game mode
     */
    private void initializePlayers(String numberOfPlayerStr, boolean single) {
        int numberOfPlayer = Integer.parseInt(numberOfPlayerStr);
        players = new ArrayList<>();
        if (single) {
            HumanPlayer humanPlayer = new HumanPlayer("You");
            players.add(humanPlayer);
            for (int i = 0; i < 7; i++) {
                humanPlayer.addCard(store.pick());
            }
            for (int i = 0; i < numberOfPlayer - 1; i++) {
                ComputerPlayer computerPlayer = new ComputerPlayer("Player" + (i + 1));
                for (int j = 0; j < 7; j++) {
                    computerPlayer.addCard(store.pick());
                }
                players.add(computerPlayer);
            }
        } else {
            for (int i = 0; i < numberOfPlayer; i++) {
                HumanPlayer humanPlayer = new HumanPlayer("Player" + (i + 1));
                for (int j = 0; j < 7; j++) {
                    humanPlayer.addCard(store.pick());
                }
                players.add(humanPlayer);
            }
        }
        Random random = new Random();
        int randomPlayer = random.nextInt(numberOfPlayer);
        turn = players.get(randomPlayer);
    }

    /**
     * Asks user to determine number of players and returns the number of players.
     *
     * @return the number of players
     */
    private String getNumberOfPlayer() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();

    }

    /**
     * Validates number of players that should be 3-5 in single mode and 2-10 multiple.
     *
     * @param number number of players
     * @param single game mode
     * @return {@code true} if number of players is valid, {@code false} otherwise
     */
    private boolean validateNumberOfPlayer(String number, boolean single) {
        if (number.length() != 1) {
            return number.equals("10");
        }
        if (single) {
            return number.charAt(0) >= '3' && number.charAt(0) <= '5';
        } else {
            return number.charAt(0) >= '2' && number.charAt(0) <= '9';
        }
    }

    /**
     * Print current turn.
     */
    private void printTurn() {
        System.out.println("Turn: " + turn.toString());
    }

    /**
     * Prints card number of all players.
     */
    private void printCardNumberOfPlayers() {
        for (Player player : players) {
            System.out.print(player.toString() + ": " + player.getCardsCount() + ", ");
        }
        System.out.println();
    }

    /**
     * Changes the ground card with specified card
     *
     * @param card card to put on the ground
     */
    public void changeGround(ColoredCard card) {
        store.store(getGround().getCard());
        getGround().putCard(card);

    }

    /**
     * Changes the ground card with specified wildcard and ground color with specified color.
     *
     * @param card  card to put on the ground
     * @param color color to set as ground color
     */
    public void changeGround(WildCard card, Color color) {
        store.store(getGround().getCard());
        getGround().putCard(card, color);
        System.out.println("Ground color changed to: " + color.toString());
    }

    /**
     * Gets opposite rotation of the game.
     *
     * @return opposite rotation of the game
     */
    private GameRotation getOtherRotation() {
        if (gameRotation.equals(clockwise)) {
            return antiClockWise;
        } else {
            return clockwise;
        }
    }

    /**
     * Checks if the game is finished. the finish state is when one player has no card is its hand.
     *
     * @return {@code true} if game is finished, {@code false} otherwise
     */
    private boolean isGameFinished() {
        for (Player player : players) {
            if (player.getCardsCount() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prints Score of all players in ascending order
     */
    private void printScore() {
        Map<String, Integer> scoreMap = new HashMap<>();
        for (Player player : players) {
            scoreMap.put(player.toString(), player.getScore());
        }
        System.out.println();
        System.out.println("scores:");
        for (int i = 0; i < players.size(); i++) {
            String maxPlayer = findMin(scoreMap);
            System.out.print(maxPlayer + ": " + scoreMap.get(maxPlayer) + (i == players.size() - 1 ? "" : ", "));
            scoreMap.remove(maxPlayer);
        }
        System.out.println();
    }

    /**
     * Finds player with minimum score.
     *
     * @param scoreMap map of players scores
     * @return player with minimum score
     */
    private String findMin(Map<String, Integer> scoreMap) {
        int min = Integer.MAX_VALUE;
        String result = "";
        for (Map.Entry<String, Integer> entry : scoreMap.entrySet()) {
            if (entry.getValue() < min) {
                min = entry.getValue();
                result = entry.getKey();
            }
        }
        return result;
    }

    /**
     * Changes the rotation of the game.
     */
    public void changRotation() {
        gameRotation = getOtherRotation();
    }
}
