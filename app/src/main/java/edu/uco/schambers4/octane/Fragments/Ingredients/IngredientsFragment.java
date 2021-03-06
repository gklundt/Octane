package edu.uco.schambers4.octane.Fragments.Ingredients;


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
import edu.uco.schambers4.octane.Activities.MainActivity;
import edu.uco.schambers4.octane.DataAccessObjects.Ingredients.IngredientRepository;
import edu.uco.schambers4.octane.DataAccessObjects.Ingredients.InternalStorageIngredientRepository;
import edu.uco.schambers4.octane.Models.MealPlanner.IIngredient;
import edu.uco.schambers4.octane.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IngredientsFragment extends Fragment
{
    @Bind(R.id.ingredients_listview)
    ListView ingredientsListview;
    @Bind(R.id.ingredients_fab)
    FloatingActionButton ingredientsFab;

    private IngredientRepository ingredientDatabase;

    public IngredientsFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ingredientDatabase = new InternalStorageIngredientRepository(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ingredients, container, false);
        ButterKnife.bind(this, view);

        bindIngredientsToListView();

        ingredientsFab.setOnClickListener(v -> launchAddIngredientFragment());

        return view;
    }

    private void bindIngredientsToListView()
    {
        ArrayAdapter<IIngredient> ingredientArrayAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                ingredientDatabase.getCollectionAsList());
        ingredientsListview.setAdapter(ingredientArrayAdapter);

        ingredientsListview.setOnItemClickListener((parent, view, position, id) -> {
            IIngredient clickedIngredient = (IIngredient) parent.getItemAtPosition(position);
            int positionInDBList = ingredientDatabase.getCollectionAsList().indexOf(clickedIngredient);
            launchEditIngredientFragment(positionInDBList);
        });

    }

    private void launchEditIngredientFragment(int clickedIngredient)
    {
        Fragment editFragment = AddIngredientFragment.newInstance(clickedIngredient);
        launchFragment(editFragment);
    }


    @Override
    public void onResume()
    {
        super.onResume();
        ingredientDatabase.refreshData();
        bindIngredientsToListView();
    }

    void launchAddIngredientFragment()
    {
        Fragment addFragment = new AddIngredientFragment();
        launchFragment(addFragment);
    }

    private void launchFragment(Fragment fragment)
    {
        ((MainActivity) getActivity()).launchFragment(fragment);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
