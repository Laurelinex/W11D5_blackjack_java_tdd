import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    Player player1;
    Card card;
    Card card2;

    @Before
    public void before() {
        player1 = new Player("Laureline");
        card = new Card(SuitType.SPADES, RankType.ACE);
        card2 = new Card(SuitType.SPADES, RankType.QUEEN);
    }

//    @Test
//    public void hasPlayerNumber() {
//        assertEquals(1, player1.getPlayerNumber());
//    }

    @Test
    public void hasName() {
        assertEquals("Laureline", player1.getName());
    }

    @Test
    public void playerHandStartsEmpty() {
        assertEquals(0, player1.getHandSize());
    }

    @Test
    public void canAddCardToHand() {
        player1.addCardToHand(card);
        assertEquals(1, player1.getHandSize());
    }

    @Test
    public void canGetScore() {
        player1.addCardToHand(card);
        assertEquals(11, player1.getScore());
    }

    @Test
    public void canShowCard() {
        player1.addCardToHand(card);
        assertEquals("ACE of SPADES", player1.showCard(0));
    }

    @Test
    public void canCheckIfHandHasBustedTrue() {
        player1.addCardToHand(card);
        player1.addCardToHand(card);
        assertEquals(22, player1.getScore());
        assertEquals(true, player1.handHasBusted());
    }

    @Test
    public void canCheckIfHandHasBustedFalse() {
        player1.addCardToHand(card);
        assertEquals(11, player1.getScore());
        assertEquals(false, player1.handHasBusted());
    }

    @Test
    public void canCheckIfHandHasBustedFalseWith21() {
        player1.addCardToHand(card);
        player1.addCardToHand(card2);
        assertEquals(21, player1.getScore());
        assertEquals(false, player1.handHasBusted());
    }

    @Test
    public void canCheckHandHasAceTrue() {
        player1.addCardToHand(card);
        assertEquals(true, player1.handHasAce());
    }

    @Test
    public void canCheckHandHasAceFalse() {
        player1.addCardToHand(card2);
        assertEquals(false, player1.handHasAce());
    }

    @Test
    public void canCheckIfHandHasBlackjackTrue() {
        player1.addCardToHand(card);
        player1.addCardToHand(card2);
        assertEquals(true, player1.handHasBlackjack());
    }

    @Test
    public void canCheckIfHandHasBlackjackFalse() {
        player1.addCardToHand(card);
        player1.addCardToHand(card);
        assertEquals(false, player1.handHasBlackjack());
    }

}
