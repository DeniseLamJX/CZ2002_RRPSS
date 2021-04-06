package database;

import base.BaseModel;
import database.exceptions.FailReadException;
import database.exceptions.FailWriteException;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SerializeDB {

    /**
     * The path to the directory that stores all .dat files.
     */
    private static final Path ROOT =
        Paths.get(System.getProperty("user.dir"), "src", "database", "db");

    /**
     * Read data saved in a .dat file into a List of Objects.
     *
     * @param filename The name of file to read your data. The file must be a .dat file in database/db.
     * @return The List of objects saved in the file.
     * @throws FailReadException
     */
    public static List readSerializedObject(String filename) throws FailReadException {
        List data = null;
        FileInputStream fin = null;
        ObjectInputStream in = null;

        String filepath = getFilePath(filename);

        try {
            fin = new FileInputStream(filepath);
            if (fin.available() > 0) {
                in = new ObjectInputStream(fin);
                data = (ArrayList) in.readObject();
                fin.close();
            }
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new FailReadException();
        }

        return data;
    }

    /**
     * Write a List of Objects into a .dat file.
     *
     * @param filename The name of file to save your data. The file must be a .dat file in database/db.
     * @param list The List of Objects to be saved into file.
     * @throws FailWriteException
     */
    public static void writeSerializeObject(String filename, List list) throws FailWriteException {
        FileOutputStream fout = null;
        ObjectOutputStream out = null;

        String filepath = getFilePath(filename);

        try {
            fout = new FileOutputStream(filepath);
            out = new ObjectOutputStream(fout);
            out.writeObject(list);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FailWriteException();
        }
    }

    /**
     * Search for a BaseModel object in an ArrayList by id.
     *
     * @param items An ArrayList of BaseModel objects.
     * @param id The id of Object of type BaseModel to be searched for.
     * @param <T> A generic type that extends BaseModel.
     * @return The Object if it is found. Else, null.
     */
    public static <T extends BaseModel> T findById(ArrayList<T> items, int id) {
        for (BaseModel item: items) {
            if (item.getId() == id) {
                return (T)item;
            }
        }
        return null;
    }

    /**
     * Generate a id for a new BaseModel object.
     *
     * @param items An ArrayList of BaseModel objects.
     * @param <T> A generic type that extends BaseModel.
     * @return An id of type int.
     */
    public static <T extends BaseModel> int generateId(ArrayList<T> items) {
        if (items.size() == 0) {
            return 1;
        }
        T latestItem = items.get(items.size() - 1);
        return latestItem.getId() + 1;
    }

    /**
     * Returns the absolute path to a file inside database/db.
     *
     * @param filename The name of file to be opened. It must be a .dat file in database/db.
     * @return The absolute path to the file.
     */
    private static String getFilePath(String filename) {
        return Paths.get(ROOT.toString(), filename).toString();
    }
}
