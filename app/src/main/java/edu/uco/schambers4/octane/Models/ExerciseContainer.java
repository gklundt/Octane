package edu.uco.schambers4.octane.Models;

import java.util.ArrayList;

import edu.uco.schambers4.octane.DataAccessObjects.ExerciseRepository;
import edu.uco.schambers4.octane.DataAccessObjects.MockExerciseRepository;

public class ExerciseContainer {
    private static ExerciseContainer ourInstance;

    private ArrayList<Exercise> mExercises;
    private ExerciseRepository mExerciseRepository;

    public static ExerciseContainer getInstance() {
        if (ourInstance == null){
            ExerciseRepository repo = new MockExerciseRepository();
            ourInstance = new ExerciseContainer(repo);
        }
        return ourInstance;
    }

    private ExerciseContainer(ExerciseRepository repo) {
        mExerciseRepository = repo;
        mExercises = new ArrayList<>();
    }

    public ArrayList<Exercise> getExercises() {
        return mExerciseRepository.getAllExercises();
        //return mExercises;
    }

    public void save(){
        mExerciseRepository.saveExercises(mExercises);
    }

    public void save(Exercise exercise){
        mExerciseRepository.saveExercise(exercise);
    }

}
