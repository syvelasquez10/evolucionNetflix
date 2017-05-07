// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.util.SubtitleUtils;
import com.netflix.mediaclient.util.StringUtils;

public class DoubleLength
{
    public static final DoubleLength SIMPLE_SDH_EXTENT;
    public static final DoubleLength ZERO;
    private float mFirst;
    private float mSecond;
    
    static {
        ZERO = new DoubleLength(0.0f, 0.0f);
        SIMPLE_SDH_EXTENT = new DoubleLength(10.0f, 10.0f);
    }
    
    private DoubleLength() {
    }
    
    private DoubleLength(final float mFirst, final float mSecond) {
        this.mFirst = mFirst;
        this.mSecond = mSecond;
    }
    
    public static boolean canUse(final DoubleLength doubleLength) {
        return doubleLength != null && doubleLength.isValid();
    }
    
    public static DoubleLength createInstance(final String s, final CellResolution cellResolution) {
        if (!StringUtils.isEmpty(s)) {
            final String[] safeSplit = StringUtils.safeSplit(s, " ");
            if (safeSplit != null && safeSplit.length >= 2) {
                int widthCount = 0;
                if (cellResolution != null) {
                    widthCount = cellResolution.getWidthCount();
                }
                final Float margin = SubtitleUtils.parseMargin(safeSplit[0], widthCount);
                final Float margin2 = SubtitleUtils.parseMargin(safeSplit[1], widthCount);
                if (margin != null && margin2 != null) {
                    return new DoubleLength(margin, margin2);
                }
            }
        }
        return null;
    }
    
    private boolean valid(final float n) {
        return n > 0.0f && n <= 1.0f;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof DoubleLength)) {
                return false;
            }
            final DoubleLength doubleLength = (DoubleLength)o;
            if (Float.floatToIntBits(this.mFirst) != Float.floatToIntBits(doubleLength.mFirst)) {
                return false;
            }
            if (Float.floatToIntBits(this.mSecond) != Float.floatToIntBits(doubleLength.mSecond)) {
                return false;
            }
        }
        return true;
    }
    
    public float getFirstLength() {
        return this.mFirst;
    }
    
    public float getSecondLength() {
        return this.mSecond;
    }
    
    @Override
    public int hashCode() {
        return (Float.floatToIntBits(this.mFirst) + 31) * 31 + Float.floatToIntBits(this.mSecond);
    }
    
    public boolean isValid() {
        return this.valid(this.mFirst) && this.valid(this.mSecond);
    }
    
    @Override
    public String toString() {
        return "DoubleLength [mFirst=" + this.mFirst + ", mSecond=" + this.mSecond + "]";
    }
}
