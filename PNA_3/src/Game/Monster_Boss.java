package Game;

public class Monster_Boss extends Monster {

    @Override
    public void attack()
    {
        System.out.println("Dooooom");
    }

    public Monster_Boss()
    {
        super();
        crit_attack = 1.5;
    }

    public Monster_Boss(String name, int health, int strength, int agility, double length_of_teeth, double length_of_claws, int count_of_paws, double crit_attack) {
        super(name, health, strength, agility, length_of_teeth, length_of_claws, count_of_paws);
        this.crit_attack = crit_attack;
    }

    @Override
    public void print()
    {
        System.out.println( toString() );
    }

    public double getCrit_attack() {
        return crit_attack;
    }

    public void setCrit_attack(double crit_attack) {
        this.crit_attack = crit_attack;
    }

    private double crit_attack;
}
