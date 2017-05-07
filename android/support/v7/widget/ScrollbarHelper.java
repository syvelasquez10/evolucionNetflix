// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.View;

class ScrollbarHelper
{
    static int computeScrollExtent(final RecyclerView$State recyclerView$State, final OrientationHelper orientationHelper, final View view, final View view2, final RecyclerView$LayoutManager recyclerView$LayoutManager, final boolean b) {
        if (recyclerView$LayoutManager.getChildCount() == 0 || recyclerView$State.getItemCount() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!b) {
            return Math.abs(recyclerView$LayoutManager.getPosition(view) - recyclerView$LayoutManager.getPosition(view2)) + 1;
        }
        return Math.min(orientationHelper.getTotalSpace(), orientationHelper.getDecoratedEnd(view2) - orientationHelper.getDecoratedStart(view));
    }
    
    static int computeScrollOffset(final RecyclerView$State recyclerView$State, final OrientationHelper orientationHelper, final View view, final View view2, final RecyclerView$LayoutManager recyclerView$LayoutManager, final boolean b, final boolean b2) {
        int n2;
        final int n = n2 = 0;
        if (recyclerView$LayoutManager.getChildCount() != 0) {
            n2 = n;
            if (recyclerView$State.getItemCount() != 0) {
                n2 = n;
                if (view != null) {
                    if (view2 == null) {
                        n2 = n;
                    }
                    else {
                        final int min = Math.min(recyclerView$LayoutManager.getPosition(view), recyclerView$LayoutManager.getPosition(view2));
                        final int max = Math.max(recyclerView$LayoutManager.getPosition(view), recyclerView$LayoutManager.getPosition(view2));
                        int n3;
                        if (b2) {
                            n3 = Math.max(0, recyclerView$State.getItemCount() - max - 1);
                        }
                        else {
                            n3 = Math.max(0, min);
                        }
                        n2 = n3;
                        if (b) {
                            return Math.round(n3 * (Math.abs(orientationHelper.getDecoratedEnd(view2) - orientationHelper.getDecoratedStart(view)) / (Math.abs(recyclerView$LayoutManager.getPosition(view) - recyclerView$LayoutManager.getPosition(view2)) + 1)) + (orientationHelper.getStartAfterPadding() - orientationHelper.getDecoratedStart(view)));
                        }
                    }
                }
            }
        }
        return n2;
    }
    
    static int computeScrollRange(final RecyclerView$State recyclerView$State, final OrientationHelper orientationHelper, final View view, final View view2, final RecyclerView$LayoutManager recyclerView$LayoutManager, final boolean b) {
        if (recyclerView$LayoutManager.getChildCount() == 0 || recyclerView$State.getItemCount() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!b) {
            return recyclerView$State.getItemCount();
        }
        return (orientationHelper.getDecoratedEnd(view2) - orientationHelper.getDecoratedStart(view)) / (Math.abs(recyclerView$LayoutManager.getPosition(view) - recyclerView$LayoutManager.getPosition(view2)) + 1) * recyclerView$State.getItemCount();
    }
}
