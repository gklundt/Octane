package edu.uco.schambers4.octane.DataAccessObjects;

import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.List;

import edu.uco.schambers4.octane.Models.IIngredient;
import edu.uco.schambers4.octane.Models.Recipe;

/**
 * Created by Steven Chambers on 10/29/2015.
 */
public class MockRecipeRepository implements RecipeRespository
{
    List<IIngredient> recipes;

    public MockRecipeRepository()
    {
        List<IIngredient> ingredients = new MockIngredientRepository().getCollectionAsList();
        Recipe recipe1 = new Recipe("Chicken ala king");
        Recipe recipe2 = new Recipe("Soup");
        Recipe recipe3 = new Recipe("Big Steak Dinner");
        for(IIngredient i : ingredients)
        {
            recipe1.addIngredient(i,4);
            recipe2.addIngredient(i,4);
            recipe3.addIngredient(i,4);
        }
        recipes = new ArrayList<>();
        recipes.add(recipe1);
        recipes.add(recipe2);
        recipes.add(recipe3);
    }

    @Override
    public void addRecipeToCollection(IIngredient i)
    {

    }

    @Override
    public void removeRecipeFromCollection(IIngredient i)
    {

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

    }

    @Override
    public void refreshData()
    {

    }
}
