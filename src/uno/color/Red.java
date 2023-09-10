package uno.color;

/**
 * Represents red color.
 */
public class Red implements Color {
    @Override
    public String getColorCode() {
        return "\u001B[31m";
    }

    @Override
    public String toString() {
        return getColorCode() + "■" + ANSI_RESET;
    }
}
