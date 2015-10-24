package edu.uco.schambers4.octane.DataAccessObjects;

import java.util.List;

import edu.uco.schambers4.octane.Models.IIngredient;

/**
 * Created by Steven Chambers on 10/24/2015.
 */
public interface IIngredientDatabase
{
    void addIngredientToCollection(IIngredient ingredient);
    void removeIngredientFromCollection(IIngredient ingredient);
    List<IIngredient> getCollectionAsList();
    void saveChanges();
}
