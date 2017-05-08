// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.buffet;

import android.view.MotionEvent;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.support.design.widget.SwipeDismissBehavior;

final class BuffetBar$Behavior extends SwipeDismissBehavior<BuffetBar$BuffetLayout>
{
    final /* synthetic */ BuffetBar this$0;
    
    BuffetBar$Behavior(final BuffetBar this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public boolean canSwipeDismissView(final View view) {
        return view instanceof BuffetBar$BuffetLayout;
    }
    
    @Override
    public boolean onInterceptTouchEvent(final CoordinatorLayout coordinatorLayout, final BuffetBar$BuffetLayout buffetBar$BuffetLayout, final MotionEvent motionEvent) {
        if (coordinatorLayout.isPointInChildBounds((View)buffetBar$BuffetLayout, (int)motionEvent.getX(), (int)motionEvent.getY())) {
            switch (motionEvent.getActionMasked()) {
                case 0: {
                    BuffetManager.getInstance().cancelTimeout(this.this$0.mManagerCallback);
                    break;
                }
                case 1:
                case 3: {
                    BuffetManager.getInstance().restoreTimeout(this.this$0.mManagerCallback);
                    break;
                }
            }
        }
        return super.onInterceptTouchEvent(coordinatorLayout, buffetBar$BuffetLayout, motionEvent);
    }
}
