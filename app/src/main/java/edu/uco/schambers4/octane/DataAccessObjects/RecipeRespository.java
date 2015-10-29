package edu.uco.schambers4.octane.DataAccessObjects;

import com.annimon.stream.Stream;

import java.util.List;

import edu.uco.schambers4.octane.Models.IIngredient;

/**
 * Created by Steven Chambers on 10/29/2015.
 */
public interface RecipeRespository
{
    void addRecipeToCollection(IIngredient i);
    void removeRecipeFromCollection(IIngredient i);
    List<IIngredient> getCollectionAsList();
    Stream<IIngredient> getCollectionAsStream();
    void saveChanges();
    void refreshData();
}
