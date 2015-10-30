package edu.uco.schambers4.octane.Fragments.Recipes;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.uco.schambers4.octane.Activities.MainActivity;
import edu.uco.schambers4.octane.DataAccessObjects.Recipes.InternalStorageRecipeRepository;
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

    private ArrayAdapter<IIngredient> recipeAdapter;

    public RecipeListFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        recipeRespository = new InternalStorageRecipeRepository(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        ButterKnife.bind(this, view);

        bindRecipesToListView();

        recipeAddFab.setOnClickListener(v ->
        {
            launchFragmentToAddRecipe();
        });

        return view;
    }

    private void launchFragmentToAddRecipe()
    {
        Fragment frag = new RecipeAddEditFragment();
        launchFragment(frag);
    }

    private void launchFragment(Fragment fragment)
    {
        ((MainActivity) getActivity()).launchFragment(fragment);
    }

    private void bindRecipesToListView()
    {
        recipeAdapter= new ArrayAdapter<IIngredient>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                recipeRespository.getCollectionAsList()
        );
        recipeListView.setAdapter(recipeAdapter);
        recipeListView.setOnItemClickListener((parent, view, position, id) -> {
            launchFragmentToEditRecipe(position);
        });
        recipeListView.setOnItemLongClickListener((parent, view, position, id) -> {
            IIngredient recipe = recipeAdapter.getItem(position);
            showDeleteRecipePopUp(recipe);
            return true;
        });
    }

    private void showDeleteRecipePopUp(IIngredient recipe)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(String.format("Delete %s?", recipe.getName()))
                .setPositiveButton(
                        "Delete",
                        (dialog, which) -> {
                            recipeRespository.removeRecipeFromCollection(recipe);
                            recipeRespository.saveChanges();
                            refreshView();
                        }
                )
                .setNegativeButton(
                        "Cancel",
                        (dialog, which) -> {
                            dialog.dismiss();
                        }
                ).show();
    }

    private void refreshView()
    {
        recipeAdapter.clear();
        recipeAdapter.addAll(recipeRespository.getCollectionAsList());
        recipeAdapter.notifyDataSetChanged();
    }

    private void launchFragmentToEditRecipe(int position)
    {
        Fragment frag = RecipeAddEditFragment.newInstance(position);
        launchFragment(frag);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        recipeRespository.refreshData();
        refreshView();
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
