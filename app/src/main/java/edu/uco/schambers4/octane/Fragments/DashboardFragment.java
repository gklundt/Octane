package edu.uco.schambers4.octane.Fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

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
import edu.uco.schambers4.octane.DataAccessObjects.Workout.MockWorkoutRepository;
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

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    private void initializeCalender(){
        calendar.setSelectionMode(CalendarSelectionMode.Single);

        calendar.setOnSelectedDatesChangedListener((context) -> {
            Date date = new Date(context.newSelection().get(0));
            launchDetailsForDateFragment(date);
        });

        MockWorkoutRepository mockWorkoutRepo = new MockWorkoutRepository();
        List<Workout> workouts = mockWorkoutRepo.getAllWorkouts(getActivity().getApplicationContext());


        List<Event> events = new ArrayList<>();
        Calendar dateProcessor = Calendar.getInstance();

        for(Workout workout : workouts) {
            dateProcessor.set(Calendar.HOUR_OF_DAY, 20);
            dateProcessor.set(Calendar.MINUTE, 0);
            dateProcessor.set(Calendar.SECOND, 0);
            dateProcessor.set(Calendar.MILLISECOND, 0);

            long eventStart = dateProcessor.getTimeInMillis();

            dateProcessor.add(Calendar.HOUR, 3);
            long eventEnd = dateProcessor.getTimeInMillis();

            Event event = new Event(workout.getName(), eventStart, eventEnd);

            events.add(event);

            dateProcessor.add(Calendar.DAY_OF_YEAR, 1);
        }

        calendar.getEventAdapter().setEvents(events);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this, view);

        initializeCalender();

        return view;
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
