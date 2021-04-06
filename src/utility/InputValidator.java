package utility;

public class InputValidator {
    private InputValidator() {};
    
    public static boolean isNegativeNumber(double number) {return number < 0;}
    
    public static boolean isEmptyString(String str) {return str.isEmpty();}
}
