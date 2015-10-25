package Domain;

public class Exercise {
    private String mName;
    private String mDescription;
    private MuscleGroup mMuscleGroup;
    private ExerciseType mExerciseType;
    private ExerciseMeasure mMaxIntensityExerciseMeasure;
    private Intensity mLowIntensity;
    private Intensity mMedIntensity;
    private Intensity mHighIntensity;

    private class Intensity{
        private double mForce;
        private double mMeasure;

        public double getForce() {
            return mForce;
        }

        public double getMeasure() {
            return mMeasure;
        }

        public Intensity(double force, double measure){
            mForce = force;
            mMeasure = measure;
        }

        public Intensity(int force, int measure){
            mForce = (double)force / 100.0;
            mMeasure = (double)measure / 100.0;
        }
    }

    public enum MuscleGroup {ABS, ARMS, LEGS, CHEST, GLUTE, BACK, SHOULDERS}

    public enum ExerciseType {STRENGTH, ENDURANCE, FLEXIBILITY, BALANCE}


}
