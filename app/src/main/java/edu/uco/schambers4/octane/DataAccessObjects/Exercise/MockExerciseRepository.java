package edu.uco.schambers4.octane.DataAccessObjects.Exercise;

import java.util.ArrayList;

import edu.uco.schambers4.octane.Models.Workout.Exercise;
import edu.uco.schambers4.octane.Models.Workout.ExerciseMeasure;
import edu.uco.schambers4.octane.Models.Workout.Intensity;
import edu.uco.schambers4.octane.Models.Workout.Workout;

/**
 * Created by gordon on 10/26/15.
 */
public class MockExerciseRepository implements ExerciseRepository {

    ArrayList<Exercise> mExercises;

    public MockExerciseRepository() {

        mExercises = new ArrayList<>();

        Exercise jumpingjacks = new Exercise();
        Exercise situps = new Exercise();
        Exercise pushups = new Exercise();
        Exercise curls = new Exercise();

        ExerciseMeasure jumpingjacks_measure = new ExerciseMeasure();
        ExerciseMeasure situps_measure = new ExerciseMeasure();
        ExerciseMeasure pushups_measure = new ExerciseMeasure();
        ExerciseMeasure curls_measure = new ExerciseMeasure();


        jumpingjacks.setHighIntensity(new Intensity(100, 100));
        jumpingjacks.setMedIntensity(new Intensity(90, 90));
        jumpingjacks.setLowIntensity(new Intensity(70, 70));

        situps.setHighIntensity(new Intensity(100, 100));
        situps.setMedIntensity(new Intensity(90, 90));
        situps.setLowIntensity(new Intensity(70, 70));

        pushups.setHighIntensity(new Intensity(100, 100));
        pushups.setMedIntensity(new Intensity(90, 90));
        pushups.setLowIntensity(new Intensity(70, 70));

        curls.setHighIntensity(new Intensity(80, 110));
        curls.setMedIntensity(new Intensity(90, 90));
        curls.setLowIntensity(new Intensity(90, 75));

        situps.setName("Situps");
        pushups.setName("Pushups");
        jumpingjacks.setName("Jumping Jacks");
        curls.setName("Curls");

        situps.setDescription("Sit up, lay down, repeat.");
        pushups.setDescription("push up, lay down, repeat.");
        jumpingjacks.setDescription("jump and flap simultaniously, repeat.");
        curls.setDescription("Repeatedly lift a dumbell.");

        situps.setMuscleGroup(Exercise.MuscleGroup.ABS);
        pushups.setMuscleGroup(Exercise.MuscleGroup.ARMS);
        jumpingjacks.setMuscleGroup(Exercise.MuscleGroup.FULLBODY);
        curls.setMuscleGroup(Exercise.MuscleGroup.ARMS);

        situps.setExerciseType(Exercise.ExerciseType.ENDURANCE);
        pushups.setExerciseType(Exercise.ExerciseType.STRENGTH);
        jumpingjacks.setExerciseType(Exercise.ExerciseType.ENDURANCE);
        curls.setExerciseType(Exercise.ExerciseType.STRENGTH);

        jumpingjacks_measure.setForce(1);
        jumpingjacks_measure.setMeasure(100);
        jumpingjacks_measure.setForceUnits(ExerciseMeasure.Units.BODYWEIGHT);
        jumpingjacks_measure.setMeasureUnits(ExerciseMeasure.Units.COUNT);
        jumpingjacks.setMaxIntensityExerciseMeasure(jumpingjacks_measure);


        situps_measure.setForce(1);
        situps_measure.setMeasure(100);
        situps_measure.setForceUnits(ExerciseMeasure.Units.BODYWEIGHT);
        situps_measure.setMeasureUnits(ExerciseMeasure.Units.COUNT);
        situps.setMaxIntensityExerciseMeasure(situps_measure);

        pushups_measure.setForce(1);
        pushups_measure.setMeasure(100);
        pushups_measure.setForceUnits(ExerciseMeasure.Units.BODYWEIGHT);
        pushups_measure.setMeasureUnits(ExerciseMeasure.Units.COUNT);
        pushups.setMaxIntensityExerciseMeasure(pushups_measure);


        curls_measure.setForce(25);
        curls_measure.setMeasure(10);
        curls_measure.setForceUnits(ExerciseMeasure.Units.LBS);
        curls_measure.setMeasureUnits(ExerciseMeasure.Units.COUNT);
        curls.setMaxIntensityExerciseMeasure(curls_measure);


        mExercises.add(situps);
        mExercises.add(pushups);
        mExercises.add(jumpingjacks);
        mExercises.add(curls);


    }

    @Override
    public Exercise getExerciseByName(String exerciseName) {
        for (Exercise e : mExercises) {
            if (e.getName().equals(exerciseName)) {
                return e;
            }

        }
        return null;
    }

    @Override
    public ArrayList<Exercise> getAllExercises() {
        return mExercises;
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
