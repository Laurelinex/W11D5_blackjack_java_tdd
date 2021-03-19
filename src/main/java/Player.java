import java.util.ArrayList;

public class Player {

    private int playerNumber;
    private String name;
    private ArrayList<Card> playerHand;

    public Player(int playerNumber, String name) {
        this.playerNumber = playerNumber;
        this.name = name;
        this.playerHand = new ArrayList<Card>();
    }

    public int getPlayerNumber() {
        return this.playerNumber;
    }

    public String getName() {
        return this.name;
    }

    public int getHandSize() {
        return this.playerHand.size();
    }

    public void addCardToHand(Card card) {
        this.playerHand.add(card);
    }

    public int getScore() {
        int total = 0;
        for(Card card : this.playerHand) {
            total += card.getValueFromEnum();
        }
        return total;
    }
}
