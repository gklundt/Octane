package edu.uco.schambers4.octane.Fragments.Workouts;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.uco.schambers4.octane.DataAccessObjects.Exercise.ExerciseRepository;
import edu.uco.schambers4.octane.Models.Workout.ExerciseAdapter;
import edu.uco.schambers4.octane.Models.Workout.ExerciseContainer;
import edu.uco.schambers4.octane.Models.Workout.Workout;
import edu.uco.schambers4.octane.Models.Workout.WorkoutContainer;
import edu.uco.schambers4.octane.Models.Workout.WorkoutExerciseAdapter;
import edu.uco.schambers4.octane.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutDetailFragment extends Fragment {

    ExerciseContainer mExerciseContainer = ExerciseContainer.getInstance();
    WorkoutContainer mWorkoutContainer = WorkoutContainer.getInstance(mExerciseContainer.getRepository());
    Workout mWorkout;

    @Bind(R.id.workout_name_et)
    EditText mWorkoutNameEt;
    @Bind(R.id.workout_calories_et)
    EditText mWorkoutCaloriesEt;
    @Bind(R.id.workout_intensity_sp)
    Spinner mWorkoutIntensitySp;
    @Bind(R.id.workout_exercise_list_lv)
    ListView mExerciseList;


    public static WorkoutDetailFragment newInstance(int index) {
        WorkoutDetailFragment f = new WorkoutDetailFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    public WorkoutDetailFragment() {
        // Required empty public constructor
    }

    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWorkout = mWorkoutContainer.getWorkouts(getActivity().getApplicationContext()).get(getShownIndex());

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workout_detail, container, false);
        ButterKnife.bind(this, view);

        if (getShownIndex() > -1) {
            fillForm(container.getContext());
        }


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

    }


    private void fillForm(Context context) {

        mWorkoutNameEt.setText(mWorkout.getName());

        mWorkoutCaloriesEt.setText(mWorkout.getCalories().toString());

        mWorkoutIntensitySp.setAdapter(mWorkoutContainer.getWorkoutIntensityArrayAdapter(context));
        mWorkoutIntensitySp.setSelection(
                mWorkoutContainer
                        .getWorkoutIntensityArrayAdapter(context)
                        .getPosition(mWorkout.getIntensityLevel().getLevel())
        );


        WorkoutExerciseAdapter a = new WorkoutExerciseAdapter(context, mWorkoutContainer.getExercises(context, getShownIndex()), mWorkout);

        mExerciseList.setAdapter(a);



    }
}