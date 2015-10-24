package edu.uco.schambers4.octane.Models;

import java.io.Serializable;

/**
 * Created by Steven Chambers on 10/24/2015.
 */
public interface IIngredient extends Serializable
{
    String getName();
    int getCalories();
}
