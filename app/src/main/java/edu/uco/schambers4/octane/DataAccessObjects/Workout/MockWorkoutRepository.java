package edu.uco.schambers4.octane.DataAccessObjects.Workout;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import edu.uco.schambers4.octane.Models.Workout.Workout;

/**
 * Created by gordon on 10/26/15.
 */
public class MockWorkoutRepository implements WorkoutRepository {
    @Override
    public Workout getWorkout(String name) {
        return null;
    }

    @Override
    public ArrayList<Workout> getAllWorkouts() {

        ArrayList<Workout> w = new ArrayList<>();

        Map<String,Integer> easyMap = new Hashtable<>();
        easyMap.put("Situps",1);
        easyMap.put("Pushups",1);
        easyMap.put("Jumping Jacks",1);
        Workout easy = new Workout("Easy",easyMap);

        Map<String,Integer> mediumMap = new Hashtable<>();
        easyMap.put("Situps",10);
        easyMap.put("Pushups",10);
        easyMap.put("Jumping Jacks",10);
        Workout medium = new Workout("Medium",easyMap);

        Map<String,Integer> highMap = new Hashtable<>();
        easyMap.put("Situps",100);
        easyMap.put("Pushups",100);
        easyMap.put("Jumping Jacks",100);
        Workout high = new Workout("Hard",easyMap);

        w.add(easy);
        w.add(medium);
        w.add(high);

        return w;
    }




    @Override
    public void saveWorkout(Workout workout) {

    }

    @Override
    public void saveWorkouts(ArrayList<Workout> workouts) {

    }

    @Override
    public void deleteWorkout(Workout workout) {

    }
}
