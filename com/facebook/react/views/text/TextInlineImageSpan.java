// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.text;

import android.graphics.drawable.Drawable;
import android.widget.TextView;
import android.text.Spannable;
import android.text.style.ReplacementSpan;

public abstract class TextInlineImageSpan extends ReplacementSpan
{
    public static void possiblyUpdateInlineImageSpans(final Spannable spannable, final TextView textView) {
        int i = 0;
        for (TextInlineImageSpan[] array = (TextInlineImageSpan[])spannable.getSpans(0, spannable.length(), (Class)TextInlineImageSpan.class); i < array.length; ++i) {
            final TextInlineImageSpan textInlineImageSpan = array[i];
            textInlineImageSpan.onAttachedToWindow();
            textInlineImageSpan.setTextView(textView);
        }
    }
    
    public abstract Drawable getDrawable();
    
    public abstract int getHeight();
    
    public abstract void onAttachedToWindow();
    
    public abstract void onDetachedFromWindow();
    
    public abstract void onFinishTemporaryDetach();
    
    public abstract void onStartTemporaryDetach();
    
    public abstract void setTextView(final TextView p0);
}
