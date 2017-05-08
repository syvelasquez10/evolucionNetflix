// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.statusbar;

import android.view.WindowInsets;
import android.view.View;
import android.view.View$OnApplyWindowInsetsListener;

class StatusBarModule$2$1 implements View$OnApplyWindowInsetsListener
{
    final /* synthetic */ StatusBarModule$2 this$1;
    
    StatusBarModule$2$1(final StatusBarModule$2 this$1) {
        this.this$1 = this$1;
    }
    
    public WindowInsets onApplyWindowInsets(final View view, final WindowInsets windowInsets) {
        final WindowInsets onApplyWindowInsets = view.onApplyWindowInsets(windowInsets);
        return onApplyWindowInsets.replaceSystemWindowInsets(onApplyWindowInsets.getSystemWindowInsetLeft(), 0, onApplyWindowInsets.getSystemWindowInsetRight(), onApplyWindowInsets.getSystemWindowInsetBottom());
    }
}
