package zad716.case_study_card_shuffling_and_dealing_simulation;
import static zad716.case_study_card_shuffling_and_dealing_simulation.DeckOfCards.*;

/**
 * Tests the DeckOfCards class.
 */
public class DeckOfCardsTest {
    public static void main(String[] args) {
        //create DeckOfCards object
        DeckOfCards myDeckOfCards=new DeckOfCards();
        //shuffle cards
        myDeckOfCards.shuffle();
        //deal cards
        Card[] pokerHand= new Card[NUMBER_OF_CARDS_IN_HAND];
        //print cards
        for (int i=0;i< pokerHand.length;i++){
            pokerHand[i]=myDeckOfCards.dealCard();
            System.out.printf("%-19s", pokerHand[i]);
        }
        System.out.println();
        //check if the hand has a pair
        if (myDeckOfCards.pairs(pokerHand)==1)
            System.out.println("Has a pair");
        //check if the hand has two pairs
        else if (myDeckOfCards.pairs(pokerHand)==2)
            System.out.println("Has two pairs");
        //check if the hand doesn't have a pair
        else
            System.out.println("Doesn't have a pair");

        //check if the hand has three of a kind
        for(int i=0;i<NUMBER_OF_FACES;i++){
            if(myDeckOfCards.howMuchOfAKind(pokerHand)[i]==3)
                System.out.println("Three of a kind");
            //check if the hand has four of a kind
            else if (myDeckOfCards.howMuchOfAKind(pokerHand)[i]==4)
                System.out.println("Four of a kind");
        }
        //check if the hand has a flush
        for(int i=0;i<NUMBER_OF_SUITS;i++) {
            //check if the hand has a flush
            if (myDeckOfCards.flush(pokerHand))
                System.out.println("flush");
        }
    }
}
