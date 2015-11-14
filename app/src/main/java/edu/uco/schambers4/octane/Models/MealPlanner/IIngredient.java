package edu.uco.schambers4.octane.Models.MealPlanner;

import java.io.Serializable;

import edu.uco.schambers4.octane.Models.GeneralInterfaces.INameable;

/**
 * Created by Steven Chambers on 10/24/2015.
 */
public interface IIngredient extends Serializable, INameable
{
    int getCalories();
    String getUnitOfMeasure();
}
