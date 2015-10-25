package WorkoutModel;

import java.util.ArrayList;

import Domain.Data.Exercise;
import Domain.Repositories.ExerciseRepository;

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
