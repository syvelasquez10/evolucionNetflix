// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import android.view.Display;
import android.os.Build$VERSION;
import com.facebook.infer.annotation.Assertions;
import android.view.WindowManager;
import android.content.Context;
import android.util.DisplayMetrics;

public class DisplayMetricsHolder
{
    private static DisplayMetrics sScreenDisplayMetrics;
    private static DisplayMetrics sWindowDisplayMetrics;
    
    public static DisplayMetrics getScreenDisplayMetrics() {
        return DisplayMetricsHolder.sScreenDisplayMetrics;
    }
    
    @Deprecated
    public static DisplayMetrics getWindowDisplayMetrics() {
        return DisplayMetricsHolder.sWindowDisplayMetrics;
    }
    
    public static void initDisplayMetrics(Context defaultDisplay) {
        final DisplayMetrics displayMetrics = ((Context)defaultDisplay).getResources().getDisplayMetrics();
        setWindowDisplayMetrics(displayMetrics);
        final DisplayMetrics screenDisplayMetrics = new DisplayMetrics();
        screenDisplayMetrics.setTo(displayMetrics);
        final WindowManager windowManager = (WindowManager)((Context)defaultDisplay).getSystemService("window");
        Assertions.assertNotNull(windowManager, "WindowManager is null!");
        defaultDisplay = (IllegalAccessException)windowManager.getDefaultDisplay();
        if (Build$VERSION.SDK_INT >= 17) {
            ((Display)defaultDisplay).getRealMetrics(screenDisplayMetrics);
        }
        else {
            try {
                final Method method = Display.class.getMethod("getRawHeight", (Class<?>[])new Class[0]);
                screenDisplayMetrics.widthPixels = (int)Display.class.getMethod("getRawWidth", (Class<?>[])new Class[0]).invoke(defaultDisplay, new Object[0]);
                screenDisplayMetrics.heightPixels = (int)method.invoke(defaultDisplay, new Object[0]);
            }
            catch (InvocationTargetException ex) {}
            catch (IllegalAccessException defaultDisplay) {
                goto Label_0131;
            }
            catch (NoSuchMethodException defaultDisplay) {
                goto Label_0131;
            }
        }
        setScreenDisplayMetrics(screenDisplayMetrics);
    }
    
    public static void initDisplayMetricsIfNotInitialized(final Context context) {
        if (getScreenDisplayMetrics() != null) {
            return;
        }
        initDisplayMetrics(context);
    }
    
    public static void setScreenDisplayMetrics(final DisplayMetrics sScreenDisplayMetrics) {
        DisplayMetricsHolder.sScreenDisplayMetrics = sScreenDisplayMetrics;
    }
    
    public static void setWindowDisplayMetrics(final DisplayMetrics sWindowDisplayMetrics) {
        DisplayMetricsHolder.sWindowDisplayMetrics = sWindowDisplayMetrics;
    }
}
