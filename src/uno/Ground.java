package uno;

import uno.card.Card;
import uno.card.ColoredCard;
import uno.card.WildCard;
import uno.color.Color;

/**
 * Represents ground of the Uno game
 */
public class Ground {
    /**
     * current card on the ground
     */
    private Card card;
    /**
     * current color of the ground to be played
     */
    private Color color;

    /**
     * Puts a wild card on the ground and sets color that player chooses as ground color.
     *
     * @param card  card to put on the ground
     * @param color color of the ground
     */
    void putCard(WildCard card, Color color) {
        this.card = card;
        this.color = color;
    }

    /**
     * Returns current card on the ground.
     *
     * @return current card on the ground
     */
    public Card getCard() {
        return card;
    }

    /**
     * Puts a card on the ground.
     *
     * @param card card to put on the ground
     */
    void putCard(ColoredCard card) {
        this.card = card;
        this.color = card.getColor();
    }

    /**
     * Gets current color of the ground.
     *
     * @return current color of the ground
     */
    public Color getColor() {
        return color;
    }

    /**
     * Prints current card of the ground.
     *
     * @param indent display indentation
     */
    void printGround(int indent) {
        card.print(indent);
        for (int i = 0; i < indent; i++) {
            System.out.print("\t\t\t");
        }
        System.out.println("ground color: "+color.toString());
    }
}
