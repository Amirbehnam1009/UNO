package uno.player;


import uno.UnoGame;
import uno.card.Card;
import uno.color.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * An Uno player
 */
public abstract class Player {
    /**
     * List of cards in hand of the player
     */
    List<Card> cards;
    /**
     * Name of the player
     */
    private String name;

    /**
     * Player constructor
     *
     * @param name player name
     */
    Player(String name) {
        this.name = name;
        cards = new ArrayList<>();
    }

    /**
     * Adds a new card to the hand of the player.
     *
     * @param card new card to add
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Returns a string representation of the player.
     *
     * @return string representation of the player
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Gets number of cards in the hand of the player.
     *
     * @return number of cards of the player
     */
    public int getCardsCount() {
        return cards.size();
    }

    /**
     * Prints player's card at the console.
     */
    public abstract void printCards();

    /**
     * At the player's turn, chooses a card from player's hand and returns chosen card.
     *
     * @param unoGame game object
     * @return chosen card for player
     */
    public abstract Card chooseCard(UnoGame unoGame);

    /**
     * At the player's turn, chooses a color for player and returns chosen color. This method is used for wild cards.
     *
     * @param unoGame game object
     * @return chosen color for player
     */
    public Color chooseColor(UnoGame unoGame) {
        String colorStr = getColorString(unoGame);
        return getColor(colorStr);

    }

    /**
     * converts input color strings from 'r', 'g', 'b' and 'y' to corresponding color object.
     *
     * @param inputColor input color string
     * @return color object
     */
    private Color getColor(String inputColor) {
        switch (inputColor.toUpperCase()) {
            case "R":
                return Color.RED;
            case "G":
                return Color.GREEN;
            case "B":
                return Color.BLUE;
            case "Y":
                return Color.YELLOW;
            default:
                return null;
        }
    }

    /**
     * At the player's turn, chooses a color string for player and returns chosen color string.
     *
     * @param unoGame game object
     * @return chosen color string
     */
    protected abstract String getColorString(UnoGame unoGame);

    /**
     * Gets cards of the player.
     *
     * @return cards of the player
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * Calculates the score of the player from cards in the hand.
     *
     * @return score of the player
     */
    public int getScore() {
        int score = 0;
        for (Card card : cards) {
            score += card.getScore();
        }
        return score;
    }

    /**
     * Prints the player
     */
    public void printPlayer() {
        System.out.print(name + "[" + getCardsCount() + " cards]" + " score: [" + getScore() + "]");
    }
}
