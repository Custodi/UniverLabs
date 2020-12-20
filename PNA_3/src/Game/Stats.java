package Game;

abstract class Stats {

    public Stats(String name, int health, int strength, int agility) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.agility = agility;
    }

    public Stats(String name, int health) {
        this.name = name;
        this.health = health;
        this.strength = 10;
        this.agility = 10;
    }

    public Stats()
    {
        name = "None";
        health = 20;
        strength = agility = 10;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    protected String name;
    protected int health;
    protected int strength;
    protected int agility;
}
