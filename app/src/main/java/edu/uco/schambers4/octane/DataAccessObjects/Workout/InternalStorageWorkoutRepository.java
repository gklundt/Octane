package edu.uco.schambers4.octane.DataAccessObjects.Workout;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;

import edu.uco.schambers4.octane.InternalStorageSerialization.InternalStorage;
import edu.uco.schambers4.octane.Models.Workout.Workout;

public class InternalStorageWorkoutRepository implements WorkoutRepository{

    ArrayList<Workout> mWorkouts;
    @Override
    public Workout getWorkout(Context context, String workoutName) {
        loadcheck(context);
        for (Workout workout: mWorkouts) {
            if (workout.getName().equals(workoutName)) {
                return workout;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Workout> getAllWorkouts(Context context) {
        loadcheck(context);
        return mWorkouts;
    }

    @Override
    public void saveWorkout(Context context, Workout workout) {
        loadcheck(context);
        boolean found = false;
        int pos = 0;
        for (Workout w : mWorkouts) {
            if (w.getName().equals(workout.getName())) {
                found = true;
                break;
            }
            pos++;
        }

        if (found) {
            mWorkouts.remove(pos);
        }
        mWorkouts.add(pos, workout);
        try {
            flush(context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveWorkouts(Context context, ArrayList<Workout> workouts) {
        for(Workout workout: workouts){
            saveWorkout(context, workout);
        }
    }

    @Override
    public void deleteWorkout(Context context, Workout workout) {
        loadcheck(context);
        boolean found = false;
        int pos = 0;
        for (Workout w : mWorkouts) {
            if (w.getName().equals(workout.getName())) {
                found = true;
                break;
            }
            pos++;
        }

        if (found) {
            mWorkouts.remove(pos);
        }
        try {
            flush(context);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void flush(Context context) throws IOException {
        loadcheck(context);
        InternalStorage.writeObject(context, InternalStorage.STORAGE_KEY_EXERCISES, mWorkouts);
    }

    private ArrayList<Workout> load(Context context) throws IOException, ClassNotFoundException {
        ArrayList<Workout> ae = (ArrayList<Workout>)
                InternalStorage.readObject(context, InternalStorage.STORAGE_KEY_EXERCISES);
        return ae;
    }

    private void loadcheck(Context context) {
        if(mWorkouts == null){
            try {
                load(context);
            } catch (IOException e) {
                mWorkouts = new ArrayList<>();
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                mWorkouts = new ArrayList<>();
                e.printStackTrace();
            }
        }
        else {
            mWorkouts = new ArrayList<>();
        }

    }


}
