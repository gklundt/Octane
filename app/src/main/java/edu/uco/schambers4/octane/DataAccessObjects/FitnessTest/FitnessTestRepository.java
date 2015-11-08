package edu.uco.schambers4.octane.DataAccessObjects.FitnessTest;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;

import edu.uco.schambers4.octane.Models.Workout.FitnessTest;

/**
 * Created by gordon on 10/25/15.
 */
public interface FitnessTestRepository {
    /* read functions */
    FitnessTest getFitnessTest(Context context, String exerciseName, Date date);
    ArrayList<FitnessTest> getFitnessTestsForExercise(Context context, String exerciseName);
    FitnessTest getLastFitnessTestsForExercise(Context context, String exerciseName);
    ArrayList<FitnessTest> getAllFitnessTests(Context context);

    /* insert and update functions */
    void saveFitnessTests(Context context, ArrayList<FitnessTest> fitnessTests);
    void saveFitnessTest(Context context, FitnessTest fitnessTest);

    /* delete functions */
    void deleteFitnessTest(Context context, FitnessTest fitnessTest);
    void deleteFitnessTestHistory(Context context, String exerciseName);
    void deleteAllHistory(Context context);


}
