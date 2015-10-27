package edu.uco.schambers4.octane.DataAccessObjects;

import java.util.ArrayList;

import edu.uco.schambers4.octane.DataAccessObjects.ExerciseRepository;
import edu.uco.schambers4.octane.Models.Exercise;
import edu.uco.schambers4.octane.Models.Workout;

/**
 * Created by gordon on 10/26/15.
 */
public class MockExerciseRepository implements ExerciseRepository {
    @Override
    public Exercise getExerciseByName(String exerciseName) {
        return null;
    }

    @Override
    public ArrayList<Exercise> getAllExercises() {

        ArrayList<Exercise> l = new ArrayList<>();

        Exercise jumpingjacks = new Exercise();
        Exercise situps = new Exercise();
        Exercise pushups = new Exercise();

        situps.setName("Situps");
        situps.setDescription("Sit up, lay down, repeat.");
        situps.setMuscleGroup(Exercise.MuscleGroup.ABS);

        pushups.setName("Pushups");
        pushups.setDescription("push up, lay down, repeat.");
        pushups.setMuscleGroup(Exercise.MuscleGroup.ABS);

        jumpingjacks.setName("Jumping Jacks");
        jumpingjacks.setDescription("jump and flap simultaniously, repeat.");
        jumpingjacks.setMuscleGroup(Exercise.MuscleGroup.ABS);

        l.add(situps);
        l.add(pushups);
        l.add(jumpingjacks);


        return l;
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
