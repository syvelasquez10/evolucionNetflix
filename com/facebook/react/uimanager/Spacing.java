// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import com.facebook.yoga.YogaConstants;

public class Spacing
{
    private static final int[] sFlagsMap;
    private float mDefaultValue;
    private boolean mHasAliasesSet;
    private final float[] mSpacing;
    private int mValueFlags;
    
    static {
        sFlagsMap = new int[] { 1, 2, 4, 8, 16, 32, 64, 128, 256 };
    }
    
    public Spacing() {
        this(0.0f);
    }
    
    public Spacing(final float mDefaultValue) {
        this.mSpacing = newFullSpacingArray();
        this.mValueFlags = 0;
        this.mDefaultValue = mDefaultValue;
    }
    
    private static float[] newFullSpacingArray() {
        return new float[] { Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN };
    }
    
    public float get(int n) {
        float mDefaultValue;
        if (n == 4 || n == 5) {
            mDefaultValue = Float.NaN;
        }
        else {
            mDefaultValue = this.mDefaultValue;
        }
        if (this.mValueFlags != 0) {
            if ((this.mValueFlags & Spacing.sFlagsMap[n]) != 0x0) {
                return this.mSpacing[n];
            }
            if (this.mHasAliasesSet) {
                if (n == 1 || n == 3) {
                    n = 7;
                }
                else {
                    n = 6;
                }
                if ((this.mValueFlags & Spacing.sFlagsMap[n]) != 0x0) {
                    return this.mSpacing[n];
                }
                if ((this.mValueFlags & Spacing.sFlagsMap[8]) != 0x0) {
                    return this.mSpacing[8];
                }
            }
        }
        return mDefaultValue;
    }
    
    public float getRaw(final int n) {
        return this.mSpacing[n];
    }
    
    public boolean set(final int n, final float n2) {
        boolean mHasAliasesSet = false;
        if (!FloatUtil.floatsEqual(this.mSpacing[n], n2)) {
            this.mSpacing[n] = n2;
            if (YogaConstants.isUndefined(n2)) {
                this.mValueFlags &= ~Spacing.sFlagsMap[n];
            }
            else {
                this.mValueFlags |= Spacing.sFlagsMap[n];
            }
            if ((this.mValueFlags & Spacing.sFlagsMap[8]) != 0x0 || (this.mValueFlags & Spacing.sFlagsMap[7]) != 0x0 || (this.mValueFlags & Spacing.sFlagsMap[6]) != 0x0) {
                mHasAliasesSet = true;
            }
            this.mHasAliasesSet = mHasAliasesSet;
            return true;
        }
        return false;
    }
}
