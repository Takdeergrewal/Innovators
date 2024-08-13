/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;
import java.util.List;

public class Dealer {
    private Hand hand;

    public Dealer() {
        this.hand = new Hand();
    }

    public void shuffleDeck(Deck deck) {
        deck.shuffle();
    }

    public void dealCard(Player player, Card card) {
        player.receiveCard(card);
    }

    public void dealCardToSelf(Card card) {
        hand.addCard(card);
    }

    public void playHand(Shoe shoe) {
        // The dealer must hit until they reach at least 17
        while (hand.getTotalValue() < 17) {
            hand.addCard(shoe.drawCard());
        }
    }

    public void determineWinner(List<Player> players) {
        int dealerTotal = hand.getTotalValue();
        System.out.println("Dealer's total: " + dealerTotal);
        boolean dealerBust = dealerTotal > 21;

        for (Player player : players) {
            int playerTotal = player.getHand().getTotalValue();
            System.out.println(player.getName() + "'s total: " + playerTotal);

            if (player.checkBust()) {
                System.out.println(player.getName() + " busts and loses.");
            } else if (dealerBust) {
                System.out.println(player.getName() + " wins! Dealer busted.");
                playerWins(player);
            } else if (playerTotal > dealerTotal) {
                System.out.println(player.getName() + " wins!");
                playerWins(player);
            } else if (playerTotal < dealerTotal) {
                System.out.println(player.getName() + " loses.");
            } else {
                System.out.println(player.getName() + " pushes (ties) with the dealer.");
                playerPushes(player);
            }
        }
    }

    public void settleBets(List<Player> players) {
        // This method would handle updating player balances and dealing with bets after the round.
        // Example: You could adjust the player’s balance based on win/loss, but this logic is already in determineWinner().
    }

    public Hand getHand() {
        return hand;
    }

    private void playerWins(Player player) {
        player.receiveWinnings(player.getBet() * 2); // Assuming 1:1 payout
    }

    private void playerPushes(Player player) {
        player.receiveWinnings(player.getBet()); // Returns the original bet to the player
    }

    void playHand() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}