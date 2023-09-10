package uno;

import uno.card.*;
import uno.color.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Store of the Uno game that keeps cards.
 */
public class Store {
    /**
     * card of the store
     */
    private List<Card> cards;

    /**
     * Store constructor that initializes all cards of the Uno game.<br>
     * - One numeric 0 card of each color, totally 4,<br>
     * - two numeric 1-9 cards of each color, totally 36,<br>
     * - two skip cards of each color, totally 8,<br>
     * - two reverse cards of each color, totally 8,<br>
     * - two 2+ cards of each color, totally 8,<br>
     * - four wild colors,<br>
     * - four wild draws
     */
    Store() {
        cards = new ArrayList<>();
        createCardWithColor(Color.YELLOW);
        createCardWithColor(Color.GREEN);
        createCardWithColor(Color.BLUE);
        createCardWithColor(Color.RED);
        createWildCards();
        Collections.shuffle(cards);
    }

    /**
     * Picks a card from store randomly.
     *
     * @return picked card
     */
    public Card pick() {
        Card card = cards.get(0);
        cards.remove(card);
        return card;
    }

    /**
     * Picks a colored card from store randomly.
     *
     * @return picked colored card
     */
    ColoredCard pickColoredCard() {
        Card card = pick();
        while (!(card instanceof ColoredCard)) {
            cards.add(card);
            card = pick();
        }
        return (ColoredCard) card;
    }

    /**
     * Creates all wild cards as follows:<br>
     * - four wild colors,<br>
     * - four wild draws
     */
    private void createWildCards() {
        for (int i = 0; i < 4; i++) {
            cards.add(new WildColor());
            cards.add(new WildDraw());
        }
    }

    /**
     * Creates all colored cards with specified colore as follows:
     * - One numeric 0 card of each color, totally 4,<br>
     * - two numeric 1-9 cards of each color, totally 36,<br>
     * - two skip cards of each color, totally 8,<br>
     * - two reverse cards of each color, totally 8,<br>
     * - two 2+ cards of each color, totally 8,<br>
     *
     * @param color card color
     */
    private void createCardWithColor(Color color) {
        cards.add(new NumericCard(color, 0));
        for (int i = 1; i < 10; i++) {
            cards.add(new NumericCard(color, i));
            cards.add(new NumericCard(color, i));
        }
        cards.add(new Skip(color));
        cards.add(new Skip(color));
        cards.add(new Reverse(color));
        cards.add(new Reverse(color));
        cards.add(new Draw2(color));
        cards.add(new Draw2(color));
    }

    /**
     * Stores specified card.
     *
     * @param card card to store
     */
    void store(Card card) {
        cards.add(card);
    }
}
