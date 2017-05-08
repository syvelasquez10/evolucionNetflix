// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.widget.TextView$BufferType;
import com.netflix.mediaclient.util.l10n.BidiMarker;
import com.netflix.android.widgetry.utils.UiUtils;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v7.widget.AppCompatTextView;

public class NetflixTextView extends AppCompatTextView
{
    public NetflixTextView(final Context context) {
        super(context);
    }
    
    public NetflixTextView(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    private CharSequence forceLayoutDirectionIfNeeded(final CharSequence charSequence) {
        if (charSequence == null || !UiUtils.isCurrentLocaleRTL()) {
            return charSequence;
        }
        return UiUtils.prependBidiMarkerIfRtl(charSequence.toString(), BidiMarker.FORCED_RTL);
    }
    
    public void setText(final CharSequence charSequence, final TextView$BufferType textView$BufferType) {
        super.setText(this.forceLayoutDirectionIfNeeded(charSequence), textView$BufferType);
    }
}
