package uno.card;

import uno.UnoGame;

/**
 * Wild color card of the Uno game, that changes ground color by player's will. It can be applied each time the player
 * decides.
 */
public class WildColor extends WildCard {
    @Override
    String getCardString() {
        return "wild color";
    }

    @Override
    public boolean validateApplyCard(UnoGame unoGame) {
        return true;
    }

    /**
     * Applies the wild color card action by asking player to choose a color for ground.
     *
     * @param unoGame game object
     */
    @Override
    public void applyCard(UnoGame unoGame) {
        unoGame.changeGround(this, getColor(unoGame));
        unoGame.setTurn(false);
    }
}
