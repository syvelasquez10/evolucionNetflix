// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.support.v4.view.WindowInsetsCompat;
import android.view.View;
import android.support.v4.view.OnApplyWindowInsetsListener;

class AppBarLayout$1 implements OnApplyWindowInsetsListener
{
    final /* synthetic */ AppBarLayout this$0;
    
    AppBarLayout$1(final AppBarLayout this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public WindowInsetsCompat onApplyWindowInsets(final View view, final WindowInsetsCompat windowInsetsCompat) {
        return this.this$0.onWindowInsetChanged(windowInsetsCompat);
    }
}
