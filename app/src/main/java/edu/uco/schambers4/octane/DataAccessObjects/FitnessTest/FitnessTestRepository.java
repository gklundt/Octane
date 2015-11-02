package edu.uco.schambers4.octane.DataAccessObjects.FitnessTest;

import java.util.ArrayList;
import java.util.Date;

import edu.uco.schambers4.octane.Models.Workout.FitnessTest;

/**
 * Created by gordon on 10/25/15.
 */
public interface FitnessTestRepository {
    /* read functions */
    FitnessTest getFitnessTest(String exerciseName, Date date);
    ArrayList<FitnessTest> getFitnessTestsForExercise(String exerciseName);

    /* insert and update functions */
    void saveFitnessTest(FitnessTest fitnessTest);
    void saveFitnessTests(ArrayList<FitnessTest> fitnessTests);

    /* delete functions */
    void deleteTest(String exerciseName, Date date);
    void deleteFitnessTestHistory(String exerciseName);
    void deleteAllHistory();
}
