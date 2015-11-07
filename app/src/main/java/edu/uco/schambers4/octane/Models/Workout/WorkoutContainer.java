package edu.uco.schambers4.octane.Models.Workout;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import edu.uco.schambers4.octane.DataAccessObjects.Exercise.ExerciseRepository;
import edu.uco.schambers4.octane.DataAccessObjects.Workout.InternalStorageWorkoutRepository;
import edu.uco.schambers4.octane.DataAccessObjects.Workout.WorkoutRepository;

public class WorkoutContainer {

    private static WorkoutContainer ourInstance;
    private ArrayList<Workout> mWorkouts;
    private WorkoutRepository mWorkoutRepository;
    private ExerciseRepository mExerciseRepository;

    public static WorkoutContainer getInstance(ExerciseRepository exerciseRepository) {
        if (ourInstance == null) {
            WorkoutRepository repo = new InternalStorageWorkoutRepository();
            ourInstance = new WorkoutContainer(repo, exerciseRepository);
        }
        return ourInstance;
    }

    public static void DestroyWorkoutContainer() {
        if (ourInstance != null) {
            ourInstance = null;
        }
    }

    private WorkoutContainer(WorkoutRepository repo, ExerciseRepository exerciseRepository) {
        mWorkoutRepository = repo;
        mExerciseRepository = exerciseRepository;

    }


    public ArrayList<Workout> getWorkouts(Context context) {
        mWorkouts = mWorkoutRepository.getAllWorkouts(context);
        return mWorkouts;
    }

    public void save(Context context) {
        mWorkoutRepository.saveWorkouts(context, mWorkouts);
    }

    public void save(Context context, Workout workout) {
        mWorkoutRepository.saveWorkout(context, workout);
    }

    private Workout getDefaultWorkout() {
        Map<String, Integer> map = new Hashtable<>();
        Workout workout = new Workout("NewWorkout", map, Workout.IntensityLevel.LOW, 3);
        return workout;
    }

    public int createDefaultWorkout(Context context) {
        Workout workout = getDefaultWorkout();
        save(context, workout);
        int i = mWorkouts.indexOf(workout);
        return i;
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

    public ArrayList<Exercise> getExercises(Context context, int index) {
        ArrayList<Exercise> ae = new ArrayList<>();


        for (Map.Entry<String, Integer> h : mWorkouts.get(index).getExerciseSets().entrySet()) {
            Exercise exercise = mExerciseRepository.getExerciseByName(context, h.getKey());
            if (exercise != null) {
                ae.add(exercise);
            }
        }

        return ae;
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
        workout.updateExerciseSet(exercise.getName(),i);
        mWorkoutRepository.saveWorkout(context,workout);
    }
}
