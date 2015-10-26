package edu.uco.schambers4.octane.Models.WorkoutModel;

import java.util.ArrayList;

import edu.uco.schambers4.octane.Domain.Data.Exercise;
import edu.uco.schambers4.octane.Domain.Repositories.ExerciseRepository;

public class ExerciseContainer {
    private static ExerciseContainer ourInstance;

    private ArrayList<Exercise> mExercises;
    private ExerciseRepository mExerciseRepository;

    public static ExerciseContainer getInstance(ExerciseRepository repo) {
        if (ourInstance == null){
            ourInstance = new ExerciseContainer(repo);
        }
        return ourInstance;
    }

    private ExerciseContainer(ExerciseRepository repo) {
        mExerciseRepository = repo;
        mExercises = new ArrayList<>();
    }

    public ArrayList<Exercise> getExercises() {
        return mExercises;
    }

    public void save(){
        mExerciseRepository.saveExercises(mExercises);
    }

    public void save(Exercise exercise){
        mExerciseRepository.saveExercise(exercise);
    }

}
