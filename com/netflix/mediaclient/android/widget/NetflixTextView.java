// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.widget.TextView$BufferType;
import com.netflix.mediaclient.util.l10n.BidiMarker;
import com.netflix.android.widgetry.utils.UiUtils;
import com.netflix.android.widgetry.R$dimen;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v7.widget.AppCompatTextView;

public class NetflixTextView extends AppCompatTextView
{
    public static final boolean COLOR_TEXT_IF_MODIFIED = false;
    public static final float MIN_LINE_HEIGHT = 1.1f;
    private static final String TAG = "NetflixTextView";
    
    public NetflixTextView(final Context context) {
        super(context);
    }
    
    public NetflixTextView(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    private void adjustLineHeightIfNeeded() {
        if (this.getLineCount() > 1) {
            final int dimensionPixelOffset = this.getResources().getDimensionPixelOffset(R$dimen.netflix_text_view_extra_line_height);
            if (dimensionPixelOffset != 0 && this.getLineSpacingMultiplier() < 1.1f && this.getLineSpacingExtra() < dimensionPixelOffset * 2) {
                this.setLineSpacing((float)dimensionPixelOffset, 1.1f);
            }
        }
    }
    
    private CharSequence forceLayoutDirectionIfNeeded(final CharSequence charSequence) {
        if (charSequence == null || !UiUtils.isCurrentLocaleRTL()) {
            return charSequence;
        }
        return UiUtils.prependBidiMarkerIfRtl(charSequence.toString(), BidiMarker.FORCED_RTL);
    }
    
    protected void onMeasure(final int n, final int n2) {
        super.onMeasure(n, n2);
        this.adjustLineHeightIfNeeded();
    }
    
    public void setText(final CharSequence charSequence, final TextView$BufferType textView$BufferType) {
        super.setText(this.forceLayoutDirectionIfNeeded(charSequence), textView$BufferType);
    }
}
