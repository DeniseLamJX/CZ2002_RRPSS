package promo;

import base.BaseCRUD;
import database.SerializeDB;
import database.exceptions.FailReadException;
import database.exceptions.FailWriteException;
import exception.EmptyFieldException;
import exception.NegativeNumberException;
import menu.Menu;
import menu.MenuUI;
import menu.exception.MenuNotFound;
import promo.exception.MenuAlreadyExistsException;
import promo.exception.NoMenuException;
import promo.exception.PromoNotFoundException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PromoUI extends BaseCRUD {

    public PromoUI() { this.resource = "promotion"; }

    @Override
    public void create() {
        Scanner sc = new Scanner(System.in);

        try {
            // Get name of promotion from user
            System.out.print("Promotion name: ");
            String promoName = sc.nextLine();

            // Get description of promotion from user
            System.out.print("Promotion description: ");
            String promoDescription = sc.nextLine();

            // Get price of promotion from user
            System.out.print("Promotion price: ");
            double promoPrice = sc.nextDouble();

            // Create promotion
            PromoController.createPromo(promoName, promoDescription, promoPrice);
            // Success message
            System.out.println("Promotion successfully created!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
        } catch (NegativeNumberException | EmptyFieldException | FailReadException | FailWriteException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update() {
        int option;
        Scanner sc = new Scanner(System.in);

        System.out.println("Your current promotions: ");
        read();

        System.out.print("Please provide the Id of promotion to be updated: ");
        try {
            int promoId = sc.nextInt();
            sc.nextLine();

            ArrayList<Promo> promoItems = PromoController.retrievePromo();
            Promo promoToBeUpdated = SerializeDB.findById(promoItems, promoId);

            if (promoToBeUpdated == null) {
                throw new PromoNotFoundException();
            }

            do {
                System.out.println("What do you want to update?");
                System.out.println("1. Information of promotion");
                System.out.println("2. Add menu to promotion");
                System.out.println("3. Remove menu from promotion");
                System.out.println("4. Quit");

                option = sc.nextInt();
                sc.nextLine();

                switch (option) {
                    case 1:
                        startUpdatePromoInfoUI(promoId);
                        break;
                    case 2:
                        startAddMenuToPromoUI(promoId);
                        break;
                    case 3:
                        startRemoveMenuFromPromoUI(promoId, promoToBeUpdated.getMenuItems());
                        //startRemoveMenuFromPromoUI(promoToBeUpdated);
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("Please choose a valid option.");
                        break;
                }
            } while (option != 4);

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
        } catch (PromoNotFoundException | FailReadException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void read() {
        try {
            ArrayList<Promo> promoItems = PromoController.retrievePromo();
            displayPromoItems(promoItems);
        } catch (FailReadException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Please provide the Id of promotion to be removed: ");
        try {
            int promoId = sc.nextInt();

            // Remove promotion item
            PromoController.removePromo(promoId);
            // Success message
            System.out.println("Promotion successfully deleted!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid promotion Id");
        } catch (PromoNotFoundException | FailReadException | FailWriteException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void startUpdatePromoInfoUI(int promoId) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Update Menu (Id: " + promoId + ")");
            System.out.println("<Enter " + PromoController.getSkipKeyword() + " to retain old value>");
            System.out.println("***************");

            System.out.print("New promotion name: " );
            String promotionNameUserInput = sc.nextLine();

            System.out.print("New promotion description: " );
            String promotionDescriptionUserInput = sc.nextLine();

            System.out.print("New promotion price: " );
            String promotionPriceUserInput = sc.nextLine();

            // Update promotion item
            PromoController.updatePromoInfo(promoId, promotionNameUserInput,
                promotionDescriptionUserInput, promotionPriceUserInput);
            // Success message
            System.out.println("Promotion successfully updated!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please try again.");
        } catch (EmptyFieldException | NegativeNumberException |
            PromoNotFoundException | FailReadException | FailWriteException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void startAddMenuToPromoUI(int promoId) {
        Scanner sc = new Scanner(System.in);
        MenuUI menuUI = new MenuUI();

        menuUI.read(); // Display menu
        System.out.print("Please provide the Id of menu to be added: ");
        try {
            int menuId = sc.nextInt();

            // Add menu to promotion
            PromoController.addMenu(promoId, menuId);
            // Success message
            System.out.println("Menu added to promotion!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
        } catch (MenuAlreadyExistsException | MenuNotFound |
            FailReadException | FailWriteException | PromoNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void startRemoveMenuFromPromoUI(int promoId, ArrayList<Menu> menuItems) {
        Scanner sc = new Scanner(System.in);
        MenuUI menuUI = new MenuUI();

        menuUI.read(menuItems); // Display menu of promotion
        System.out.print("Please provide the Id of menu to be removed: ");
        try {
            int menuId = sc.nextInt();

            // Remove menu from promotion
            PromoController.removeMenu(promoId, menuId);
            // Success message
            System.out.println("Menu removed from promotion!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
        } catch (NoMenuException | FailReadException | FailWriteException | PromoNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void displayPromoItems(ArrayList<Promo> promoItems) {
        MenuUI menuUI = new MenuUI();

        if (promoItems.size() == 0) {
            System.out.println("Promotion is empty.");
        } else {
            System.out.println("----------");

            for (int i = 0; i < promoItems.size(); i++) {
                Promo currentPromo = promoItems.get(i);
                System.out.println("Promotion Id: " + currentPromo.getId());
                System.out.println("Name: " + currentPromo.getName());
                System.out.println("Description: " + currentPromo.getDescription());
                System.out.println("Price: " + currentPromo.getPrice());
                System.out.println("Menu: ");
                menuUI.read(currentPromo.getMenuItems());
                System.out.println("----------");
            }
        }
    }

}
