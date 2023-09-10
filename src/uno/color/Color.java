package uno.color;

/**
 * Represents a card color
 */
public interface Color {
    /**
     * yellow color instance
     */
    Yellow YELLOW = new Yellow();
    /**
     * green color instance
     */
    Green GREEN = new Green();
    /**
     * blue color instance
     */
    Blue BLUE = new Blue();
    /**
     * red color instance
     */
    Red RED = new Red();
    /**
     * Color code to reset console color to default
     */
    String ANSI_RESET = "\u001B[0m";

    /**
     * Gets color code of the color for console print.
     *
     * @return color code
     */
    String getColorCode();

    /**
     * Returns a string representation of the color.
     *
     * @return string representation of the color
     */
    String toString();
}
