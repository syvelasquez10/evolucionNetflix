// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

class TransitionSetPort$1 extends TransitionPort$TransitionListenerAdapter
{
    final /* synthetic */ TransitionSetPort this$0;
    final /* synthetic */ TransitionPort val$nextTransition;
    
    TransitionSetPort$1(final TransitionSetPort this$0, final TransitionPort val$nextTransition) {
        this.this$0 = this$0;
        this.val$nextTransition = val$nextTransition;
    }
    
    @Override
    public void onTransitionEnd(final TransitionPort transitionPort) {
        this.val$nextTransition.runAnimators();
        transitionPort.removeListener(this);
    }
}
