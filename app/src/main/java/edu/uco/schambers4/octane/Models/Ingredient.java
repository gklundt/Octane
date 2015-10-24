package edu.uco.schambers4.octane.Models;

/**
 * Created by Steven Chambers on 10/24/2015.
 */
public class Ingredient implements IIngredient
{
    private String name;
    private int calories;
    private double amount;
    private String unitOfMeasure;

    public Ingredient(String name, double amount, String unitOfMeasure)
    {
        this.name = name;
        this.amount = amount;
        this.unitOfMeasure = unitOfMeasure;
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

    public String getQuantity()
    {
        return String.format("$.2f %s", amount, unitOfMeasure);
    }
}
