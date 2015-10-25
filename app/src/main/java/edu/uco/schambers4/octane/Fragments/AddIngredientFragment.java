package edu.uco.schambers4.octane.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.uco.schambers4.octane.DataAccessObjects.IIngredientDatabase;
import edu.uco.schambers4.octane.DataAccessObjects.IngredientDatabase;
import edu.uco.schambers4.octane.Models.IIngredient;
import edu.uco.schambers4.octane.Models.Ingredient;
import edu.uco.schambers4.octane.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddIngredientFragment extends Fragment
{


    @Bind(R.id.name_et)
    EditText nameEt;
    @Bind(R.id.quantity_et)
    EditText quantityEt;
    @Bind(R.id.calories_et)
    EditText caloriesEt;
    @Bind(R.id.add_ingredients_fab)
    FloatingActionButton addIngredientsFab;

    IIngredientDatabase ingredientDatabase;

    double quantity;
    int calories;
    @Bind(R.id.unit_of_measure_spinner)
    Spinner unitOfMeasureSpinner;

    public AddIngredientFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ingredientDatabase = new IngredientDatabase(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_ingredient, container, false);
        ButterKnife.bind(this, view);

        addIngredientsFab.setOnClickListener(v -> saveIngredientAndReturn());

        return view;
    }

    private void saveIngredientAndReturn()
    {
        if (fieldsAreValid())
        {
            String ingredientName = nameEt.getText().toString();
            String unitOfMeasure = unitOfMeasureSpinner.getSelectedItem().toString();
            IIngredient newIngredient = new Ingredient(ingredientName, quantity, unitOfMeasure, calories);
            ingredientDatabase.addIngredientToCollection(newIngredient);
            ingredientDatabase.saveChanges();
            getActivity().onBackPressed();
        }
    }

    private boolean fieldsAreValid()
    {

        return !nameEt.getText().toString().equals("")
                && !quantityEt.getText().toString().equals("")
                && quantityIsValidDouble()
                && !caloriesEt.getText().toString().equals("")
                && caloriesIsValidInteger();
    }


    private boolean quantityIsValidDouble()
    {
        try
        {
            quantity = Double.parseDouble(quantityEt.getText().toString());
            return true;
        } catch (NumberFormatException e)
        {
            Toast.makeText(getActivity(), "Please enter a valid number for quantity", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private boolean caloriesIsValidInteger()
    {
        try
        {
            calories = Integer.parseInt(caloriesEt.getText().toString());
            return true;
        } catch (NumberFormatException e)
        {
            Toast.makeText(getActivity(), "Please enter a valid number for quantity", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
