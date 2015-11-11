package edu.uco.schambers4.octane.Fragments.Dashboard;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.telerik.widget.calendar.CalendarSelectionMode;
import com.telerik.widget.calendar.RadCalendarView;
import com.telerik.widget.calendar.events.Event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.uco.schambers4.octane.Activities.MainActivity;
import edu.uco.schambers4.octane.DataAccessObjects.Recipes.InternalStorageRecipeRepository;
import edu.uco.schambers4.octane.DataAccessObjects.Schedules.InternalStorageScheduleRepository;
import edu.uco.schambers4.octane.InternalStorageSerialization.InternalStorage;
import edu.uco.schambers4.octane.Models.MealPlanner.IIngredient;
import edu.uco.schambers4.octane.Models.MealPlanner.Recipe;
import edu.uco.schambers4.octane.Models.Schedule.Schedule;
import edu.uco.schambers4.octane.Models.Workout.Workout;
import edu.uco.schambers4.octane.Models.Workout.WorkoutContainer;
import edu.uco.schambers4.octane.R;


public class DashboardFragment extends Fragment {

    private static WorkoutContainer WorkoutDatabase;
    private static InternalStorageRecipeRepository RecipeDatabase;
    private static InternalStorageScheduleRepository<Workout> WorkoutScheduleDatabase;
    private static InternalStorageScheduleRepository<Recipe> MealScheduleDatabase;

    @Bind(R.id.fragment_container_dashboard)
    FrameLayout fragmentContainer;
    @Bind(R.id.calendarView)
    RadCalendarView calendar;
    @Bind(R.id.add_meal_fab)
    FloatingActionButton mealFab;
    @Bind(R.id.add_workout_fab)
    FloatingActionButton workoutFab;

    final int colorWorkout = Color.MAGENTA;
    final int colorMealPlan = Color.GREEN;

    public DashboardFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this, view);

        mealFab.setOnClickListener(click -> launchScheduleMealPlanFragment());
        workoutFab.setOnClickListener(click -> launchScheduleWorkoutFragment());

        WorkoutScheduleDatabase = new InternalStorageScheduleRepository<>(getActivity(), InternalStorage.STORAGE_SUBKEY_WORKOUTS);
        MealScheduleDatabase = new InternalStorageScheduleRepository<>(getActivity(), InternalStorage.STORAGE_SUBKEY_RECIPES);
        RecipeDatabase = new InternalStorageRecipeRepository(getActivity());
        WorkoutDatabase = WorkoutContainer.getInstance();

        //TEMP CODE FOR PURPOSES OF DEMO

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        for(Workout workout : WorkoutDatabase.getWorkouts(getActivity())) {
            Schedule<Workout> schedule = new Schedule<>(workout, c.getTime());
            WorkoutScheduleDatabase.saveSchedule(schedule);
        }

        for(IIngredient mealPlan : RecipeDatabase.getCollectionAsList()) {
            Recipe meal = (Recipe)mealPlan;
            Schedule<Recipe> schedule = new Schedule<>(meal, c.getTime());
            MealScheduleDatabase.saveSchedule(schedule);
        }

        initializeCalendar();

        return view;
    }

    private void initializeCalendar()
    {
        calendar.setSelectionMode(CalendarSelectionMode.Single);

        calendar.setOnSelectedDatesChangedListener((context) -> {
            Date date = new Date(context.newSelection().get(0));
            launchDetailsForDateFragment(date);
        });

        loadCalendarEvents();
    }

    private void loadCalendarEvents()
    {
        List<Event> events = new ArrayList<>();

        //Load Workouts

        ArrayList<Schedule<Workout>> workouts = WorkoutScheduleDatabase.getAllSchedules();

        for(Schedule<Workout> workout : workouts) {
            Event event = new Event(workout.getItem().getName(), workout.getDate().getTime(), workout.getDate().getTime());
            event.setEventColor(colorWorkout);

            events.add(event);
        }

        //Load Meal Plans
        ArrayList<Schedule<Recipe>> mealPlans = MealScheduleDatabase.getAllSchedules();

        for(Schedule<Recipe> mealPlan : mealPlans) {
            Event event = new Event(mealPlan.getItem().getName(), mealPlan.getDate().getTime(), mealPlan.getDate().getTime());
            event.setEventColor(colorMealPlan);

            events.add(event);
        }

        calendar.getEventAdapter().setEvents(events);
    }

    private void launchDetailsForDateFragment(Date date)
    {
        Fragment fragment = DetailsForDateFragment.newInstance(date);

        launchSubFragment(fragment);
    }

    private void launchScheduleMealPlanFragment(){
        List<IIngredient> recipes = RecipeDatabase.getCollectionAsList();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View layout = inflater.inflate(R.layout.schedule_dialog, null);
        ViewContainer holder = new ViewContainer(layout);

        ArrayAdapter userAdapter = new ArrayAdapter(getActivity(), R.layout.schedule_dialog, recipes);
        holder.itemSpinner.setAdapter(userAdapter);

        builder.setView(layout);

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

        builder.setTitle("Choose Meal")
                .setPositiveButton(
                        "Save",
                        (dialog, which) -> {
                            imm.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0);
                            Recipe recipe = (Recipe) holder.itemSpinner.getSelectedItem();

                            Calendar c = Calendar.getInstance();
                            c.set(Calendar.HOUR_OF_DAY, 0);
                            c.set(Calendar.MINUTE, 0);
                            c.set(Calendar.SECOND, 0);
                            c.set(Calendar.MILLISECOND, 0);

                            Schedule<Recipe> schedule = new Schedule(recipe, c.getTime());
                            MealScheduleDatabase.saveSchedule(schedule);

                            loadCalendarEvents();
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
//        holder.itemSpinner.setOnEditorActionListener((v, actionId, event) -> {
//            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE))
//            {
//                dialog.getButton(DialogInterface.BUTTON_POSITIVE).performClick();
//                return true;
//            }
//            return false;
//        });
        dialog.show();
    }

    private void launchScheduleWorkoutFragment(){

    }

    public void launchSubFragment(Fragment fragment)
    {
        FragmentTransaction trans = getFragmentManager().beginTransaction();
        trans.replace(R.id.fragment_container_dashboard, fragment).addToBackStack(null).commit();
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


    static class ViewContainer
    {
        @Bind(R.id.itemSpinner)
        Spinner itemSpinner;

        ViewContainer(View view)
        {
            ButterKnife.bind(this, view);
        }
    }
}
