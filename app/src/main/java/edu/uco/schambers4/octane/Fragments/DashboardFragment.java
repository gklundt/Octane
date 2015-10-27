package edu.uco.schambers4.octane.Fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.uco.schambers4.octane.Activities.MainActivity;
import edu.uco.schambers4.octane.DataAccessObjects.IngredientDatabase;
import edu.uco.schambers4.octane.Models.IIngredient;
import edu.uco.schambers4.octane.R;


public class DashboardFragment extends Fragment {

    @Bind(R.id.calendarView)
    CalendarView calendar;
//    @Bind(R.id.ingredients_fab)
//    FloatingActionButton ingredientsFab;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


//        ingredientDatabase = new IngredientDatabase(getActivity());
    }

    private void initializeCalender(){
        //The background color for the selected week.


        calendar.setSelectedWeekBackgroundColor(getResources().getColor(R.color.colorAccent));


        //sets the color for the dates of an unfocused month.

        calendar.setUnfocusedMonthDateColor(getResources().getColor(R.color.colorPrimary));

        //sets the color for the separator line between weeks.

        calendar.setWeekSeparatorLineColor(getResources().getColor(R.color.colorPrimary));

        //sets the color for the vertical bar shown at the beginning and at the end of the selected date.

        calendar.setSelectedDateVerticalBar(R.color.colorPrimary);

        //sets the listener to be notified upon selected date change.

        calendar.setOnDateChangeListener((view, year, month, day) -> {
                Calendar cal = Calendar.getInstance();
                cal.set(year, month, day);
                Date date = cal.getTime();

                displayDetailsForDate(date);
            });
    }

    protected void displayDetailsForDate(Date date){

//            Fragment editFragment = AddIngredientFragment.newInstance(clickedIngredient);
//            launchFragment(editFragment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this, view);

        initializeCalender();

//        bindIngredientsToListView();

//        ingredientsFab.setOnClickListener(v -> launchAddIngredientFragment());

        return view;
    }

    private void bindIngredientsToListView()
    {
//        ArrayAdapter<IIngredient> ingredientArrayAdapter = new ArrayAdapter<>(
//                getActivity(),
//                android.R.layout.simple_list_item_1,
//                ingredientDatabase.getCollectionAsList());
//        ingredientsListview.setAdapter(ingredientArrayAdapter);
//
//        ingredientsListview.setOnItemClickListener((parent, view, position, id) -> {
//            IIngredient clickedIngredient = (IIngredient) parent.getItemAtPosition(position);
//            int positionInDBList = ingredientDatabase.getCollectionAsList().indexOf(clickedIngredient);
//            launchEditIngredientFragment(positionInDBList);
//        });

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
//        ingredientDatabase.refreshData();
//        bindIngredientsToListView();
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
