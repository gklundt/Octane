package edu.uco.schambers4.octane.Fragments;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.uco.schambers4.octane.Activities.MainActivity;
import edu.uco.schambers4.octane.Models.IIngredient;
import edu.uco.schambers4.octane.R;


public class DashboardFragment extends Fragment {

    @Bind(R.id.fragment_container_dashboard)
    FrameLayout fragmentContainer;
    @Bind(R.id.calendarView)
    CalendarView calendar;
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
//        calendar.setSelectedWeekBackgroundColor(getResources().getColor(R.color.colorAccent));
        calendar.setUnfocusedMonthDateColor(getResources().getColor(R.color.colorPrimary));
        calendar.setWeekSeparatorLineColor(getResources().getColor(R.color.colorPrimary));
        calendar.setSelectedDateVerticalBar(R.color.colorPrimary);

        calendar.setOnDateChangeListener((view, year, month, day) -> {
            Calendar cal = Calendar.getInstance();
            cal.set(year, month, day);
            Date date = cal.getTime();

            launchDetailsForDateFragment(date);
        });
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
