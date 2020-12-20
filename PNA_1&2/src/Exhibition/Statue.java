package Exhibition;

import java.util.Objects;

public class Statue extends Elem implements Hidden {
    private String artist;

    public Statue() {
        artist = name = "";
        year = 2000;
        is_hidden = false;
    }

    public Statue(String _name, int _year, String _artist) {
        name = _name;
        year = _year;
        artist = _artist;
        is_hidden = false;
    }

    @Override
    void get_info() {
        System.out.println("This is " + name + " statue, that was created in " + year + " by " + artist);
    }

    @Override
    public void hide() {
        is_hidden = true;
    }

    @Override
    public void showup() {
        is_hidden = false;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public int hashCode()
    {
        return year * 1000 + (name.length() * 21 + artist.length() * 44) % 80;
    }

    @Override
    public String toString() {
        return name + Integer.toString(year) + artist + Boolean.toString(is_hidden);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Statue statue = (Statue) o;
        return Objects.equals(artist, statue.artist);
    }
}
