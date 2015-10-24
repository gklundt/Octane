package edu.uco.schambers4.octane.DataAccessObjects;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.uco.schambers4.octane.InternalStorageSerialization.InternalStorage;
import edu.uco.schambers4.octane.Models.IIngredient;

/**
 * Created by Steven Chambers on 10/24/2015.
 */
public class IngredientDatabase implements IIngredientDatabase
{
    private List<IIngredient> ingredients;
    private Context context;


    public IngredientDatabase(Context context)
    {
        this.context = context;

        try
        {
            ingredients = (ArrayList<IIngredient>)InternalStorage.readObject(context, InternalStorage.STORAGE_KEY_INGREDIENTS);
        }catch (Exception e)
        {
            Log.d("IngredientDatabase", "Ingredient list not found on disk. Replacing with empty list");
            ingredients = new ArrayList<>();
        }
    }

    @Override
    public void addIngredientToCollection(IIngredient ingredient)
    {
        ingredients.add(ingredient);
    }

    @Override
    public void removeIngredientFromCollection(IIngredient ingredient)
    {
        ingredients.remove(ingredient);
    }

    @Override
    public List<IIngredient> getCollectionAsList()
    {
        return ingredients;
    }

    @Override
    public void saveChanges()
    {
        try
        {
            InternalStorage.writeObject(context,InternalStorage.STORAGE_KEY_INGREDIENTS, ingredients);
        }catch (Exception e)
        {
            Log.d("IngredientDatabase", String.format("Unable to write ingredient list to disk. \n Exception: \n %s",e.toString()));
        }
    }
}
