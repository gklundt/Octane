package edu.uco.schambers4.octane.Models.WorkoutModel.InternalStorageRepostiories;

import java.util.ArrayList;

import edu.uco.schambers4.octane.Domain.Data.Exercise;
import edu.uco.schambers4.octane.Domain.Data.Workout;
import edu.uco.schambers4.octane.Domain.Repositories.ExerciseRepository;

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
