// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.View;
import android.view.ViewGroup$OnHierarchyChangeListener;

class CoordinatorLayout$HierarchyChangeListener implements ViewGroup$OnHierarchyChangeListener
{
    final /* synthetic */ CoordinatorLayout this$0;
    
    CoordinatorLayout$HierarchyChangeListener(final CoordinatorLayout this$0) {
        this.this$0 = this$0;
    }
    
    public void onChildViewAdded(final View view, final View view2) {
        if (this.this$0.mOnHierarchyChangeListener != null) {
            this.this$0.mOnHierarchyChangeListener.onChildViewAdded(view, view2);
        }
    }
    
    public void onChildViewRemoved(final View view, final View view2) {
        this.this$0.onChildViewsChanged(2);
        if (this.this$0.mOnHierarchyChangeListener != null) {
            this.this$0.mOnHierarchyChangeListener.onChildViewRemoved(view, view2);
        }
    }
}
