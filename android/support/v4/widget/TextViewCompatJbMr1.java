// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

class TextViewCompatJbMr1
{
    public static void setCompoundDrawablesRelative(final TextView textView, Drawable drawable, final Drawable drawable2, final Drawable drawable3, final Drawable drawable4) {
        boolean b;
        if (textView.getLayoutDirection() == 1) {
            b = true;
        }
        else {
            b = false;
        }
        Drawable drawable5;
        if (b) {
            drawable5 = drawable3;
        }
        else {
            drawable5 = drawable;
        }
        if (!b) {
            drawable = drawable3;
        }
        textView.setCompoundDrawables(drawable5, drawable2, drawable, drawable4);
    }
}
