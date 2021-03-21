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

//        To refractor and add to Game + initiate Game with a dealer.
        if(dealer.handHasBlackjack()) {
            System.out.println("Oh no! The dealer's hand has a Blackjack!");
            for(Player player : players.subList(1, players.size())) {
                if(player.handHasBlackjack()) {
                    System.out.println(player.getName() + " also has a Blackjack. It's a draw?");
                } else {
                    System.out.println(player.getName() + " loses.");
                }
            }
        } else {
            for(Player player : players.subList(1, players.size())) {
                if(player.handHasBlackjack()) {
                    System.out.println(player.getName() + " has a Blackjack!");
                }
            }
        }

        for(Player player : players.subList(1, players.size())) {
            String decision;
            char d;
            do {
                do {
                    String prompt = String.format("%s, would you like to (s)tand or (t)wist?", player.getName());
                    System.out.println(prompt);
                    decision = scanner.next();
                    d = decision.toLowerCase().charAt(0);
                } while (! (d == 's' || d == 't'));
                if(d == 't') {
                    System.out.println(player.getName() + " draws another card.");
                    game.deal(player);
                    for(int i=0; i<player.getHandSize(); i++) {
                        System.out.println(player.showCard(i));
                    }
                    System.out.println(String.format("Hand total is now %s", player.getScore()));
                }
                if(d == 's') {
                    System.out.println(player.getName() + " stands.");
                    System.out.println("");
                }
            } while (d != 's' && player.getScore() <= 21);
        }

//        After each player has had their turn, the dealer will show their hand.
        System.out.println("The dealer reveals their hand:");
        for (int i=0; i<dealer.getHandSize(); i++) {
            System.out.println(dealer.showCard(i));
        }
        System.out.println(String.format("Hand total is %s", dealer.getScore()));
        System.out.println("");

//        If the dealer has 16 or less, then they will draw another card.
        if(dealer.getScore() <= 16) {
            System.out.println("The dealer draws another card.");
            game.deal(dealer);
            for (int i=0; i<dealer.getHandSize(); i++) {
                System.out.println(dealer.showCard(i));
            }
            System.out.println(String.format("Hand total is %s", dealer.getScore()));
            System.out.println("");
        } else {
            System.out.println("The dealer stands.");
        }

//        If the dealer does not bust, then the higher point total between the player and dealer will win.
        if(dealer.handHasBusted()) {
            System.out.println("The dealer busts.");
            Player winner = game.getWinner();
            String winnerName = winner.getName();
            String output = String.format("%s wins!", winnerName);
            System.out.println(output);
        } else {
            System.out.println();
            if (game.isDraw()) {
                System.out.println("It's a draw!");
            } else {
                Player winner = game.getWinner();
                String winnerName = winner.getName();
                String output = String.format("%s wins!", winnerName);
                System.out.println(output);
            }
        }

//        if(game.isDraw()) {
//            System.out.println("It's a draw!");
//        } else {
//            Player winner = game.getWinner();
//            String winnerName = winner.getName();
//            String output = String.format("%s wins!", winnerName);
//            System.out.println(output);
//        }



    }
}
