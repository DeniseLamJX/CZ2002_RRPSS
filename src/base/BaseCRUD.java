package base;

import base.crud.*;
import base.crud.Readable;

import java.util.Scanner;

abstract public class BaseCRUD implements Creatable, Updatable, Deletable, Readable {
    public String resource = "";

    public void startUI() {
        int option;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Choose an option:");
            System.out.printf("1. %s %s\n", Action.CREATE, resource);
            System.out.printf("2. %s %s\n", Action.UPDATE, resource);
            System.out.printf("3. %s %s\n", Action.DELETE, resource);
            System.out.printf("4. %s %s\n", Action.READ, resource);
            System.out.println("5. Quit");

            option = sc.nextInt(); // Read user option
            sc.nextLine(); // Read newline character after hitting Enter key

            switch (option) {
                case 1:
                    create();
                    break;
                case 2:
                    update();
                    break;
                case 3:
                    delete();
                    break;
                case 4:
                    read();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Please choose a valid option.");
            }
        } while (option != 5);
    }
}
