package edu.uco.schambers4.octane.DataAccessObjects.Recipes;

import android.content.Context;
import android.util.Log;

import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.List;

import edu.uco.schambers4.octane.InternalStorageSerialization.InternalStorage;
import edu.uco.schambers4.octane.Models.MealPlanner.IIngredient;

/**
 * Created by Steven Chambers on 10/29/2015.
 */
public class InternalStorageRecipeRepository implements RecipeRespository
{
    private List<IIngredient> recipes;
    private Context context;


    public InternalStorageRecipeRepository(Context context)
    {
        this.context = context;
        refreshData();
    }

    @Override
    public void addRecipeToCollection(IIngredient recipe)
    {
        recipes.add(recipe);
    }

    @Override
    public void removeRecipeFromCollection(IIngredient recipe)
    {
        recipes.remove(recipe);
    }

    @Override
    public List<IIngredient> getCollectionAsList()
    {
        return recipes;
    }

    @Override
    public Stream<IIngredient> getCollectionAsStream()
    {
        return Stream.of(recipes);
    }

    @Override
    public void saveChanges()
    {
        try
        {
            InternalStorage.writeObject(context, InternalStorage.STORAGE_KEY_RECIPES, recipes);
        }catch (Exception e)
        {
            Log.d("IngredientRepository", String.format("Unable to write ingredient list to disk. \n Exception: \n %s", e.toString()));
        }
    }

    @Override
    public void refreshData()
    {
        try
        {
            recipes = (ArrayList<IIngredient>)InternalStorage.readObject(context, InternalStorage.STORAGE_KEY_RECIPES);
        }catch (Exception e)
        {
            Log.d("IngredientRepository", "Ingredient list not found on disk. Replacing with empty list");
            recipes = new ArrayList<>();
        }
    }
}
