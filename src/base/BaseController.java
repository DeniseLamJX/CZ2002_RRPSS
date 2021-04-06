package base;

import java.util.ArrayList;

public class BaseController {
    private static final String SKIP = "-";

    public static boolean isSkipUpdate(String userInput) { return userInput.equals(SKIP); }
    
    public static String getSkipKeyword() { return SKIP; }

    public static <T extends BaseModel> T findById(ArrayList<T> items, int id) {
        for (BaseModel item: items) {
            if (item.getId() == id) {
                return (T)item;
            }
        }
        return null;
    }

    public static <T extends BaseModel> int generateId(ArrayList<T> items) {
        if (items.size() == 0) {
            return 1;
        }
        T latestItem = items.get(items.size() - 1);
        return latestItem.getId() + 1;
    }

    // TODO: Add method to load from text file
}
