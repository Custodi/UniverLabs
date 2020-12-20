package DB_Classes;

public class Staff extends Database_obj
{
    public Staff(int id_staff, String nickname, String name, String surname, String position, String password, int access_level) {
        this.id_staff = id_staff;
        this.access_level = access_level;
        this.nickname = nickname;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.position = position;
    }

    public Staff()
    {

    }

    public int id_staff, access_level;
    public String nickname, name, surname, password, position;
}
