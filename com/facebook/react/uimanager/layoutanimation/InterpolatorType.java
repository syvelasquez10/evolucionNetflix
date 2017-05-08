// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager.layoutanimation;

enum InterpolatorType
{
    EASE_IN("easeIn"), 
    EASE_IN_EASE_OUT("easeInEaseOut"), 
    EASE_OUT("easeOut"), 
    LINEAR("linear"), 
    SPRING("spring");
    
    private final String mName;
    
    private InterpolatorType(final String mName) {
        this.mName = mName;
    }
    
    public static InterpolatorType fromString(final String s) {
        final InterpolatorType[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final InterpolatorType interpolatorType = values[i];
            if (interpolatorType.toString().equalsIgnoreCase(s)) {
                return interpolatorType;
            }
        }
        throw new IllegalArgumentException("Unsupported interpolation type : " + s);
    }
    
    @Override
    public String toString() {
        return this.mName;
    }
}
