// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

interface TransitionInterfaceListener<TransitionT extends TransitionInterface>
{
    void onTransitionCancel(final TransitionT p0);
    
    void onTransitionEnd(final TransitionT p0);
    
    void onTransitionPause(final TransitionT p0);
    
    void onTransitionResume(final TransitionT p0);
    
    void onTransitionStart(final TransitionT p0);
}
