// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import java.util.Iterator;
import java.util.ArrayList;

class TransitionIcs$CompatListener implements TransitionPort$TransitionListener
{
    private final ArrayList<TransitionInterfaceListener> mListeners;
    final /* synthetic */ TransitionIcs this$0;
    
    TransitionIcs$CompatListener(final TransitionIcs this$0) {
        this.this$0 = this$0;
        this.mListeners = new ArrayList<TransitionInterfaceListener>();
    }
    
    public void addListener(final TransitionInterfaceListener transitionInterfaceListener) {
        this.mListeners.add(transitionInterfaceListener);
    }
    
    public boolean isEmpty() {
        return this.mListeners.isEmpty();
    }
    
    @Override
    public void onTransitionEnd(final TransitionPort transitionPort) {
        final Iterator<TransitionInterfaceListener<TransitionInterface>> iterator = this.mListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onTransitionEnd(this.this$0.mExternalTransition);
        }
    }
    
    @Override
    public void onTransitionPause(final TransitionPort transitionPort) {
        final Iterator<TransitionInterfaceListener<TransitionInterface>> iterator = this.mListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onTransitionPause(this.this$0.mExternalTransition);
        }
    }
    
    @Override
    public void onTransitionResume(final TransitionPort transitionPort) {
        final Iterator<TransitionInterfaceListener<TransitionInterface>> iterator = this.mListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onTransitionResume(this.this$0.mExternalTransition);
        }
    }
    
    @Override
    public void onTransitionStart(final TransitionPort transitionPort) {
        final Iterator<TransitionInterfaceListener<TransitionInterface>> iterator = this.mListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onTransitionStart(this.this$0.mExternalTransition);
        }
    }
    
    public void removeListener(final TransitionInterfaceListener transitionInterfaceListener) {
        this.mListeners.remove(transitionInterfaceListener);
    }
}
