package edu.uco.schambers4.octane.DataAccessObjects.Workout;

import android.content.Context;

import java.util.ArrayList;

import edu.uco.schambers4.octane.Models.Workout.Workout;

/**
 * Created by gordon on 10/25/15.
 */
public interface WorkoutRepository {
    /* read functions */
    Workout getWorkout(Context context, String name);
    ArrayList<Workout> getAllWorkouts(Context context);

    /* insert and update functions */
    void saveWorkout(Context context, Workout workout);
    void saveWorkouts(Context context, ArrayList<Workout> workouts);

    /* delete functions */
    void deleteWorkout(Context context, Workout workout);

}
