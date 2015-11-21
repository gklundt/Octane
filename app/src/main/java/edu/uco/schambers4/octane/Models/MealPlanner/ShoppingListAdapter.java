package edu.uco.schambers4.octane.Models.MealPlanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.uco.schambers4.octane.R;

/**
 * Created by Steven Chambers on 11/21/2015.
 */
public class ShoppingListAdapter extends ArrayAdapter<IIngredient>
{
    private Map<IIngredient, Boolean> checkedMap;

    Map<IIngredient, Double> ingredientQuantityMap;

    public ShoppingListAdapter(Context context, Map<IIngredient, Double> ingredientQuantityMap)
    {
        super(context, 0, new ArrayList<>(ingredientQuantityMap.keySet()));
        this.ingredientQuantityMap = ingredientQuantityMap;
        checkedMap = new HashMap<>();
        for(IIngredient ingredient : ingredientQuantityMap.keySet())
        {
            checkedMap.put(ingredient, false);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        IIngredient ingredient = getItem(position);
        double quantity = ingredientQuantityMap.get(ingredient);

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.shopping_list_item_layout, parent, false);
        }

        ViewHolder holder = new ViewHolder(convertView);

        holder.shoppingListItemNameTv.setText(ingredient.getName());
        holder.shoppingIngredientQuantityTv.setText(String.valueOf(quantity));
        if (ingredient instanceof Ingredient)
        {
            holder.shoppingListUnitOfMeasureTv.setText(((Ingredient) ingredient).getUnitOfMeasure());
        } else
        {
            holder.shoppingListUnitOfMeasureTv.setText("preparation");
        }
        holder.shoppingListCheckBox.setChecked(checkedMap.get(ingredient));
        holder.shoppingListCheckBox.setOnClickListener(v -> {
            if(((CheckBox) v).isChecked())
            {
                checkedMap.put(ingredient, true);
            }
            else
            {
                checkedMap.put(ingredient, false);
            }
        });

        return convertView;
    }
    public void removeCheckedItems()
    {
        List<IIngredient> itemsToRemoveFromCheckedMap = new ArrayList<>();
        for(Map.Entry<IIngredient,Boolean> entry : checkedMap.entrySet())
        {
            if(checkedMap.get(entry.getKey()))
            {
                ingredientQuantityMap.remove(entry.getKey());
                itemsToRemoveFromCheckedMap.add(entry.getKey());
            }
        }
        for(IIngredient ingredient : itemsToRemoveFromCheckedMap)
        {
            checkedMap.remove(ingredient);
        }
        List<IIngredient> items = new ArrayList<>(ingredientQuantityMap.keySet());
        this.clear();
        this.addAll(items);
        notifyDataSetChanged();
    }
    public void refreshIngredientChanges(Map<IIngredient,Double> map)
    {
        //get items not common to both checkedMaps keyset and the passed in map's keyset
        //so we can remove irrelevant items for checkedmap
        List<IIngredient> list1 = new ArrayList<>(map.keySet());
        List<IIngredient> list2 = new ArrayList<>(checkedMap.keySet());
        list1.removeAll(checkedMap.keySet());
        list2.removeAll(map.keySet());
        list2.addAll(list1);

        for (IIngredient ingredient : list2)
        {
            checkedMap.remove(ingredient);
        }

        this.clear();
        this.ingredientQuantityMap = map;
        this.addAll(ingredientQuantityMap.keySet());
        notifyDataSetChanged();
    }
    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'shopping_list_item_layout.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder
    {
        @Bind(R.id.shopping_list_check_box)
        CheckBox shoppingListCheckBox;
        @Bind(R.id.shopping_list_item_name_tv)
        TextView shoppingListItemNameTv;
        @Bind(R.id.shopping_list_unit_of_measure_tv)
        TextView shoppingListUnitOfMeasureTv;
        @Bind(R.id.shopping_ingredient_quantity_tv)
        TextView shoppingIngredientQuantityTv;

        ViewHolder(View view)
        {
            ButterKnife.bind(this, view);
        }
    }
}
