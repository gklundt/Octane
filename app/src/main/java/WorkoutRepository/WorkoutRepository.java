package WorkoutRepository;

import java.util.ArrayList;

import Domain.Workout;

/**
 * Created by gordon on 10/25/15.
 */
public interface WorkoutRepository {
    /* read functions */
    Workout getWorkout(String name);
    ArrayList<Workout> getAllWorkouts();

    /* insert and update functions */
    void saveWorkout(Workout workout);
    void saveWorkouts(ArrayList<Workout> workouts);

    /* delete functions */
    void deleteWorkout(Workout workout);

}
