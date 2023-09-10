package uno.card;

import uno.UnoGame;
import uno.color.Color;
import uno.player.Player;

import java.util.List;

/**
 * Draw 2 or 2+ card of Uno game, that is an action card that when applied, forces next player to pick two cards from
 * store and lose turn. It is also a {@link CascadableCard}. It can be applied when ground card color has same color
 * (or wild card selects same color for ground) or ground card was a 2+ card.
 */
public class Draw2 extends ActionCard implements CascadableCard {
    /**
     * Draw 2 constructor
     *
     * @param color color of the card
     */
    public Draw2(Color color) {
        super(color);
    }

    @Override
    public String getCardString() {
        return "+2";
    }

    /**
     * Applies the 2+ card action that forces next player to pick two cards from store and lose turn. It is also a
     * {@link CascadableCard} and if next player applies another 2+ card, next one will affect with one more cascade
     * factor.
     *
     * @param unoGame game object
     */
    @Override
    public void applyCard(UnoGame unoGame) {
        apply(unoGame, 1);
    }

    /**
     * Applies the 2+ card action with specified cascade factor, that forces next player to pick two cards from store
     * and lose turn. It is also a {@link CascadableCard} and if next player applies another 2+ card, next one will
     * affect with one more cascade factor.
     *
     * @param unoGame       game object
     * @param cascadeFactor current cascade factor
     */
    private void apply(UnoGame unoGame, int cascadeFactor) {
        Player nextPlayer = unoGame.getGameRotation().getNextPlayer(unoGame.getTurn(), unoGame.getPlayers(), false);
        List<Card> nextPlayerCards = nextPlayer.getCards();
        CascadableCard card = removeCardWithSameType(nextPlayerCards);
        if (card != null) {
            unoGame.changeGround(this);
            unoGame.setTurn(false);
            card.applyCascadingCard(unoGame, cascadeFactor + 1);
        } else {
            for (int i = 0; i < 2 * cascadeFactor; i++) {
                nextPlayer.addCard(unoGame.getStore().pick());
            }
            unoGame.changeGround(this);
            unoGame.setTurn(true);
        }
    }

    @Override
    public void applyCascadingCard(UnoGame unoGame, int cascadeFactor) {
        apply(unoGame, cascadeFactor);
    }
}
