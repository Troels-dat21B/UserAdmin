import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static String FILENAME = "user.txt";
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

    public void run(){
        while(running){
            menu.printMenu();

            switch (menu.readChoice()){
                case 1:
                    viewUserList();
                    break;
                case 2:
                    //Create user
                    createNewUser();
                    break;
                case 3:
                    //Delete user
                    deleteUser();
                    break;
                case 4:
                    safeFile();
                    break;
                case 5:
                    readFile();
                    break;
                case 9:
                    System.out.println("Bye!");
                    running = false;
                    break;
                default:
                    System.out.println("Not a valid input");

            }
        }
    }
    public void createNewUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type an ID number");
        int id = Integer.parseInt(scanner.nextLine());

        if(id <= 0){
            System.out.println("ID number has to be positive");
            createNewUser();
        }else{
            System.out.println("Type user name");
            String name = scanner.nextLine();

            System.out.println("Make a password");
            String password = scanner.nextLine();
            users.add(new Users(id, name, password));
        }


    }

    public void viewUserList(){
        if(users.size() == 0){
            System.out.println("No user exist");
        }else{
            for (int i = 0; i < users.size(); i++) {
                System.out.println(users.get(i));
            }
        }
    }

    public void deleteUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type ID number for the user you wish to delete");
        int id = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId() == id){
                users.remove(i);
            }
        }
        System.out.println("User removed!");
    }

    public void safeFile(){
        try{
            PrintStream writer = new PrintStream(FILENAME);
            for (int i = 0; i < users.size(); i++) {
                if(users.get(i) != null){
                    writer.println(("ID: " + users.get(i).getId()));
                    writer.println("Name: " + users.get(i).getName());
                    writer.println(("Password: " + users.get(i).getPassword()));
                }else{
                    System.out.println("No user on file.");
                }
                System.out.println("User added!");
            }
            writer.close();
        }catch (IOException e){
            System.out.println("An error occurred.");
        }
    }

    public void readFile(){
        try{
            File user = new File(FILENAME);
            Scanner reader = new Scanner(user);
            while (reader.hasNextLine()){
                String data = reader.nextLine();
                System.out.println(data);
            }
        }catch (FileNotFoundException e){
            System.out.println("Couldn't find a file.");
        }
    }



}
