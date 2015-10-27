package edu.uco.schambers4.octane.DataAccessObjects;

import java.util.ArrayList;
import edu.uco.schambers4.octane.Models.Exercise;
import edu.uco.schambers4.octane.Models.Workout;

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
