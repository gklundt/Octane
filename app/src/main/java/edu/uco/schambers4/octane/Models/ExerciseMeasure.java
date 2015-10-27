package edu.uco.schambers4.octane.Models;

public class ExerciseMeasure {

    private Integer mForce;
    private Integer mMeasure;
    private Units mUnits;

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

    public Units getUnits() {
        return mUnits;
    }

    public void setUnits(Units units) {
        mUnits = units;
    }

    public enum UnitKind {WEIGHT, REPS, DISTANCE, TIME}

    public enum UnitSystem {IMPERIAL, METRIC, UNIVERSAL}

    public enum Units {

        BODYWEIGHT(UnitKind.WEIGHT, UnitSystem.UNIVERSAL),
        COUNT(UnitKind.REPS, UnitSystem.UNIVERSAL),
        HOURS(UnitKind.TIME, UnitSystem.UNIVERSAL),
        MINUTES(UnitKind.TIME, UnitSystem.UNIVERSAL),
        SECONDS(UnitKind.TIME, UnitSystem.UNIVERSAL),
        LBS(UnitKind.WEIGHT, UnitSystem.IMPERIAL),
        MILES(UnitKind.DISTANCE, UnitSystem.IMPERIAL),
        FEET(UnitKind.DISTANCE, UnitSystem.IMPERIAL),
        YARDS(UnitKind.DISTANCE, UnitSystem.IMPERIAL),
        KILOGRAMS(UnitKind.WEIGHT, UnitSystem.METRIC),
        KILOMETERS(UnitKind.DISTANCE, UnitSystem.METRIC),
        METERS(UnitKind.DISTANCE, UnitSystem.METRIC);

        private final UnitKind mUnitKind;
        private final UnitSystem mUnitSystem;

        Units(UnitKind unitKind, UnitSystem unitSystem) {
            this.mUnitKind = unitKind;
            this.mUnitSystem = unitSystem;
        }

        private UnitSystem UnitSystem() {
            return mUnitSystem;
        }

        private UnitKind UnitKind() {
            return mUnitKind;
        }

    }

}