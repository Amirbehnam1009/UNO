package uno.card;

import uno.UnoGame;
import uno.color.Color;

/**
 * Represents wild cards of the Uno game. A wild card is an special card that has no color, but changes the ground
 * color by player's will and has some actions.
 */
public abstract class WildCard extends Card {
    /**
     * Returns the color chosen by the player.
     *
     * @param unoGame game object
     * @return chosen color
     */
    Color getColor(UnoGame unoGame) {
        return unoGame.getTurn().chooseColor(unoGame);
    }

    @Override
    public int getScore() {
        return 50;
    }
}
