// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.MotionEvent;
import android.view.View;

final class Snackbar$Behavior extends SwipeDismissBehavior<Snackbar$SnackbarLayout>
{
    final /* synthetic */ Snackbar this$0;
    
    Snackbar$Behavior(final Snackbar this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public boolean canSwipeDismissView(final View view) {
        return view instanceof Snackbar$SnackbarLayout;
    }
    
    @Override
    public boolean onInterceptTouchEvent(final CoordinatorLayout coordinatorLayout, final Snackbar$SnackbarLayout snackbar$SnackbarLayout, final MotionEvent motionEvent) {
        if (coordinatorLayout.isPointInChildBounds((View)snackbar$SnackbarLayout, (int)motionEvent.getX(), (int)motionEvent.getY())) {
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
        return super.onInterceptTouchEvent(coordinatorLayout, snackbar$SnackbarLayout, motionEvent);
    }
}
