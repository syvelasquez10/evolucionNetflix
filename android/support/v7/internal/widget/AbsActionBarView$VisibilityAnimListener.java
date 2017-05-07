// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.View;
import android.support.v4.view.ViewPropertyAnimatorListener;

public class AbsActionBarView$VisibilityAnimListener implements ViewPropertyAnimatorListener
{
    private boolean mCanceled;
    int mFinalVisibility;
    final /* synthetic */ AbsActionBarView this$0;
    
    protected AbsActionBarView$VisibilityAnimListener(final AbsActionBarView this$0) {
        this.this$0 = this$0;
        this.mCanceled = false;
    }
    
    @Override
    public void onAnimationCancel(final View view) {
        this.mCanceled = true;
    }
    
    @Override
    public void onAnimationEnd(final View view) {
        if (!this.mCanceled) {
            this.this$0.mVisibilityAnim = null;
            this.this$0.setVisibility(this.mFinalVisibility);
            if (this.this$0.mSplitView != null && this.this$0.mMenuView != null) {
                this.this$0.mMenuView.setVisibility(this.mFinalVisibility);
            }
        }
    }
    
    @Override
    public void onAnimationStart(final View view) {
        this.this$0.setVisibility(0);
        this.mCanceled = false;
    }
    
    public AbsActionBarView$VisibilityAnimListener withFinalVisibility(final ViewPropertyAnimatorCompat mVisibilityAnim, final int mFinalVisibility) {
        this.this$0.mVisibilityAnim = mVisibilityAnim;
        this.mFinalVisibility = mFinalVisibility;
        return this;
    }
}
