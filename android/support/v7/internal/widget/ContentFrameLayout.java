// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.graphics.Rect;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.FrameLayout;

public class ContentFrameLayout extends FrameLayout
{
    public ContentFrameLayout(final Context context) {
        this(context, null);
    }
    
    public ContentFrameLayout(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public ContentFrameLayout(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    public void dispatchFitSystemWindows(final Rect rect) {
        this.fitSystemWindows(rect);
    }
}
