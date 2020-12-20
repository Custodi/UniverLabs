package Game;

public class Mage extends Stats implements Character, Object {

    public Mage(String name, int health, int strength, int agility, String magic) {
        super(name, health, strength, agility);
        this.magic = magic;
    }

    public Mage(String name, int health, String magic) {
        super(name, health);
        this.magic = magic;
    }

    public Mage()
    {
        super();
        this.magic = "Delightful";
    }

    public String getMagic() {
        return magic;
    }

    public void setMagic(String magic) {
        this.magic = magic;
    }

    private String magic;

    @Override
    public void attack()
    {
        System.out.println("Power of magic");
    }

    @Override
    public void print() { System.out.println(toString()); };
}
