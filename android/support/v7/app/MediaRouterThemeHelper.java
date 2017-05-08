// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.content.res.TypedArray;
import android.util.TypedValue;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.appcompat.R$attr;
import android.content.Context;

final class MediaRouterThemeHelper
{
    public static int getControllerColor(final Context context, final int n) {
        if (ColorUtils.calculateContrast(-1, getThemeColor(context, n, R$attr.colorPrimary)) >= 3.0) {
            return -1;
        }
        return -570425344;
    }
    
    public static float getDisabledAlpha(final Context context) {
        final TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(16842803, typedValue, true)) {
            return typedValue.getFloat();
        }
        return 0.5f;
    }
    
    private static int getThemeColor(final Context context, int color, final int n) {
        if (color != 0) {
            final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(color, new int[] { n });
            color = obtainStyledAttributes.getColor(0, 0);
            obtainStyledAttributes.recycle();
            if (color != 0) {
                return color;
            }
        }
        final TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(n, typedValue, true);
        if (typedValue.resourceId != 0) {
            return context.getResources().getColor(typedValue.resourceId);
        }
        return typedValue.data;
    }
}
