// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import java.util.Iterator;
import android.view.View;
import android.support.v4.util.ArrayMap;
import java.util.Collection;
import java.util.ArrayList;
import android.view.ViewGroup;
import android.view.ViewTreeObserver$OnPreDrawListener;
import android.view.View$OnAttachStateChangeListener;

class TransitionManagerPort$MultiListener implements View$OnAttachStateChangeListener, ViewTreeObserver$OnPreDrawListener
{
    ViewGroup mSceneRoot;
    TransitionPort mTransition;
    
    TransitionManagerPort$MultiListener(final TransitionPort mTransition, final ViewGroup mSceneRoot) {
        this.mTransition = mTransition;
        this.mSceneRoot = mSceneRoot;
    }
    
    private void removeListeners() {
        this.mSceneRoot.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this);
        this.mSceneRoot.removeOnAttachStateChangeListener((View$OnAttachStateChangeListener)this);
    }
    
    public boolean onPreDraw() {
        this.removeListeners();
        TransitionManagerPort.sPendingTransitions.remove(this.mSceneRoot);
        final ArrayMap<ViewGroup, ArrayList<TransitionPort>> runningTransitions = TransitionManagerPort.getRunningTransitions();
        ArrayList<TransitionPort> list = runningTransitions.get(this.mSceneRoot);
        ArrayList<TransitionPort> list2;
        if (list == null) {
            list = new ArrayList<TransitionPort>();
            runningTransitions.put(this.mSceneRoot, list);
            list2 = null;
        }
        else if (list.size() > 0) {
            list2 = new ArrayList<TransitionPort>(list);
        }
        else {
            list2 = null;
        }
        list.add(this.mTransition);
        this.mTransition.addListener(new TransitionManagerPort$MultiListener$1(this, runningTransitions));
        this.mTransition.captureValues(this.mSceneRoot, false);
        if (list2 != null) {
            final Iterator<TransitionPort> iterator = list2.iterator();
            while (iterator.hasNext()) {
                iterator.next().resume((View)this.mSceneRoot);
            }
        }
        this.mTransition.playTransition(this.mSceneRoot);
        return true;
    }
    
    public void onViewAttachedToWindow(final View view) {
    }
    
    public void onViewDetachedFromWindow(final View view) {
        this.removeListeners();
        TransitionManagerPort.sPendingTransitions.remove(this.mSceneRoot);
        final ArrayList<TransitionPort> list = TransitionManagerPort.getRunningTransitions().get(this.mSceneRoot);
        if (list != null && list.size() > 0) {
            final Iterator<TransitionPort> iterator = list.iterator();
            while (iterator.hasNext()) {
                iterator.next().resume((View)this.mSceneRoot);
            }
        }
        this.mTransition.clearValues(true);
    }
}
