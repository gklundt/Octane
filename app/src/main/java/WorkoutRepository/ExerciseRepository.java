package WorkoutRepository;

import java.util.ArrayList;
import Domain.Exercise;
import Domain.Workout;

public interface ExerciseRepository {

    /* read functions */
    Exercise getExerciseByName(String exerciseName);
    ArrayList<Exercise> getAllExercises();
    ArrayList<Exercise> getExerciseByType(Exercise.ExerciseType exerciseType);
    ArrayList<Exercise> getExerciseByMuscleGroup(Exercise.MuscleGroup muscleGroup);
    ArrayList<Exercise> getExerciseByWorkout(Workout workout);

    /* insert and update functions */
    void saveExercise(Exercise exercise);
    void saveExercises(ArrayList<Exercise> exercises);

    /* delete functions */
    void deleteExercise(Exercise exercise);
    void deleteExercises(ArrayList<Exercise> exercises);

}
