import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Player dealer = new Player("The dealer");
        ArrayList<Player> players = new ArrayList<>();
        players.add(dealer);

//        Not working, ask later...
//        List<Player> playersNoDealer = players.subList(1, players.size());

        System.out.println("Welcome to Blackjack!");
        System.out.println("What is your name?");

        String name = scanner.next();
        Player player2 = new Player(name);
        players.add(player2);

        Deck deck = new Deck();
        Game game = new Game(players);

        System.out.println("The dealer deals two cards to each player and two cards for themselves.");

        game.start();

        String dealerShow = String.format(("%s reveals its first card:"), dealer.getName());
        System.out.println(dealerShow);
        System.out.println(dealer.showCard(0));
        System.out.println("");

        for(Player player : players.subList(1, players.size())) {
            String output = String.format("%s has:", player.getName());
            System.out.println(output);
            for(int i=0; i<player.getHandSize(); i++) {
                System.out.println(player.showCard(i));
            }
            System.out.println(String.format("Hand total is %s", player.getScore()));
        }
//
//        if(checkBlackjack()) {
//
//            System.out.println();
//        }

        if(game.isDraw()) {
            System.out.println("It's a draw!");
        } else {
            Player winner = game.getWinner();
            String winnerName = winner.getName();
            String output = String.format("%s wins!", winnerName);
            System.out.println(output);
        }



    }
}
