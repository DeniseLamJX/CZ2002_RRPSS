package promo;

import base.BaseModel;
import exception.EmptyFieldException;
import exception.NegativeNumberException;
import menu.Menu;
import utility.InputValidator;

import java.util.ArrayList;

public class Promo extends BaseModel {
    private String name;
    private String description;
    private double price;
    private final ArrayList<Menu> menuItems = new ArrayList<>();

    public Promo(int id, String name, String description, double price) {
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws EmptyFieldException {
        if (InputValidator.isEmptyString(name)) {
            throw new EmptyFieldException("Promo name cannot be empty.");
        }

        this.name = name;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() { return price; }

    public void setPrice(double price) throws NegativeNumberException {
        if (InputValidator.isNegativeNumber(price)) {
            throw new NegativeNumberException("Promo price cannot be empty.");
        }

        this.price = price;
    }

    public ArrayList<Menu> getMenuItems() {
        return menuItems;
    }

    public void addMenu(Menu menu) {
        menuItems.add(menu);
    }

    public void removeMenu(Menu menu) {
        menuItems.remove(menu);
    }
}
