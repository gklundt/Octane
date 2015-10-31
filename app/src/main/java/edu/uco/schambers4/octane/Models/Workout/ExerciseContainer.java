package edu.uco.schambers4.octane.Models.Workout;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;

import edu.uco.schambers4.octane.DataAccessObjects.Exercise.ExerciseRepository;
import edu.uco.schambers4.octane.DataAccessObjects.Exercise.MockExerciseRepository;

public class ExerciseContainer {
    private static ExerciseContainer ourInstance;

    private ArrayList<Exercise> mExercises;
    private ExerciseRepository mExerciseRepository;

    public static ExerciseContainer getInstance() {
        if (ourInstance == null) {
            ExerciseRepository repo = new MockExerciseRepository();
            ourInstance = new ExerciseContainer(repo);
        }
        return ourInstance;
    }

    public static void DestroyExerciseContainer(){
        if(ourInstance != null){
            ourInstance = null;
        }
    }
    private ExerciseContainer(ExerciseRepository repo) {
        mExerciseRepository = repo;
        mExercises = new ArrayList<>();
    }

    public ArrayList<Exercise> getExercises() {
        return mExerciseRepository.getAllExercises();
        //return mExercises;
    }

    public void save() {
        mExerciseRepository.saveExercises(mExercises);
    }

    public void save(Exercise exercise) {
        mExerciseRepository.saveExercise(exercise);
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

}
