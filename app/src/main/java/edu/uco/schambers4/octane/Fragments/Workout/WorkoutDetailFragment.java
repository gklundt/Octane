package edu.uco.schambers4.octane.Fragments.Workout;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.uco.schambers4.octane.Models.Workout.Exercise;
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

    @Bind(R.id.workout_add_exercise_ib)
    ImageButton mWorkoutAddExerciseIb;

    @Bind(R.id.update_workout_fab)
    FloatingActionButton mUpdateWorkout;
    @Bind(R.id.delete_workout_fab)
    FloatingActionButton mDeleteWorkout;


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
        int i = getShownIndex();
        ArrayList<Workout> workouts = mWorkoutContainer.getWorkouts(getActivity().getApplicationContext());
        mWorkout = workouts.get(i);
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

        mUpdateWorkout.setOnClickListener(v -> doUpdate());
        mDeleteWorkout.setOnClickListener(v -> doDelete());
        mWorkoutAddExerciseIb.setOnClickListener(v -> loadAddExerciseDialog());
        mExerciseList.setOnItemClickListener((parent, view1, position, id) -> loadEditExerciseDialog(parent, position));
        mExerciseList.setOnItemLongClickListener((parent, view1, position, id) -> deleteExerciseFromWorkout(parent, position));

        return view;
    }

    private boolean deleteExerciseFromWorkout(AdapterView<?> parent, int position) {
        Exercise exercise = (Exercise) parent.getAdapter().getItem(position);
        mWorkoutContainer.removeExercise(getActivity().getApplicationContext(), mWorkout, exercise);
        WorkoutExerciseAdapter workoutExerciseAdapter = (WorkoutExerciseAdapter) mExerciseList.getAdapter();
        workoutExerciseAdapter.remove(exercise);
        workoutExerciseAdapter.notifyDataSetChanged();
        return false;
    }

    private void loadEditExerciseDialog(AdapterView<?> parent, int position) {
        Exercise exercise = (Exercise) parent.getAdapter().getItem(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Number of Sets")
                .setView(android.R.layout.select_dialog_item)
                .setItems(R.array.set_counts, (dialog, which) -> {
                    updateWorkoutExercise(exercise, which);
                }).show();
    }

    private void updateWorkoutExercise(Exercise exercise, int which) {
        mWorkoutContainer.updateExercise(getActivity().getApplicationContext(), mWorkout, exercise, which + 1);
        WorkoutExerciseAdapter workoutExerciseAdapter = (WorkoutExerciseAdapter) mExerciseList.getAdapter();
        workoutExerciseAdapter.notifyDataSetChanged();

    }

    private void loadAddExerciseDialog() {
        ExerciseAdapter exerciseAdapter = mExerciseContainer.getExerciseAdapter(getActivity());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose an exercise")
                .setAdapter(exerciseAdapter
                        , (dialog, which) -> {
                    addExerciseToWorkout(exerciseAdapter.getItem(which));
                }).show();

    }

    private void addExerciseToWorkout(Exercise exercise) {
        mWorkoutContainer.addExercise(getActivity().getApplicationContext(), mWorkout, exercise.getName(), 1);
        WorkoutExerciseAdapter workoutExerciseAdapter = (WorkoutExerciseAdapter) mExerciseList.getAdapter();
        if (workoutExerciseAdapter.getPosition(exercise) == -1) {
            workoutExerciseAdapter.add(exercise);
            workoutExerciseAdapter.notifyDataSetChanged();
        }
    }

    private void doUpdate() {

        Context context = getActivity().getApplicationContext();

        Workout reference = new Workout(mWorkout.getName(), mWorkout.getExerciseSets(), mWorkout.getIntensityLevel(), mWorkout.getCalories());

        String fWorkoutName = mWorkoutNameEt.getText().toString();
        if (fWorkoutName == null || fWorkoutName.trim().length() == 0) {
            Toast.makeText(context, "Workout Name", Toast.LENGTH_SHORT).show();
            return;
        }
        mWorkout.setName(fWorkoutName);

        String scals = mWorkoutCaloriesEt.getText().toString();
        Integer cals = 0;
        try {
            cals = Integer.parseInt(scals);
        } catch (NumberFormatException e) {
            Toast.makeText(context, "Calories", Toast.LENGTH_SHORT).show();
            return;
        }
        mWorkout.setCalories(cals);


        Workout.IntensityLevel fIntensityLevel = null;
        String intensityLevelItem = mWorkoutIntensitySp.getSelectedItem().toString();
        for (Workout.IntensityLevel i : Workout.IntensityLevel.values()) {
            if (i.getLevel().equals(intensityLevelItem)) {
                fIntensityLevel = i;
            }
        }
        if (fIntensityLevel == null) {
            Toast.makeText(context, "Intensity Level", Toast.LENGTH_SHORT).show();
            return;
        }
        mWorkout.setIntensityLevel(fIntensityLevel);

        mWorkoutContainer.delete(getActivity().getApplicationContext(), reference);
        mWorkoutContainer.save(getActivity().getApplicationContext(), mWorkout);
        getActivity().onBackPressed();
    }

    private void doDelete() {
        mWorkoutContainer.delete(getActivity().getApplicationContext(), mWorkout);
        getActivity().onBackPressed();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

    }


    private void fillForm(Context context) {

        mWorkoutNameEt.setText(mWorkout.getName());
        if (mWorkout.getCalories() != null) {
            mWorkoutCaloriesEt.setText(mWorkout.getCalories().toString());

        }

        mWorkoutIntensitySp.setAdapter(mWorkoutContainer.getWorkoutIntensityArrayAdapter(context));
        mWorkoutIntensitySp.setSelection(
                mWorkoutContainer
                        .getWorkoutIntensityArrayAdapter(context)
                        .getPosition(mWorkout.getIntensityLevel().getLevel())
        );

        //WorkoutExerciseAdapter workoutExerciseAdapter = new WorkoutExerciseAdapter(context, mWorkoutContainer.getExercises(context, getShownIndex()), mWorkout);
        WorkoutExerciseAdapter workoutExerciseAdapter = mWorkoutContainer.getWorkoutExerciseAdapter(context, mWorkout);
        mExerciseList.setAdapter(workoutExerciseAdapter);

    }
}