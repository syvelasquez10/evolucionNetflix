// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.ViewTreeObserver$OnPreDrawListener;

class FloatingActionButtonImpl$1 implements ViewTreeObserver$OnPreDrawListener
{
    final /* synthetic */ FloatingActionButtonImpl this$0;
    
    FloatingActionButtonImpl$1(final FloatingActionButtonImpl this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onPreDraw() {
        this.this$0.onPreDraw();
        return true;
    }
}
