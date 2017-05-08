// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.generic;

import com.facebook.common.internal.Preconditions;
import java.util.Arrays;

public class RoundingParams
{
    private int mBorderColor;
    private float mBorderWidth;
    private float[] mCornersRadii;
    private int mOverlayColor;
    private float mPadding;
    private boolean mRoundAsCircle;
    private RoundingParams$RoundingMethod mRoundingMethod;
    
    public RoundingParams() {
        this.mRoundingMethod = RoundingParams$RoundingMethod.BITMAP_ONLY;
        this.mRoundAsCircle = false;
        this.mCornersRadii = null;
        this.mOverlayColor = 0;
        this.mBorderWidth = 0.0f;
        this.mBorderColor = 0;
        this.mPadding = 0.0f;
    }
    
    public static RoundingParams fromCornersRadius(final float cornersRadius) {
        return new RoundingParams().setCornersRadius(cornersRadius);
    }
    
    private float[] getOrCreateRoundedCornersRadii() {
        if (this.mCornersRadii == null) {
            this.mCornersRadii = new float[8];
        }
        return this.mCornersRadii;
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = false;
        boolean b2;
        if (this == o) {
            b2 = true;
        }
        else {
            b2 = b;
            if (o != null) {
                b2 = b;
                if (this.getClass() == o.getClass()) {
                    final RoundingParams roundingParams = (RoundingParams)o;
                    b2 = b;
                    if (this.mRoundAsCircle == roundingParams.mRoundAsCircle) {
                        b2 = b;
                        if (this.mOverlayColor == roundingParams.mOverlayColor) {
                            b2 = b;
                            if (Float.compare(roundingParams.mBorderWidth, this.mBorderWidth) == 0) {
                                b2 = b;
                                if (this.mBorderColor == roundingParams.mBorderColor) {
                                    b2 = b;
                                    if (Float.compare(roundingParams.mPadding, this.mPadding) == 0) {
                                        b2 = b;
                                        if (this.mRoundingMethod == roundingParams.mRoundingMethod) {
                                            return Arrays.equals(this.mCornersRadii, roundingParams.mCornersRadii);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return b2;
    }
    
    public int getBorderColor() {
        return this.mBorderColor;
    }
    
    public float getBorderWidth() {
        return this.mBorderWidth;
    }
    
    public float[] getCornersRadii() {
        return this.mCornersRadii;
    }
    
    public int getOverlayColor() {
        return this.mOverlayColor;
    }
    
    public float getPadding() {
        return this.mPadding;
    }
    
    public boolean getRoundAsCircle() {
        return this.mRoundAsCircle;
    }
    
    public RoundingParams$RoundingMethod getRoundingMethod() {
        return this.mRoundingMethod;
    }
    
    @Override
    public int hashCode() {
        int floatToIntBits = 0;
        int hashCode;
        if (this.mRoundingMethod != null) {
            hashCode = this.mRoundingMethod.hashCode();
        }
        else {
            hashCode = 0;
        }
        int n;
        if (this.mRoundAsCircle) {
            n = 1;
        }
        else {
            n = 0;
        }
        int hashCode2;
        if (this.mCornersRadii != null) {
            hashCode2 = Arrays.hashCode(this.mCornersRadii);
        }
        else {
            hashCode2 = 0;
        }
        final int mOverlayColor = this.mOverlayColor;
        int floatToIntBits2;
        if (this.mBorderWidth != 0.0f) {
            floatToIntBits2 = Float.floatToIntBits(this.mBorderWidth);
        }
        else {
            floatToIntBits2 = 0;
        }
        final int mBorderColor = this.mBorderColor;
        if (this.mPadding != 0.0f) {
            floatToIntBits = Float.floatToIntBits(this.mPadding);
        }
        return ((floatToIntBits2 + ((hashCode2 + (n + hashCode * 31) * 31) * 31 + mOverlayColor) * 31) * 31 + mBorderColor) * 31 + floatToIntBits;
    }
    
    public RoundingParams setBorder(final int mBorderColor, final float mBorderWidth) {
        Preconditions.checkArgument(mBorderWidth >= 0.0f, (Object)"the border width cannot be < 0");
        this.mBorderWidth = mBorderWidth;
        this.mBorderColor = mBorderColor;
        return this;
    }
    
    public RoundingParams setCornersRadii(final float n, final float n2, final float n3, final float n4) {
        final float[] orCreateRoundedCornersRadii = this.getOrCreateRoundedCornersRadii();
        orCreateRoundedCornersRadii[0] = (orCreateRoundedCornersRadii[1] = n);
        orCreateRoundedCornersRadii[2] = (orCreateRoundedCornersRadii[3] = n2);
        orCreateRoundedCornersRadii[4] = (orCreateRoundedCornersRadii[5] = n3);
        orCreateRoundedCornersRadii[6] = (orCreateRoundedCornersRadii[7] = n4);
        return this;
    }
    
    public RoundingParams setCornersRadius(final float n) {
        Arrays.fill(this.getOrCreateRoundedCornersRadii(), n);
        return this;
    }
    
    public RoundingParams setOverlayColor(final int mOverlayColor) {
        this.mOverlayColor = mOverlayColor;
        this.mRoundingMethod = RoundingParams$RoundingMethod.OVERLAY_COLOR;
        return this;
    }
    
    public RoundingParams setRoundingMethod(final RoundingParams$RoundingMethod mRoundingMethod) {
        this.mRoundingMethod = mRoundingMethod;
        return this;
    }
}
