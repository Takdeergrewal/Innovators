/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;
public class Player {
    private String name;
    private Hand hand;
    private int balance;
    private int bet;

    public Player(String name, int balance) {
        this.name = name;
        this.balance = balance;
        this.hand = new Hand();
    }

    public void placeBet(int amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Bet amount exceeds balance.");
        }
        bet = amount;
        balance -= amount;
    }

    public void receiveWinnings(int amount) {
        balance += amount;
    }

    public void receiveCard(Card card) {
        hand.addCard(card);
    }

    public void hit(Card card) {
        receiveCard(card);
    }

    public void stand() {
        // Player stands, do nothing.
    }

    public void doubleDown() {
        placeBet(bet);
    }

    public void split() {
        // Splitting logic if needed.
    }

    public boolean checkBust() {
        return hand.checkBust();
    }

    public boolean checkBlackjack() {
        return hand.checkBlackjack();
    }

    public int getBalance() {
        return balance;
    }

    public int getBet() {
        return bet;
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }
}