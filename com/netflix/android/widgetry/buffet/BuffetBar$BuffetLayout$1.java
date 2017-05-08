// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.buffet;

import android.support.v4.view.WindowInsetsCompat;
import android.view.View;
import android.support.v4.view.OnApplyWindowInsetsListener;

class BuffetBar$BuffetLayout$1 implements OnApplyWindowInsetsListener
{
    final /* synthetic */ BuffetBar$BuffetLayout this$0;
    
    BuffetBar$BuffetLayout$1(final BuffetBar$BuffetLayout this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public WindowInsetsCompat onApplyWindowInsets(final View view, final WindowInsetsCompat windowInsetsCompat) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), windowInsetsCompat.getSystemWindowInsetBottom());
        return windowInsetsCompat;
    }
}
