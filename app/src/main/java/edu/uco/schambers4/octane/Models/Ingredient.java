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

    public Ingredient(String name, double amount, String unitOfMeasure, int calories)
    {
        this.name = name;
        this.amount = amount;
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

    public String getQuantity()
    {
        return String.format("$.2f %s", amount, unitOfMeasure);
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
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
        return String.format("%s, %.2f %s", this.name, this.amount, this.unitOfMeasure);
    }
}
