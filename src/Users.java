import java.util.ArrayList;
import java.util.Scanner;

public class Users {
    private int id;
    private String name;
    private String password;

    public Users() {
    }

    public Users(int id) {

    }

    public Users(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;

    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "ID: " + id + ',' + " " + "User: " + name + ',' + " " + "Password: " + password;
    }
}
