// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.recyclerview;

import android.view.View;
import com.facebook.infer.annotation.Assertions;
import android.content.Context;
import android.view.ViewGroup;

class RecyclerViewBackedScrollView$RecyclableWrapperViewGroup extends ViewGroup
{
    public RecyclerViewBackedScrollView$RecyclableWrapperViewGroup(final Context context) {
        super(context);
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
    }
    
    protected void onMeasure(final int n, final int n2) {
        if (this.getChildCount() > 0) {
            final View child = this.getChildAt(0);
            this.setMeasuredDimension(child.getMeasuredWidth(), child.getMeasuredHeight());
            return;
        }
        Assertions.assertUnreachable("RecyclableWrapperView measured but no view attached");
    }
}
