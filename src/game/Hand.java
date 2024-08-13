/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;
    private int totalValue;

    public Hand() {
        this.cards = new ArrayList<>();
        this.totalValue = 0;
    }

    public void addCard(Card card) {
        cards.add(card);
        calculateValue();
    }

    public boolean checkBust() {
        return totalValue > 21;
    }

    public boolean checkBlackjack() {
        return totalValue == 21 && cards.size() == 2;
    }

    public boolean checkFiveCardCharlie() {
        return cards.size() == 5 && totalValue <= 21;
    }

    private void calculateValue() {
        totalValue = 0;
        int aceCount = 0;
        for (Card card : cards) {
            totalValue += card.getValue();
            if (card.getRank().equals("A")) {
                aceCount++;
            }
        }
        // Adjust for Aces if totalValue > 21
        while (totalValue > 21 && aceCount > 0) {
            totalValue -= 10;
            aceCount--;
        }
    }

    public int getTotalValue() {
        return totalValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card.getRank()).append(" of ").append(card.getSuit()).append(", ");
        }
        // Remove the trailing comma and space if the StringBuilder is not empty
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString().isEmpty() ? "No cards" : sb.toString();
    }
}
