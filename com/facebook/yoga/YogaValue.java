// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class YogaValue
{
    static final YogaValue UNDEFINED;
    static final YogaValue ZERO;
    public final YogaUnit unit;
    public final float value;
    
    static {
        UNDEFINED = new YogaValue(Float.NaN, YogaUnit.UNDEFINED);
        ZERO = new YogaValue(0.0f, YogaUnit.PIXEL);
    }
    
    YogaValue(final float n, final int n2) {
        this(n, YogaUnit.fromInt(n2));
    }
    
    public YogaValue(final float value, final YogaUnit unit) {
        this.value = value;
        this.unit = unit;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b2;
        final boolean b = b2 = false;
        if (o instanceof YogaValue) {
            final YogaValue yogaValue = (YogaValue)o;
            b2 = b;
            if (this.value == yogaValue.value) {
                b2 = b;
                if (this.unit == yogaValue.unit) {
                    b2 = true;
                }
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        return Float.floatToIntBits(this.value) + this.unit.intValue();
    }
}
