package uno.card;

import uno.UnoGame;

import java.util.List;

/**
 * A cascadable card is a card that its action can be cascaded if applied on each other. for example if one player
 * applies {@link WildDraw} and next one applied another {@link WildDraw}, the action cascaded for next one in the
 * way that next player must pick 4+4 cards from store, and so on.
 */
public interface CascadableCard {
    /**
     * Applies the card action using current cascade factor. Cascade factor is number of cascade actions that takes
     * place.
     *
     * @param unoGame       game object
     * @param cascadeFactor number of cascades
     */
    void applyCascadingCard(UnoGame unoGame, int cascadeFactor);

    /**
     * Removes and returns next card of same type of current cascadable card from next player's cards.
     *
     * @param nextPlayerCards cards of next player
     * @return card of same type of cascadable card
     */
    default CascadableCard removeCardWithSameType(List<Card> nextPlayerCards) {
        CascadableCard result = null;
        for (Card card : nextPlayerCards) {
            if (card.getClass().equals(this.getClass())) {
                result = (CascadableCard) card;
            }
        }
        nextPlayerCards.remove(result);
        return result;
    }
}
