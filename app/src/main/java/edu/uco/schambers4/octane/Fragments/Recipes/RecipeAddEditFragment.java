package edu.uco.schambers4.octane.Fragments.Recipes;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

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
        setUpListView();
    }

    private void loadExistingRecipie()
    {
        nameEt.setText(existingRecipe.getName());
        setUpListView();
    }

    private void setUpListView()
    {
        recipeIngredientAdapter = new RecipeIngredientListAdapter(getActivity(), existingRecipe.getIngredientQuantityMap());
        recipeIngredientsListView.setAdapter(recipeIngredientAdapter);

        recipeIngredientsListView.setOnItemLongClickListener((parent, view, position, id) -> {
            IIngredient ingredient = (IIngredient) parent.getItemAtPosition(position);
            launchDeleteIngredientDialog(ingredient);
            return true;
        });

        recipeIngredientsListView.setOnItemClickListener((parent, view, position, id) -> {
            IIngredient ingredient = (IIngredient) parent.getItemAtPosition(position);
            launchIngredientAmountDialog(ingredient);
        });
    }

    private void launchDeleteIngredientDialog(IIngredient ingredient)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(String.format("Delete %s?", ingredient.getName()))
                .setPositiveButton(
                        "Delete",
                        (dialog, which) -> {
                            existingRecipe.removeIngredient(ingredient);
                            recipeIngredientAdapter.refreshIngredientChanges(existingRecipe);
                        }
                )
                .setNegativeButton(
                        "Cancel",
                        (dialog, which) -> {
                            dialog.dismiss();
                        }
                ).show();
    }

    private void launchIngredientAmountDialog(IIngredient ingredient)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View layout = inflater.inflate(R.layout.ingredient_quantity_dialog, null);
        ViewHolder holder = new ViewHolder(layout);
        holder.quantityLblDialog.setText(ingredient.getUnitOfMeasure());
        builder.setView(layout);

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);



        builder.setTitle(String.format("Quantity for %s", ingredient.getName()))
                .setPositiveButton(
                        "Save",
                        (dialog, which) -> {
                            imm.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0);
                            existingRecipe.getIngredientQuantityMap().put(ingredient, parseQuantity(holder.quantityEtDialog.getText().toString()));
                            recipeIngredientAdapter.refreshIngredientChanges(existingRecipe);
                        }
                )
                .setNegativeButton(
                        "Cancel",
                        (dialog, which) -> {
                            dialog.dismiss();
                            imm.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0);
                        }
                );
        AlertDialog dialog = builder.create();
        holder.quantityEtDialog.setOnEditorActionListener((v, actionId, event) -> {
            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE))
            {
                dialog.getButton(DialogInterface.BUTTON_POSITIVE).performClick();
                return true;
            }
            return false;
        });
        dialog.show();
    }

    private Double parseQuantity(String s)
    {
        try
        {
            double d =Double.parseDouble(s);
            return d;
        }catch (Exception e)
        {
            return 1.0;
        }
    }

    private void saveRecipeAndReturn()
    {
        if (nameValid())
        {
            existingRecipe.setName(nameEt.getText().toString());
            if (newRecipe)
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
        launchIngredientAmountDialog(ingredient);
        existingRecipe.addIngredient(ingredient, 1);
        recipeIngredientAdapter.refreshIngredientChanges(existingRecipe);
    }

    private ArrayAdapter<IIngredient> createEligibleIngredientsAdapter()
    {
        List<IIngredient> recipeList = recipeRespository.getCollectionAsStream()
                .filter(item -> !item.getName().equals(existingRecipe.getName())).collect(Collectors.toList());
        List<IIngredient> ingredientsList = ingredientRepository.getCollectionAsStream()
                .filter(item -> !existingRecipe.getIngredientNames().contains(item.getName()))
                .collect(Collectors.toList());
        List<IIngredient> aggregateList = new ArrayList<>();
        aggregateList.addAll(ingredientsList);
        aggregateList.addAll(recipeList);
        return new ArrayAdapter<IIngredient>(getActivity(), android.R.layout.simple_list_item_1, aggregateList);
    }


    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'ingredient_quantity_dialog.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder
    {
        @Bind(R.id.quantity_et_dialog)
        EditText quantityEtDialog;
        @Bind(R.id.quantity_lbl_dialog)
        TextView quantityLblDialog;

        ViewHolder(View view)
        {
            ButterKnife.bind(this, view);
        }
    }
}
