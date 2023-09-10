package uno.card;

import uno.UnoGame;
import uno.color.Color;

/**
 * A numeric card of the Uno game, that is a colored card with a number in range [0-9]. It can be applied on same color
 * or same number on the ground.
 */
public class NumericCard extends ColoredCard {
    /**
     * card number
     */
    private int number;

    /**
     * Numeric card constructor
     *
     * @param color  card color
     * @param number card number
     */
    public NumericCard(Color color, int number) {
        super(color);
        this.number = number;
    }

    @Override
    public String getCardString() {
        return String.valueOf(number);
    }

    /**
     * Validated application of the card on the ground. It can be applied on same color or same number on the ground.
     *
     * @param unoGame game object
     * @return {@code true} if application is valid, {@code false} otherwise
     */
    @Override
    public boolean validateApplyCard(UnoGame unoGame) {
        Card card = unoGame.getGround().getCard();

        if (card instanceof ColoredCard) {
            if (((ColoredCard) card).color.equals(this.color)) {
                return true;
            }
        }
        if (card instanceof NumericCard) {
            if (((NumericCard) card).number == this.number)
                return true;
        }
        return ((unoGame.getGround().getColor()).equals(this.color));
    }

    /**
     * Applies the card that is simply put card on the ground and give turn to next player.
     *
     * @param unoGame game object
     */
    @Override
    public void applyCard(UnoGame unoGame) {
        unoGame.changeGround(this);
        unoGame.setTurn(false);
    }

    @Override
    public int getScore() {
        return number;
    }
}
