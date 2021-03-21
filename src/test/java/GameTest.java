import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GameTest {

    Game game;

    Player player1;
    Player player2;
    Player player3;

    Deck deck;

    Card card;
    Card card2;

    @Before
    public void before() {
        player1 = new Player("The dealer");
        player2 = new Player("Larry");
        player3 = new Player("Bob");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
//        players.add(player3);
        deck = new Deck();
        game = new Game(players);
        card = new Card(SuitType.SPADES, RankType.ACE);
        card2 = new Card(SuitType.SPADES, RankType.TEN);
    }

    @Test
    public void canCountPlayers() {
        assertEquals(2, game.playerCount());
    }

    @Test
    public void canDeal() {
        game.deal(player1);
        assertEquals(1, player1.getHandSize());
    }

    @Test
    public void dealToAllPlayers() {
        game.dealToAllPlayers(3);
        assertEquals(3, player1.getHandSize());
        assertEquals(3, player2.getHandSize());
    }

//    @Test
//    public void canCheckWinner() {
//        game.dealToAllPlayers(3);
//        Player winner;
//        if(player1.getScore() >= player2.getScore()) {
//            winner = player1;
//        } else {
//            winner = player2;
//        }
//        assertEquals(winner, game.getWinner());
//    }

    @Test
    public void isDraw() {
        player1.addCardToHand(card);
        player2.addCardToHand(card);
        assertEquals(true, game.isDraw());
    }

    @Test
    public void playStartsDealing2CardsToEachPlayer() {
        game.start();
        assertEquals(2, player1.getHandSize());
        assertEquals(2, player2.getHandSize());
    }

    @Test
    public void playerCannotWinIfScoreMoreThan21() {
        player1.addCardToHand(card);
        player1.addCardToHand(card);
        player2.addCardToHand(card);
        assertEquals(player2, game.getWinner());
    }

    @Test
    public void canSelectCorrectWinnerIfMoreThan2() {
        Game multiplayerGame;
        ArrayList<Player> multiPlayers = new ArrayList<>();
        multiplayerGame = new Game(multiPlayers);
        multiPlayers.add(player1);
        multiPlayers.add(player2);
        multiPlayers.add(player3);
        player1.addCardToHand(card);
        player1.addCardToHand(card);
        player2.addCardToHand(card);
        player3.addCardToHand(card2);
        player3.addCardToHand(card2);
        assertEquals(player3, multiplayerGame.getWinner());
    }

    @Test
    public void canSatThanksForPlayingOnEnd() {
        game.end();
        assertEquals("Thank you for playing!", game.end());
    }

    @Test
    public void canCheckIfAnyPlayerIsStillInBesidesDealerFalse() {
        Game multiplayerGame;
        ArrayList<Player> multiPlayers = new ArrayList<>();
        multiplayerGame = new Game(multiPlayers);
        multiPlayers.add(player1);
        multiPlayers.add(player2);
        multiPlayers.add(player3);
        player1.addCardToHand(card);
        player2.addCardToHand(card);
        player2.addCardToHand(card);
        player3.addCardToHand(card);
        player3.addCardToHand(card);
        assertEquals(false, multiplayerGame.playersRemain());
    }

    @Test
    public void canCheckIfAnyPlayerIsStillInBesidesDealerTrue() {
        Game multiplayerGame;
        ArrayList<Player> multiPlayers = new ArrayList<>();
        multiplayerGame = new Game(multiPlayers);
        multiPlayers.add(player1);
        multiPlayers.add(player2);
        multiPlayers.add(player3);
        player1.addCardToHand(card);
        player2.addCardToHand(card);
        player2.addCardToHand(card);
        player3.addCardToHand(card);
        assertEquals(true, multiplayerGame.playersRemain());
    }

//    @Test
//    public void canCheckForBlackJack() {
//        player1.addCardToHand(card);
//        player1.addCardToHand(card2);
//        player2.addCardToHand(card);
//        player2.addCardToHand(card);
//
//    }

//    @Test
//    public void playCanDetermineWinner() {
//        game.start();
//        Player winner;
//        if(player1.getScore() >= player2.getScore()) {
//            winner = player1;
//        } else {
//            winner = player2;
//        }
//        assertEquals(winner, game.getWinner());
//    }

}
