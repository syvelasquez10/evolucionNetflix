// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.util.Log;
import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;
import java.lang.reflect.Field;
import android.annotation.TargetApi;

@TargetApi(9)
class CompoundButtonCompatGingerbread
{
    private static final String TAG = "CompoundButtonCompatGingerbread";
    private static Field sButtonDrawableField;
    private static boolean sButtonDrawableFieldFetched;
    
    static Drawable getButtonDrawable(final CompoundButton compoundButton) {
        while (true) {
            if (!CompoundButtonCompatGingerbread.sButtonDrawableFieldFetched) {
                while (true) {
                    try {
                        (CompoundButtonCompatGingerbread.sButtonDrawableField = CompoundButton.class.getDeclaredField("mButtonDrawable")).setAccessible(true);
                        CompoundButtonCompatGingerbread.sButtonDrawableFieldFetched = true;
                        if (CompoundButtonCompatGingerbread.sButtonDrawableField != null) {
                            final Field field = CompoundButtonCompatGingerbread.sButtonDrawableField;
                            final CompoundButton compoundButton2 = compoundButton;
                            final Object o = field.get(compoundButton2);
                            final Drawable drawable = (Drawable)o;
                            return drawable;
                        }
                        return null;
                    }
                    catch (NoSuchFieldException ex) {
                        Log.i("CompoundButtonCompatGingerbread", "Failed to retrieve mButtonDrawable field", (Throwable)ex);
                        continue;
                    }
                    break;
                }
                try {
                    final Field field = CompoundButtonCompatGingerbread.sButtonDrawableField;
                    final CompoundButton compoundButton2 = compoundButton;
                    final Object o = field.get(compoundButton2);
                    final Drawable drawable2;
                    final Drawable drawable = drawable2 = (Drawable)o;
                    return drawable2;
                }
                catch (IllegalAccessException ex2) {
                    Log.i("CompoundButtonCompatGingerbread", "Failed to get button drawable via reflection", (Throwable)ex2);
                    CompoundButtonCompatGingerbread.sButtonDrawableField = null;
                }
                return null;
            }
            continue;
        }
    }
    
    static ColorStateList getButtonTintList(final CompoundButton compoundButton) {
        if (compoundButton instanceof TintableCompoundButton) {
            return ((TintableCompoundButton)compoundButton).getSupportButtonTintList();
        }
        return null;
    }
    
    static PorterDuff$Mode getButtonTintMode(final CompoundButton compoundButton) {
        if (compoundButton instanceof TintableCompoundButton) {
            return ((TintableCompoundButton)compoundButton).getSupportButtonTintMode();
        }
        return null;
    }
    
    static void setButtonTintList(final CompoundButton compoundButton, final ColorStateList supportButtonTintList) {
        if (compoundButton instanceof TintableCompoundButton) {
            ((TintableCompoundButton)compoundButton).setSupportButtonTintList(supportButtonTintList);
        }
    }
    
    static void setButtonTintMode(final CompoundButton compoundButton, final PorterDuff$Mode supportButtonTintMode) {
        if (compoundButton instanceof TintableCompoundButton) {
            ((TintableCompoundButton)compoundButton).setSupportButtonTintMode(supportButtonTintMode);
        }
    }
}
