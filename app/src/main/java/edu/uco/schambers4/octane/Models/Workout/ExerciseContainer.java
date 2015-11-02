package edu.uco.schambers4.octane.Models.Workout;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import edu.uco.schambers4.octane.DataAccessObjects.Exercise.ExerciseRepository;
import edu.uco.schambers4.octane.DataAccessObjects.Exercise.MockExerciseRepository;

public class ExerciseContainer {
    private static ExerciseContainer ourInstance;

    private ArrayList<Exercise> mExercises;
    private ExerciseRepository mExerciseRepository;

    public static ExerciseContainer getInstance() {
        if (ourInstance == null) {
            //ExerciseRepository repo = new InternalStorageExerciseRepository();
            ExerciseRepository repo = new MockExerciseRepository();
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
        mExercises = new ArrayList<>();
    }

    public ArrayList<Exercise> getExercises(Context context) {
        mExercises = mExerciseRepository.getAllExercises(context);
        return mExercises;
    }

    public void save(Context context) {
        mExerciseRepository.saveExercises(context, mExercises);
    }

    public void save(Context context, Exercise exercise) {
        mExerciseRepository.saveExercise(context, exercise);
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

        for (ExerciseMeasure.Units u : ExerciseMeasure.Units.values()) {
            if (u.getUnitKind() == ExerciseMeasure.UnitKind.WEIGHT) {
                myArray.add(u.getUnitName());
            }
        }
        return myArray;
    }

    private ArrayList<String> getMeasurementUnitsArray() {
        ArrayList<String> myArray = new ArrayList<>();

        for (ExerciseMeasure.Units u : ExerciseMeasure.Units.values()) {
            if (u.getUnitKind() != ExerciseMeasure.UnitKind.WEIGHT) {
                myArray.add(u.getUnitName());
            }
        }
        return myArray;
    }

    private ArrayList<String> getUnitsArray() {
        ArrayList<String> myArray = new ArrayList<>();

        for (ExerciseMeasure.Units u : ExerciseMeasure.Units.values()) {
            myArray.add(u.getUnitName());
        }
        return myArray;
    }


    public ArrayAdapter<String> getMuscleArrayAdapter(Context context) {

        ArrayAdapter<String> aa = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, getMusclesArray());
        return aa;
    }

    public ArrayAdapter<String> getExerciseTypeArrayAdapter(Context context) {
        ArrayAdapter<String> aa = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, getExerciseTypeArray());
        return aa;

    }

    public ArrayAdapter<String> getResistanceUnitsArrayAdapter(Context context) {
        ArrayAdapter<String> aa = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, getResistanceUnitsArray());
        return aa;

    }

    public ArrayAdapter<String> getMeasurementUnitsArrayAdapter(Context context) {
        ArrayAdapter<String> aa = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, getMeasurementUnitsArray());
        return aa;

    }

    public ArrayAdapter<String> getUnitsArrayAdapter(Context context) {
        ArrayAdapter<String> aa = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, getUnitsArray());
        return aa;

    }

    public ExerciseAdapter getExerciseAdapter(Context context) {
        ExerciseAdapter ea = new ExerciseAdapter(context, getExercises(context));
        return ea;
    }

    public ExerciseRepository getRepository() {
        return mExerciseRepository;
    }

    public Exercise getDefaultExercise() {
        Exercise exercise = new Exercise();

        exercise.setName("New Exercise");

        exercise.setExerciseType(Exercise.ExerciseType.STRENGTH);

        ExerciseMeasure measure = new ExerciseMeasure();
        measure.setMeasure(0);
        measure.setForce(0);
        measure.setForceUnits(ExerciseMeasure.Units.LBS);
        measure.setMeasureUnits(ExerciseMeasure.Units.MILES);
        exercise.setMaxIntensityExerciseMeasure(measure);

        exercise.setHighIntensity(new Intensity(100, 100));
        exercise.setMedIntensity(new Intensity(100, 100));
        exercise.setLowIntensity(new Intensity(100, 100));

        exercise.setDescription("");

        exercise.setMuscleGroup(Exercise.MuscleGroup.ABS);

        return exercise;
    }

    public int createDefaultExercise() {
        Exercise exercise = getDefaultExercise();
        mExercises.add(exercise);
        return mExercises.indexOf(exercise);

    }
}
