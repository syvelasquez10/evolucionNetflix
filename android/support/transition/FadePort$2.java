// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.animation.Animator;
import android.view.ViewGroup;
import android.view.View;
import android.animation.AnimatorListenerAdapter;

class FadePort$2 extends AnimatorListenerAdapter
{
    final /* synthetic */ FadePort this$0;
    final /* synthetic */ View val$finalOverlayView;
    final /* synthetic */ ViewGroup val$finalSceneRoot;
    final /* synthetic */ View val$finalView;
    final /* synthetic */ View val$finalViewToKeep;
    final /* synthetic */ int val$finalVisibility;
    
    FadePort$2(final FadePort this$0, final View val$finalView, final View val$finalViewToKeep, final int val$finalVisibility, final View val$finalOverlayView, final ViewGroup val$finalSceneRoot) {
        this.this$0 = this$0;
        this.val$finalView = val$finalView;
        this.val$finalViewToKeep = val$finalViewToKeep;
        this.val$finalVisibility = val$finalVisibility;
        this.val$finalOverlayView = val$finalOverlayView;
        this.val$finalSceneRoot = val$finalSceneRoot;
    }
    
    public void onAnimationEnd(final Animator animator) {
        this.val$finalView.setAlpha(1.0f);
        if (this.val$finalViewToKeep != null) {
            this.val$finalViewToKeep.setVisibility(this.val$finalVisibility);
        }
        if (this.val$finalOverlayView != null) {
            ViewGroupOverlay.createFrom(this.val$finalSceneRoot).remove(this.val$finalOverlayView);
        }
    }
}
