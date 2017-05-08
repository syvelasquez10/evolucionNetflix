// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.widget.TextView;

class TextViewCompat$JbTextViewCompatImpl extends TextViewCompat$BaseTextViewCompatImpl
{
    @Override
    public int getMaxLines(final TextView textView) {
        return TextViewCompatJb.getMaxLines(textView);
    }
    
    @Override
    public int getMinLines(final TextView textView) {
        return TextViewCompatJb.getMinLines(textView);
    }
}
