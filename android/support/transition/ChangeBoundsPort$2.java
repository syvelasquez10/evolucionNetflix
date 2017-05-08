// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

class ChangeBoundsPort$2 extends TransitionPort$TransitionListenerAdapter
{
    boolean mCanceled;
    final /* synthetic */ ChangeBoundsPort this$0;
    
    ChangeBoundsPort$2(final ChangeBoundsPort this$0) {
        this.this$0 = this$0;
        this.mCanceled = false;
    }
    
    @Override
    public void onTransitionEnd(final TransitionPort transitionPort) {
        if (!this.mCanceled) {}
    }
    
    @Override
    public void onTransitionPause(final TransitionPort transitionPort) {
    }
    
    @Override
    public void onTransitionResume(final TransitionPort transitionPort) {
    }
}
