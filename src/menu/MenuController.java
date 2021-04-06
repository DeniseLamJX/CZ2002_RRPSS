package menu;

import base.BaseController;
import database.SerializeDB;
import database.exceptions.FailReadException;
import database.exceptions.FailWriteException;
import exception.EmptyFieldException;
import exception.NegativeNumberException;
import menu.exception.MenuNotFound;
import utility.InputValidator;

import java.util.ArrayList;
import java.util.List;

public class MenuController extends BaseController {

    private MenuController() { super(); }; // Prevent the instantiation of MenuController

    public static void createMenu(String name, String description, double price, MenuType type)
        throws EmptyFieldException, NegativeNumberException, FailReadException, FailWriteException {

        // Retrieve from file
        ArrayList<Menu> menuItems = retrieveMenu();

        // Checks if menu name is empty. Throws EmptyFieldException if true
        if (InputValidator.isEmptyString(name)) {
            throw new EmptyFieldException("Menu name cannot be empty.");
        }

        // Checks if menu price is negative. Throws NegativeNumberException if true
        if (InputValidator.isNegativeNumber(price)) {
            throw new NegativeNumberException("Menu price cannot be negative.");
        }

        int newMenuId = SerializeDB.generateId(menuItems);
        Menu newMenuItem = new Menu(newMenuId, name, description, price, type); // Create new menu item
        menuItems.add(newMenuItem);

        saveMenuToFile(menuItems);
    }

    public static void updateMenu(int menuId, String name, String description, String price, MenuType type)
        throws EmptyFieldException, NegativeNumberException, FailReadException, FailWriteException,
        MenuNotFound, NumberFormatException {

        // Retrieve from file
        ArrayList<Menu> menuItems = retrieveMenu();
        Menu menuToBeUpdated = SerializeDB.findById(menuItems, menuId);

        // If menu is not found, throw MenuNotFound exception
        if (menuToBeUpdated == null) {
            throw new MenuNotFound();
        }

        // If the entered input is the SKIP keyword, retain the original value
        String newMenuName = isSkipUpdate(name) ? menuToBeUpdated.getName() : name;
        String newMenuDescription = isSkipUpdate(description) ? menuToBeUpdated.getDescription() : description;
        double newMenuPrice = isSkipUpdate(price) ? menuToBeUpdated.getPrice() : Double.parseDouble(price);

        // Checks if menu name is empty. Throws EmptyFieldException if true
        if (InputValidator.isEmptyString(newMenuName)) {
            throw new EmptyFieldException("Menu name cannot be empty");
        }

        // Checks if menu price is negative. Throws NegativeNumberException if true
        if (InputValidator.isNegativeNumber(newMenuPrice)) {
            throw new NegativeNumberException("Menu price cannot be negative.");
        }

        // Perform updates
        menuToBeUpdated.setName(newMenuName);
        menuToBeUpdated.setDescription(newMenuDescription);
        menuToBeUpdated.setPrice(newMenuPrice);
        menuToBeUpdated.setType(type);

        // Save array list of menu into file
        saveMenuToFile(menuItems);
    }

    public static void removeMenu(int id) throws MenuNotFound, FailReadException, FailWriteException {
        ArrayList<Menu> menuItems = retrieveMenu();

        Menu menuToBeDeleted = SerializeDB.findById(menuItems, id);

        if (menuToBeDeleted == null) {
            throw new MenuNotFound();
        }

        menuItems.remove(menuToBeDeleted);

        saveMenuToFile(menuItems);
    }

    public static ArrayList<Menu> retrieveMenu() throws FailReadException  {
        List list = SerializeDB.readSerializedObject("menu.dat");
        return list == null ? new ArrayList<>() : (ArrayList) list;
    }

    private static void saveMenuToFile(ArrayList<Menu> menuItems) throws FailWriteException {
        SerializeDB.writeSerializeObject("menu.dat", menuItems);
    }
}
