package edu.uco.schambers4.octane.Models.Workout;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import edu.uco.schambers4.octane.DataAccessObjects.Workout.InternalStorageWorkoutRepository;
import edu.uco.schambers4.octane.DataAccessObjects.Workout.WorkoutRepository;

public class WorkoutContainer {

    private static WorkoutContainer ourInstance;
    private WorkoutRepository mWorkoutRepository;

    public static WorkoutContainer getInstance() {
        if (ourInstance == null) {
            WorkoutRepository repo = new InternalStorageWorkoutRepository();
            ourInstance = new WorkoutContainer(repo);
        }
        return ourInstance;
    }

    private WorkoutContainer(WorkoutRepository repo) {
        mWorkoutRepository = repo;
    }

    public ArrayList<Workout> getWorkouts(Context context) {
        return mWorkoutRepository.getAllWorkouts(context);
    }

    public void save(Context context, Workout workout) {
        mWorkoutRepository.saveWorkout(context, workout);
    }

    private Workout getDefaultWorkout() {
        Map<String, Integer> map = new Hashtable<>();
        return new Workout("", map, Workout.IntensityLevel.LOW, 3);
    }

    public Workout createDefaultWorkout(Context context) {
        Workout workout = getDefaultWorkout();
        save(context, workout);
        return workout;
    }

    private ArrayList<String> getIntensityLevelArray() {
        ArrayList<String> myArray = new ArrayList<>();

        for (Workout.IntensityLevel i : Workout.IntensityLevel.values()) {
            myArray.add(i.getLevel());
        }
        return myArray;
    }

    public ArrayAdapter<String> getWorkoutIntensityArrayAdapter(Context context) {
        return new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, getIntensityLevelArray());
    }

    public WorkoutAdapter getWorkoutAdapter(Context context) {
        return new WorkoutAdapter(context, getWorkouts(context));
    }

    public void delete(Context context, Workout workout) {
        mWorkoutRepository.deleteWorkout(context, workout);
    }

    public void addExercise(Context context, Workout workout, String exerciseName, Integer sets) {
        workout.addExerciseSet(exerciseName, sets);
        mWorkoutRepository.saveWorkout(context, workout);
    }

    public void removeExercise(Context context, Workout workout, Exercise exercise) {
        workout.removeExerciseSet(exercise.getName());
        mWorkoutRepository.saveWorkout(context, workout);
    }

    public void updateExercise(Context context, Workout workout, Exercise exercise, int i) {
        workout.updateExerciseSet(exercise.getName(), i);
        mWorkoutRepository.saveWorkout(context, workout);
    }

    public WorkoutExerciseAdapter getWorkoutExerciseAdapter(Context context, Workout workout) {
        return new WorkoutExerciseAdapter(context, getExercisesByWorkout(context, workout), workout);
    }

    private ArrayList<Exercise> getExercisesByWorkout(Context context, Workout workout) {
        ArrayList<Exercise> exercises = new ArrayList<>();
        ExerciseContainer exerciseContainer = ExerciseContainer.getInstance();
        for(Map.Entry<String, Integer> n:workout.getExerciseSets().entrySet()){
            Exercise e = exerciseContainer.getExerciseByName(context, n.getKey());
            if (e != null){
                exercises.add(e);
            }

        }
        return exercises;
    }

    public Workout getWorkoutByName(Context context, String workoutName){
        return mWorkoutRepository.getWorkout(context, workoutName);
    }

    public static void DestroyWorkoutContainer() {
        if (ourInstance != null) {
            ourInstance = null;
        }
    }

}
