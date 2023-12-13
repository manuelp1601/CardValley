package com.example.hellofx3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GameLoop {
    public void initializeDeck() {
        //create list of cards
        List<Cards> deck = new ArrayList<>();
        //adding instances of Strike and Defense
        for(int i = 0; i < 3; i++) {
            deck.add(new Cards.Strike());
            deck.add(new Cards.Defend());
        }
        //adding instances of Heal
        for(int i = 0; i < 3; i++){
            deck.add(new Cards.Heal());
        }
        //adding instances of Fireball and ShieldBash
        for(int i = 0; i < 3; i++){
            deck.add(new Cards.Fireball());
            deck.add(new Cards.ShieldBash());
        }
    }

    static class Characther {
        private static int health = 30;
        private int energy = 3;
        private static int defense;


        public void takeDamage(int damage) {
            if (damage > 0) {
                health -= damage;

                if (health <= 0) {
                    health = 0;

                }
            }
        }
        public void setDefense(int shield){
            if (shield > 0) {
                defense += shield;

                if (defense <= 0){
                    defense = 0;
                }
            }

        }
    }



    static class Enemy {
        private int health = 50;
        private int defense;
        private  int damage = 6;


        public void takeDamage(int damage) {
            if (damage > 0) {
                health -= damage;

                if (health <= 0) {
                    health = 0;

                }
            }
        }

        public void setDefense(int shield) {
            if (shield > 0) {
                defense += shield;

                if (defense <= 0) {
                    defense = 0;
                }
            }
        }

        public void attack (){
            if (Characther.defense != 0 ) {
                int damage = 6;
                Characther.defense =- damage;
            }
            else Characther.health =- damage;
        }

        public void buff () {
            damage *= 2;
        }
    }
}