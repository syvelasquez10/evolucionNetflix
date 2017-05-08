// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;
import android.support.v4.view.ViewPropertyAnimatorCompat;

class DefaultItemAnimator$7 extends DefaultItemAnimator$VpaListenerAdapter
{
    final /* synthetic */ DefaultItemAnimator this$0;
    final /* synthetic */ DefaultItemAnimator$ChangeInfo val$changeInfo;
    final /* synthetic */ ViewPropertyAnimatorCompat val$oldViewAnim;
    
    DefaultItemAnimator$7(final DefaultItemAnimator this$0, final DefaultItemAnimator$ChangeInfo val$changeInfo, final ViewPropertyAnimatorCompat val$oldViewAnim) {
        this.this$0 = this$0;
        this.val$changeInfo = val$changeInfo;
        this.val$oldViewAnim = val$oldViewAnim;
    }
    
    @Override
    public void onAnimationEnd(final View view) {
        this.val$oldViewAnim.setListener(null);
        ViewCompat.setAlpha(view, 1.0f);
        ViewCompat.setTranslationX(view, 0.0f);
        ViewCompat.setTranslationY(view, 0.0f);
        this.this$0.dispatchChangeFinished(this.val$changeInfo.oldHolder, true);
        this.this$0.mChangeAnimations.remove(this.val$changeInfo.oldHolder);
        this.this$0.dispatchFinishedWhenDone();
    }
    
    @Override
    public void onAnimationStart(final View view) {
        this.this$0.dispatchChangeStarting(this.val$changeInfo.oldHolder, true);
    }
}
