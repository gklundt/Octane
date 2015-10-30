package edu.uco.schambers4.octane.Fragments.Recipes;


import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.annimon.stream.Collectors;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.uco.schambers4.octane.DataAccessObjects.Ingredients.IngredientRepository;
import edu.uco.schambers4.octane.DataAccessObjects.Ingredients.InternalStorageIngredientRepository;
import edu.uco.schambers4.octane.DataAccessObjects.Recipes.InternalStorageRecipeRepository;
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
    IngredientRepository ingredientRepository;
    Recipe existingRecipe;
    @Bind(R.id.name_et)
    EditText nameEt;
    @Bind(R.id.recipe_ingredients_list_view)
    ListView recipeIngredientsListView;
    @Bind(R.id.add_ingredient_to_recipe_btn)
    ImageButton addIngredientToRecipeBtn;

    RecipeIngredientListAdapter recipeIngredientAdapter;
    @Bind(R.id.add_recipe_fab)
    FloatingActionButton addRecipeFab;

    private boolean newRecipe;

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

        recipeRespository = new InternalStorageRecipeRepository(getActivity());
        ingredientRepository = new InternalStorageIngredientRepository(getActivity());

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
        if (existingRecipe != null)
        {
            loadExistingRecipie();
        } else
        {
            setUpNewRecipe();
        }

        addIngredientToRecipeBtn.setOnClickListener(v -> loadAddIngredientDialog());
        addRecipeFab.setOnClickListener(v ->
        {
            saveRecipeAndReturn();
        });

        return view;
    }

    private void setUpNewRecipe()
    {
        newRecipe = true;
        existingRecipe = new Recipe("");
        recipeIngredientAdapter = new RecipeIngredientListAdapter(getActivity(), existingRecipe.getIngredientQuantityMap());
        recipeIngredientsListView.setAdapter(recipeIngredientAdapter);
    }

    private void saveRecipeAndReturn()
    {
        if(nameValid())
        {
            existingRecipe.setName(nameEt.getText().toString());
            if(newRecipe)
            {
                recipeRespository.addRecipeToCollection(existingRecipe);
            }
            recipeRespository.saveChanges();
            getActivity().onBackPressed();
        }
    }

    private boolean nameValid()
    {
        return !nameEt.getText().equals("");
    }

    private void loadAddIngredientDialog()
    {
        ArrayAdapter<IIngredient> adapter = createEligibleIngredientsAdapter();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose an ingredient")
                .setAdapter(adapter,
                        (dialog, which) -> {
                            addSelectedIngredientToRecipe(adapter.getItem(which));
                        }
                ).show();


    }

    private void addSelectedIngredientToRecipe(IIngredient ingredient)
    {
        existingRecipe.addIngredient(ingredient, 1);
        recipeIngredientAdapter.refreshWithNewIngredient(existingRecipe);
    }

    private ArrayAdapter<IIngredient> createEligibleIngredientsAdapter()
    {
        List<IIngredient> recipeList = recipeRespository.getCollectionAsStream()
                .filter(item -> !item.getName().equals(existingRecipe.getName())).collect(Collectors.toList());
        List<IIngredient> ingredientsList = ingredientRepository.getCollectionAsStream()
                .filter(item -> !existingRecipe.getIngredientQuantityMap().containsKey(item))
                .collect(Collectors.toList());
        List<IIngredient> aggregateList = new ArrayList<>();
        aggregateList.addAll(ingredientsList);
        aggregateList.addAll(recipeList);
        return new ArrayAdapter<IIngredient>(getActivity(), android.R.layout.simple_list_item_1, aggregateList);
    }

    private void loadExistingRecipie()
    {
        nameEt.setText(existingRecipe.getName());
        recipeIngredientAdapter = new RecipeIngredientListAdapter(getActivity(), existingRecipe.getIngredientQuantityMap());
        recipeIngredientsListView.setAdapter(recipeIngredientAdapter);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
