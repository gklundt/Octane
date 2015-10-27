package edu.uco.schambers4.octane.DataAccessObjects;

import java.util.ArrayList;

import edu.uco.schambers4.octane.DataAccessObjects.WorkoutRepository;
import edu.uco.schambers4.octane.Models.Workout;

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
        return null;
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
