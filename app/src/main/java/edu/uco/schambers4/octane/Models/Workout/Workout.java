package edu.uco.schambers4.octane.Models.Workout;

import java.io.Serializable;
import java.util.Map;

import edu.uco.schambers4.octane.Models.GeneralInterfaces.INameable;

public class Workout implements Serializable, INameable
{
    private String mName;
    private Map<String, Integer> mExerciseSets;
    private Integer mCalories;
    private IntensityLevel mIntensityLevel;

    public IntensityLevel getIntensityLevel() {
        return mIntensityLevel;
    }

    public void setIntensityLevel(IntensityLevel intensityLevel) {
        mIntensityLevel = intensityLevel;
    }

    public Integer getCalories() {
        return mCalories;
    }

    public void setCalories(Integer calories) {
        mCalories = calories;
    }

    public enum IntensityLevel{
        HIGH("High"),
        MEDIUM("Medium"),
        LOW("Low");

        private final String mLevel;

        IntensityLevel(String level){
            mLevel = level;
        }

        public String getLevel(){
            return mLevel;
        }

    }

    public Workout(String name, Map<String, Integer> exerciseSets, IntensityLevel intensityLevel, Integer calories) {
        mName = name;
        mExerciseSets = exerciseSets;
        mIntensityLevel = intensityLevel;
        mCalories = calories;
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
