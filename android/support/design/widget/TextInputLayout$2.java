// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.View;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;

class TextInputLayout$2 extends ViewPropertyAnimatorListenerAdapter
{
    final /* synthetic */ TextInputLayout this$0;
    
    TextInputLayout$2(final TextInputLayout this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onAnimationStart(final View view) {
        view.setVisibility(0);
    }
}
