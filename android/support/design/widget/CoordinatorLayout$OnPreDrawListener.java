// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.ViewTreeObserver$OnPreDrawListener;

class CoordinatorLayout$OnPreDrawListener implements ViewTreeObserver$OnPreDrawListener
{
    final /* synthetic */ CoordinatorLayout this$0;
    
    CoordinatorLayout$OnPreDrawListener(final CoordinatorLayout this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onPreDraw() {
        this.this$0.dispatchOnDependentViewChanged(false);
        return true;
    }
}
