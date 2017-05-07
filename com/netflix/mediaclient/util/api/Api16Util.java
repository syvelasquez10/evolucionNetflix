// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.api;

import com.netflix.mediaclient.util.AndroidUtils;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.ViewTreeObserver;
import android.annotation.TargetApi;

@TargetApi(16)
public class Api16Util
{
    public static void removeOnGlobalLayoutListener(final ViewTreeObserver viewTreeObserver, final ViewTreeObserver$OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener) {
        viewTreeObserver.removeOnGlobalLayoutListener(viewTreeObserver$OnGlobalLayoutListener);
    }
    
    public static void setBackgroundDrawableCompat(final View view, final Drawable drawable) {
        if (AndroidUtils.getAndroidVersion() >= 16) {
            view.setBackground(drawable);
            return;
        }
        view.setBackgroundDrawable(drawable);
    }
}
