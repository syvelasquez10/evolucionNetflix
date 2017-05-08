// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.slider;

import android.util.AttributeSet;
import android.content.Context;
import android.widget.SeekBar;

public class ReactSlider extends SeekBar
{
    private static int DEFAULT_TOTAL_STEPS;
    private double mMaxValue;
    private double mMinValue;
    private double mStep;
    private double mStepCalculated;
    private double mValue;
    
    static {
        ReactSlider.DEFAULT_TOTAL_STEPS = 128;
    }
    
    public ReactSlider(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mMinValue = 0.0;
        this.mMaxValue = 0.0;
        this.mValue = 0.0;
        this.mStep = 0.0;
        this.mStepCalculated = 0.0;
    }
    
    private double getStepValue() {
        if (this.mStep > 0.0) {
            return this.mStep;
        }
        return this.mStepCalculated;
    }
    
    private int getTotalSteps() {
        return (int)Math.ceil((this.mMaxValue - this.mMinValue) / this.getStepValue());
    }
    
    private void updateAll() {
        if (this.mStep == 0.0) {
            this.mStepCalculated = (this.mMaxValue - this.mMinValue) / ReactSlider.DEFAULT_TOTAL_STEPS;
        }
        this.setMax(this.getTotalSteps());
        this.updateValue();
    }
    
    private void updateValue() {
        this.setProgress((int)Math.round((this.mValue - this.mMinValue) / (this.mMaxValue - this.mMinValue) * this.getTotalSteps()));
    }
    
    void setMaxValue(final double mMaxValue) {
        this.mMaxValue = mMaxValue;
        this.updateAll();
    }
    
    void setMinValue(final double mMinValue) {
        this.mMinValue = mMinValue;
        this.updateAll();
    }
    
    void setStep(final double mStep) {
        this.mStep = mStep;
        this.updateAll();
    }
    
    void setValue(final double mValue) {
        this.mValue = mValue;
        this.updateValue();
    }
    
    public double toRealProgress(final int n) {
        if (n == this.getMax()) {
            return this.mMaxValue;
        }
        return n * this.getStepValue() + this.mMinValue;
    }
}
