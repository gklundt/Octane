package edu.uco.schambers4.octane.Domain.Data;

public class ExerciseMeasure {

    private Integer mForce;
    private Integer mMeasure;
    private Units mUnits;

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
    }

}