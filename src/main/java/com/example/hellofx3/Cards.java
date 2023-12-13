package com.example.hellofx3;

import javafx.scene.image.ImageView;

public class Cards {
    private String name;
    private int energyCost;

    public Cards(String name, int energyCost) {
        this.name = name;
        this.energyCost = energyCost;
    }

    public String getName(){
        return name;
    }
    public int getEnergyCost(){
        return energyCost;
    }
    //method to use card
    public void useCard(){
        System.out.println(name);
    }


    /** Subclasses inheriting main methods from cards class */
    static class Strike extends Cards {
        private int damage;
        private ImageView imageView;




        public Strike() {
            super("Strike", 1);
            this.damage = 6;
        }

        public int getDamage() {
            return damage;
        }

        @Override
        public void useCard() {
            System.out.println("Performing a strike with " + damage + " damage!");
            // Code for dealing damage to an enemy can be added here
        }
    }

    static class Defend extends Cards{
        private int defense;



        public Defend() {
            super("Defend", 1);

            this.defense = 6;
        }

        public int getDefense(){
            return defense;
        }
    }

    static class Fireball extends Cards{
        private int damage;


        public Fireball() {
            super("FireBall",2);
            this.damage = 10;
        }

        public int getDamage(){
            return damage;
        }


    }

    static class Heal extends Cards{
        private int life;


        public Heal() {
            super("Heal", 2);
            this.life = 10;
        }

        public int getLife(){
            return life ;
        }
    }

    static class ShieldBash extends Cards{
        private int defense;
        private int damage;

        public ShieldBash () {
            super("ShielBash", 2);
            this.defense = defense;
            this.damage = defense;
        }

        public int getDefense() {
            return defense;
        }

        public int getDamage(){
            return damage;
        }
    }
}
