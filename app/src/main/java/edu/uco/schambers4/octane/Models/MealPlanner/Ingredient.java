package edu.uco.schambers4.octane.Models.MealPlanner;

/**
 * Created by Steven Chambers on 10/24/2015.
 */
public class Ingredient implements IIngredient
{
    private String name;
    private int calories;
    private String unitOfMeasure;

    public Ingredient(String name, String unitOfMeasure, int calories)
    {
        this.name = name;
        this.unitOfMeasure = unitOfMeasure;
        this.calories = calories;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public int getCalories()
    {
        return calories;
    }

    public void setCalories(int calories)
    {
        this.calories = calories;
    }

    public String getCaloriesPerUnitOfMeasure()
    {
        return String.format("$.2f per %s", calories, unitOfMeasure);
    }

    public void setName(String name)
    {
        this.name = name;
    }


    public String getUnitOfMeasure()
    {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure)
    {
        this.unitOfMeasure = unitOfMeasure;
    }

    @Override
    public String toString()
    {
        return String.format("%s, %d calories/%s", this.name, this.calories, this.unitOfMeasure);
    }
}
