// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import java.util.Iterator;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.View;

class DefaultItemAnimator$8 extends DefaultItemAnimator$VpaListenerAdapter
{
    final /* synthetic */ DefaultItemAnimator this$0;
    final /* synthetic */ DefaultItemAnimator$ChangeInfo val$changeInfo;
    final /* synthetic */ View val$newView;
    final /* synthetic */ ViewPropertyAnimatorCompat val$newViewAnimation;
    
    DefaultItemAnimator$8(final DefaultItemAnimator this$0, final DefaultItemAnimator$ChangeInfo val$changeInfo, final ViewPropertyAnimatorCompat val$newViewAnimation, final View val$newView) {
        this.this$0 = this$0;
        this.val$changeInfo = val$changeInfo;
        this.val$newViewAnimation = val$newViewAnimation;
        this.val$newView = val$newView;
        super(null);
    }
    
    @Override
    public void onAnimationEnd(final View view) {
        this.val$newViewAnimation.setListener(null);
        ViewCompat.setAlpha(this.val$newView, 1.0f);
        ViewCompat.setTranslationX(this.val$newView, 0.0f);
        ViewCompat.setTranslationY(this.val$newView, 0.0f);
        this.this$0.dispatchChangeFinished(this.val$changeInfo.newHolder, false);
        this.this$0.mChangeAnimations.remove(this.val$changeInfo.newHolder);
        this.this$0.dispatchFinishedWhenDone();
    }
    
    @Override
    public void onAnimationStart(final View view) {
        this.this$0.dispatchChangeStarting(this.val$changeInfo.newHolder, false);
    }
}
