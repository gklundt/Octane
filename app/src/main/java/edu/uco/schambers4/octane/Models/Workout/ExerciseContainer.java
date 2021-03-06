package edu.uco.schambers4.octane.Models.Workout;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import edu.uco.schambers4.octane.DataAccessObjects.Exercise.ExerciseRepository;
import edu.uco.schambers4.octane.DataAccessObjects.Exercise.InternalStorageExerciseRepository;

public class ExerciseContainer {
    private static ExerciseContainer ourInstance;
    //private ArrayList<Exercise> mExercises;
    private ExerciseRepository mExerciseRepository;

    public static ExerciseContainer getInstance() {
        if (ourInstance == null) {
            ExerciseRepository repo = new InternalStorageExerciseRepository();
            ourInstance = new ExerciseContainer(repo);
        }
        return ourInstance;
    }

    public static void DestroyExerciseContainer() {
        if (ourInstance != null) {
            ourInstance = null;
        }
    }

    private ExerciseContainer(ExerciseRepository repo) {
        mExerciseRepository = repo;
    }

    public ArrayList<Exercise> getExercises(Context context) {
        return mExerciseRepository.getAllExercises(context);
    }

    public void save(Context context, Exercise exercise) {
        mExerciseRepository.saveExercise(context, exercise);
    }

    public Exercise getDefaultExercise() {
        Exercise exercise = new Exercise();

        exercise.setName("");

        exercise.setExerciseType(Exercise.ExerciseType.ENDURANCE);

        ExerciseMeasure measure = new ExerciseMeasure();
        measure.setMeasure(0);
        measure.setForce(0);
        measure.setForceUnits(ExerciseMeasure.Unit.BODYWEIGHT);
        measure.setMeasureUnits(ExerciseMeasure.Unit.COUNT);
        exercise.setMaxIntensityExerciseMeasure(measure);

        exercise.setHighIntensity(new Intensity(100, 100));
        exercise.setMedIntensity(new Intensity(100, 100));
        exercise.setLowIntensity(new Intensity(100, 100));

        exercise.setDescription("");

        exercise.setMuscleGroup(Exercise.MuscleGroup.FULLBODY);

        return exercise;
    }

    public Exercise createDefaultExercise(Context context) {
        Exercise exercise = getDefaultExercise();
        save(context, exercise);
        return exercise;
    }

    private ArrayList<String> getMusclesArray() {
        ArrayList<String> ma = new ArrayList<>();

        for (Exercise.MuscleGroup m : Exercise.MuscleGroup.values()) {
            ma.add(m.getGroupName());
        }
        return ma;
    }

    private ArrayList<String> getExerciseTypeArray() {
        ArrayList<String> ta = new ArrayList<>();

        for (Exercise.ExerciseType t : Exercise.ExerciseType.values()) {
            ta.add(t.getExerciseTypeName());
        }
        return ta;
    }

    private ArrayList<String> getResistanceUnitsArray() {
        ArrayList<String> myArray = new ArrayList<>();

        for (ExerciseMeasure.Unit u : ExerciseMeasure.Unit.values()) {
            if (u.getUnitKind() == ExerciseMeasure.UnitKind.WEIGHT || u.getUnitKind() == ExerciseMeasure.UnitKind.RATE) {
                myArray.add(u.getUnitName());
            }
        }
        return myArray;
    }

    private ArrayList<String> getMeasurementUnitsArray() {
        ArrayList<String> myArray = new ArrayList<>();

        for (ExerciseMeasure.Unit u : ExerciseMeasure.Unit.values()) {
            if (!(u.getUnitKind() == ExerciseMeasure.UnitKind.WEIGHT || u.getUnitKind() == ExerciseMeasure.UnitKind.RATE)) {
                myArray.add(u.getUnitName());
            }
        }
        return myArray;
    }

    private ArrayList<String> getUnitsArray() {
        ArrayList<String> myArray = new ArrayList<>();

        for (ExerciseMeasure.Unit u : ExerciseMeasure.Unit.values()) {
            myArray.add(u.getUnitName());
        }
        return myArray;
    }

    public ArrayAdapter<String> getMuscleArrayAdapter(Context context) {
        return new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, getMusclesArray());
    }

    public ArrayAdapter<String> getExerciseTypeArrayAdapter(Context context) {
        return new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, getExerciseTypeArray());
    }

    public ArrayAdapter<String> getResistanceUnitsArrayAdapter(Context context) {
        return new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, getResistanceUnitsArray());
    }

    public ArrayAdapter<String> getMeasurementUnitsArrayAdapter(Context context) {
        return new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, getMeasurementUnitsArray());
    }

    public ArrayAdapter<String> getUnitsArrayAdapter(Context context) {
        return new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, getUnitsArray());
    }

    public ExerciseAdapter getExerciseAdapter(Context context) {
        return new ExerciseAdapter(context, getExercises(context));
    }

    public void delete(Context context, Exercise exercise) {
        mExerciseRepository.deleteExercise(context, exercise);
    }

    public Exercise getExerciseByName(Context context, String exerciseName) {
        return mExerciseRepository.getExerciseByName(context, exerciseName);
    }
}
