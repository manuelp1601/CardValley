package com.example.hellofx3;

import java.util.ArrayList;
import java.util.List;

public class GameLoop {
    private static Controller controller;
    private Enemy enemy = new Enemy();
    private Characther characther = new Characther();
    private final List<Cards> deck = new ArrayList<>();
    private List<Cards> hand = new ArrayList<>();



    public GameLoop() {
    }

    //Create Deck of card (just list of cards, no Gui methods.
    public List<Cards> initializeDeck() {
        //create list of cards

        //adding instances of Strike and Defense
        for(int i = 0; i < 3; i++) {
            deck.add(new Cards.Strike());
            deck.add(new Cards.Defend());
        }
        //adding instances of Fireball and ShieldBash
        for(int i = 0; i < 3; i++){
            deck.add(new Cards.ShieldBash());
        }
        return deck;
    }

    public Enemy getEnemy() {
        return this.enemy;
    }
    public Characther getCharacther() {
        return this.characther;
    }

    public List<Cards> drawHand(int cardnum) {
        List<Cards> drawnCards = new ArrayList<>();
        for(int i = 0; i < cardnum; i++) {
            if(!deck.isEmpty()) {
                drawnCards.add(deck.remove(0));
            }
        }
        hand.addAll(drawnCards);
        return drawnCards;
    }

    public List<Cards> newTurn() {
        List<Cards> hand = drawHand(5);
        return hand;
    }

    static class Characther {
        private static int health = 30;
        private static int energy = 4;
        private static int defense;

        public static int useEnergy(int amount) {
            if (energy >= amount) {
                energy -= amount;
            } else {
                if (energy <= 0) {
                    energy = 0;
                }
            }
            return energy;
        }
        public int takeDamage(int damage) {
            if (defense >= damage) {
                // If defense is greater than or equal to damage, deduct damage from defense
                defense -= damage;
            } else {
                // If defense is less than damage, first use defense to reduce damage,
                // then apply the remaining damage to health
                damage -= defense; // Reduce incoming damage by the amount of defense
                defense = 0; // Defense is fully used
                health -= damage; // Apply remaining damage to health
            }
            return health;
        }

        public int getHealth(){
            return health;
        }
        public int getEnergy() {
            return energy;
        }
        public int getDefense() {
            return defense;
        }
        public void setDefense(int additionalDefense){
            defense += additionalDefense;
        }

        public void resetEnergy(){
            energy = 4;
        }
        public void resetDefense() {
            defense = 0;
        }


    }



    static class Enemy {
        private int health = 50;
        private int defense;
        private  int damage = 6;


        public void takeDamage(int damage) {
            health = Math.max(health - damage, 0);
        }


        public void setDefense(int shield) {
            if (shield > 0) {
                defense += shield;

                if (defense <= 0) {
                    defense = 0;
                }
            }
        }

        public int attack (){
            damage = 10;
            return damage ;
        }

        public void buff () {
            damage *= 2;
        }

        public int getHealth(){
            return health;
        }
    }
}