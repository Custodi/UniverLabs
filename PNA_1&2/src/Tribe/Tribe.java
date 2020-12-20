package Tribe;

public class Tribe
{
    private String name;
    private int count;
    private boolean is_fired;

    Tribe()
    {
        name = " ";
        count = 0;
        is_fired = false;
    }

    public Tribe(String n, int c, boolean f) {
        name = n;
        count = c;
        is_fired = f;
    }

    public static int get_summary_count(Tribe[] arr) {
        int result = 0;
        for(Tribe t:arr){
            result += t.get_count();
        }
        return result;
    }
    public static int get_summary_is_fired(Tribe []arr){
        int result = 0;
        for(Tribe t:arr){
            result += t.get_is_fired() ? 1 : 0;
        }
        return result;
    }

    public void get_info()
    {
        System.out.println("Name of tribe: " + get_name() + "\nCount of tribe: "
                + get_count() + "\nTribe is fired? " + get_is_fired());
    }

    // Getters
    public String get_name(){return name;}
    public int get_count(){return count;}
    public boolean get_is_fired(){return is_fired;}

    // Setters
    public void set_name(String name){this.name = name;}
    public void set_count(int count){this.count = count;}
    public void set_is_fired(boolean is_fired){this.is_fired = is_fired;}
}