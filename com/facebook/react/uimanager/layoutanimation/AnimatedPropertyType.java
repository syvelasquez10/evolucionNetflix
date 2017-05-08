// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager.layoutanimation;

enum AnimatedPropertyType
{
    OPACITY("opacity"), 
    SCALE_XY("scaleXY");
    
    private final String mName;
    
    private AnimatedPropertyType(final String mName) {
        this.mName = mName;
    }
    
    public static AnimatedPropertyType fromString(final String s) {
        final AnimatedPropertyType[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final AnimatedPropertyType animatedPropertyType = values[i];
            if (animatedPropertyType.toString().equalsIgnoreCase(s)) {
                return animatedPropertyType;
            }
        }
        throw new IllegalArgumentException("Unsupported animated property : " + s);
    }
    
    @Override
    public String toString() {
        return this.mName;
    }
}
