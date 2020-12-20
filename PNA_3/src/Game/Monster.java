package Game;

abstract class Monster extends Stats implements Character, Object {
    public Monster(String name, int health, int strength, int agility, double length_of_teeth, double length_of_claws, int count_of_paws) {
        super(name, health, strength, agility);
        this.length_of_teeth = length_of_teeth;
        this.length_of_claws = length_of_claws;
        this.count_of_paws = count_of_paws;
    }

    public Monster(String name, int health, double length_of_teeth, double length_of_claws) {
        super(name, health);
        this.length_of_teeth = length_of_teeth;
        this.length_of_claws = length_of_claws;
        this.count_of_paws = 8;
    }

    public Monster()
    {
        super();
        this.length_of_teeth = 10.5;
        this.length_of_claws = 5.7;
        this.count_of_paws = 8;
    }

    public double getLength_of_teeth() {
        return length_of_teeth;
    }

    public void setLength_of_teeth(double length_of_teeth) {
        this.length_of_teeth = length_of_teeth;
    }

    public double getLength_of_claws() {
        return length_of_claws;
    }

    public void setLength_of_claws(double length_of_claws) {
        this.length_of_claws = length_of_claws;
    }

    public int getCount_of_paws() {
        return count_of_paws;
    }

    public void setCount_of_paws(int count_of_paws) {
        this.count_of_paws = count_of_paws;
    }

    protected double length_of_teeth;
    protected double length_of_claws;
    protected int count_of_paws;
}
