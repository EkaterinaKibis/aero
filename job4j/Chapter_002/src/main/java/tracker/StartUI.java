package tracker;

import java.util.Scanner;


public class StartUI {

    public void init(Scanner scanner, Tracker tracker) {
        boolean run = false;
        while (run) {
            this.showMenu();
            System.out.print("Select: ");
            int select = Integer.valueOf(scanner.nextLine());
            if (select == 0) {
                System.out.println("=== Create a new Item ====");
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                Item item = new Item(name);
                tracker.add(item);
            }else if (select == 1) {
                System.out.println(" 1. Show all items");
                Item [] items = tracker.findAll();
                for (Item item : items) {
                    System.out.println("Item name : " + item.getName());
                }
                }else if (select == 2){
                System.out.println("2. Edit item");
                System.out.print("Enter id ");
                String id = scanner.nextLine();
                if (tracker.findById(id) != null){
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                Item item = new Item(name);
                tracker.replace(id,item);
                }else
                    System.out.println(" id incorrect");


//             Добавить остальные действия системы по меню.
            } else if (select == 6) {
                run = false;
            }
        }
    }

    private void showMenu() {
        System.out.println("Menu.");
        System.out.println("0. Add new ");
        System.out.println(" 1. Show all items");
        System.out.println("2. Edit item");
        System.out.println(" Delete item");
        System.out.println(" Find item by Id");
        System.out.println(" Find items by name");
        System.out.println(" Exit Program");
        System.out.println(" Select:");
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tracker tracker = new Tracker();
        new StartUI().init(scanner, tracker);
    }
}