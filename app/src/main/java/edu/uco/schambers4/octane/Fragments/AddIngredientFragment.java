package edu.uco.schambers4.octane.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.uco.schambers4.octane.DataAccessObjects.IngredientRepository;
import edu.uco.schambers4.octane.DataAccessObjects.InternalStorageIngredientRepository;
import edu.uco.schambers4.octane.Models.IIngredient;
import edu.uco.schambers4.octane.Models.Ingredient;
import edu.uco.schambers4.octane.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddIngredientFragment extends Fragment
{

    private static final String ARG_INGREDIENT_POSITION = "ingredient";

    @Bind(R.id.name_et)
    EditText nameEt;
    @Bind(R.id.quantity_et)
    EditText quantityEt;
    @Bind(R.id.calories_et)
    EditText caloriesEt;
    @Bind(R.id.add_ingredients_fab)
    FloatingActionButton addIngredientsFab;

    IngredientRepository ingredientDatabase;

    double quantity;
    int calories;
    @Bind(R.id.unit_of_measure_spinner)
    Spinner unitOfMeasureSpinner;

    private Ingredient existingIngredient;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment EditIngredientFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddIngredientFragment newInstance(int param1)
    {
        AddIngredientFragment fragment = new AddIngredientFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_INGREDIENT_POSITION, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public AddIngredientFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ingredientDatabase = new InternalStorageIngredientRepository(getActivity());
        if (getArguments() != null)
        {
            int position= getArguments().getInt(ARG_INGREDIENT_POSITION);
            existingIngredient = (Ingredient) ingredientDatabase.getCollectionAsList().get(position);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_ingredient, container, false);
        ButterKnife.bind(this, view);

        addIngredientsFab.setOnClickListener(v -> saveIngredientAndReturn());

        if(existingIngredient != null)
        {
            populateFieldsFromExistingIngredient();
        }

        return view;
    }

    private void populateFieldsFromExistingIngredient()
    {
        nameEt.setText(existingIngredient.getName());
        //set spinner to show the name of the existing ingredient's unit of measure
        unitOfMeasureSpinner.setSelection(((ArrayAdapter)unitOfMeasureSpinner.getAdapter())
                .getPosition(existingIngredient.getUnitOfMeasure()));
        caloriesEt.setText(String.valueOf(existingIngredient.getCalories()));
    }

    private void saveIngredientAndReturn()
    {
        if (fieldsAreValid())
        {
            String ingredientName = nameEt.getText().toString();
            String unitOfMeasure = unitOfMeasureSpinner.getSelectedItem().toString();

            if (existingIngredient == null)
            {
                IIngredient newIngredient = new Ingredient(ingredientName, unitOfMeasure, calories);
                ingredientDatabase.addIngredientToCollection(newIngredient);
            } else
            {
                existingIngredient.setName(ingredientName);
                existingIngredient.setCalories(calories);
                existingIngredient.setUnitOfMeasure(unitOfMeasure);
            }

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
