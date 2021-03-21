import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Runner {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Player dealer = new Player("The dealer");
        ArrayList<Player> players = new ArrayList<>();
        players.add(dealer);

        System.out.println("Welcome to Blackjack!");
        System.out.println("How many players would like to play?");

        String input = scanner.next();
        int numberOfPlayers = parseInt(input);

        for(int i=0; i<numberOfPlayers; i++) {
            String prompt = String.format("Player %s, enter your name: ", (i + 1));
            System.out.println(prompt);
            String playerName = scanner.next();
            Player player = new Player(playerName);
            players.add(player);
        }

        System.out.println("");

        Game game = new Game(players);

        List<Player> playersWithoutDealer = players.subList(1, players.size());

        System.out.println("The dealer deals two cards to each player and two cards for themselves.");

//        2 cards are distributed to all players.
        game.start();

//        One of the dealer cards is dealt face up.
        String dealerShow = String.format(("%s reveals their first card:"), dealer.getName());
        System.out.println(dealerShow);
        System.out.println(dealer.showCard(0) + " worth " + dealer.getShowCardValue(0));
        System.out.println("");

//        We loop through the players and display hand info each, skipping the dealer at index 0.
        for(Player player : playersWithoutDealer) {
            String output = String.format("%s has:", player.getName());
            System.out.println(output);
            for(int i=0; i<player.getHandSize(); i++) {
                System.out.println(player.showCard(i));
            }
            System.out.println(String.format("Hand total is %s", player.getScore()));
            System.out.println("");
        }

//        To refractor and add to Game class + initiate Game with a dealer.
//        The game first checks for a Blackjack consisting of an ace and any 10-point card.
        if(dealer.handHasBlackjack()) {
            System.out.println("Oh no! The dealer's hand has a Blackjack!");
            for(Player player : playersWithoutDealer) {
                if(player.handHasBlackjack()) {
                    System.out.println(player.getName() + " also has a Blackjack. It's a draw?");
                } else {
                    System.out.println(player.getName() + " loses.");
                    System.out.println("The dealer wins.");
                }
            }
        } else {
            for(Player player : playersWithoutDealer) {
                if(player.handHasBlackjack()) {
                    System.out.println(player.getName() + " has a Blackjack!");
                    System.out.println(player.getName() + " wins.");
                }
            }
        }

        for(Player player : playersWithoutDealer) {
            String decision;
            char d;
            if(!player.handHasBusted()) {
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
                        if(player.handHasBusted()) {
                            System.out.println(player.getName() + " busts.");
                        } else if(player.getScore() == 21) {
                            System.out.println(player.getName() + " has a Blackjack.");
                        }
                        System.out.println("");
                    }
                    if(d == 's') {
                        System.out.println(player.getName() + " stands.");
                        System.out.println("");
                    }
                } while (d != 's' && player.getScore() < 21);
            }
        }

//        After each player has had their turn, the dealer will show their hand.
        if(!game.playersRemain()) {
            System.out.println("All players have busted.");
        } else {
            System.out.println("The dealer reveals their hand:");
            for (int i=0; i<dealer.getHandSize(); i++) {
                System.out.println(dealer.showCard(i));
            }
            System.out.println(String.format("Hand total is %s", dealer.getScore()));
            System.out.println("");
        }

//        If the dealer has 16 or less, then they will draw another card.
        if(dealer.getScore() <= 16 && game.playersRemain()) {
            System.out.println("The dealer draws another card.");
            game.deal(dealer);
            for (int i=0; i<dealer.getHandSize(); i++) {
                System.out.println(dealer.showCard(i));
            }
            System.out.println(String.format("Hand total is %s", dealer.getScore()));
            System.out.println("");
        } else if(dealer.getScore() > 16 && !dealer.handHasBusted() && game.playersRemain()){
            System.out.println("The dealer stands.");
        }

//        If the dealer does not bust, then the higher point total between the player and dealer will win.
        if(dealer.handHasBusted() && game.playersRemain()) {
            System.out.println("The dealer busts.");
            Player winner = game.getWinner();
            String winnerName = winner.getName();
            String output = String.format("%s wins!", winnerName);
            System.out.println(output);
        } else {
            System.out.println("Let's see...");
            if (game.isDraw()) {
                System.out.println("It's a draw!");
            } else {
                Player winner = game.getWinner();
                String winnerName = winner.getName();
                String output = String.format("%s wins!", winnerName);
                System.out.println(output);
            }
        }

        System.out.println(game.end());

    }
}
