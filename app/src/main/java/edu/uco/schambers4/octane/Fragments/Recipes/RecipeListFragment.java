package edu.uco.schambers4.octane.Fragments.Recipes;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.uco.schambers4.octane.DataAccessObjects.Recipes.MockRecipeRepository;
import edu.uco.schambers4.octane.DataAccessObjects.Recipes.RecipeRespository;
import edu.uco.schambers4.octane.Models.MealPlanner.IIngredient;
import edu.uco.schambers4.octane.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeListFragment extends Fragment
{


    @Bind(R.id.recipe_list_view)
    ListView recipeListView;
    @Bind(R.id.recipe_add_fab)
    FloatingActionButton recipeAddFab;

    private RecipeRespository recipeRespository;

    public RecipeListFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        recipeRespository = new MockRecipeRepository();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        ButterKnife.bind(this, view);

        bindRecipesToListView();

        return view;
    }

    private void bindRecipesToListView()
    {
        ArrayAdapter<IIngredient> adapter = new ArrayAdapter<IIngredient>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                recipeRespository.getCollectionAsList()
                );
        recipeListView.setAdapter(adapter);
    }


    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
