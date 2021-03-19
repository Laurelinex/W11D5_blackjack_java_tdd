import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Game {

    private ArrayList<Player> players;
    private Deck deck;

    public Game(ArrayList<Player> players) {
        this.players = players;
        this.deck = new Deck();
        deck.populate();
        deck.shuffle();
    }

    public int playerCount() {
        return this.players.size();
    }

    public void deal(Player player) {
        player.addCardToHand(this.deck.dealOne());
    }

    public void dealToAllPlayers(int numberOfCards) {
        for(Player player : this.players) {
            for(int i=0; i<numberOfCards; i++) {
                this.deal(player);
            }
        }
    }

    public Player checkWinner() {
        Player winner = this.players.get(0);
        for(Player player : this.players) {
            if(winner.getScore() <= player.getScore()) {
                winner = player;
            }
        }
        return winner;
    }

    public boolean isDraw() {
        ArrayList<Integer> scores = new ArrayList<>();
        for(Player player : this.players) {
            scores.add(player.getScore());
        }
        Set<Integer> set = new HashSet<Integer>(scores);
        if(set.size() < scores.size()) {
            return true;
        } else {
            return false;
        }
    }
}
