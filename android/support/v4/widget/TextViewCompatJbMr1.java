// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

class TextViewCompatJbMr1
{
    static Drawable[] getCompoundDrawablesRelative(final TextView textView) {
        return textView.getCompoundDrawablesRelative();
    }
    
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
    
    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(final TextView textView, int n, final int n2, final int n3, final int n4) {
        boolean b;
        if (textView.getLayoutDirection() == 1) {
            b = true;
        }
        else {
            b = false;
        }
        int n5;
        if (b) {
            n5 = n3;
        }
        else {
            n5 = n;
        }
        if (!b) {
            n = n3;
        }
        textView.setCompoundDrawablesWithIntrinsicBounds(n5, n2, n, n4);
    }
    
    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(final TextView textView, Drawable drawable, final Drawable drawable2, final Drawable drawable3, final Drawable drawable4) {
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
        textView.setCompoundDrawablesWithIntrinsicBounds(drawable5, drawable2, drawable, drawable4);
    }
}
