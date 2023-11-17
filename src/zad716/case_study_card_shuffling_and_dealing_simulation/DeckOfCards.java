package zad716.case_study_card_shuffling_and_dealing_simulation;
import java.security.SecureRandom;

/**
 * Represents a deck of playing cards.
 * This class provides methods for shuffling, dealing, checking for pairs, three of a kind, four of a kind, flush, straight
 * It contains a constructor for initializing a standard deck of cards.
 *
 * @author karolinanowacka
 * @version 1.0
 * @since 2023-11-03
 */
public class DeckOfCards {


    //array of cards
    private Card[] deck;
    //index of next card to be dealt
    private int currentCard;
    //number of cards in a deck
    private static final int NUMBER_OF_CARDS = 56;
    //number of faces in a deck
    public static final int NUMBER_OF_FACES=14;
    //number of suits in a deck
    public static final int NUMBER_OF_SUITS=4;
    //number of cards in a hand
    public static final int NUMBER_OF_CARDS_IN_HAND=5;
    //random number generator
    private static final SecureRandom randomNumbers = new SecureRandom();
    //array of faces
    public String[] faces = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Knight", "Queen", "King"};
    //array of suits
    public String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
    //creates an array of Unicode characters representing the backs of the cards
    public String[][] back_of_card =new String[NUMBER_OF_SUITS][NUMBER_OF_FACES];

    /**
     * Sets the Unicode characters for the backs of the cards based on the combination of suits and faces.
     * The Unicode characters are created from the base of the Unicode characters for playing cards (0x1F0A0) and adding the offset of the suit and face.
     * The resulting Unicode characters are stored in the array back_of_card.
     */

    public void setting_backs() {
        //Iterate through each suit and face
        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < faces.length; j++) {
                //Create a Unicode character for the back of the card
                int base = 0x1F0A0 + 16*i;
                //Convert the Unicode character to a string
                    String unicodeCharacter = new String(Character.toChars(base + j+1));
                    //Store the Unicode character in the array
                    back_of_card[i][j] = unicodeCharacter;}
        }
    }
    /**
    *Creates a new deck of playing cards.
     *The deck is initialized with faces, suits and card backs.
     *
     * @see Card
    */

    public DeckOfCards() {
        //Initialize the deck
        deck = new Card[NUMBER_OF_CARDS];
        //Initialize the currentCard
        currentCard = 0;
        //Initialize the Unicode characters for the backs of the cards
        setting_backs();
        //Iterate through each suit and face
        for (int i = 0; i < NUMBER_OF_SUITS; i++) {
            for (int j = 0; j < NUMBER_OF_FACES; j++) {
                //Create a new card and add it to the deck
                deck[currentCard] = new Card(faces[j], suits[i], back_of_card[i][j]);
                //Increment the currentCard
                currentCard++;
            }
        }
    }

    /**
     * Shuffles the deck of cards.
     * The method swaps each card in the deck with another randomly selected card.
     * The method resets the currentCard to 0.
     */
    public void shuffle() {
        //Reset the currentCard
        currentCard = 0;
        //Iterate through each card in the deck
        for (int first = 0; first < deck.length; first++) {
            //Select a random card
            int second = randomNumbers.nextInt(NUMBER_OF_CARDS);
            //Swap the cards
            Card temp = deck[first];
            deck[first] = deck[second];
            deck[second] = temp;
        }
    }

    /**
     * Deals a card from the deck.
     * If the deck is not empty, the method returns the card at the currentCard index in the deck.
     * If the deck is empty, the method returns null.
     *
     * @return the card at the currentCard index in the deck, or null if the deck is empty
     */

    public Card dealCard() {
        //Check if the deck is not empty
        if (currentCard < deck.length)
            //Return the card at the currentCard index in the deck
            return deck[currentCard++];
        //If the deck is empty, return null
        else return null;
    }

    /**
     * Determines the number of paris in a hand of cards.
     * A pari is any two cards with the same face.
     *
     * @param hand An array of cards representing a hand of cards
     * @return the number of pairs in the hand, or 0 if there are no pairs, 1 if there is one pair, 2 if there are two pairs.
     */
    public int pairs(Card[] hand) {
        //Initialize a counter for the number of pairs
        int counter = 0;
        //Iterate through each card in the hand
        for (int i = 0; i < hand.length - 1; i++) {
            //Iterate through each card in the hand starting from the next card
            for (int j = i + 1; j < hand.length; j++) {
                //Check if the face of the card at index i is equal to the face of the card at index j
                if (hand[i].face.equals(hand[j].face))
                    //Increment the counter
                    counter+=1;
            }
        }
        //Return the number of pairs
        if (counter == 1)
            return 1;
        else if (counter == 2)
            return 2;
        else
            return 0;
    }

    /**
     * Determines the count of each face in a given hand of cards.
     * Analyzes the hand and returns an array of integers representing the number of cards with each face.
     *
     * @param hand An array of cards representing the player's hand.
     * @return an array of integers representing the number of cards with each face in the hand.
     * The index of the array represents the face, and the value at that index represents the number of cards with that face.
     */

    public int[] howMuchOfAKind(Card[] hand) {
        //Initialize an array to store the count of each face
        int[] counter = new int[faces.length];
        // Iterate through each face value
        for (int i = 0; i < faces.length; i++) {
            // Iterate through each card in the hand
            for (Card card : hand) {
                //Check if the face of the card matches the current face value
                if (card.face.equals(faces[i]))
                    counter[i] += 1;
            }
        }
        //Return the array representing the count of each face in the hand.
        return counter;
    }

    /**
     * Determines if a hand of cards contains a flush. A flush is any five cards with the same suit.
     *
     * @param hand An array of cards representing a player's hand of cards.
     * @return true if the hand contains a flush, false otherwise.
     */
    public boolean flush(Card[] hand) {
        //Initialize a flag to indicate if the hand contains a flush
        boolean flag=false;
        //Initialize an array to store the count of each suit
        int[] counter = new int[suits.length];
        //Iterate through each suit
        for (int i = 0; i < suits.length; i++) {
            //Iterate through each card in the hand
            for (Card card : hand) {
                //Check if the suit of the card matches the current suit
                if (card.suit.equals(suits[i])) {
                    //Increment the counter
                    counter[i] += 1;
                }
            }
            //Check if the counter is equal to the number of cards in the hand
            if(counter[i]==hand.length)
                //Set the flag to true
                flag=true;
        }
        //Return the flag
        return flag;
    }

    /**
     * Determines if a hand of cards contains a straight. A straight is any five cards with consecutive face values.
     *
     * @param hand An array of cards representing a player's hand of cards.
     * @return true if the hand contains a straight, false otherwise.
     */

    public boolean straight(Card[] hand) {
        //Initialize a flag to indicate if the hand contains a straight
        boolean flag=false;
        //Initialize an array to store the count of each face
        boolean[] counter = new boolean[faces.length];
        //Iterate through each face
        for (int i = 0; i < faces.length; i++) {
            //Iterate through each card in the hand
            for (Card card : hand) {
                //Check if the face of the card matches the current face value
                if (!card.face.equals(faces[i])) {
                    //Set the flag to false
                    counter[i] = false;
                    //Break out of the loop
                    break;
                }
            }
            //Check if the flag is true
            flag= counter[i];
        }
        //Return the flag
        return flag;
    }
}