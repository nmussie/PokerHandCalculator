package pokerHand;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The PokerHand class has the main method 
 * which takes an input of 5 hands and,
 * outputs the type of hand the player received.
 * 
 * @author Nathan Mussie
 * @version 9/21/2016
 */
public class PokerHand {
    private static Cards cards;

    /**
     * Pre-Condition: 
     * Assumed 5 cards are always input 
     * Ex:5H AS 5C 4D AC
     * 
     * Post-Condition: 
     * Outputs type of hand 
     * Ex: Two Pair
     * 
     * @param args
     *            string array with input inside of it
     * 
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(args[0]);
        Scanner scanner = new Scanner(file);
        char[] pokerHand = scanner.nextLine().toCharArray();
        cards = new Cards(pokerHand);
        cards.getCards();
        scanner.close();
    }
}