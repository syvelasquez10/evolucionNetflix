// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.support.v4.view.WindowInsetsCompat;
import android.view.View;
import android.support.v4.view.OnApplyWindowInsetsListener;

class CoordinatorLayout$1 implements OnApplyWindowInsetsListener
{
    final /* synthetic */ CoordinatorLayout this$0;
    
    CoordinatorLayout$1(final CoordinatorLayout this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public WindowInsetsCompat onApplyWindowInsets(final View view, final WindowInsetsCompat windowInsets) {
        return this.this$0.setWindowInsets(windowInsets);
    }
}
