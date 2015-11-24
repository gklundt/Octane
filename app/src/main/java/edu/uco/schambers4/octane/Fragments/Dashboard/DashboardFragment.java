package edu.uco.schambers4.octane.Fragments.Dashboard;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.net.ParseException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.telerik.widget.calendar.CalendarSelectionMode;
import com.telerik.widget.calendar.RadCalendarView;
import com.telerik.widget.calendar.events.Event;
import com.telerik.widget.calendar.events.EventsDisplayMode;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.uco.schambers4.octane.DataAccessObjects.Recipes.InternalStorageRecipeRepository;
import edu.uco.schambers4.octane.DataAccessObjects.Schedules.InternalStorageScheduleRepository;
import edu.uco.schambers4.octane.DataAccessObjects.Schedules.ScheduleRepository;
import edu.uco.schambers4.octane.InternalStorageSerialization.InternalStorage;
import edu.uco.schambers4.octane.Models.GeneralInterfaces.INameable;
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
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    @Bind(R.id.fragment_container_dashboard)
    FrameLayout fragmentContainer;
    @Bind(R.id.calendarView)
    RadCalendarView calendar;
    @Bind(R.id.add_meal_fab)
    FloatingActionButton mealFab;
    @Bind(R.id.add_workout_fab)
    FloatingActionButton workoutFab;

    ViewContainer scheduleDialog;

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

        initializeCalendar();

        return view;
    }

    private void initializeCalendar()
    {
        calendar.setSelectionMode(CalendarSelectionMode.Single);
        calendar.setEventsDisplayMode(EventsDisplayMode.Popup);

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
            Event event = new Event(workout.getItem().getName(), workout.getDate().getTime(), workout.getDate().getTime() + 300);
            event.setEventColor(colorWorkout);

            events.add(event);
        }

        //Load Meal Plans
        ArrayList<Schedule<Recipe>> mealPlans = MealScheduleDatabase.getAllSchedules();

        for(Schedule<Recipe> mealPlan : mealPlans) {
            Event event = new Event(mealPlan.getItem().getName(), mealPlan.getDate().getTime(), mealPlan.getDate().getTime() + 300);
            event.setEventColor(colorMealPlan);

            events.add(event);
        }

        calendar.getEventAdapter().setEvents(events);
    }

    private void launchDetailsForDateFragment(Date date)
    {
        ArrayList<Schedule<Workout>> workouts = WorkoutScheduleDatabase.getSchedulesForDate(date);
        ArrayList<Schedule<Recipe>> mealPlans = MealScheduleDatabase.getSchedulesForDate(date);

        Fragment fragment = DetailsForDateFragment.newInstance(date, workouts, mealPlans);

        launchSubFragment(fragment);
    }

    private void launchScheduleMealPlanFragment(){
        List<IIngredient> recipes = RecipeDatabase.getCollectionAsList();
        List<INameable> items = new ArrayList<>(recipes);

        launchScheduleFragment(items, MealScheduleDatabase, "Meal");
    }

    private void launchScheduleWorkoutFragment(){
        List<Workout> workouts = WorkoutDatabase.getWorkouts(getActivity());
        List<INameable> items = new ArrayList<>(workouts);

        launchScheduleFragment(items, WorkoutScheduleDatabase, "Workout");
    }

    private <T extends INameable> void launchScheduleFragment(List<INameable> schedulableItems, ScheduleRepository<T> repository, String itemName) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View layout = inflater.inflate(R.layout.schedule_dialog, null);
        scheduleDialog = new ViewContainer(layout);

        ArrayAdapter userAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, schedulableItems);

        //set default date to now
        Date selectedCalendarDate = new Date(System.currentTimeMillis());
        if(!calendar.getSelectedDates().isEmpty())
        {
            selectedCalendarDate = new Date(calendar.getSelectedDates().get(0));
        }

        scheduleDialog.itemSpinner.setAdapter(userAdapter);
        scheduleDialog.itemLabel.setText(itemName);
        scheduleDialog.itemDate.setText(dateFormat.format(selectedCalendarDate));

        builder.setView(layout);

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

        builder.setTitle("Choose " + itemName)
                .setPositiveButton(
                        "Save",
                        (dialog, which) -> {
                            if(scheduleDialog.itemDate.getText().toString() == "" ||
                                    scheduleDialog.itemSpinner.getSelectedItem() == null )
                            {
                                dialog.dismiss();
                            }

                            imm.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0);
                            T itemToSchedule = (T) scheduleDialog.itemSpinner.getSelectedItem();


                            Date date = null;

                            try {
                                date = dateFormat.parse(scheduleDialog.itemDate.getText().toString(), new ParsePosition(0));
                            } catch (ParseException e) {
                                dialog.dismiss();
                            }

                            if(date == null)
                                dialog.dismiss();

                            Calendar cal = Calendar.getInstance();
                            cal.setTime(date);
                            cal.set(Calendar.HOUR_OF_DAY, 0);
                            cal.set(Calendar.MINUTE, 0);
                            cal.set(Calendar.SECOND, 0);
                            cal.set(Calendar.MILLISECOND, 0);
                            date = cal.getTime();

                            Schedule<T> schedule = new Schedule(itemToSchedule, date);
                            repository.saveSchedule(schedule);

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
        dialog.show();
    }

    public void launchSubFragment(Fragment fragment)
    {
        FragmentTransaction trans = getFragmentManager().beginTransaction();
        trans.replace(R.id.fragment_container_dashboard, fragment).addToBackStack(null).commit();
    }

    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.target = scheduleDialog.itemDate;
        newFragment.show(getFragmentManager(), "datePicker");
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    class ViewContainer {
        @Bind(R.id.itemSpinner)
        Spinner itemSpinner;
        @Bind(R.id.txtScheduleDate)
        EditText itemDate;
        @Bind(R.id.txtItemLabel)
        TextView itemLabel;

        ViewContainer(View view)
        {
            ButterKnife.bind(this, view);

            itemDate.setOnClickListener((v) -> showDatePickerDialog(v));
        }
    }
}
