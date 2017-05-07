// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.graphics.PointF;
import android.content.Context;

class LinearLayoutManager$1 extends LinearSmoothScroller
{
    final /* synthetic */ LinearLayoutManager this$0;
    
    LinearLayoutManager$1(final LinearLayoutManager this$0, final Context context) {
        this.this$0 = this$0;
        super(context);
    }
    
    @Override
    public PointF computeScrollVectorForPosition(final int n) {
        return this.this$0.computeScrollVectorForPosition(n);
    }
}
