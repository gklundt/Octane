package edu.uco.schambers4.octane.Fragments.ShoppingList;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.uco.schambers4.octane.Models.MealPlanner.IIngredient;
import edu.uco.schambers4.octane.Models.MealPlanner.MealPlanByWeek;
import edu.uco.schambers4.octane.Models.MealPlanner.Recipe;
import edu.uco.schambers4.octane.Models.MealPlanner.RecipeIngredientListAdapter;
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
    private MealPlanByWeek weekMealPlan;
    private RecipeIngredientListAdapter ingredientAdapter;


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

        return view;
    }

    private void setUpListView()
    {
        HashMap<IIngredient, Double> ingredientQuantityMap = new HashMap<>();
        for(Schedule<Recipe> schedule : weekMealPlan.getPlan())
        {
            Recipe r = schedule.getItem();
            ingredientQuantityMap.putAll(r.getAllIngredientsAndQuantity(null));
        }
        ingredientAdapter = new RecipeIngredientListAdapter(getActivity(),ingredientQuantityMap);
        shoppingListView.setAdapter(ingredientAdapter);

    }


    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
