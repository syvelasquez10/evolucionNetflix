// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.graphics.drawable.Drawable;
import android.widget.TextView;
import android.os.Build$VERSION;

public class TextViewCompat
{
    static final TextViewCompat$TextViewCompatImpl IMPL;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 18) {
            IMPL = new TextViewCompat$JbMr2TextViewCompatImpl();
            return;
        }
        if (sdk_INT >= 17) {
            IMPL = new TextViewCompat$JbMr1TextViewCompatImpl();
            return;
        }
        IMPL = new TextViewCompat$BaseTextViewCompatImpl();
    }
    
    public static void setCompoundDrawablesRelative(final TextView textView, final Drawable drawable, final Drawable drawable2, final Drawable drawable3, final Drawable drawable4) {
        TextViewCompat.IMPL.setCompoundDrawablesRelative(textView, drawable, drawable2, drawable3, drawable4);
    }
}
