// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.text.style.UnderlineSpan;
import android.text.SpannableString;
import android.widget.TextView$BufferType;
import android.content.res.TypedArray;
import com.netflix.mediaclient.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.TextView;

public class UnderlineTextView extends TextView
{
    private boolean mUnderline;
    
    public UnderlineTextView(final Context context) {
        super(context);
    }
    
    public UnderlineTextView(final Context context, final AttributeSet set) {
        super(context, set);
        this.init(set);
    }
    
    public UnderlineTextView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.init(set);
    }
    
    private void init(final AttributeSet set) {
        final TypedArray obtainStyledAttributes = this.getContext().obtainStyledAttributes(set, R$styleable.UnderlineTextView);
        if (obtainStyledAttributes == null) {
            return;
        }
        for (int i = 0; i < obtainStyledAttributes.getIndexCount(); ++i) {
            final int index = obtainStyledAttributes.getIndex(i);
            switch (index) {
                case 0: {
                    this.mUnderline = obtainStyledAttributes.getBoolean(index, false);
                    break;
                }
            }
        }
        obtainStyledAttributes.recycle();
    }
    
    public boolean isUnderline() {
        return this.mUnderline;
    }
    
    public void setText(final CharSequence charSequence, final TextView$BufferType textView$BufferType) {
        if (charSequence != null && this.mUnderline) {
            final SpannableString spannableString = new SpannableString(charSequence);
            spannableString.setSpan((Object)new UnderlineSpan(), 0, charSequence.length(), 0);
            super.setText((CharSequence)spannableString, TextView$BufferType.SPANNABLE);
            return;
        }
        super.setText(charSequence, textView$BufferType);
    }
    
    public void setUnderline(final boolean mUnderline) {
        this.mUnderline = mUnderline;
    }
}
