package edu.uco.schambers4.octane.Models.Workout;

import android.content.Context;

import java.util.ArrayList;

import edu.uco.schambers4.octane.DataAccessObjects.FitnessTest.FitnessTestRepository;
import edu.uco.schambers4.octane.DataAccessObjects.FitnessTest.InternalStorageFitnessTestRepository;

public class FitnessTestContainer {
    private static FitnessTestContainer ourInstance;
    private FitnessTestRepository mFitnessTestRepository;

    public static FitnessTestContainer getInstance() {
        if (ourInstance == null) {
            FitnessTestRepository repo = new InternalStorageFitnessTestRepository();
            ourInstance = new FitnessTestContainer(repo);
        }
        return ourInstance;
    }

    public static void DestroyFitnessTestContainer() {
        if (ourInstance != null) {
            ourInstance = null;
        }
    }

    private FitnessTestContainer(FitnessTestRepository repo) {
        mFitnessTestRepository = repo;
    }

    public ArrayList<FitnessTest> getFitnessTests(Context context, String exerciseName) {
        return mFitnessTestRepository.getFitnessTestsForExercise(context, exerciseName);
    }

    public ArrayList<FitnessTest> getRecentFitnessTests(Context context) {
        ArrayList<Exercise> exercises = ExerciseContainer.getInstance().getExercises(context);
        ArrayList<FitnessTest> fitnessTests = new ArrayList<>();
        for (Exercise exercise : exercises) {
            FitnessTest fitnessTest = mFitnessTestRepository.getLastFitnessTestsForExercise(context, exercise.getName());
            if (fitnessTest != null) {
                fitnessTests.add(fitnessTest);
            } else {
                fitnessTests.add(new FitnessTest(exercise.getName(), null, null));
            }
        }
        return fitnessTests;
    }

    public FitnessTestAdapter getRecentFitnessTestAdapter(Context context) {
        return new FitnessTestAdapter(context, getRecentFitnessTests(context));
    }

    public void delete(Context context, FitnessTest fitnessTest) {
        mFitnessTestRepository.deleteFitnessTest(context, fitnessTest);
    }

    public void save(Context context, FitnessTest fitnessTest) {
        mFitnessTestRepository.saveFitnessTest(context, fitnessTest);
    }
}
