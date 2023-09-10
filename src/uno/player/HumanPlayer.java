package uno.player;

import uno.UnoGame;
import uno.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Determines a simple human player
 */
public class HumanPlayer extends Player {
    /**
     * Constructor of the human player
     *
     * @param name player name
     */
    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public void printCards() {
        List<String[]> presentations = new ArrayList<>();
        for (Card card : cards) {
            presentations.add(card.getPresentation());
        }

        for (int i = 0; i < 5; i++) {
            for (String[] presentation : presentations) {
                System.out.print(presentation[i]);
                System.out.print("     ");
            }
            System.out.println();
        }
    }

    /**
     * Asks user to choose a card from current player's cards and returns the chosen card.
     *
     * @param unoGame game object
     * @return chosen card
     */
    @Override
    public Card chooseCard(UnoGame unoGame) {
        System.out.println("Choose a card,a number between 1 and " + cards.size() + " ,0 for store: ");
        boolean validChoice = false;
        Card choice = null;
        while (!validChoice) {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine().trim();
            while (!validateSelectedCard(line, unoGame)) {
                System.out.println("Selected card is invalid, Choose a card,a number between 1 and " + cards.size() + " ,0 for store: try again :");
                line = scanner.nextLine().trim();
            }
            int cardNumber = Integer.parseInt(line);
            if (cardNumber == 0) {
                return null;

            }
            choice = cards.get(cardNumber - 1);
            if (!choice.validateApplyCard(unoGame)) {
                System.out.println("Selected card can't be applied, try again:");
            } else {
                validChoice = true;
            }
        }
        cards.remove(choice);
        return choice;

    }

    /**
     * Asks user to choose a color string from 'r', 'g', 'b' or 'y'.
     *
     * @param unoGame game object
     * @return chosen color string
     */
    @Override
    protected String getColorString(UnoGame unoGame) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter color from 'r' for red,'g' for green, 'b' for blue, 'y' for yellow: ");
        String colorStr = scanner.nextLine().trim();
        while (!validColor(colorStr)) {
            System.out.println("Color must be between 'r' and 'g' and 'b' and 'y', try again:");
            colorStr = scanner.nextLine().trim();
        }
        return colorStr;
    }

    /**
     * Validates color string to be chosen from 'r', 'g', 'b' or 'y'.
     *
     * @param colorStr color string to validate
     * @return {@code true} if the color string is valid, {@code false} otherwise
     */
    private boolean validColor(String colorStr) {
        if (colorStr.length() != 1) {
            return false;
        }
        return colorStr.charAt(0) == 'r' || colorStr.charAt(0) == 'g' || colorStr.charAt(0) == 'y' || colorStr.charAt(0) == 'b';
    }

    /**
     * Validates selected card number to be a number and determines a card from player's cards.
     *
     * @param cardStr card string to be validated
     * @param unoGame game object
     * @return {@code true} if the card string is valid, {@code false} otherwise
     */
    private boolean validateSelectedCard(String cardStr, UnoGame unoGame) {
        for (int i = 0; i < cardStr.length(); i++) {
            if (!(cardStr.charAt(i) >= '0' && cardStr.charAt(i) <= '9')) {
                return false;
            }
        }
        int cardNumber = Integer.parseInt(cardStr);
        if (cardNumber < 0) {
            return false;
        }
        if (cardNumber > cards.size()) {
            return false;
        }
        if (cardNumber == 0) {
            for (Card card : cards) {
                if (card.validateApplyCard(unoGame)) {
                    return false;
                }
            }
        }

        return true;
    }
}
