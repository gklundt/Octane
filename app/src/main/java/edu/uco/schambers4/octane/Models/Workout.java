package edu.uco.schambers4.octane.Models;

import java.io.Serializable;
import java.util.Map;

public class Workout implements Serializable {
    private String mName;
    private Map<String, Integer> mExerciseSets;

    public Workout(String name, Map<String, Integer> exerciseSets) {
        mName = name;
        mExerciseSets = exerciseSets;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Map<String, Integer> getExerciseSets() {
        return mExerciseSets;
    }

    public void addExerciseSet(String exerciseName, Integer sets) {
        if (!mExerciseSets.containsKey(exerciseName)) {
            mExerciseSets.put(exerciseName, sets);
        }
    }

    public void updateExerciseSet(String exerciseName, Integer sets) {
        mExerciseSets.remove(exerciseName);
        mExerciseSets.put(exerciseName, sets);
    }

    public void removeExerciseSet(String exerciseName) {
        if (mExerciseSets.containsKey(exerciseName)) {
            mExerciseSets.remove(exerciseName);
        }
    }

}
