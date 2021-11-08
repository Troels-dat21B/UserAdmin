import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static String FILENAME = "users.txt";
    Menu menu = new Menu("MENU:", "Please choose option",
            new String[]{"1. View user list",
                    "2. Create new user",
                    "3. Delete user",
                    "4. Save current users to file",
                    "5. Load users from file",
                    "9. Quit"});
    ArrayList<Users> users = new ArrayList<>();
    boolean running = true;

    public static void main(String[] args) {
        Main main = new Main();

        main.run();
    }

    public void run() {
        while (running) {
            menu.printMenu();

            switch (menu.readChoice()) {
                case 1 -> viewUserList();
                case 2 ->
                        //Create user
                        createNewUser();
                case 3 ->
                        //Delete user
                        deleteUser();
                case 4 -> safeFile();
                case 5 -> readFile();
                case 9 -> {
                    System.out.println("Bye!");
                    running = false;
                }
                default -> System.out.println("Not a valid input!\n");
            }
        }
    }

    public void createNewUser() {
        Scanner scanner = new Scanner(System.in);
        int id;

        System.out.println("Type an ID number");
        try {
            id = Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException e) {
            System.out.println("That was not a number!\n");
            return;
        }

        if (id <= 0) {
            System.out.println("ID number has to be positive and above 0\n");
            createNewUser();
        } else {
            System.out.println("Type user name");
            String name = scanner.nextLine();

            System.out.println("Make a password");
            String password = scanner.nextLine();
            users.add(new Users(id, name, password));
            System.out.println("User added! :)\n");
        }
    }

    public void viewUserList() {
        if (users.size() == 0) {
            System.out.println("No user exist\n");
        } else {

            for (Users user : users) {
                System.out.println(user);
            }
            System.out.println(" ");
        }
    }

    public void deleteUser() {
        Scanner scanner = new Scanner(System.in);

        if(users.size() == 0){
            System.out.println("No users on file!\n");
            return;
        }else{
            System.out.println("Type ID number for the user you wish to delete\n");
            for (Users user : users) {
                System.out.println("ID: " + user.getId() + " User: " + user.getName());
            }
        }
        int id = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.remove(i);
                System.out.println("User removed!\n");
                return;
            }
        }
        System.out.println("No user found!\n");
    }

    public void safeFile() {
        try {
            PrintStream writer = new PrintStream(FILENAME);
            for (Users user : users) {
                if (user != null) {
                    writer.append("ID: ").append(String.valueOf(user.getId()));
                    writer.println();
                    writer.append("Name: ").append(user.getName());
                    writer.println();
                    writer.append("Password: ").append(user.getPassword());
                    writer.println();
                } else {
                    System.out.println("No user on file.");
                }
            }
            writer.close();
            System.out.println("File updated!\n");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public void readFile() {
        int id = 0;
        String name = "";
        String password = "";
        try {
            File user = new File(FILENAME);
            Scanner reader = new Scanner(user);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                //System.out.println(data);
                if (data.contains("ID:") || data.contains("Id:") || data.contains("id:")) {
                    id = Integer.parseInt(data.substring(4));
                } else if (data.contains("name:") || data.contains("Name:")) {
                    name = data.substring(6);
                } else if (data.contains("password:") || data.contains("Password:")) {
                    password = data.substring(10);
                }
                if (id != 0 && !name.equals("") && !password.equals("")) {
                    users.add(new Users(id, name, password));
                    name = "";
                    password = "";
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find a file.");
        }
        System.out.println("File has been loaded and added to User list!\n");
    }
}