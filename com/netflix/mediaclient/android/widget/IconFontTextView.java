// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

public class IconFontTextView extends TextView
{
    private static Typeface iconTypeface;
    
    public IconFontTextView(final Context context) {
        super(context);
        this.init();
    }
    
    public IconFontTextView(final Context context, final AttributeSet set) {
        super(context, set);
        this.init();
    }
    
    public IconFontTextView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.init();
    }
    
    private void init() {
        if (IconFontTextView.iconTypeface == null) {
            IconFontTextView.iconTypeface = Typeface.createFromAsset(this.getContext().getAssets(), "nf-icon.ttf");
        }
        this.setTypeface(IconFontTextView.iconTypeface);
    }
}
