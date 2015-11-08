package edu.uco.schambers4.octane.DataAccessObjects.FitnessTest;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import edu.uco.schambers4.octane.InternalStorageSerialization.InternalStorage;
import edu.uco.schambers4.octane.Models.Workout.FitnessTest;
import edu.uco.schambers4.octane.Models.Workout.FitnessTestComparator;

public class InternalStorageFitnessTestRepository implements FitnessTestRepository {

    ArrayList<FitnessTest> mFitnessTests;

    private boolean DateTest(Date lhs, Date rhs) {
        return String.format("%tm/%td/%tY", lhs, lhs, lhs)
                .equals(String.format("%tm/%td/%tY", rhs, rhs, rhs));
    }

    @Override
    public FitnessTest getFitnessTest(Context context, String exerciseName, Date date) {
        loadCheck(context);
        for (FitnessTest f : mFitnessTests) {
            if (f.getExerciseName().equals(exerciseName) && DateTest(f.getDate(), date)) {
                return f;
            }
        }
        return null;
    }

    @Override
    public ArrayList<FitnessTest> getFitnessTestsForExercise(Context context, String exerciseName) {
        loadCheck(context);
        ArrayList<FitnessTest> fitnessTests = new ArrayList<>();
        for (FitnessTest f : mFitnessTests) {
            if (f.getExerciseName().equals(exerciseName)) {
                fitnessTests.add(f);
            }
        }
        return fitnessTests;
    }

    @Override
    public FitnessTest getLastFitnessTestsForExercise(Context context, String exerciseName) {
        loadCheck(context);
        ArrayList<FitnessTest> fitnessTests = new ArrayList<>();
        for (FitnessTest f : mFitnessTests) {
            if (f.getExerciseName().equals(exerciseName)) {
                fitnessTests.add(f);
            }
        }
        if (fitnessTests.size() == 1) {
            return fitnessTests.get(0);
        }
        if (fitnessTests.size() > 1) {
            Collections.sort(fitnessTests, new FitnessTestComparator());
            return fitnessTests.get(fitnessTests.size() - 1);
        }
        return null;
    }

    @Override
    public void saveFitnessTests(Context context, ArrayList<FitnessTest> fitnessTests) {
        for (FitnessTest f : fitnessTests) {
            saveFitnessTest(context, f);
        }
    }

    @Override
    public void saveFitnessTest(Context context, FitnessTest fitnessTest) {
        loadCheck(context);
        boolean found = false;
        int pos = 0;
        for (FitnessTest f : mFitnessTests) {
            if (f.getExerciseName().equals(fitnessTest.getExerciseName())
                    && DateTest(f.getDate(), fitnessTest.getDate())) {
                found = true;
                break;
            }
            pos++;
        }

        if (found) {
            mFitnessTests.remove(pos);
        }
        mFitnessTests.add(pos, fitnessTest);
        try {
            flush(context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFitnessTest(Context context, FitnessTest fitnessTest) {
        loadCheck(context);
        boolean found = false;
        int pos = 0;
        for (FitnessTest f : mFitnessTests) {
            if (f.getExerciseName().equals(fitnessTest.getExerciseName())
                    && DateTest(f.getDate(), fitnessTest.getDate())) {
                found = true;
                break;
            }
            pos++;
        }

        if (found) {
            mFitnessTests.remove(pos);
        }
        try {
            flush(context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFitnessTestHistory(Context context, String exerciseName) {
        loadCheck(context);
        ArrayList<FitnessTest> fitnessTests = getFitnessTestsForExercise(context, exerciseName);
        for (FitnessTest f : fitnessTests) {
            deleteFitnessTest(context, f);
        }
    }

    @Override
    public void deleteAllHistory(Context context) {
        ArrayList<FitnessTest> fitnessTests = getAllFitnessTests(context);
        for (FitnessTest f : fitnessTests) {
            deleteFitnessTest(context, f);
        }
    }

    @Override
    public ArrayList<FitnessTest> getAllFitnessTests(Context context) {
        loadCheck(context);
        return mFitnessTests;
    }

    private void flush(Context context) throws IOException {
        loadCheck(context);
        InternalStorage.writeObject(context, InternalStorage.STORAGE_KEY_FITNESSTESTS, mFitnessTests);
    }

    private ArrayList<FitnessTest> load(Context context) throws IOException, ClassNotFoundException {
        return (ArrayList<FitnessTest>)
                InternalStorage.readObject(context, InternalStorage.STORAGE_KEY_FITNESSTESTS);
    }


    private void loadCheck(Context context) {
        if (mFitnessTests == null) try {
            mFitnessTests = load(context);
            if (mFitnessTests == null) {
                mFitnessTests = new ArrayList<>();
            }
        } catch (IOException | ClassNotFoundException e) {
            mFitnessTests = new ArrayList<>();
            e.printStackTrace();
        }
    }
}
