package edu.uco.schambers4.octane.DataAccessObjects.FitnessTest;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;

import edu.uco.schambers4.octane.Models.Workout.FitnessTest;

/**
 * Created by gordon on 10/26/15.
 */
public class MockFitnessTestRepository implements FitnessTestRepository{

    @Override
    public FitnessTest getFitnessTest(Context context, String exerciseName, Date date) {
        return null;
    }

    @Override
    public ArrayList<FitnessTest> getFitnessTestsForExercise(Context context, String exerciseName) {
        return null;
    }

    @Override
    public FitnessTest getLastFitnessTestsForExercise(Context context, String exerciseName) {
        return null;
    }

    @Override
    public void saveFitnessTests(Context context, ArrayList<FitnessTest> fitnessTests) {

    }

    @Override
    public void saveFitnessTest(Context context, FitnessTest fitnessTest) {

    }

    @Override
    public void deleteFitnessTest(Context context, FitnessTest fitnessTest) {

    }

    @Override
    public void deleteFitnessTestHistory(Context context, String fitnessTestName) {

    }

    @Override
    public void deleteAllHistory(Context context) {

    }

}
