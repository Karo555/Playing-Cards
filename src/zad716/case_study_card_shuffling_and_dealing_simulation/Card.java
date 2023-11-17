package zad716.case_study_card_shuffling_and_dealing_simulation;

/**
 * Represents a card.
 * This class provides a constructor for initializing a card.
 */
public class Card {
    //face of card ("Ace", "Deuce", ...)
    public final String face;
    //suit of card ("Hearts", "Diamonds", ...)
    public final String suit;
    //back of card
    public final String back;

    /**
     * Creates a new card. It is initialized with face, suit and card back.
     * @param cardFace A string representing the face of the card.
     * @param cardSuit A string representing the suit of the card.
     * @param cardBack A string representing the back of the card.
     */

    public Card(String cardFace, String cardSuit, String cardBack) {
        //initialize face of card
        this.face = cardFace;
        //initialize suit of card
        this.suit = cardSuit;
        //initialize back of card
        this.back = cardBack;
    }

    /**
     * Returns a string representation of the card.
     *
     * @return A string representation of the card.
     */
    //return String representation of Card
    public String toString(){
        return face + " of " + suit + " which looks like: " + back + " ";
    }
}
