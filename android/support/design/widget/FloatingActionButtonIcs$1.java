// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class FloatingActionButtonIcs$1 extends AnimatorListenerAdapter
{
    private boolean mCancelled;
    final /* synthetic */ FloatingActionButtonIcs this$0;
    final /* synthetic */ boolean val$fromUser;
    final /* synthetic */ FloatingActionButtonImpl$InternalVisibilityChangedListener val$listener;
    
    FloatingActionButtonIcs$1(final FloatingActionButtonIcs this$0, final boolean val$fromUser, final FloatingActionButtonImpl$InternalVisibilityChangedListener val$listener) {
        this.this$0 = this$0;
        this.val$fromUser = val$fromUser;
        this.val$listener = val$listener;
    }
    
    public void onAnimationCancel(final Animator animator) {
        this.mCancelled = true;
    }
    
    public void onAnimationEnd(final Animator animator) {
        this.this$0.mAnimState = 0;
        if (!this.mCancelled) {
            final VisibilityAwareImageButton mView = this.this$0.mView;
            int n;
            if (this.val$fromUser) {
                n = 8;
            }
            else {
                n = 4;
            }
            mView.internalSetVisibility(n, this.val$fromUser);
            if (this.val$listener != null) {
                this.val$listener.onHidden();
            }
        }
    }
    
    public void onAnimationStart(final Animator animator) {
        this.this$0.mView.internalSetVisibility(0, this.val$fromUser);
        this.mCancelled = false;
    }
}
