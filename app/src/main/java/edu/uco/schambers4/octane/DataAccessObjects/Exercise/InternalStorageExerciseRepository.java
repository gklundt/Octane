package edu.uco.schambers4.octane.DataAccessObjects.Exercise;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;

import edu.uco.schambers4.octane.InternalStorageSerialization.InternalStorage;
import edu.uco.schambers4.octane.Models.Workout.Exercise;

public class InternalStorageExerciseRepository implements ExerciseRepository {

    ArrayList<Exercise> mExercises;

    @Override
    public Exercise getExerciseByName(Context context, String exerciseName) {
        loadcheck(context);
        for (Exercise exercise : mExercises) {
            if (exercise.getName().equals(exerciseName)) {
                return exercise;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Exercise> getAllExercises(Context context) {
        loadcheck(context);
        return mExercises;
    }

    @Override
    public ArrayList<Exercise> getExerciseByType(Context context, Exercise.ExerciseType exerciseType) {
        loadcheck(context);
        ArrayList<Exercise> searchList = new ArrayList<>();
        for (Exercise exercise : mExercises) {
            if (exercise.getExerciseType().equals(exerciseType)) {
                searchList.add(exercise);
            }
        }
        return searchList;
    }

    @Override
    public ArrayList<Exercise> getExerciseByMuscleGroup(Context context, Exercise.MuscleGroup muscleGroup) {
        loadcheck(context);
        ArrayList<Exercise> searchList = new ArrayList<>();
        for (Exercise exercise : mExercises) {
            if (exercise.getMuscleGroup().equals(muscleGroup)) {
                searchList.add(exercise);
            }
        }
        return searchList;
    }

    @Override
    public void saveExercise(Context context, Exercise exercise) {
        loadcheck(context);
        boolean found = false;
        int pos = 0;
        for (Exercise e : mExercises) {
            if (e.getName().equals(exercise.getName())) {
                found = true;
                break;
            }
            pos++;
        }

        if (found) {
            mExercises.remove(pos);
        }
        mExercises.add(pos, exercise);
        try {
            flush(context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveExercises(Context context, ArrayList<Exercise> exercises) {
        for (Exercise exercise : exercises) {
            saveExercise(context, exercise);
        }
    }

    @Override
    public void deleteExercise(Context context, Exercise exercise) {
        loadcheck(context);
        boolean found = false;
        int pos = 0;
        for (Exercise e : mExercises) {
            if (e.getName().equals(exercise.getName())) {
                found = true;
                break;
            }
            pos++;
        }

        if (found) {
            mExercises.remove(pos);
        }
        try {
            flush(context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteExercises(Context context, ArrayList<Exercise> exercises) {
        for (Exercise exercise : exercises) {
            deleteExercise(context, exercise);
        }
    }

    private void flush(Context context) throws IOException {
        loadcheck(context);
        InternalStorage.writeObject(context, InternalStorage.STORAGE_KEY_EXERCISES, mExercises);
    }

    private ArrayList<Exercise> load(Context context) throws IOException, ClassNotFoundException {
        return (ArrayList<Exercise>)
                InternalStorage.readObject(context, InternalStorage.STORAGE_KEY_EXERCISES);
    }

    private void loadcheck(Context context) {
        if (mExercises == null) {
            try {
                mExercises = load(context);
                if (mExercises == null) {
                    mExercises = new ArrayList<>();
                }
            } catch (IOException e) {
                mExercises = new ArrayList<>();
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                mExercises = new ArrayList<>();
                e.printStackTrace();
            }
        }
    }
}
