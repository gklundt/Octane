package edu.uco.schambers4.octane.InternalStorageSerialization;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * A class to house static helper methods for writing and retrieving objects directly to or from memory
 *
 * In order to utilize this class, the objects you wish to store/retrieve must implement the Serializable
 * interface
 *
 * @author Steven Chambers
 */
public final class InternalStorage
{
    public static final String STORAGE_KEY_INGREDIENTS = "edu.uco.schambers4.octane.InternalStorageSerialization.InternalStorage.Ingredients";
    public static final String STORAGE_KEY_RECIPES = "edu.uco.schambers4.octane.InternalStorageSerialization.InternalStorage.Recipes";
    public static final String STORAGE_KEY_EXERCISES = "edu.uco.schambers4.octane.InternalStorageSerialization.InternalStorage.Exercises";
    public static final String STORAGE_KEY_WORKOUTS = "edu.uco.schambers4.octane.InternalStorageSerialization.InternalStorage.Workouts";
    public static final String STORAGE_KEY_FITNESSTESTS = "edu.uco.schambers4.octane.InternalStorageSerialization.InternalStorage.FitnessTests";
    public static final String STORAGE_KEY_SCHEDULES = "edu.uco.schambers4.octane.InternalStorageSerialization.InternalStorage.Schedules.%s";

    public static final String STORAGE_SUBKEY_RECIPES = "Recipes";
    public static final String STORAGE_SUBKEY_WORKOUTS = "Workouts";

    private InternalStorage()
    {
    }

    public static void writeObject(Context context, String key, Object object) throws IOException
    {
        FileOutputStream fos = context.openFileOutput(key, Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        oos.close();
        fos.close();
    }

    public static Object readObject(Context context, String key) throws IOException, ClassNotFoundException
    {
        FileInputStream fis = context.openFileInput(key);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object object = ois.readObject();
        return object;
    }
}
