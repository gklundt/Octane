package edu.uco.schambers4.octane.DataAccessObjects.Exercise;

import java.util.ArrayList;

import edu.uco.schambers4.octane.Models.Workout.Exercise;
import edu.uco.schambers4.octane.Models.Workout.Workout;

public class InternalStorageExerciseRepository implements ExerciseRepository {
    @Override
    public Exercise getExerciseByName(String exerciseName) {
        return null;
    }

    @Override
    public ArrayList<Exercise> getAllExercises() {
        return null;
    }

    @Override
    public ArrayList<Exercise> getExerciseByType(Exercise.ExerciseType exerciseType) {
        return null;
    }

    @Override
    public ArrayList<Exercise> getExerciseByMuscleGroup(Exercise.MuscleGroup muscleGroup) {
        return null;
    }

    @Override
    public ArrayList<Exercise> getExerciseByWorkout(Workout workout) {
        return null;
    }

    @Override
    public void saveExercise(Exercise exercise) {

    }

    @Override
    public void saveExercises(ArrayList<Exercise> exercises) {

    }

    @Override
    public void deleteExercise(Exercise exercise) {

    }

    @Override
    public void deleteExercises(ArrayList<Exercise> exercises) {

    }
}
