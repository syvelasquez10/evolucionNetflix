// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import java.util.Iterator;
import android.view.ViewTreeObserver$OnPreDrawListener;
import android.view.View$OnAttachStateChangeListener;
import java.lang.ref.Reference;
import android.view.View;
import android.support.v4.view.ViewCompat;
import android.support.v4.util.ArrayMap;
import java.lang.ref.WeakReference;
import android.view.ViewGroup;
import java.util.ArrayList;
import android.annotation.TargetApi;

@TargetApi(14)
class TransitionManagerPort
{
    private static final String[] EMPTY_STRINGS;
    private static String LOG_TAG;
    private static TransitionPort sDefaultTransition;
    static ArrayList<ViewGroup> sPendingTransitions;
    private static ThreadLocal<WeakReference<ArrayMap<ViewGroup, ArrayList<TransitionPort>>>> sRunningTransitions;
    
    static {
        EMPTY_STRINGS = new String[0];
        TransitionManagerPort.LOG_TAG = "TransitionManager";
        TransitionManagerPort.sDefaultTransition = new AutoTransitionPort();
        TransitionManagerPort.sRunningTransitions = new ThreadLocal<WeakReference<ArrayMap<ViewGroup, ArrayList<TransitionPort>>>>();
        TransitionManagerPort.sPendingTransitions = new ArrayList<ViewGroup>();
    }
    
    public static void beginDelayedTransition(final ViewGroup viewGroup, TransitionPort clone) {
        if (!TransitionManagerPort.sPendingTransitions.contains(viewGroup) && ViewCompat.isLaidOut((View)viewGroup)) {
            TransitionManagerPort.sPendingTransitions.add(viewGroup);
            TransitionPort sDefaultTransition;
            if ((sDefaultTransition = clone) == null) {
                sDefaultTransition = TransitionManagerPort.sDefaultTransition;
            }
            clone = sDefaultTransition.clone();
            sceneChangeSetup(viewGroup, clone);
            ScenePort.setCurrentScene((View)viewGroup, null);
            sceneChangeRunTransition(viewGroup, clone);
        }
    }
    
    static ArrayMap<ViewGroup, ArrayList<TransitionPort>> getRunningTransitions() {
        final WeakReference<ArrayMap<ViewGroup, ArrayList<TransitionPort>>> weakReference = TransitionManagerPort.sRunningTransitions.get();
        if (weakReference != null) {
            final Reference<T> reference = (Reference<T>)weakReference;
            if (weakReference.get() != null) {
                return (ArrayMap<ViewGroup, ArrayList<TransitionPort>>)reference.get();
            }
        }
        final Reference<T> reference = (Reference<T>)new WeakReference<Object>(new ArrayMap<ViewGroup, ArrayList<TransitionPort>>());
        TransitionManagerPort.sRunningTransitions.set((WeakReference<ArrayMap<ViewGroup, ArrayList<TransitionPort>>>)reference);
        return (ArrayMap<ViewGroup, ArrayList<TransitionPort>>)reference.get();
    }
    
    private static void sceneChangeRunTransition(final ViewGroup viewGroup, final TransitionPort transitionPort) {
        if (transitionPort != null && viewGroup != null) {
            final TransitionManagerPort$MultiListener transitionManagerPort$MultiListener = new TransitionManagerPort$MultiListener(transitionPort, viewGroup);
            viewGroup.addOnAttachStateChangeListener((View$OnAttachStateChangeListener)transitionManagerPort$MultiListener);
            viewGroup.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)transitionManagerPort$MultiListener);
        }
    }
    
    private static void sceneChangeSetup(final ViewGroup viewGroup, final TransitionPort transitionPort) {
        final ArrayList<TransitionPort> list = getRunningTransitions().get(viewGroup);
        if (list != null && list.size() > 0) {
            final Iterator<TransitionPort> iterator = list.iterator();
            while (iterator.hasNext()) {
                iterator.next().pause((View)viewGroup);
            }
        }
        if (transitionPort != null) {
            transitionPort.captureValues(viewGroup, true);
        }
        final ScenePort currentScene = ScenePort.getCurrentScene((View)viewGroup);
        if (currentScene != null) {
            currentScene.exit();
        }
    }
}
