package edu.uco.schambers4.octane.Models.Workout;

import java.io.Serializable;

public class ExerciseMeasure implements Serializable{

    private Integer mForce;
    private Integer mMeasure;
    private Unit mForceUnits;
    private Unit mMeasureUnits;

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

    public Unit getForceUnits() {
        return mForceUnits;
    }

    public Unit getMeasureUnits() {
        return mMeasureUnits;
    }

    public void setForceUnits(Unit forceUnits) {
        mForceUnits = forceUnits;
    }

    public void setMeasureUnits(Unit forceUnits) {
        mMeasureUnits = forceUnits;
    }

    public enum UnitKind {WEIGHT, REPS, DISTANCE, TIME, RATE}

    public enum UnitSystem {IMPERIAL, METRIC, UNIVERSAL}

    public enum Unit {

        BODYWEIGHT(UnitKind.WEIGHT, UnitSystem.UNIVERSAL,"Body Weight"),
        COUNT(UnitKind.REPS, UnitSystem.UNIVERSAL,"Count"),
        HOURS(UnitKind.TIME, UnitSystem.UNIVERSAL,"Hours"),
        MINUTES(UnitKind.TIME, UnitSystem.UNIVERSAL,"Minutes"),
        SECONDS(UnitKind.TIME, UnitSystem.UNIVERSAL,"Seconds"),
        LBS(UnitKind.WEIGHT, UnitSystem.IMPERIAL,"Lbs"),
        MILES(UnitKind.DISTANCE, UnitSystem.IMPERIAL,"Miles"),
        FEET(UnitKind.DISTANCE, UnitSystem.IMPERIAL,"Feet"),
        YARDS(UnitKind.DISTANCE, UnitSystem.IMPERIAL,"Yards"),
        KILOGRAMS(UnitKind.WEIGHT, UnitSystem.METRIC,"Kilograms"),
        KILOMETERS(UnitKind.DISTANCE, UnitSystem.METRIC,"Kilometers"),
        METERS(UnitKind.DISTANCE, UnitSystem.METRIC,"Meters"),
        MPH(UnitKind.RATE,UnitSystem.IMPERIAL,"MPH"),
        KPH(UnitKind.RATE,UnitSystem.IMPERIAL,"KPH")
        ;

        private final UnitKind mUnitKind;
        private final UnitSystem mUnitSystem;
        private final String mUnitName;

        Unit(UnitKind unitKind, UnitSystem unitSystem, String unitName) {
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