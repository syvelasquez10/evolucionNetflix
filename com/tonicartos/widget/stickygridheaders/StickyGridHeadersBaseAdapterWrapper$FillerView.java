// 
// Decompiled by Procyon v0.5.30
// 

package com.tonicartos.widget.stickygridheaders;

import android.view.View$MeasureSpec;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View;

public class StickyGridHeadersBaseAdapterWrapper$FillerView extends View
{
    private View mMeasureTarget;
    final /* synthetic */ StickyGridHeadersBaseAdapterWrapper this$0;
    
    public StickyGridHeadersBaseAdapterWrapper$FillerView(final StickyGridHeadersBaseAdapterWrapper this$0, final Context context) {
        this.this$0 = this$0;
        super(context);
    }
    
    public StickyGridHeadersBaseAdapterWrapper$FillerView(final StickyGridHeadersBaseAdapterWrapper this$0, final Context context, final AttributeSet set) {
        this.this$0 = this$0;
        super(context, set);
    }
    
    public StickyGridHeadersBaseAdapterWrapper$FillerView(final StickyGridHeadersBaseAdapterWrapper this$0, final Context context, final AttributeSet set, final int n) {
        this.this$0 = this$0;
        super(context, set, n);
    }
    
    protected void onMeasure(final int n, final int n2) {
        super.onMeasure(n, View$MeasureSpec.makeMeasureSpec(this.mMeasureTarget.getMeasuredHeight(), 1073741824));
    }
    
    public void setMeasureTarget(final View mMeasureTarget) {
        this.mMeasureTarget = mMeasureTarget;
    }
}
