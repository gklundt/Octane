package WorkoutModel.InternalStorageRepostiories;

import java.util.ArrayList;

import Domain.Data.Workout;
import Domain.Repositories.WorkoutRepository;

/**
 * Created by gordon on 10/25/15.
 */
public class InternalStorageWorkoutRepository implements WorkoutRepository{
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
