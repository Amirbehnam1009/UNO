package uno.player;

import uno.UnoGame;
import uno.card.*;
import uno.color.Color;

import java.util.*;

/**
 * Determines a semi-intelligent computer player that can choose its next card based on some simple heuristics.
 */
public class ComputerPlayer extends Player {
    /**
     * Computer player constructor
     *
     * @param name computer player name
     */
    public ComputerPlayer(String name) {
        super(name);
    }

    /**
     * Print of computer player does nothing to keep cards of its hand secret!
     */
    @Override
    public void printCards() {

    }

    /**
     * Chooses next card based on following heuristics:<br>
     * From cards that can be chosen based on ground card and color, <br>
     * 1. first choose a numeric card randomly,<br>
     * 2. otherwise choose an action card randomly,<br>
     * 3. otherwise choose a wild card randomly,<br>
     * 4. otherwise ask for store
     *
     * @param unoGame game object
     * @return chosen card
     */
    @Override
    public Card chooseCard(UnoGame unoGame) {
        List<Card> cards = new ArrayList<>();
        for (Card card : this.cards) {
            if (card.validateApplyCard(unoGame)) {
                cards.add(card);
            }
        }

        Card card = simpleChooseCard(cards);
        this.cards.remove(card);
        return card;
    }

    /**
     * Chooses next card based on following heuristics:<br>
     * From cards that can be chosen based on ground card and color, <br>
     * 1. first choose a numeric card randomly,<br>
     * 2. otherwise choose an action card randomly,<br>
     * 3. otherwise choose a wild card randomly,<br>
     * 4. otherwise ask for store
     *
     * @param cards cards of the player
     * @return chosen card
     */
    private Card simpleChooseCard(List<Card> cards) {
        List<Card> numericCards = new ArrayList<>();
        List<Card> actionCards = new ArrayList<>();
        List<Card> wildCards = new ArrayList<>();

        for (Card card : cards) {
            if (card instanceof NumericCard) {
                numericCards.add(card);
            } else if (card instanceof ActionCard) {
                actionCards.add(card);
            } else if (card instanceof WildCard) {
                wildCards.add(card);
            }
        }

        if (!numericCards.isEmpty()) {
            return randomChoose(numericCards);
        } else if (!actionCards.isEmpty()) {
            return randomChoose(actionCards);
        } else if (!wildCards.isEmpty()) {
            return randomChoose(wildCards);
        }
        return null;
    }

    /**
     * randomly chooses a card from the input list.
     *
     * @param cards input cards
     * @return chosen card
     */
    private Card randomChoose(List<Card> cards) {
        if (cards.isEmpty()) {
            return null;
        }
        if (cards.size() == 1) {
            return cards.get(0);
        }
        Random random = new Random();
        return cards.get(random.nextInt(cards.size()));
    }

    /**
     * Chooses a color from player's cards colors based on following rules:<br>
     * 1. choose majority color of cards.<br>
     * 2. if there's equal counts, choose color with most numeric cards,<br>
     * 3. choose randomly from equal count and numeric colors.
     *
     * @param unoGame game object
     * @return chosen color
     */
    @Override
    protected String getColorString(UnoGame unoGame) {
        return simpleChooseColor(cards);
    }

    /**
     * Chooses a color from input cards colors based on following rules:<br>
     * 1. choose majority color of cards.<br>
     * 2. if there's equal counts, choose color with most numeric cards,<br>
     * 3. choose randomly from equal count and numeric colors.
     *
     * @param cards cards to select color from
     * @return chosen color
     */
    private String simpleChooseColor(List<Card> cards) {
        Map<String, Integer[]> colorMap = new HashMap<>();
        colorMap.putIfAbsent("r", new Integer[]{0, 0, 0});
        colorMap.putIfAbsent("g", new Integer[]{0, 0, 0});
        colorMap.putIfAbsent("b", new Integer[]{0, 0, 0});
        colorMap.putIfAbsent("y", new Integer[]{0, 0, 0});
        for (Card card : cards) {
            if (card instanceof ColoredCard) {
                Color color = ((ColoredCard) card).getColor();
                String colorStr = "";
                if (color.equals(Color.RED)) {
                    colorStr = "r";
                } else if (color.equals(Color.GREEN)) {
                    colorStr = "g";
                } else if (color.equals(Color.BLUE)) {
                    colorStr = "b";
                } else if (color.equals(Color.YELLOW)) {
                    colorStr = "y";
                }

                Integer[] counts = colorMap.get(colorStr);
                counts[0]++;
                if (card instanceof NumericCard) {
                    counts[1]++;
                } else if (card instanceof ActionCard) {
                    counts[2]++;
                }
            }
        }

        int maxCount = 0;
        List<String> maxColors = new ArrayList<>();
        for (Map.Entry<String, Integer[]> entry : colorMap.entrySet()) {
            if (entry.getValue()[0] > maxCount) {
                maxColors.clear();
                maxColors.add(entry.getKey());
                maxCount = entry.getValue()[0];
            } else if (entry.getValue()[0] == maxCount) {
                maxColors.add(entry.getKey());
            }
        }

        int maxNumeric = 0;
        List<String> maxNumericColors = new ArrayList<>();
        for (String maxColor : maxColors) {
            Integer numericCount = colorMap.get(maxColor)[1];
            if (numericCount > maxNumeric) {
                maxNumericColors.clear();
                maxNumericColors.add(maxColor);
                maxNumeric = numericCount;
            } else if (numericCount == maxNumeric) {
                maxNumericColors.add(maxColor);
            }
        }

        if (maxNumericColors.size() == 1) {
            return maxNumericColors.get(0);
        } else {
            Random random = new Random();
            return maxNumericColors.get(random.nextInt(maxNumericColors.size()));
        }
    }
}
