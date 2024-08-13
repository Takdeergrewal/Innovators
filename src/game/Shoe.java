/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.util.ArrayList;
import java.util.List;

public class Shoe {
    private final List<Deck> decks;

    public Shoe(int numberOfDecks) {
        this.decks = new ArrayList<>();
        for (int i = 0; i < numberOfDecks; i++) {
            decks.add(new Deck());
        }
    }

    public void shuffle() {
        for (Deck deck : decks) {
            deck.shuffle();
        }
    }

    public Card drawCard() {
        for (Deck deck : decks) {
            if (!deck.cards.isEmpty()) {
                return deck.drawCard();
            }
        }
        throw new IllegalStateException("All decks are empty!");
    }
}