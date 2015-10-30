package edu.uco.schambers4.octane.Models.MealPlanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.uco.schambers4.octane.R;

/**
 * Created by Steven Chambers on 10/29/2015.
 */
public class RecipeIngredientListAdapter extends ArrayAdapter<IIngredient>
{
    Map<IIngredient, Double> ingredientQuantityMap;

    public RecipeIngredientListAdapter(Context context, Map<IIngredient, Double> ingredientQuantityMap)
    {
        super(context, 0, new ArrayList<>(ingredientQuantityMap.keySet()));
        this.ingredientQuantityMap = ingredientQuantityMap;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        IIngredient ingredient = getItem(position);
        double quantity = ingredientQuantityMap.get(ingredient);

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.recipe_ingredient_list_view_layout, parent, false);
        }

        ViewHolder holder = new ViewHolder(convertView);

        holder.recipeIngredientNameTv.setText(ingredient.getName());
        holder.ingredientQuantityTv.setText(String.valueOf(quantity));
        if(ingredient instanceof Ingredient)
        {
            holder.unitOfMeasureTv.setText(((Ingredient) ingredient).getUnitOfMeasure());
        }
        else
        {
            holder.unitOfMeasureTv.setText("preparation");
        }

        return convertView;
    }

    public void refreshWithNewIngredient(Recipe recipe)
    {
        this.clear();
        this.ingredientQuantityMap = recipe.getIngredientQuantityMap();
        this.addAll(ingredientQuantityMap.keySet());
        notifyDataSetChanged();
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'recipe_ingredient_list_view_layout.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder
    {
        @Bind(R.id.recipe_ingredient_name_tv)
        TextView recipeIngredientNameTv;
        @Bind(R.id.unit_of_measure_tv)
        TextView unitOfMeasureTv;
        @Bind(R.id.ingredient_quantity_et)
        TextView ingredientQuantityTv;

        ViewHolder(View view)
        {
            ButterKnife.bind(this, view);
        }
    }
}
