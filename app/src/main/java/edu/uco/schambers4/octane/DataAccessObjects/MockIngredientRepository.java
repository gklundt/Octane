package edu.uco.schambers4.octane.DataAccessObjects;

import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.List;

import edu.uco.schambers4.octane.Models.IIngredient;
import edu.uco.schambers4.octane.Models.Ingredient;

/**
 * Created by Steven Chambers on 10/25/2015.
 */
public class MockIngredientRepository implements IngredientRepository
{
    List<IIngredient> ingredients;

    public MockIngredientRepository()
    {
        ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Steak", "Ounce", 100));
        ingredients.add(new Ingredient("Cheese", "Cup", 100));
        ingredients.add(new Ingredient("Bacon", "Rasher", 100));
    }

    @Override
    public void addIngredientToCollection(IIngredient ingredient)
    {

    }

    @Override
    public void removeIngredientFromCollection(IIngredient ingredient)
    {

    }

    @Override
    public List<IIngredient> getCollectionAsList()
    {
        return ingredients;
    }

    @Override
    public Stream<IIngredient> getCollectionAsStream()
    {
        return Stream.of(ingredients);
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
