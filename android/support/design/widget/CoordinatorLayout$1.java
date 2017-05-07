// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.View;
import java.util.Comparator;

class CoordinatorLayout$1 implements Comparator<View>
{
    final /* synthetic */ CoordinatorLayout this$0;
    
    CoordinatorLayout$1(final CoordinatorLayout this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public int compare(final View view, final View view2) {
        if (view == view2) {
            return 0;
        }
        if (((CoordinatorLayout$LayoutParams)view.getLayoutParams()).dependsOn(this.this$0, view, view2)) {
            return 1;
        }
        if (((CoordinatorLayout$LayoutParams)view2.getLayoutParams()).dependsOn(this.this$0, view2, view)) {
            return -1;
        }
        return 0;
    }
}
