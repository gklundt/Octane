package edu.uco.schambers4.octane.Models;

import java.util.ArrayList;

import edu.uco.schambers4.octane.DataAccessObjects.MockWorkoutRepository;
import edu.uco.schambers4.octane.DataAccessObjects.WorkoutRepository;


/**
 * Created by gordon on 10/25/15.
 */
public class WorkoutContainer {

    private static WorkoutContainer ourInstance;

    private ArrayList<Workout> mWorkouts;
    private WorkoutRepository mWorkoutRepository;

    public static WorkoutContainer getInstance() {
        if (ourInstance == null){
            WorkoutRepository repo = new MockWorkoutRepository();
            ourInstance = new WorkoutContainer(repo);
        }
        return ourInstance;
    }

    private WorkoutContainer(WorkoutRepository repo) {
        mWorkoutRepository = repo;
        mWorkouts = new ArrayList<>();
    }

    public ArrayList<Workout> getWorkouts() {
        return mWorkoutRepository.getAllWorkouts();
        //return mWorkouts;
    }

    public void save(){
        mWorkoutRepository.saveWorkouts(mWorkouts);
    }

    public void save(Workout workout){
        mWorkoutRepository.saveWorkout(workout);
    }








}
