// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.IconFontGlyph;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

public class IconFontTextView extends TextView
{
    private static final String TAG = "IconFontTextView";
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
            IconFontTextView.iconTypeface = Typeface.createFromAsset(this.getContext().getAssets(), "nf-icon.otf");
        }
        this.setTypeface(IconFontTextView.iconTypeface);
    }
    
    public void setToIcon(final IconFontGlyph iconFontGlyph, final int n) {
        final int dimensionPixelOffset = this.getResources().getDimensionPixelOffset(n);
        if (Log.isLoggable()) {
            Log.v("IconFontTextView", "Setting icon char to: " + iconFontGlyph + ", unicode: " + StringUtils.toUnicode(iconFontGlyph.getUnicodeChar()) + ", dimenId: " + n);
            Log.v("IconFontTextView", "Setting icon text size to: " + dimensionPixelOffset);
        }
        this.setText((CharSequence)String.valueOf(iconFontGlyph.getUnicodeChar()));
        this.setTextSize((float)dimensionPixelOffset);
    }
}
