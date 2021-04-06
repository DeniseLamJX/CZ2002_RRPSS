package promo;

import base.BaseController;
import database.SerializeDB;
import database.exceptions.FailReadException;
import database.exceptions.FailWriteException;
import exception.EmptyFieldException;
import exception.NegativeNumberException;
import menu.Menu;
import menu.MenuController;
import menu.exception.MenuNotFound;
import promo.exception.MenuAlreadyExistsException;
import promo.exception.NoMenuException;
import promo.exception.PromoNotFoundException;
import utility.InputValidator;

import java.util.ArrayList;
import java.util.List;

public class PromoController extends BaseController {

    private PromoController() {}; // Prevent the instantiation of PromoController

    private static final ArrayList<Promo> promoItems = new ArrayList<>();

    public static void createPromo(String name, String description, double price)
        throws EmptyFieldException, NegativeNumberException, FailReadException, FailWriteException {

        ArrayList<Promo> promoItems = retrievePromo();

        // Checks if promo name is empty. Throws EmptyFieldException if true
        if (InputValidator.isEmptyString(name)) {
            throw new EmptyFieldException("Promotion name cannot be empty.");
        }

        // Checks if promo price is negative. Throws NegativeNumberException if true
        if (InputValidator.isNegativeNumber(price)) {
            throw new NegativeNumberException("Promotion price cannot be negative.");
        }

        int newPromoId = SerializeDB.generateId(promoItems);
        Promo newPromoItem = new Promo(newPromoId, name, description, price); // Create new promo item
        promoItems.add(newPromoItem);

        savePromoToFile(promoItems);
    }

    public static void updatePromoInfo(int promoId, String name, String description, String price)
        throws EmptyFieldException, NegativeNumberException, NumberFormatException,
        PromoNotFoundException, FailReadException, FailWriteException {

        // Retrieve from file
        ArrayList<Promo> promoItems = retrievePromo();
        Promo promoToBeUpdated = SerializeDB.findById(promoItems, promoId);

        if (promoToBeUpdated == null) {
            throw new PromoNotFoundException();
        }

        // If the entered input is the SKIP keyword, retain the original value
        String newPromoName = isSkipUpdate(name) ? promoToBeUpdated.getName() : name;
        String newPromoDescription = isSkipUpdate(description)
            ? promoToBeUpdated.getDescription() : description;
        double newPromoPrice = isSkipUpdate(price) ? promoToBeUpdated.getPrice() : Double.parseDouble(price);

        // Checks if promotion name is empty. Throw EmptyFieldException if true
        if (InputValidator.isEmptyString(newPromoName)) {
            throw new EmptyFieldException("Promotion name cannot be empty.");
        }

        // Checks if promotion price is negative. Throw NegativeNumberException if true
        if (InputValidator.isNegativeNumber(newPromoPrice)) {
            throw new NegativeNumberException("Promotion price cannot be negative.");
        }

        // Perform updates
        promoToBeUpdated.setName(newPromoName);
        promoToBeUpdated.setDescription(newPromoDescription);
        promoToBeUpdated.setPrice(newPromoPrice);

        savePromoToFile(promoItems);
    }

    public static void addMenu(int promoId, int menuId)
        throws MenuNotFound, MenuAlreadyExistsException, FailReadException, PromoNotFoundException,
        FailWriteException {

        ArrayList<Promo> promoItems = retrievePromo();
        Promo promoToBeUpdated = SerializeDB.findById(promoItems, promoId);

        if (promoToBeUpdated == null) {
            throw new PromoNotFoundException();
        }

        ArrayList<Menu> menuItems = MenuController.retrieveMenu();
        Menu menuToBeAdded = SerializeDB.findById(menuItems, menuId);

        if (menuToBeAdded == null) {
            throw new MenuNotFound();
        }

        if (isMenuInPromo(promoToBeUpdated, menuToBeAdded)) {
            throw new MenuAlreadyExistsException();
        }

        promoToBeUpdated.addMenu(menuToBeAdded);
        savePromoToFile(promoItems);
    }

    public static void removeMenu(int promoId, int menuId)
        throws NoMenuException, FailReadException, FailWriteException, PromoNotFoundException {

        ArrayList<Promo> promoItems = retrievePromo();
        Promo promoToBeUpdated = SerializeDB.findById(promoItems, promoId);

        if (promoToBeUpdated == null) {
            throw new PromoNotFoundException();
        }

        ArrayList<Menu> menuItems = MenuController.retrieveMenu();
        Menu menuToBeDeleted = SerializeDB.findById(menuItems, menuId);

        if (menuToBeDeleted == null || !isMenuInPromo(promoToBeUpdated, menuToBeDeleted)) {
            throw new NoMenuException();
        }

        promoToBeUpdated.removeMenu(menuToBeDeleted);
        savePromoToFile(promoItems);
    }

    public static void removePromo(int promoId)
        throws PromoNotFoundException, FailReadException, FailWriteException {
        ArrayList<Promo> promoItems = retrievePromo();

        Promo promotionToBeDeleted = SerializeDB.findById(promoItems, promoId);

        if (promotionToBeDeleted == null) {
            throw new PromoNotFoundException();
        }

        promoItems.remove(promotionToBeDeleted);
        savePromoToFile(promoItems);
    }

    private static boolean isMenuInPromo(Promo promo, Menu menu) {
        ArrayList<Menu> menuItems = promo.getMenuItems();

        for (Menu m: menuItems) {
            if (m.getId() == menu.getId()) {
                return true;
            }
        }

        return false;
    }

    public static ArrayList<Promo> retrievePromo() throws FailReadException {
        List list = SerializeDB.readSerializedObject("promo.dat");
        return list == null ? new ArrayList<>() : (ArrayList) list;
    }

    private static void savePromoToFile(ArrayList<Promo> promoItems) throws FailWriteException {
        SerializeDB.writeSerializeObject("promo.dat", promoItems);
    }
}
