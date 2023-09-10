package uno.color;

/**
 * Represents green color.
 */
public class Green implements Color {
    @Override
    public String getColorCode() {
        return "\u001B[32m";
    }

    @Override
    public String toString() {
        return getColorCode() + "■" + ANSI_RESET;
    }
}
