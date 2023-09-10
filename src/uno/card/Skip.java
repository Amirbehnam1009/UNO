package uno.card;

import uno.UnoGame;
import uno.color.Color;

/**
 * Skip card of the Uno game, that skips next players turn. It has also a color and can be applied either on a card
 * with same color or a skip card.
 */
public class Skip extends ActionCard {
    /**
     * Skip card constructor
     *
     * @param color card color
     */
    public Skip(Color color) {
        super(color);
    }

    @Override
    public String getCardString() {
        return "skip";
    }

    /**
     * Applies the skip card action that skips next players turn.
     *
     * @param unoGame game object
     */
    @Override
    public void applyCard(UnoGame unoGame) {
        unoGame.changeGround(this);
        unoGame.setTurn(true);
    }
}
