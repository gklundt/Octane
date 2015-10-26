package edu.uco.schambers4.octane.Domain.Data;

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

    public class Intensity {
        private double mForce;
        private double mMeasure;

        public double getForce() {
            return mForce;
        }

        public double getMeasure() {
            return mMeasure;
        }

        public Intensity(double force, double measure) {
            mForce = force;
            mMeasure = measure;
        }

        public Intensity(int force, int measure) {
            mForce = (double) force / 100.0;
            mMeasure = (double) measure / 100.0;
        }
    }

    public enum MuscleGroup {ABS, ARMS, LEGS, CHEST, GLUTE, BACK, SHOULDERS}

    public enum ExerciseType {STRENGTH, ENDURANCE, FLEXIBILITY, BALANCE}

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
