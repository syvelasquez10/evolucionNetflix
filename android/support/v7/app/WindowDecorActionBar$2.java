// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.View;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;

class WindowDecorActionBar$2 extends ViewPropertyAnimatorListenerAdapter
{
    final /* synthetic */ WindowDecorActionBar this$0;
    
    WindowDecorActionBar$2(final WindowDecorActionBar this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onAnimationEnd(final View view) {
        this.this$0.mCurrentShowAnim = null;
        this.this$0.mContainerView.requestLayout();
    }
}
