// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.utils;

public class FloatUtils
{
    private static final float EPSILON = 1.0E-5f;
    
    public static boolean floatsEqual(final float n, final float n2) {
        if (Float.isNaN(n) || Float.isNaN(n2)) {
            if (!Float.isNaN(n) || !Float.isNaN(n2)) {
                return false;
            }
        }
        else if (Math.abs(n2 - n) >= 1.0E-5f) {
            return false;
        }
        return true;
    }
}
