// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.graphics.Canvas;
import android.view.View;
import android.graphics.Rect;

public abstract class RecyclerView$ItemDecoration
{
    @Deprecated
    public void getItemOffsets(final Rect rect, final int n, final RecyclerView recyclerView) {
        rect.set(0, 0, 0, 0);
    }
    
    public void getItemOffsets(final Rect rect, final View view, final RecyclerView recyclerView, final RecyclerView$State recyclerView$State) {
        this.getItemOffsets(rect, ((RecyclerView$LayoutParams)view.getLayoutParams()).getViewPosition(), recyclerView);
    }
    
    @Deprecated
    public void onDraw(final Canvas canvas, final RecyclerView recyclerView) {
    }
    
    public void onDraw(final Canvas canvas, final RecyclerView recyclerView, final RecyclerView$State recyclerView$State) {
        this.onDraw(canvas, recyclerView);
    }
    
    @Deprecated
    public void onDrawOver(final Canvas canvas, final RecyclerView recyclerView) {
    }
    
    public void onDrawOver(final Canvas canvas, final RecyclerView recyclerView, final RecyclerView$State recyclerView$State) {
        this.onDrawOver(canvas, recyclerView);
    }
}
