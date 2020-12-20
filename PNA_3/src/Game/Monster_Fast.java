package Game;

public class Monster_Fast extends Monster {

    public Monster_Fast()
    {
        super();
        speed = 25.4;
    }

    public Monster_Fast(String name, int health, int strength, int agility, double length_of_teeth, double length_of_claws, int count_of_paws, double speed) {
        super(name, health, strength, agility, length_of_teeth, length_of_claws, count_of_paws);
        this.speed = speed;
    }

    @Override
    public void attack()
    {
        System.out.println("Shoohh");
    }

    @Override
    public void print()
    {
        System.out.println( toString() );
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    private double speed;
}
