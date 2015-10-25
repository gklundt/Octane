package WorkoutModel.InternalStorageRepostiories;

import java.util.ArrayList;
import java.util.Date;

import Domain.Data.FitnessTest;
import Domain.Repositories.FitnessTestRepository;

public class InternalStorageFitnessTestRepository implements FitnessTestRepository {
    @Override
    public FitnessTest getFitnessTest(String exerciseName, Date date) {
        return null;
    }

    @Override
    public ArrayList<FitnessTest> getFitnessTestsForExercise(String exerciseName) {
        return null;
    }

    @Override
    public void saveFitnessTest(FitnessTest fitnessTest) {

    }

    @Override
    public void saveFitnessTests(ArrayList<FitnessTest> fitnessTests) {

    }

    @Override
    public void deleteTest(String exerciseName, Date date) {

    }

    @Override
    public void deleteFitnessTestHistory(String exerciseName) {

    }

    @Override
    public void deleteAllHistory() {

    }
}
