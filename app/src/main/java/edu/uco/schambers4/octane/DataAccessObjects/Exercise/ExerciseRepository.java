package edu.uco.schambers4.octane.DataAccessObjects.Exercise;

import android.content.Context;

import java.util.ArrayList;
import edu.uco.schambers4.octane.Models.Workout.Exercise;
import edu.uco.schambers4.octane.Models.Workout.Workout;

public interface ExerciseRepository {

    /* read functions */
    Exercise getExerciseByName(Context context, String exerciseName);
    ArrayList<Exercise> getAllExercises(Context context);
    ArrayList<Exercise> getExerciseByType(Context context, Exercise.ExerciseType exerciseType);
    ArrayList<Exercise> getExerciseByMuscleGroup(Context context, Exercise.MuscleGroup muscleGroup);

    /* insert and update functions */
    void saveExercise(Context context, Exercise exercise);
    void saveExercises(Context context, ArrayList<Exercise> exercises);

    /* delete functions */
    void deleteExercise(Context context, Exercise exercise);
    void deleteExercises(Context context, ArrayList<Exercise> exercises);

}
