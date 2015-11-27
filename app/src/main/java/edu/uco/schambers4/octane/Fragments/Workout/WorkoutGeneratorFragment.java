package edu.uco.schambers4.octane.Fragments.Workout;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.uco.schambers4.octane.Models.Workout.Exercise;
import edu.uco.schambers4.octane.Models.Workout.ExerciseContainer;
import edu.uco.schambers4.octane.Models.Workout.Workout;
import edu.uco.schambers4.octane.Models.Workout.WorkoutContainer;
import edu.uco.schambers4.octane.R;

public class WorkoutGeneratorFragment extends Fragment {

    @Bind(R.id.fragment_workout_generator_workout_name_et)
    EditText mWorkoutNameEt;

    @Bind(R.id.fragment_workout_generator_muscle_group_abs_cb)
    CheckBox mMuscleGroupAbsCb;
    @Bind(R.id.fragment_workout_generator_muscle_group_arms_cb)
    CheckBox mMuscleGroupArmsCb;
    @Bind(R.id.fragment_workout_generator_muscle_group_back_cb)
    CheckBox mMuscleGroupBackCb;
    @Bind(R.id.fragment_workout_generator_muscle_group_chest_cb)
    CheckBox mMuscleGroupChestCb;
    @Bind(R.id.fragment_workout_generator_muscle_group_core_cb)
    CheckBox mMuscleGroupCoreCb;
    @Bind(R.id.fragment_workout_generator_muscle_group_fullbody_cb)
    CheckBox mMuscleGroupFullbodyCb;
    @Bind(R.id.fragment_workout_generator_muscle_group_glute_cb)
    CheckBox mMuscleGroupGluteCb;
    @Bind(R.id.fragment_workout_generator_muscle_group_legs_cb)
    CheckBox mMuscleGroupLegsCb;
    @Bind(R.id.fragment_workout_generator_muscle_group_shoulders_cb)
    CheckBox mMuscleGroupShouldersCb;

    @Bind(R.id.fragment_workout_generator_exercise_type_balance_cb)
    CheckBox mExerciseTypeBalanceCb;
    @Bind(R.id.fragment_workout_generator_exercise_type_endurance_cb)
    CheckBox mExerciseTypeEnduranceCb;
    @Bind(R.id.fragment_workout_generator_exercise_type_flexibility_cb)
    CheckBox mExerciseTypeFlexibilityCb;
    @Bind(R.id.fragment_workout_generator_exercise_type_strength_cb)
    CheckBox mExerciseTypeStrengthCb;

    @Bind(R.id.fragment_workout_generator_intensity_sp)
    Spinner mIntensitySp;

    @Bind(R.id.generate_workout_fab)
    FloatingActionButton mGenerateWorkoutFab;

    public WorkoutGeneratorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workout_generator, container, false);
        ButterKnife.bind(this, view);
        mIntensitySp.setAdapter(WorkoutContainer.getInstance().getWorkoutIntensityArrayAdapter(container.getContext()));


        mGenerateWorkoutFab.setOnClickListener(v -> GenerateWorkout());

        return view;

    }

    void GenerateWorkout() {

        Context context = getActivity().getApplicationContext();

        String workoutName;
        HashMap<String, Integer> exerciseList = new HashMap<>();
        Workout.IntensityLevel intensityLevel = null;
        Integer calories = 100;

        workoutName = mWorkoutNameEt.getText().toString();
        if (workoutName.trim().length() == 0) {
            Toast.makeText(context, "Workout Name", Toast.LENGTH_SHORT).show();
            return;
        }

        String intensityLevelItem = mIntensitySp.getSelectedItem().toString();
        for (Workout.IntensityLevel i : Workout.IntensityLevel.values()) {
            if (i.getLevel().equals(intensityLevelItem)) {
                intensityLevel = i;
            }
        }
        if (intensityLevel == null) {
            Toast.makeText(context, "Intensity Level", Toast.LENGTH_SHORT).show();
            return;
        }

        for (Exercise ex : ExerciseContainer.getInstance().getExercises(context)) {

            if (checkExerciseType(ex, Exercise.ExerciseType.BALANCE, mExerciseTypeBalanceCb.isChecked(), exerciseList))
                continue;
            if (checkExerciseType(ex, Exercise.ExerciseType.ENDURANCE, mExerciseTypeEnduranceCb.isChecked(), exerciseList))
                continue;
            if (checkExerciseType(ex, Exercise.ExerciseType.STRENGTH, mExerciseTypeStrengthCb.isChecked(), exerciseList))
                continue;
            if (checkExerciseType(ex, Exercise.ExerciseType.FLEXIBILITY, mExerciseTypeFlexibilityCb.isChecked(), exerciseList))
                continue;

            if (checkExerciseMuscleGroup(ex, Exercise.MuscleGroup.ABS, mMuscleGroupAbsCb.isChecked(), exerciseList))
                continue;
            if (checkExerciseMuscleGroup(ex, Exercise.MuscleGroup.ARMS, mMuscleGroupArmsCb.isChecked(), exerciseList))
                continue;
            if (checkExerciseMuscleGroup(ex, Exercise.MuscleGroup.BACK, mMuscleGroupBackCb.isChecked(), exerciseList))
                continue;
            if (checkExerciseMuscleGroup(ex, Exercise.MuscleGroup.CHEST, mMuscleGroupChestCb.isChecked(), exerciseList))
                continue;
            if (checkExerciseMuscleGroup(ex, Exercise.MuscleGroup.CORE, mMuscleGroupCoreCb.isChecked(), exerciseList))
                continue;
            if (checkExerciseMuscleGroup(ex, Exercise.MuscleGroup.FULLBODY, mMuscleGroupFullbodyCb.isChecked(), exerciseList))
                continue;
            if (checkExerciseMuscleGroup(ex, Exercise.MuscleGroup.GLUTE, mMuscleGroupGluteCb.isChecked(), exerciseList))
                continue;
            if (checkExerciseMuscleGroup(ex, Exercise.MuscleGroup.LEGS, mMuscleGroupLegsCb.isChecked(), exerciseList))
                continue;
            checkExerciseMuscleGroup(ex, Exercise.MuscleGroup.SHOULDERS, mMuscleGroupShouldersCb.isChecked(), exerciseList);


        }

        Workout workout = new Workout(workoutName, exerciseList, intensityLevel, calories);
        workout.setIntensityLevel(intensityLevel);
        WorkoutContainer.getInstance().save(context, workout);
        getActivity().onBackPressed();

    }

    boolean checkExerciseType(Exercise exercise, Exercise.ExerciseType exerciseType, boolean cb, HashMap<String, Integer> exerciseList) {
        if (exercise.getExerciseType() == exerciseType && cb) {
            exerciseList.put(exercise.getName(), 1);
        }
        return false;
    }

    boolean checkExerciseMuscleGroup(Exercise exercise, Exercise.MuscleGroup muscleGroup, boolean cb, HashMap<String, Integer> exerciseList) {
        if (exercise.getMuscleGroup() == muscleGroup && cb) {
            exerciseList.put(exercise.getName(), 1);
        }
        return false;
    }


}
