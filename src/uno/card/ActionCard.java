package uno.card;

import uno.UnoGame;
import uno.color.Color;

/**
 * Represents an action card in Uno game. An action card performs an action and has a color. It can be applied on card
 * with same color or same action.
 */
public abstract class ActionCard extends ColoredCard {
    /**
     * Action card constructor
     *
     * @param color color of card
     */
    ActionCard(Color color) {
        super(color);
    }

    /**
     * Validates application of the card on ground card. Checks if color of the card equals color of the card on the
     * ground or color selected for next move (in wild card case) or action of the card equals action of the card on
     * the ground.
     *
     * @param unoGame game object
     * @return {@code true} if application is valid, {@code false} otherwise
     */
    @Override
    public boolean validateApplyCard(UnoGame unoGame) {
        Card card = unoGame.getGround().getCard();
        if (card.getClass().equals(this.getClass())) {
            return true;
        }
        if (card instanceof ColoredCard) {
            if (((ColoredCard) card).color.equals(this.color)) {
                return true;
            }
        }
        return ((unoGame.getGround().getColor()).equals(this.color));
    }

    public int getScore() {
        return 20;
    }
}
