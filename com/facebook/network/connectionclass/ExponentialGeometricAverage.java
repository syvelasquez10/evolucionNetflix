// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.network.connectionclass;

class ExponentialGeometricAverage
{
    private int mCount;
    private final int mCutover;
    private final double mDecayConstant;
    private double mValue;
    
    public ExponentialGeometricAverage(final double mDecayConstant) {
        this.mValue = -1.0;
        this.mDecayConstant = mDecayConstant;
        int mCutover;
        if (mDecayConstant == 0.0) {
            mCutover = Integer.MAX_VALUE;
        }
        else {
            mCutover = (int)Math.ceil(1.0 / mDecayConstant);
        }
        this.mCutover = mCutover;
    }
    
    public void addMeasurement(final double mValue) {
        final double n = 1.0 - this.mDecayConstant;
        if (this.mCount > this.mCutover) {
            this.mValue = Math.exp(n * Math.log(this.mValue) + this.mDecayConstant * Math.log(mValue));
        }
        else if (this.mCount > 0) {
            final double n2 = n * this.mCount / (this.mCount + 1.0);
            this.mValue = Math.exp(n2 * Math.log(this.mValue) + (1.0 - n2) * Math.log(mValue));
        }
        else {
            this.mValue = mValue;
        }
        ++this.mCount;
    }
    
    public double getAverage() {
        return this.mValue;
    }
}
