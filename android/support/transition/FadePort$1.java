// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.view.View;

class FadePort$1 extends TransitionPort$TransitionListenerAdapter
{
    boolean mCanceled;
    float mPausedAlpha;
    final /* synthetic */ FadePort this$0;
    final /* synthetic */ View val$endView;
    
    FadePort$1(final FadePort this$0, final View val$endView) {
        this.this$0 = this$0;
        this.val$endView = val$endView;
        this.mCanceled = false;
    }
    
    @Override
    public void onTransitionEnd(final TransitionPort transitionPort) {
        if (!this.mCanceled) {
            this.val$endView.setAlpha(1.0f);
        }
    }
    
    @Override
    public void onTransitionPause(final TransitionPort transitionPort) {
        this.mPausedAlpha = this.val$endView.getAlpha();
        this.val$endView.setAlpha(1.0f);
    }
    
    @Override
    public void onTransitionResume(final TransitionPort transitionPort) {
        this.val$endView.setAlpha(this.mPausedAlpha);
    }
}
