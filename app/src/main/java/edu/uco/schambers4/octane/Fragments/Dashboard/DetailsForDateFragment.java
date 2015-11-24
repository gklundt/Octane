package edu.uco.schambers4.octane.Fragments.Dashboard;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.uco.schambers4.octane.Models.GeneralInterfaces.INameable;
import edu.uco.schambers4.octane.Models.MealPlanner.Recipe;
import edu.uco.schambers4.octane.Models.Schedule.Schedule;
import edu.uco.schambers4.octane.Models.Schedule.ScheduleAdapter;
import edu.uco.schambers4.octane.Models.Workout.Workout;
import edu.uco.schambers4.octane.R;


public class DetailsForDateFragment extends Fragment {
    private static final String ARG_DATE = "DATE";
    private static final String ARG_WORKOUTS = "WORKOUTS";
    private static final String ARG_MEALPLANS = "MEALPLANS";

    private Date date;
    private ArrayList<Schedule<INameable>> workouts;
    private ArrayList<Schedule<INameable>> mealPlans;

    @Bind(R.id.details_lbl)
    TextView detailsTitle;
    @Bind(R.id.workouts_lbl)
    TextView workoutsTitle;
    @Bind(R.id.workouts_listview)
    ListView workoutsListView;
    @Bind(R.id.meals_lbl)
    TextView mealsTitle;
    @Bind(R.id.meals_listview)
    ListView mealsListView;

    public static DetailsForDateFragment newInstance(Date param1, ArrayList<Schedule<Workout>> workouts, ArrayList<Schedule<Recipe>> mealPlans)
    {
        DetailsForDateFragment fragment = new DetailsForDateFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, param1);
        args.putSerializable(ARG_WORKOUTS, workouts);
        args.putSerializable(ARG_MEALPLANS, mealPlans);
        fragment.setArguments(args);

        return fragment;
    }

    public DetailsForDateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            date =  (Date)getArguments().getSerializable(ARG_DATE);
            workouts =  (ArrayList<Schedule<INameable>>)getArguments().getSerializable(ARG_WORKOUTS);
            mealPlans =  (ArrayList<Schedule<INameable>>)getArguments().getSerializable(ARG_MEALPLANS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details_for_date, container, false);
        ButterKnife.bind(this, view);

        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = formatter.format(date);

        detailsTitle.setText(String.format("%s", formattedDate));

        if(workouts.size() > 0) {
            workoutsTitle.setVisibility(View.VISIBLE);

            ArrayAdapter workoutsAdapter = new ScheduleAdapter(getActivity(), R.layout.detail_listview, workouts);
            workoutsListView.setAdapter(workoutsAdapter);
        }

        if(mealPlans.size() > 0) {
            mealsTitle.setVisibility(View.VISIBLE);

            ArrayAdapter mealsAdapter = new ScheduleAdapter(getActivity(), R.layout.detail_listview, mealPlans);
            mealsListView.setAdapter(mealsAdapter);
        }

        return view;
    }
}
