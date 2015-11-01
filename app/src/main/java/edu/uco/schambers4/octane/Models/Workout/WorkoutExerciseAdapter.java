package edu.uco.schambers4.octane.Models.Workout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.uco.schambers4.octane.R;

public class WorkoutExerciseAdapter extends ArrayAdapter<Exercise> {

    Workout mWorkout;
    @Bind(R.id.workout_exercise_name_tv)
    TextView mWorkoutExerciseNameTv;
    @Bind(R.id.workout_exercise_resistance_tv)
    TextView mWorkoutExerciseResistanceTv;
    @Bind(R.id.workout_exercise_measure_tv)
    TextView mWorkoutExerciseMeasureTv;
    @Bind(R.id.workout_exercise_sets_sp)
    Spinner mWorkoutExerciseSp;

    public WorkoutExerciseAdapter(Context context, ArrayList<Exercise> exercises, Workout workout) {
        super(context, 0, exercises);
        mWorkout = workout;
        //Collections.sort(exercises, new ExerciseComparator());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Exercise exercise = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_workout_exercise, parent, false);
        }

        ButterKnife.bind(this, convertView);

        mWorkoutExerciseNameTv.setText(exercise.getName());

        Double wef = getIntensity(exercise).getForce().doubleValue() *
                exercise.getMaxIntensityExerciseMeasure().getForce().doubleValue();
        String wefString = String.format("%d %s", ((Double) Math.ceil(wef)).intValue(), exercise.getMaxIntensityExerciseMeasure().getForceUnits().getUnitName());
        mWorkoutExerciseResistanceTv.setText(wefString);

        Double wem = getIntensity(exercise).getMeasure().doubleValue() *
                exercise.getMaxIntensityExerciseMeasure().getMeasure().doubleValue();
        String wemString = String.format("%d %s", ((Double) Math.ceil(wem)).intValue(), exercise.getMaxIntensityExerciseMeasure().getMeasureUnits().getUnitName());
        mWorkoutExerciseMeasureTv.setText(wemString);

        mWorkoutExerciseSp.setSelection(mWorkout.getExerciseSets().get(exercise.getName()) - 1);

        return convertView;
    }


    private Intensity getIntensity(Exercise exercise) {

        switch (mWorkout.getIntensityLevel()) {
            case HIGH:
                return exercise.getHighIntensity();
            case MEDIUM:
                return exercise.getMedIntensity();
            case LOW:
                return exercise.getLowIntensity();
            default:
                return new Intensity(100, 100);
        }

    }


}
