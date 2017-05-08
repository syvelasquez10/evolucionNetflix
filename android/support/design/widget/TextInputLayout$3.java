// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.View;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;

class TextInputLayout$3 extends ViewPropertyAnimatorListenerAdapter
{
    final /* synthetic */ TextInputLayout this$0;
    final /* synthetic */ CharSequence val$error;
    
    TextInputLayout$3(final TextInputLayout this$0, final CharSequence val$error) {
        this.this$0 = this$0;
        this.val$error = val$error;
    }
    
    @Override
    public void onAnimationEnd(final View view) {
        this.this$0.mErrorView.setText(this.val$error);
        view.setVisibility(4);
    }
}
