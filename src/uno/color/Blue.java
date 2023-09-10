package uno.color;

/**
 * Represents blue color.
 */
public class Blue implements Color {
    @Override
    public String getColorCode() {
        return "\u001B[34m";
    }

    @Override
    public String toString() {
        return getColorCode() + "■" + ANSI_RESET;
    }
}
