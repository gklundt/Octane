package edu.uco.schambers4.octane.Models.Workout;


public class Intensity {
    private Double mForce;
    private Double mMeasure;

    public Double getForce() {
        return mForce;
    }

    public Double getMeasure() {
        return mMeasure;
    }

    public Integer getForceInteger() {
        Double d = mForce * 100;
        return d.intValue();
    }

    public Integer getMeasureInteger() {
        Double d = mMeasure * 100;
        return d.intValue();
    }

    public Intensity(Double force, Double measure) {
        mForce = force;
        mMeasure = measure;
    }

    public Intensity(Integer force, Integer measure) {
        mForce = force.doubleValue() / 100.0;
        mMeasure = measure.doubleValue() / 100.0;
    }
}
