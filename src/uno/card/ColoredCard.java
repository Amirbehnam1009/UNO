package uno.card;

import uno.color.Color;

/**
 * A colored card in Uno game, that is cards that have a color.
 */
public abstract class ColoredCard extends Card {
    /**
     * color of the card
     */
    protected Color color;

    /**
     * Colored card constructor
     *
     * @param color color of the card
     */
    ColoredCard(Color color) {
        this.color = color;
    }

    /**
     * Adds the color to the presentation of the card.
     *
     * @return presentation of the card with color.
     * @see Card#getPresentation()
     */
    @Override
    public String[] getPresentation() {
        String[] result = super.getPresentation();

        for (int i = 0; i < 5; i++) {
            result[i] = color.getColorCode() + result[i] + Color.ANSI_RESET;
        }
        return result;
    }

    /**
     * Returns color of the card.
     *
     * @return color of the card
     */
    public Color getColor() {
        return color;
    }
}
