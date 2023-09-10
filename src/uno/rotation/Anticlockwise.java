package uno.rotation;

import uno.player.Player;

import java.util.List;

/**
 * Represents the anti-clockwise rotation of the game cycle.
 */
public class Anticlockwise implements GameRotation {
    @Override
    public String toString() {
        return "<<<<<<<<<<Anticlockwise<<<<<<<<<<";
    }

    /**
     * Gets next turn player in anti-clockwise direction.
     *
     * @param turn    current player
     * @param players all players
     * @param skip    there is a skip
     * @return next turn player
     */
    @Override
    public Player getNextPlayer(Player turn, List<Player> players, boolean skip) {
        Player result = null;

        if (turn.equals(players.get(0))) {
            result = players.get(players.size() - 1);
        } else {
            for (int i = 0; i < players.size(); i++) {
                if (turn == players.get(i)) {
                    result = players.get(i - 1);
                }
            }
        }
        if (skip) {
            return getNextPlayer(result, players, false);
        }
        return result;
    }
}

