/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package game;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class Game {
    private List<Player> players;
    private Dealer dealer;
    private Shoe shoe;
    private Scanner scanner;

    public Game(int numberOfDecks) {
        this.players = new ArrayList<>();
        this.dealer = new Dealer();
        this.shoe = new Shoe(numberOfDecks);
        this.scanner = new Scanner(System.in);
    }

    public void startGame() {
        shoe.shuffle();
        dealInitialCards();

        for (Player player : players) {
            playerTurn(player);
        }

        dealer.playHand(shoe); // Dealer plays their hand.
        dealer.determineWinner(players);
        dealer.settleBets(players);
    }

    private void dealInitialCards() {
        for (Player player : players) {
            dealer.dealCard(player, shoe.drawCard());
            dealer.dealCard(player, shoe.drawCard());
        }
        dealer.dealCardToSelf(shoe.drawCard()); // Dealer's first card
        dealer.dealCardToSelf(shoe.drawCard()); // Dealer's second card
    }

    private void playerTurn(Player player) {
        System.out.println(player.getName() + "'s turn:");
        boolean finished = false;

        while (!finished) {
            System.out.println("Your hand: " + player.getHand() + " (Total value: " + player.getHand().getTotalValue() + ")");
            System.out.println("Choose an action: (H)it, (S)tand, (D)ouble down");

            String action = scanner.nextLine().toUpperCase();

            switch (action) {
                case "H":
                    player.hit(shoe.drawCard());
                    if (player.checkBust()) {
                        System.out.println("Busted! Your total is " + player.getHand().getTotalValue());
                        finished = true;
                    }
                    break;
                case "S":
                    finished = true;
                    break;
                case "D":
                    if (player.getBalance() >= player.getBet()) {
                        player.placeBet(player.getBet());
                        player.hit(shoe.drawCard());
                        finished = true;
                    } else {
                        System.out.println("Not enough balance to double down.");
                    }
                    break;
                default:
                    System.out.println("Invalid action. Please choose (H)it, (S)tand, or (D)ouble down.");
            }
        }
    }

    public void registerPlayer(Player player) {
        players.add(player);
    }

    public static void main(String[] args) {
        Game game = new Game(6); // 6-deck shoe

        // Create a player
        Player player = new Player("Player 1", 1000);
        player.placeBet(50);

        // Register the player
        game.registerPlayer(player);

        // Start the game
        game.startGame();
    }
}
