package uno.rotation;

import uno.player.Player;

import java.util.List;

/**
 * Represents the rotation of the game cycle.
 */
public interface GameRotation {
    /**
     * Returns a string representation of the rotation.
     *
     * @return string representation of the rotation
     */
    String toString();

    /**
     * Gets next turn player.
     *
     * @param turn    current player
     * @param players all players
     * @param skip    there is a skip
     * @return next turn player
     */
    Player getNextPlayer(Player turn, List<Player> players, boolean skip);
}
