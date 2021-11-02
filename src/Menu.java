import java.util.Scanner;

public class Menu {
    private int choice;
    private String menuHeader;
    private String leadText;
    private String[] menuItem;


    public Menu(String menuHeader, String leadText, String[] menuItem) {
        setMenuHeader(menuHeader);
        setLeadText(leadText);
        setMenuItem(menuItem);
    }



//region Set/Get

    public void setLeadText(String leadText) {
        this.leadText = leadText;
    }

    public String getLeadText() {
        return leadText;
    }

    public void setMenuHeader(String menuHeader) {
        this.menuHeader = menuHeader;
    }

    public String getMenuHeader() {
        return menuHeader;
    }

    public void setMenuItem(String[] menuItem) {
        this.menuItem = menuItem;
    }

    public String[] getMenuItem() {
        return menuItem;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public int getChoice() {
        return choice;
    }

    //endregion

    void printMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(getMenuHeader());
        for (int i = 0; i < menuItem.length; i++) {
            System.out.println(menuItem[i]);
        }
        System.out.println("");
        System.out.println(getLeadText());
        setChoice(scanner.nextInt());
    }

    int readChoice() {
        //Hvis ikke det valgte tal er true til et nummer i arrayed, skal den køre while
        if (getChoice() == 9) {
            return getChoice();
        }
        while (!(getChoice() <= menuItem.length)) {
            printMenu();
            readChoice();
        }
        return getChoice();
    }
}