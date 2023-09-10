package uno.card;

import uno.UnoGame;
import uno.player.Player;

import java.util.List;

/**
 * Wild draw card of the Uno game, that changes the color of the ground by player's will, forces next player to pick
 * 4 cards from store and skips its turn. It is also a {@link CascadableCard}. It can be applied when the player has
 * no other card to choose.
 */
public class WildDraw extends WildCard implements CascadableCard {
    @Override
    String getCardString() {
        return "4+";
    }

    /**
     * Validated application of wild draw card. It can be applied when the player has no other card to choose.
     *
     * @param unoGame game object
     * @return {@code true} if application is valid, {@code false} otherwise
     */
    @Override
    public boolean validateApplyCard(UnoGame unoGame) {
        List<Card> playerCards = unoGame.getTurn().getCards();
        for (Card playerCard : playerCards) {
            if (!(playerCard instanceof WildDraw) && playerCard.validateApplyCard(unoGame)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Applies the wild draw card action that changes ground color by player's will and forces next player to pick four
     * cards from store and lose turn. It is also a {@link CascadableCard} and if next player applies another wild draw
     * card, next one will affect with one more cascade factor.
     *
     * @param unoGame game object
     */
    @Override
    public void applyCard(UnoGame unoGame) {
        apply(unoGame, 1);
    }

    /**
     * Applies the wild draw card cascade factor that changes ground color by player's will and forces next player
     * to pick four cards from store and lose turn. It is also a {@link CascadableCard} and if next player applies
     * another wild draw card, next one will affect with one more cascade factor.
     *
     * @param unoGame       game object
     * @param cascadeFactor current cascade factor
     */
    private void apply(UnoGame unoGame, int cascadeFactor) {
        Player nextPlayer = unoGame.getGameRotation().getNextPlayer(unoGame.getTurn(), unoGame.getPlayers(), false);
        List<Card> nextPlayerCards = nextPlayer.getCards();
        CascadableCard card = removeCardWithSameType(nextPlayerCards);
        if (card != null) {
            unoGame.changeGround(this, getColor(unoGame));
            unoGame.setTurn(false);
            card.applyCascadingCard(unoGame, cascadeFactor + 1);
        } else {
            for (int i = 0; i < 4 * cascadeFactor; i++) {
                nextPlayer.addCard(unoGame.getStore().pick());
            }
            unoGame.changeGround(this, getColor(unoGame));
            unoGame.setTurn(true);
        }
    }

    @Override
    public void applyCascadingCard(UnoGame unoGame, int cascadeFactor) {
        apply(unoGame, cascadeFactor);
    }
}
