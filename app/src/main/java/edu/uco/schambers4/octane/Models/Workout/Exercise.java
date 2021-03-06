package edu.uco.schambers4.octane.Models.Workout;

import java.io.Serializable;

public class Exercise implements Serializable {
    private String mName;
    private String mDescription;
    private MuscleGroup mMuscleGroup;
    private ExerciseType mExerciseType;
    private ExerciseMeasure mMaxIntensityExerciseMeasure;
    private Intensity mLowIntensity;
    private Intensity mMedIntensity;
    private Intensity mHighIntensity;

    public enum MuscleGroup {
        ABS("Abdominals"),
        ARMS("Arms"),
        LEGS("Legs"),
        CHEST("Chest"),
        GLUTE("Glutes"),
        BACK("Back"),
        SHOULDERS("Shoulders"),
        FULLBODY("Full Body"),
        CORE("Core");

        private final String mGroupName;

        MuscleGroup(String groupName) {
            mGroupName = groupName;
        }

        public String getGroupName() {
            return mGroupName;
        }

    }

    public enum ExerciseType {
        STRENGTH("Strength"),
        ENDURANCE("Endurance"),
        FLEXIBILITY("Flexibility"),
        BALANCE("Balance");

        private final String mExerciseTypeName;

        ExerciseType(String exerciseTypeName) {
            mExerciseTypeName = exerciseTypeName;
        }

        public String getExerciseTypeName() {
            return mExerciseTypeName;
        }
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public MuscleGroup getMuscleGroup() {
        return mMuscleGroup;
    }

    public void setMuscleGroup(MuscleGroup muscleGroup) {
        mMuscleGroup = muscleGroup;
    }

    public ExerciseType getExerciseType() {
        return mExerciseType;
    }

    public void setExerciseType(ExerciseType exerciseType) {
        mExerciseType = exerciseType;
    }

    public ExerciseMeasure getMaxIntensityExerciseMeasure() {
        return mMaxIntensityExerciseMeasure;
    }

    public void setMaxIntensityExerciseMeasure(ExerciseMeasure maxIntensityExerciseMeasure) {
        mMaxIntensityExerciseMeasure = maxIntensityExerciseMeasure;
    }

    public Intensity getLowIntensity() {
        return mLowIntensity;
    }

    public void setLowIntensity(Intensity lowIntensity) {
        mLowIntensity = lowIntensity;
    }

    public Intensity getMedIntensity() {
        return mMedIntensity;
    }

    public void setMedIntensity(Intensity medIntensity) {
        mMedIntensity = medIntensity;
    }

    public Intensity getHighIntensity() {
        return mHighIntensity;
    }

    public void setHighIntensity(Intensity highIntensity) {
        mHighIntensity = highIntensity;
    }
}
