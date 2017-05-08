// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import java.util.Iterator;
import android.transition.Transition;
import java.util.ArrayList;
import android.transition.Transition$TransitionListener;

class TransitionKitKat$CompatListener implements Transition$TransitionListener
{
    private final ArrayList<TransitionInterfaceListener> mListeners;
    final /* synthetic */ TransitionKitKat this$0;
    
    TransitionKitKat$CompatListener(final TransitionKitKat this$0) {
        this.this$0 = this$0;
        this.mListeners = new ArrayList<TransitionInterfaceListener>();
    }
    
    void addListener(final TransitionInterfaceListener transitionInterfaceListener) {
        this.mListeners.add(transitionInterfaceListener);
    }
    
    boolean isEmpty() {
        return this.mListeners.isEmpty();
    }
    
    public void onTransitionCancel(final Transition transition) {
        final Iterator<TransitionInterfaceListener<TransitionInterface>> iterator = this.mListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onTransitionCancel(this.this$0.mExternalTransition);
        }
    }
    
    public void onTransitionEnd(final Transition transition) {
        final Iterator<TransitionInterfaceListener<TransitionInterface>> iterator = this.mListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onTransitionEnd(this.this$0.mExternalTransition);
        }
    }
    
    public void onTransitionPause(final Transition transition) {
        final Iterator<TransitionInterfaceListener<TransitionInterface>> iterator = this.mListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onTransitionPause(this.this$0.mExternalTransition);
        }
    }
    
    public void onTransitionResume(final Transition transition) {
        final Iterator<TransitionInterfaceListener<TransitionInterface>> iterator = this.mListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onTransitionResume(this.this$0.mExternalTransition);
        }
    }
    
    public void onTransitionStart(final Transition transition) {
        final Iterator<TransitionInterfaceListener<TransitionInterface>> iterator = this.mListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onTransitionStart(this.this$0.mExternalTransition);
        }
    }
    
    void removeListener(final TransitionInterfaceListener transitionInterfaceListener) {
        this.mListeners.remove(transitionInterfaceListener);
    }
}
