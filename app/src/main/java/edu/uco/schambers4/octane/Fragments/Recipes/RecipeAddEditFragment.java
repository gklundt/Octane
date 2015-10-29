package edu.uco.schambers4.octane.Fragments.Recipes;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.uco.schambers4.octane.DataAccessObjects.Recipes.MockRecipeRepository;
import edu.uco.schambers4.octane.DataAccessObjects.Recipes.RecipeRespository;
import edu.uco.schambers4.octane.Models.MealPlanner.IIngredient;
import edu.uco.schambers4.octane.Models.MealPlanner.Recipe;
import edu.uco.schambers4.octane.Models.MealPlanner.RecipeIngredientListAdapter;
import edu.uco.schambers4.octane.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecipeAddEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipeAddEditFragment extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_RECIPE_POSITION = "arg_recipe_position";

    RecipeRespository recipeRespository;
    Recipe existingRecipe;
    @Bind(R.id.name_et)
    EditText nameEt;
    @Bind(R.id.recipe_ingredients_list_view)
    ListView recipeIngredientsListView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    // TODO: Rename and change types and number of parameters
    public static RecipeAddEditFragment newInstance(int param1)
    {
        RecipeAddEditFragment fragment = new RecipeAddEditFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_RECIPE_POSITION, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public RecipeAddEditFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        recipeRespository = new MockRecipeRepository();

        if (getArguments() != null)
        {
            int position = getArguments().getInt(ARG_RECIPE_POSITION);
            existingRecipe = (Recipe) recipeRespository.getCollectionAsList().get(position);
        }

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_add_edit, container, false);
        ButterKnife.bind(this, view);
        if(existingRecipe != null)
        {
            loadExistingRecipie();
        }
        return view;
    }
    private void loadExistingRecipie()
    {
        nameEt.setText(existingRecipe.getName());
        ArrayAdapter<IIngredient> adapter = new RecipeIngredientListAdapter(getActivity(),existingRecipe.getIngredientQuantityMap());
        recipeIngredientsListView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
