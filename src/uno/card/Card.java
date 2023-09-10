package uno.card;

import uno.UnoGame;

/**
 * Represents a card in Uno game.
 */
public abstract class Card {
    /**
     * space count for presentation
     */
    private static final int SPACES = 15;

    /**
     * An array that contains presentation of the card.
     *
     * @return presentation of the card
     */
    public String[] getPresentation() {
        String[] result = new String[5];
        result[0] = "|$$$$$$$$$$$$$$$|";
        result[1] = "|               |";
        result[2] = "|" + getCardStringWithLeftAndRightPad() + "|";
        result[3] = "|               |";
        result[4] = "|$$$$$$$$$$$$$$$|";
        return result;
    }

    /**
     * returns text that represented on the middle of card representation.
     *
     * @return card string
     */
    abstract String getCardString();

    /**
     * Returns card string that left and right padded with spaces.
     *
     * @return left and right padded card string
     */
    private String getCardStringWithLeftAndRightPad() {
        String cardString = getCardString();
        int len = cardString.length();
        int remain = SPACES - len;
        for (int i = 0; i < remain / 2; i++) {
            cardString = " " + cardString + " ";
        }
        if (remain % 2 == 1) {
            cardString = cardString + " ";
        }
        return cardString;
    }

    /**
     * Validates application of the card on ground card.
     *
     * @param unoGame game object
     * @return {@code true} if application is valid, {@code false} otherwise
     */
    public abstract boolean validateApplyCard(UnoGame unoGame);

    /**
     * Applies the card.
     *
     * @param unoGame game object
     */
    public abstract void applyCard(UnoGame unoGame);

    /**
     * Gets current card score.
     *
     * @return card score
     */
    public abstract int getScore();

    /**
     * Prints the presentation of the card.
     *
     * @param indent display indentation
     */
    public void print(int indent) {
        for (String cardItem : getPresentation()) {
            for (int i = 0; i < indent; i++) {
                System.out.print("\t\t\t");
            }
            System.out.println(cardItem);
        }
    }
}
