package edu.uco.schambers4.octane.Fragments.ShoppingList;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.uco.schambers4.octane.DataAccessObjects.Schedules.InternalStorageScheduleRepository;
import edu.uco.schambers4.octane.DataAccessObjects.Schedules.MockScheduleRepository;
import edu.uco.schambers4.octane.InternalStorageSerialization.InternalStorage;
import edu.uco.schambers4.octane.Models.MealPlanner.MealPlanByWeek;
import edu.uco.schambers4.octane.Models.MealPlanner.Recipe;
import edu.uco.schambers4.octane.Models.Schedule.Schedule;
import edu.uco.schambers4.octane.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GenerateShoppingListFragment extends Fragment
{
    @Bind(R.id.meal_plan_list_view)
    ListView mealPlanListView;

    private MockScheduleRepository<Recipe> mealPlanRepository;
    private List<MealPlanByWeek> mealPlansByWeek;

    public GenerateShoppingListFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_generate_shopping_list, container, false);
        ButterKnife.bind(this, view);

        initializeMealPlanRepo();
        groupMealPlansByWeek();
        setUpListView();

        return view;
    }


    private void initializeMealPlanRepo()
    {
        //mealPlanRepository = new InternalStorageScheduleRepository<>(getActivity(), InternalStorage.STORAGE_SUBKEY_RECIPES);
        mealPlanRepository = new MockScheduleRepository<>(getActivity());
    }

    private void groupMealPlansByWeek()
    {
        ArrayList<Schedule<Recipe>> allMealPlans = mealPlanRepository.getAllSchedules();
        mealPlansByWeek = new ArrayList<>();
        Stream.of(allMealPlans).groupBy(mealPlan -> {

            Calendar cal = Calendar.getInstance();
            cal.setTime(mealPlan.getDate());
            return cal.get(Calendar.WEEK_OF_YEAR);

        }).forEach(entry -> {
            Log.d("Meal", "Putting week" + entry.getKey() + "entry is: " + entry.getValue());
            MealPlanByWeek plan = new MealPlanByWeek(entry.getKey(), entry.getValue());
            mealPlansByWeek.add(plan);
        });
        Collections.sort(mealPlansByWeek, (item1, item2) -> {
            if(item1.getWeekOfYear() > item2.getWeekOfYear())
                return -1;
            else
                return 1;
        });
    }

    private void setUpListView()
    {
        ArrayAdapter<MealPlanByWeek> adapter = new ArrayAdapter<MealPlanByWeek>(getActivity(), android.R.layout.simple_list_item_1, mealPlansByWeek);
        mealPlanListView.setAdapter(adapter);
        mealPlanListView.setOnItemClickListener((parent, view, position, id) -> {
            MealPlanByWeek plan = (MealPlanByWeek) parent.getItemAtPosition(position);
            launchShoppingListFragment(plan);
        });
    }

    private void launchShoppingListFragment(MealPlanByWeek plan)
    {

    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
