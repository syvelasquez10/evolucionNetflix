// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.MotionEvent;

public interface RecyclerView$OnItemTouchListener
{
    boolean onInterceptTouchEvent(final RecyclerView p0, final MotionEvent p1);
    
    void onTouchEvent(final RecyclerView p0, final MotionEvent p1);
}
