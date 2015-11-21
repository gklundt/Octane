package edu.uco.schambers4.octane.Fragments.ShoppingList;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.uco.schambers4.octane.Models.MealPlanner.IIngredient;
import edu.uco.schambers4.octane.Models.MealPlanner.MealPlanByWeek;
import edu.uco.schambers4.octane.Models.MealPlanner.Recipe;
import edu.uco.schambers4.octane.Models.MealPlanner.ShoppingListAdapter;
import edu.uco.schambers4.octane.Models.Schedule.Schedule;
import edu.uco.schambers4.octane.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShoppingListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoppingListFragment extends Fragment
{
    @Bind(R.id.shopping_list_view)
    ListView shoppingListView;

    private static final String ARG_MEALPLAN_WEEK = "arg_mealplan_week";
    @Bind(R.id.refresh_list_fab)
    FloatingActionButton refreshListFab;
    private MealPlanByWeek weekMealPlan;
    private ShoppingListAdapter ingredientAdapter;


    public static ShoppingListFragment newInstance(MealPlanByWeek param1)
    {
        ShoppingListFragment fragment = new ShoppingListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_MEALPLAN_WEEK, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public ShoppingListFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            weekMealPlan = (MealPlanByWeek) getArguments().getSerializable(ARG_MEALPLAN_WEEK);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping_list, container, false);
        ButterKnife.bind(this, view);

        setUpListView();
        refreshListFab.setOnClickListener(v -> {
           ingredientAdapter.removeCheckedItems();
        });

        return view;
    }

    private void setUpListView()
    {
        Map<IIngredient, Double> ingredientQuantityMap = new HashMap<>();
        for (Schedule<Recipe> schedule : weekMealPlan.getPlan())
        {
            Recipe r = schedule.getItem();
            Map<IIngredient, Double> recipeIngredientAndQuantityMap = r.getAllIngredientsAndQuantity(null);
            for (Map.Entry<IIngredient, Double> entry : recipeIngredientAndQuantityMap.entrySet())
            {
                List<IIngredient> keyList = new ArrayList<>(ingredientQuantityMap.keySet());
                //this list should only contain 1 element or be empty because this shadow of a library doesn't really
                //allow for returning a single element in the way that the real Java Streams API does (or I'm completely wrong and blind)
                //Either way, this is the approach I came out with.
                keyList = Stream.of(keyList).filter(k -> k.getName().equals(entry.getKey().getName())).collect(Collectors.toList());

                //we haven't seen this ingredient yet when constructing this list, so we're going to add it and its quantity.
                if (keyList.isEmpty())
                {
                    ingredientQuantityMap.put(entry.getKey(), entry.getValue());
                }
                //we have seen it, so we are just going to add the quantity to the existing ingredient
                else
                {
                    double newQuantity = ingredientQuantityMap.get(keyList.get(0)) + entry.getValue();
                    ingredientQuantityMap.put(keyList.get(0), newQuantity);
                }
            }
        }
        ingredientAdapter = new ShoppingListAdapter(getActivity(), ingredientQuantityMap);
        shoppingListView.setAdapter(ingredientAdapter);

    }


    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
