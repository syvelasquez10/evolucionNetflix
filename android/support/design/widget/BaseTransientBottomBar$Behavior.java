// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.MotionEvent;
import android.view.View;

final class BaseTransientBottomBar$Behavior extends SwipeDismissBehavior<BaseTransientBottomBar$SnackbarBaseLayout>
{
    final /* synthetic */ BaseTransientBottomBar this$0;
    
    BaseTransientBottomBar$Behavior(final BaseTransientBottomBar this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public boolean canSwipeDismissView(final View view) {
        return view instanceof BaseTransientBottomBar$SnackbarBaseLayout;
    }
    
    @Override
    public boolean onInterceptTouchEvent(final CoordinatorLayout coordinatorLayout, final BaseTransientBottomBar$SnackbarBaseLayout baseTransientBottomBar$SnackbarBaseLayout, final MotionEvent motionEvent) {
        if (coordinatorLayout.isPointInChildBounds((View)baseTransientBottomBar$SnackbarBaseLayout, (int)motionEvent.getX(), (int)motionEvent.getY())) {
            switch (motionEvent.getActionMasked()) {
                case 0: {
                    SnackbarManager.getInstance().cancelTimeout(this.this$0.mManagerCallback);
                    break;
                }
                case 1:
                case 3: {
                    SnackbarManager.getInstance().restoreTimeout(this.this$0.mManagerCallback);
                    break;
                }
            }
        }
        return super.onInterceptTouchEvent(coordinatorLayout, baseTransientBottomBar$SnackbarBaseLayout, motionEvent);
    }
}
