package edu.uco.schambers4.octane.Fragments.Dashboard;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.telerik.widget.calendar.CalendarSelectionMode;
import com.telerik.widget.calendar.RadCalendarView;
import com.telerik.widget.calendar.events.Event;
import com.telerik.widget.calendar.events.EventsDisplayMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.uco.schambers4.octane.Activities.MainActivity;
import edu.uco.schambers4.octane.DataAccessObjects.Recipes.MockRecipeRepository;
import edu.uco.schambers4.octane.DataAccessObjects.Workout.MockWorkoutRepository;
import edu.uco.schambers4.octane.Models.MealPlanner.IIngredient;
import edu.uco.schambers4.octane.Models.Workout.Workout;
import edu.uco.schambers4.octane.R;


public class DashboardFragment extends Fragment {

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
        // Required empty public constructor
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
        //Add Meal Plans and Workouts to Calendar
        //Temporary code - mock repositories

        //Load Workouts
        MockWorkoutRepository mockWorkoutRepo = new MockWorkoutRepository();
        List<Workout> workouts = mockWorkoutRepo.getAllWorkouts(getActivity().getApplicationContext());

        List<Event> events = new ArrayList<>();
        Calendar dateProcessor = Calendar.getInstance();

        for(Workout workout : workouts) {
            dateProcessor.set(Calendar.HOUR_OF_DAY, 15);
            dateProcessor.set(Calendar.MINUTE, 0);
            dateProcessor.set(Calendar.SECOND, 0);
            dateProcessor.set(Calendar.MILLISECOND, 0);

            long eventStart = dateProcessor.getTimeInMillis();

            dateProcessor.add(Calendar.HOUR, 3);
            long eventEnd = dateProcessor.getTimeInMillis();

            Event event = new Event(workout.getName(), eventStart, eventEnd);
            event.setEventColor(colorWorkout);

            events.add(event);

            dateProcessor.add(Calendar.DAY_OF_YEAR, 2);
        }

        //Load Meal Plans
        MockRecipeRepository mockMealPlanRepo = new MockRecipeRepository();
        List<IIngredient> mealPlans =  mockMealPlanRepo.getCollectionAsList();
        dateProcessor = Calendar.getInstance();

        for(IIngredient mealPlan : mealPlans) {
            dateProcessor.set(Calendar.HOUR_OF_DAY, 20);
            dateProcessor.set(Calendar.MINUTE, 0);
            dateProcessor.set(Calendar.SECOND, 0);
            dateProcessor.set(Calendar.MILLISECOND, 0);

            long eventStart = dateProcessor.getTimeInMillis();

            dateProcessor.add(Calendar.HOUR, 3);
            long eventEnd = dateProcessor.getTimeInMillis();

            Event event = new Event(mealPlan.getName(), eventStart, eventEnd);
            event.setEventColor(colorMealPlan);

            events.add(event);

            dateProcessor.add(Calendar.DAY_OF_YEAR, 1);
        }

        calendar.getEventAdapter().setEvents(events);
    }

    private void launchDetailsForDateFragment(Date date)
    {
        Fragment fragment = DetailsForDateFragment.newInstance(date);

        launchSubFragment(fragment);
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
}
