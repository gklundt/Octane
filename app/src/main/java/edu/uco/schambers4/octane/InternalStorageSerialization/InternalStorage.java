package edu.uco.schambers4.octane.InternalStorageSerialization;

import android.content.Context;

import java.io.*;

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
