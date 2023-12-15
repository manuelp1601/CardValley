package com.example.hellofx3;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Cards {


    private static Image cardImage;
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

    public Image getCardImage() {
        return cardImage;
    }

    //method to use card
    public int useCard(){
        System.out.println(name);
        return 0;
    }


    /** Subclasses inheriting main methods from cards class */
    static class Strike extends Cards {
        private static int damage = 6;
        private ImageView imageView;

        @Override
        public Image getCardImage(){
            return super.getCardImage();
        }


        public Strike() {
            super("Strike", 1);


        }

        public int getDamage() {

            return damage;
        }


        public void useCard(GameLoop.Characther character, GameLoop.Enemy enemy) {
            if(character.getEnergy() >= this.getEnergyCost()) {
                System.out.println("Performing a strike with " + damage + " damage!");
                enemy.takeDamage(damage);
                character.useEnergy(this.getEnergyCost());
            } else {
                System.out.println("Not enough energy to perform strike");
            }
        }

    }

    static class Defend extends Cards{
        private int defense = 6;



        public Defend() {
            super("Defend", 1);


        }

        public int getDefense(){
            return defense;
        }

        public void useCard(GameLoop.Characther character) {
            if (character.getEnergy() >= this.getEnergyCost()) {
                character.setDefense(this.defense); // Add defense value to character
                GameLoop.Characther.useEnergy(this.getEnergyCost());
                System.out.println("Defense increased by " + this.defense);
            } else {
                System.out.println("Not enough energy to defend");
            }
        }

        @Override
        public Image getCardImage(){
            return super.getCardImage();
        }
    }



    static class ShieldBash extends Cards{
        private int defense;
        private int damage;

        public ShieldBash () {
            super("ShielBash", 2);
        }
        public void useCard(GameLoop.Characther character, GameLoop.Enemy enemy) {
            if (character.getEnergy() >= this.getEnergyCost()) {
                int damageBasedOnDefense = character.getDefense();
                System.out.println("Performing Shield Bash with " + damageBasedOnDefense + " damage!");
                enemy.takeDamage(damageBasedOnDefense);
                GameLoop.Characther.useEnergy(this.getEnergyCost());
            } else {
                System.out.println("Not enough energy to perform Shield Bash");
            }
        }

        @Override
        public Image getCardImage(){
            return super.getCardImage();
        }


        public int getDefense() {
            return defense;
        }

        public int getDamage(){
            return damage;
        }
    }
}
