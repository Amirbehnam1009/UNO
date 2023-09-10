package uno.color;

/**
 * Represents yellow color.
 */
public class Yellow implements Color {
    @Override
    public String getColorCode() {
        return "\u001B[33m";
    }

    @Override
    public String toString() {
        return getColorCode() + "■" + ANSI_RESET;
    }
}
