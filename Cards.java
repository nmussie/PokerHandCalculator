package pokerHand;

/**
 * The cards class deals with all the methods required for knowing what kind of
 * hand a player would have.
 * 
 * @author Nathan Mussie
 * @version 9/21/2016
 * 
 */
public class Cards {

    private int[] rankOfCards;
    private char[] suiteOfCards;
    private char[] cards;

    /**
     * Constructor class for Cards
     * 
     * @param cards
     *            from the scanner in PokerHand
     */
    public Cards(char[] cards) {
        this.cards = cards;
        rankOfCards = new int[5];
        suiteOfCards = new char[5];
    }

    /**
     * Figures out what hand you have received
     */
    public void getCards() {
        int count = 0;
        int i = 0;
        while (count < cards.length) {
            char currCard = cards[count];
            if (currCard == '1') {
                count += 1;
                currCard = cards[count];
            }
            rankOfCards[i] = rankNumCard(currCard);
            suiteOfCards[i] = cards[count + 1];
            count += 3;
            i++;
        }
        sortRankAndSuite();
        if (isStraightFlush()) {
            // rankOfHand = 0
            System.out.print("Straight Flush");
        } 
        else if (isFours()) {
            // rankOfHand = 1
            System.out.println("Four Pair");
        } 
        else if (isFullHouse()) {
            // rankOfHand = 2
            System.out.print("Full House");
        } 
        else if (isFlush()) {
            // rankOfHand = 3
            System.out.print("Flush");
        } 
        else if (isStraight()) {
            // rankOfHand = 4
            System.out.print("Straight");
        } 
        else if (isThrees()) {
            // rankOfHand = 5
            System.out.print("Three Pair");
        } 
        else if (isTwos()) {
            // rankOfHand = 6
            System.out.print("Two Pair");
        } 
        else if (isOnes()) {
            // rankOfHand = 7
            System.out.print("One Pair");
        } 
        else {
            // rankOfHand = 8
            System.out.println("High card");
        }
    }

    /**
     * Boolean for a Flush
     * 
     * @return true if Flush
     */
    public boolean isFlush() {
        boolean ifAllMatch = suiteOfCards[0] == suiteOfCards[1] 
                && suiteOfCards[1] == suiteOfCards[2]
                && suiteOfCards[2] == suiteOfCards[3] 
                && suiteOfCards[3] == suiteOfCards[4];
        return ifAllMatch;
    }

    /**
     * Boolean for a Straight
     * 
     * @return true if Straight
     */
    public boolean isStraight() {
        if (rankOfCards[4] == 14) {
            boolean aceFirst = rankOfCards[0] == 2 
                    && rankOfCards[1] == 3 
                    && rankOfCards[2] == 4 
                    && rankOfCards[3] == 5;
            boolean aceEnd = rankOfCards[0] == 10 
                    && rankOfCards[1] == 11 
                    && rankOfCards[2] == 12
                    && rankOfCards[3] == 13;
            return (aceFirst || aceEnd);
        } 
        else {
            int nextHand = rankOfCards[0] + 1;
            for (int i = 1; i < 5; i++) {
                if (rankOfCards[i] != nextHand) {
                    return false;
                }
                nextHand++;
            }
            return true;
        }
    }

    /**
     * Boolean for a Straight Flush
     * 
     * @return true if Straight Flush
     */
    public boolean isStraightFlush() {
        return isStraight() && isFlush();
    }

    /**
     * Boolean for Four of a kind
     * 
     * @return true if Four Pair
     */
    public boolean isFours() {
        boolean higherHand = rankOfCards[0] == rankOfCards[1] 
                && rankOfCards[1] == rankOfCards[2]
                && rankOfCards[2] == rankOfCards[3];
        boolean lowerHand = rankOfCards[1] == rankOfCards[2] 
                && rankOfCards[2] == rankOfCards[3]
                && rankOfCards[3] == rankOfCards[4];
        return (higherHand || lowerHand);
    }

    /**
     * Boolean for Full House
     * 
     * @return true if Full House
     */
    public boolean isFullHouse() {
        boolean higherHand = rankOfCards[0] == rankOfCards[1] 
                && rankOfCards[1] == rankOfCards[2]
                && rankOfCards[3] == rankOfCards[4];
        boolean lowerHand = rankOfCards[0] == rankOfCards[1] 
                && rankOfCards[2] == rankOfCards[3]
                && rankOfCards[3] == rankOfCards[4];
        return (higherHand || lowerHand);
    }

    /**
     * Boolean for Three of a kind
     * 
     * @return true if Three Pair
     */
    public boolean isThrees() {
        if (isFours() || isFullHouse()) {
            return false;
        }
        boolean firstCase = rankOfCards[0] == rankOfCards[1] 
                && rankOfCards[1] == rankOfCards[2];

        boolean secondCase = rankOfCards[1] == rankOfCards[2] 
                && rankOfCards[2] == rankOfCards[3];

        boolean thirdCase = rankOfCards[2] == rankOfCards[3] 
                && rankOfCards[3] == rankOfCards[4];

        return (firstCase || secondCase || thirdCase);
    }

    /**
     * Boolean for Two of a kind
     * 
     * @return true if Two pair
     */
    public boolean isTwos() {
        if (isFours() || isFullHouse() || isThrees()) {
            return false;
        }
        boolean firstCase = rankOfCards[0] == rankOfCards[1] 
                && rankOfCards[2] == rankOfCards[3];

        boolean secondCase = rankOfCards[0] == rankOfCards[1] 
                && rankOfCards[3] == rankOfCards[4];

        boolean thirdCase = rankOfCards[1] == rankOfCards[2] 
                && rankOfCards[3] == rankOfCards[4];
        return (firstCase || secondCase || thirdCase);
    }

    /**
     * Boolean for one of a kind
     * 
     * @return true if One Pair
     */
    public boolean isOnes() {
        if (isFours() || isFullHouse() || isThrees() || isTwos()) {
            return false;
        }
        boolean one = rankOfCards[0] == rankOfCards[1];
        boolean two = rankOfCards[1] == rankOfCards[2];
        boolean three = rankOfCards[2] == rankOfCards[3];
        boolean four = rankOfCards[3] == rankOfCards[4];
        return (one || two || three || four);
    }

    /**
     * Sorts rank of cards from lowest to highest
     */
    public void sortRankAndSuite() {
        int bigger = 0;
        char suite;
        for (int i = 0; i < rankOfCards.length; i++) {
            for (int j = i + 1; j < rankOfCards.length; j++)
                if (rankOfCards[i] > rankOfCards[j] && (i != j)) {
                    bigger = rankOfCards[j];
                    rankOfCards[j] = rankOfCards[i];
                    rankOfCards[i] = bigger;
                    suite = suiteOfCards[j];
                    suiteOfCards[j] = suiteOfCards[i];
                    suiteOfCards[i] = suite;
                }
        }
    }

    /**
     * Ranks cards per their number
     * 
     * @param card
     *            in hand
     * @return the card's integer number
     */
    public int rankNumCard(char card) {
        if (card == '2') 
            return 2;
        else if (card == '3') 
            return 3;
        else if (card == '4') 
            return 4;
        else if (card == '5') 
            return 5;
        else if (card == '6') 
            return 6;
        else if (card == '7') 
            return 7;
        else if (card == '8') 
            return 8;
        else if (card == '9')
            return 9;
        else if (card == '0') 
            return 10;
        else if (card == 'J')
            return 11;
        else if (card == 'Q')
            return 12;
        else if (card == 'K') 
            return 13;
        else if (card == 'A') 
            return 14;
        else
        return 0;
    }

}