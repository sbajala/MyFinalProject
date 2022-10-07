package com.example.myfinalproject.utils;

import android.view.animation.Interpolator;

/** Used for button bounce animation */
public class BounceInterpolator implements Interpolator {

    private double amplitude = 1;
    private double frequency = 10;

    public BounceInterpolator(double amplitude, double frequency) {
        this.amplitude = amplitude;
        this.frequency = frequency;
    }

    @Override
    public float getInterpolation(float time) {
        return (float)(-1*Math.pow(Math.E, -time/amplitude)*Math.cos(frequency*time)+1);
    }
}
