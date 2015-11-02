package edu.uco.schambers4.octane.DataAccessObjects.Workout;

import android.content.Context;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import edu.uco.schambers4.octane.Models.Workout.Workout;

/**
 * Created by gordon on 10/26/15.
 */
public class MockWorkoutRepository implements WorkoutRepository {
    @Override
    public Workout getWorkout(Context context, String name) {
        return null;
    }

    @Override
    public ArrayList<Workout> getAllWorkouts(Context context) {

        ArrayList<Workout> w = new ArrayList<>();

        Map<String, Integer> easyMap = new Hashtable<>();
        easyMap.put("Situps", 3);
        easyMap.put("Pushups", 3);
        easyMap.put("Jumping Jacks", 3);
        easyMap.put("Curls", 3);
        Workout easy = new Workout("Easy", easyMap, Workout.IntensityLevel.LOW, 10);

        Map<String, Integer> mediumMap = new Hashtable<>();
        mediumMap.put("Situps", 3);
        mediumMap.put("Pushups", 3);
        mediumMap.put("Jumping Jacks", 3);
        mediumMap.put("Curls", 3);
        Workout medium = new Workout("Medium", mediumMap, Workout.IntensityLevel.MEDIUM, 80);

        Map<String, Integer> highMap = new Hashtable<>();
        highMap.put("Situps", 3);
        highMap.put("Pushups", 3);
        highMap.put("Jumping Jacks", 3);
        highMap.put("Curls", 3);
        Workout high = new Workout("Hard", highMap, Workout.IntensityLevel.HIGH, 150);

        w.add(easy);
        w.add(medium);
        w.add(high);

        return w;
    }


    @Override
    public void saveWorkout(Context context, Workout workout) {

    }

    @Override
    public void saveWorkouts(Context context, ArrayList<Workout> workouts) {

    }

    @Override
    public void deleteWorkout(Context context, Workout workout) {

    }
}
