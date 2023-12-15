package com.example.hellofx3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLoop {
    private static Controller controller;
    private static Enemy enemy = new Enemy();
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
        public static int takeDamage(int damage) {
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
        private int damage;
        private String nextAction;

        public Enemy(){
            prepareNextAction();
        }

        public void takeDamage(int damage) {
            if (defense > 0) {
                int effectiveDamage = Math.max(damage - defense, 0);
                defense = Math.max(defense - damage, 0);
                health -= effectiveDamage;
            } else {
                health = Math.max(health - damage, 0);
            }
        }


        public void setDefense(int shield) {
            defense += shield;
        }

        public int attack() {
            damage = 10;
            return damage;
        }

        public int defend() {
            defense = 6;
            return defense;
        }

        public void buff() {
            damage *= 2;
        }


        public int getHealth() {
            return health;
        }
        public int getDefense() {
            return defense;
        }

        // Method to determine the next action
        private void prepareNextAction() {
            Random rand = new Random();
            int action = rand.nextInt(3); // Randomly choose between 0, 1, and 2

            switch (action) {
                case 0:
                    nextAction = "Attack";
                    break;
                case 1:
                    nextAction = "Defend";
                    break;
                case 2:
                    nextAction = "Buff";
                    break;
                default:
                    nextAction = "Unknown";
                    break;
            }
        }

        // Public method to preview the next action
        public String previewNextAction() {
            return nextAction;
        }


        //enemy turn actions
        // enemyTurn method now uses the stored nextAction
        public void enemyTurn() {
            defense = 0;
            switch (nextAction) {
                case "Attack":
                    GameLoop.Characther.takeDamage(attack());
                    System.out.println("attack");
                    break;
                case "Defend":
                    defend();
                    System.out.println("defend" + defense);
                    break;
                case "Buff":
                    buff();
                    System.out.println("buff");
                    break;
            }
            prepareNextAction(); // Prepare the next action for the upcoming turn
        }
    }
}