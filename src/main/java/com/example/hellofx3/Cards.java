package com.example.hellofx3;

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
    class Strike extends Cards {
        private int damage;


        public Strike(String name,int energyCost, int damage) {
            super(name, energyCost);
            this.damage = damage;
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

    class Defend extends Cards{
        private int defense;



        public Defend(String name, int energyCost, int defense) {
            super(name, energyCost);

            this.defense = defense;
        }

        public int getDefense(){
            return defense;
        }
    }

    class Fireball extends Cards{
        private int damage;


        public Fireball(String name, int energyCost, int damage) {
            super(name, energyCost);
            this.damage = damage;
        }

        public int getDamage(){
            return damage;
        }


    }

    class Heal extends Cards{
        private int life;


        public Heal(String name, int energyCost, int life) {
            super(name, energyCost);
            this.life = life;
        }

        public int getLife(){
            return life ;
        }
    }

    class ShieldBash extends Cards{
        private int defense;
        private int damage;

        public ShieldBash (String name, int energyCost, int defense, int damage) {
            super(name, energyCost);
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
