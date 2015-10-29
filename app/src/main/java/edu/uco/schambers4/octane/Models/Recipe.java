package edu.uco.schambers4.octane.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Steven Chambers on 10/29/2015.
 */
public class Recipe implements IIngredient
{
    private String name;
    private Map<IIngredient, Double> ingredientAndAmountMap;

    public Recipe(String name)
    {
        this.name = name;
        ingredientAndAmountMap = new HashMap<>();
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public int getCalories()
    {
        int calorieSum = 0;
        for (Map.Entry<IIngredient, Double> cursor : ingredientAndAmountMap.entrySet())
        {
            IIngredient ingredient = cursor.getKey();
            calorieSum += ingredientAndAmountMap.get(ingredient) * ingredient.getCalories();
        }
        return calorieSum;
    }

    public Map<IIngredient, Double> getIngredientQuantityMap()
    {
        return ingredientAndAmountMap;
    }

    public void addIngredient(IIngredient ingredient, double quantity)
    {
        ingredientAndAmountMap.put(ingredient, quantity);
    }
    public void removeIngredient(IIngredient ingredient)
    {
        ingredientAndAmountMap.remove(ingredient);
    }

    @Override
    public String toString()
    {
        return name;
    }
}
