// 
// Decompiled by Procyon v0.5.30
// 

package com.tonicartos.widget.stickygridheaders;

import android.view.ViewGroup;
import android.database.DataSetObserver;
import android.widget.BaseAdapter;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.content.Context;
import android.widget.FrameLayout;

public class StickyGridHeadersBaseAdapterWrapper$HeaderFillerView extends FrameLayout
{
    private int mHeaderId;
    final /* synthetic */ StickyGridHeadersBaseAdapterWrapper this$0;
    
    public StickyGridHeadersBaseAdapterWrapper$HeaderFillerView(final StickyGridHeadersBaseAdapterWrapper this$0, final Context context) {
        this.this$0 = this$0;
        super(context);
    }
    
    protected FrameLayout$LayoutParams generateDefaultLayoutParams() {
        return new FrameLayout$LayoutParams(-1, -1);
    }
    
    public int getHeaderId() {
        return this.mHeaderId;
    }
    
    protected void onMeasure(final int n, int childMeasureSpec) {
        final View view = (View)this.getTag();
        Object layoutParams;
        if ((layoutParams = view.getLayoutParams()) == null) {
            layoutParams = this.generateDefaultLayoutParams();
            view.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        }
        if (view.getVisibility() != 8) {
            childMeasureSpec = getChildMeasureSpec(View$MeasureSpec.makeMeasureSpec(0, 0), 0, ((ViewGroup$LayoutParams)layoutParams).height);
            view.measure(getChildMeasureSpec(View$MeasureSpec.makeMeasureSpec(this.this$0.mGridView.getWidth(), 1073741824), 0, ((ViewGroup$LayoutParams)layoutParams).width), childMeasureSpec);
        }
        this.setMeasuredDimension(View$MeasureSpec.getSize(n), view.getMeasuredHeight());
    }
}
