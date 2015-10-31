package edu.uco.schambers4.octane.Models.Workout;

public class ExerciseMeasure {

    private Integer mForce;
    private Integer mMeasure;
    private Units mForceUnits;
    private Units mMeasureUnits;

    public Integer getForce() {
        return mForce;
    }

    public void setForce(Integer force) {
        mForce = force;
    }

    public Integer getMeasure() {
        return mMeasure;
    }

    public void setMeasure(Integer measure) {
        mMeasure = measure;
    }

    public Units getForceUnits() {
        return mForceUnits;
    }

    public Units getMeasureUnits() {
        return mMeasureUnits;
    }

    public void setForceUnits(Units forceUnits) {
        mForceUnits = forceUnits;
    }

    public void setMeasureUnits(Units forceUnits) {
        mMeasureUnits = forceUnits;
    }

    public enum UnitKind {WEIGHT, REPS, DISTANCE, TIME}

    public enum UnitSystem {IMPERIAL, METRIC, UNIVERSAL}

    public enum Units {

        BODYWEIGHT(UnitKind.WEIGHT, UnitSystem.UNIVERSAL,"Body Weight"),
        COUNT(UnitKind.REPS, UnitSystem.UNIVERSAL,"Count"),
        HOURS(UnitKind.TIME, UnitSystem.UNIVERSAL,"Hours"),
        MINUTES(UnitKind.TIME, UnitSystem.UNIVERSAL,"Minutes"),
        SECONDS(UnitKind.TIME, UnitSystem.UNIVERSAL,"Seconds"),
        LBS(UnitKind.WEIGHT, UnitSystem.IMPERIAL,"Lbs"),
        MILES(UnitKind.DISTANCE, UnitSystem.IMPERIAL,"Miles"),
        FEET(UnitKind.DISTANCE, UnitSystem.IMPERIAL,"Feet"),
        YARDS(UnitKind.DISTANCE, UnitSystem.IMPERIAL,"Yards"),
        KILOGRAMS(UnitKind.WEIGHT, UnitSystem.METRIC,"kg"),
        KILOMETERS(UnitKind.DISTANCE, UnitSystem.METRIC,"km"),
        METERS(UnitKind.DISTANCE, UnitSystem.METRIC,"m");

        private final UnitKind mUnitKind;
        private final UnitSystem mUnitSystem;
        private final String mUnitName;

        Units(UnitKind unitKind, UnitSystem unitSystem, String unitName) {
            mUnitKind = unitKind;
            mUnitSystem = unitSystem;
            mUnitName = unitName;
        }

        public UnitSystem getUnitSystem() {
            return mUnitSystem;
        }

        public UnitKind getUnitKind() {
            return mUnitKind;
        }

        public String getUnitName(){
            return mUnitName;
        }

    }

}