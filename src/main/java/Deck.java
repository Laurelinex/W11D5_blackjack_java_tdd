import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> cards;
    public Deck() {
        this.cards = new ArrayList<Card>();
    }

    public int getSize() {
        return cards.size();
    }

    public void populate() {

        SuitType[] suits = SuitType.values();
        RankType[] ranks = RankType.values();

        for(SuitType suit: SuitType.values())
        {
            for(RankType rank: RankType.values())
            {
                this.cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    public Object[] getAllCards() {

        return this.cards.toArray();
    }

    public Card dealOne() {
        return cards.remove(0);
    }
}
