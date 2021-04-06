package menu;

import base.BaseModel;
import exception.EmptyFieldException;
import exception.NegativeNumberException;
import utility.InputValidator;

public class Menu extends BaseModel {
    private String name;
    private String description;
    private double price;
    private MenuType type;

    public Menu(int id, String name, String description, double price, MenuType type) {
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws EmptyFieldException {
        if (InputValidator.isEmptyString(name)) {
            throw new EmptyFieldException("Menu name cannot be empty.");
        }

        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws NegativeNumberException {
        if (InputValidator.isNegativeNumber(price)) {
            throw new NegativeNumberException("Menu price cannot be negative");
        }

        this.price = price;
    }

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }
}
