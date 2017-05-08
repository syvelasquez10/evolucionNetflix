// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.utils;

import android.content.res.ColorStateList;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.l10n.BidiMarker;
import android.text.TextUtils;
import java.util.Locale;
import android.content.Context;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.View;

public class UiUtils
{
    public static int getMarginBottom(final View view) {
        if (view != null && view.getLayoutParams() instanceof ViewGroup$MarginLayoutParams) {
            return ((ViewGroup$MarginLayoutParams)view.getLayoutParams()).topMargin;
        }
        return 0;
    }
    
    public static int getMarginEnd(final View view) {
        if (view != null && view.getLayoutParams() instanceof ViewGroup$MarginLayoutParams) {
            return ((ViewGroup$MarginLayoutParams)view.getLayoutParams()).getMarginEnd();
        }
        return 0;
    }
    
    public static int getMarginStart(final View view) {
        if (view != null && view.getLayoutParams() instanceof ViewGroup$MarginLayoutParams) {
            return ((ViewGroup$MarginLayoutParams)view.getLayoutParams()).getMarginStart();
        }
        return 0;
    }
    
    public static int getMarginTop(final View view) {
        if (view != null && view.getLayoutParams() instanceof ViewGroup$MarginLayoutParams) {
            return ((ViewGroup$MarginLayoutParams)view.getLayoutParams()).topMargin;
        }
        return 0;
    }
    
    public static int getStatusBarHeight(final Context context) {
        int dimensionPixelSize = 0;
        final int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            dimensionPixelSize = context.getResources().getDimensionPixelSize(identifier);
        }
        return dimensionPixelSize;
    }
    
    public static boolean isCurrentLocaleRTL() {
        return isLocaleRTL(Locale.getDefault());
    }
    
    public static boolean isLocaleRTL(final Locale locale) {
        return TextUtils.getLayoutDirectionFromLocale(locale) == 1;
    }
    
    public static String prependBidiMarkerIfRtl(final String s, final BidiMarker bidiMarker) {
        String string = s;
        if (s != null) {
            string = s;
            if (isCurrentLocaleRTL()) {
                string = bidiMarker.getMarker() + s;
            }
        }
        return string;
    }
    
    public static void setRtlLayoutDirectionIfApplicable(final View view) {
        if (view != null && isCurrentLocaleRTL()) {
            view.setLayoutDirection(1);
        }
    }
    
    public static Drawable tintAndGet(Drawable wrap, final int n) {
        if (wrap == null) {
            return null;
        }
        wrap = DrawableCompat.wrap(wrap.mutate());
        DrawableCompat.setTint(wrap, n);
        return wrap;
    }
    
    public static Drawable tintAndGet(Drawable drawable, final ColorStateList list, final int n) {
        if (drawable == null) {
            drawable = null;
        }
        else {
            final Drawable wrap = DrawableCompat.wrap(drawable.mutate());
            if (n > 0) {
                wrap.setBounds(0, 0, n, n);
            }
            drawable = wrap;
            if (list != null) {
                DrawableCompat.setTint(wrap, list.getDefaultColor());
                return wrap;
            }
        }
        return drawable;
    }
}
