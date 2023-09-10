package uno.card;

import uno.UnoGame;
import uno.color.Color;

/**
 * A reverse card that is an action card and reverses the game rotation. It has also a color and can be applied either
 * on a card with same color or a reverse card.
 */
public class Reverse extends ActionCard {
    /**
     * Reverse card constructor
     *
     * @param color card color
     */
    public Reverse(Color color) {
        super(color);
    }

    @Override
    public String getCardString() {
        return "reverse";
    }

    /**
     * Applies the reverse card action that changes the game rotation.
     *
     * @param unoGame game object
     */
    @Override
    public void applyCard(UnoGame unoGame) {
        unoGame.changRotation();
        unoGame.changeGround(this);
        unoGame.setTurn(false);
    }
}
