package Game;

public class Warrior extends Stats implements Character, Object
{
    private String weapon;
    private String shield;

    public Warrior(String name, int health, int strength, int agility, String weapon, String shield) {
        super(name, health, strength, agility);
        this.weapon = weapon;
        this.shield = shield;
    }

    public Warrior(String name, int health, String weapon) {
        super(name, health);
        this.weapon = weapon;
        this.shield = "Round";
    }

    public Warrior() {
        super();
        this.weapon = "Knife";
        this.shield = "Round";
    }

    @Override
    public void attack()
    {
        System.out.println("Aaaarrrhhh!!!");
    }

    @Override
    public void print() { System.out.println(toString()); }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getShield() {
        return shield;
    }

    public void setShield(String shield) {
        this.shield = shield;
    }
}
