import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class DeckTest {

    Deck deck;

    @Before
    public void before(){
        deck = new Deck();
    }

    @Test
    public void deckStartsEmpty(){
        assertEquals(0, deck.getSize());
    }

    @Test
    public void canPopulateDeck(){
        deck.populate();
        assertEquals(52, deck.getSize());
    }

    @Test
    public void canGetAllCards(){
        deck.populate();
        Object[] allCards = deck.getAllCards();

        assertEquals(52, allCards.length);
        assertEquals(Card.class, allCards[0].getClass());
    }

    @Test
    public void canShuffleDeck(){
        deck.populate();
        deck.shuffle();

        Deck deck2 = new Deck();
        deck2.populate();

        assertEquals(52, deck.getSize());

        Object[] allCards = deck.getAllCards();
        Object[] allCards2 = deck2.getAllCards();
        assertFalse(Arrays.equals(allCards, allCards2));
    }

    @Test
    public void canGetOneCard(){
        deck.populate();

        ArrayList<Card> playerHand = new ArrayList<Card>();
        playerHand.add(deck.dealOne());

        assertEquals(51, deck.getSize());
        assertEquals(1, playerHand.size());
    }
}
