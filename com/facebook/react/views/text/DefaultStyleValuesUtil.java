// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.text;

import android.content.res.TypedArray;
import android.content.res.Resources$Theme;
import android.content.res.ColorStateList;
import android.content.Context;

public final class DefaultStyleValuesUtil
{
    private static ColorStateList getDefaultTextAttribute(Context obtainStyledAttributes, final int n) {
        final Resources$Theme theme = obtainStyledAttributes.getTheme();
        obtainStyledAttributes = null;
        try {
            return ((TypedArray)(obtainStyledAttributes = (Context)theme.obtainStyledAttributes(new int[] { n }))).getColorStateList(0);
        }
        finally {
            if (obtainStyledAttributes != null) {
                ((TypedArray)obtainStyledAttributes).recycle();
            }
        }
    }
    
    public static ColorStateList getDefaultTextColor(final Context context) {
        return getDefaultTextAttribute(context, 16842904);
    }
    
    public static int getDefaultTextColorHighlight(final Context context) {
        return getDefaultTextAttribute(context, 16842905).getDefaultColor();
    }
    
    public static ColorStateList getDefaultTextColorHint(final Context context) {
        return getDefaultTextAttribute(context, 16842906);
    }
}
