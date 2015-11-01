package edu.uco.schambers4.octane.Models.Workout;

import android.content.Context;
import android.os.DropBoxManager;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import edu.uco.schambers4.octane.DataAccessObjects.Exercise.ExerciseRepository;
import edu.uco.schambers4.octane.DataAccessObjects.Exercise.MockExerciseRepository;
import edu.uco.schambers4.octane.DataAccessObjects.Workout.MockWorkoutRepository;
import edu.uco.schambers4.octane.DataAccessObjects.Workout.WorkoutRepository;


/**
 * Created by gordon on 10/25/15.
 */
public class WorkoutContainer {

    private static WorkoutContainer ourInstance;

    private ArrayList<Workout> mWorkouts;
    private WorkoutRepository mWorkoutRepository;
    private ExerciseRepository mExerciseRepository;

    public static WorkoutContainer getInstance() {
        if (ourInstance == null){
            WorkoutRepository repo = new MockWorkoutRepository();
            ExerciseRepository exerciseRepository = new MockExerciseRepository();
            ourInstance = new WorkoutContainer(repo, exerciseRepository);
        }
        return ourInstance;
    }

    private WorkoutContainer(WorkoutRepository repo, ExerciseRepository exerciseRepository) {
        mWorkoutRepository = repo;
        mWorkouts = mWorkoutRepository.getAllWorkouts();
        mExerciseRepository = exerciseRepository;

    }

    public ArrayList<Workout> getWorkouts() {
        mWorkouts = mWorkoutRepository.getAllWorkouts();
        return mWorkouts;
    }

    public void save(){
        mWorkoutRepository.saveWorkouts(mWorkouts);
    }

    public void save(Workout workout){
        mWorkoutRepository.saveWorkout(workout);
    }


    private ArrayList<String> getIntensityLevelArray() {
        ArrayList<String> myArray = new ArrayList<>();

        for (Workout.IntensityLevel i : Workout.IntensityLevel.values()) {
                myArray.add(i.getLevel());
        }
        return myArray;
    }


    public ArrayAdapter<String> getWorkoutIntensityArrayAdapter(Context context) {
        ArrayAdapter<String> aa = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, getIntensityLevelArray());
        return aa;

    }

    public ArrayList<Exercise> getExercises(int index) {
        ArrayList<Exercise> ae = new ArrayList<>();
        for(Map.Entry<String, Integer> h : mWorkouts.get(index).getExerciseSets().entrySet()){
            ae.add(mExerciseRepository.getExerciseByName(h.getKey()));
        }
        return ae;
    }
}
