// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.util.Log;
import android.graphics.drawable.DrawableContainer$DrawableContainerState;
import android.graphics.drawable.Drawable$ConstantState;
import android.graphics.drawable.DrawableContainer;
import java.lang.reflect.Method;

class DrawableUtils
{
    private static final String LOG_TAG = "DrawableUtils";
    private static Method sSetConstantStateMethod;
    private static boolean sSetConstantStateMethodFetched;
    
    static boolean setContainerConstantState(final DrawableContainer drawableContainer, final Drawable$ConstantState drawable$ConstantState) {
        return setContainerConstantStateV9(drawableContainer, drawable$ConstantState);
    }
    
    private static boolean setContainerConstantStateV9(final DrawableContainer drawableContainer, final Drawable$ConstantState drawable$ConstantState) {
        while (true) {
            if (!DrawableUtils.sSetConstantStateMethodFetched) {
                while (true) {
                    try {
                        (DrawableUtils.sSetConstantStateMethod = DrawableContainer.class.getDeclaredMethod("setConstantState", DrawableContainer$DrawableContainerState.class)).setAccessible(true);
                        DrawableUtils.sSetConstantStateMethodFetched = true;
                        if (DrawableUtils.sSetConstantStateMethod != null) {
                            final Method method = DrawableUtils.sSetConstantStateMethod;
                            final DrawableContainer drawableContainer2 = drawableContainer;
                            final int n = 1;
                            final Object[] array = new Object[n];
                            final int n2 = 0;
                            final Drawable$ConstantState drawable$ConstantState2 = drawable$ConstantState;
                            array[n2] = drawable$ConstantState2;
                            method.invoke(drawableContainer2, array);
                            return true;
                        }
                        return false;
                    }
                    catch (NoSuchMethodException ex) {
                        Log.e("DrawableUtils", "Could not fetch setConstantState(). Oh well.");
                        continue;
                    }
                    break;
                }
                try {
                    final Method method = DrawableUtils.sSetConstantStateMethod;
                    final DrawableContainer drawableContainer2 = drawableContainer;
                    final int n = 1;
                    final Object[] array = new Object[n];
                    final int n2 = 0;
                    final Drawable$ConstantState drawable$ConstantState2 = drawable$ConstantState;
                    array[n2] = drawable$ConstantState2;
                    method.invoke(drawableContainer2, array);
                    return true;
                }
                catch (Exception ex2) {
                    Log.e("DrawableUtils", "Could not invoke setConstantState(). Oh well.");
                }
                return false;
            }
            continue;
        }
    }
}
