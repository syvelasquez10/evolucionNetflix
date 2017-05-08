// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget.advisor;

import android.animation.TimeInterpolator;
import com.netflix.mediaclient.Log;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import com.netflix.model.leafs.advisory.Advisory;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.animation.Animator$AnimatorListener;
import android.view.View;
import android.view.animation.Interpolator;

public class TwoLineAdvisor extends Advisor
{
    private static final int ANIMATION_IN_DURATION_MILLIS = 660;
    private static final int ANIMATION_START_DELAY_MILLIS = 100;
    private static final int BAR_ANIMATION_OUT_DURATION_MILLIS = 400;
    private static final int CONTAINER_ANIMATE_OUT_DURATION_MILLIS = 833;
    private static final float HIDE = 0.0f;
    private static final float SHOW = 1.0f;
    private static final int SUBTITLE_ANIMATE_OUT_DURATION_MILLIS = 500;
    private Interpolator animateInInterpolator;
    private Interpolator animateOutInterpolator;
    private final View container;
    private Animator$AnimatorListener mHideAnimatorEndListener;
    private final View netflixBarView;
    private final int translationY;
    
    public TwoLineAdvisor(final NetflixActivity netflixActivity, final Advisory advisory) {
        super(netflixActivity, advisory);
        this.animateInInterpolator = (Interpolator)new DecelerateInterpolator();
        this.animateOutInterpolator = (Interpolator)new AccelerateInterpolator();
        this.mHideAnimatorEndListener = (Animator$AnimatorListener)new TwoLineAdvisor$1(this);
        final View rootView = this.getRootView();
        this.netflixBarView = rootView.findViewById(2131821518);
        this.container = rootView.findViewById(2131821515);
        this.translationY = rootView.getResources().getDimensionPixelSize(2131427635);
        this.container.setVisibility(0);
    }
    
    private void cancelAnimation() {
        this.netflixBarView.animate().cancel();
        this.messageView.animate().cancel();
        if (this.hasSecondMessage) {
            this.secondaryMessageView.animate().cancel();
        }
        this.container.animate().cancel();
    }
    
    private void resetViews() {
        this.netflixBarView.setTranslationY(0.0f);
        this.messageView.setTranslationY(0.0f);
        if (this.hasSecondMessage) {
            this.secondaryMessageView.setTranslationY(0.0f);
        }
        this.container.setTranslationY(0.0f);
    }
    
    private void setTranslationYForAnimation() {
        this.netflixBarView.setTranslationY((float)this.netflixBarView.getMeasuredHeight());
        this.messageView.setTranslationY((float)this.translationY);
        if (this.hasSecondMessage) {
            this.secondaryMessageView.setTranslationY((float)this.translationY);
        }
        this.container.setTranslationY((float)this.translationY);
    }
    
    @Override
    public void dismiss() {
        Log.d("AdvisorToast", "dismiss -- two line advisor");
        if (this.isShowing() && this.container != null) {
            this.netflixBarView.animate().translationYBy((float)(-this.netflixBarView.getMeasuredHeight())).setInterpolator((TimeInterpolator)this.animateOutInterpolator).setDuration(400L);
            this.messageView.animate().alpha(0.0f).translationYBy((float)(-this.translationY)).setInterpolator((TimeInterpolator)this.animateOutInterpolator).setDuration(500L);
            if (this.hasSecondMessage) {
                this.secondaryMessageView.animate().alpha(0.0f).translationYBy((float)(-this.translationY)).setInterpolator((TimeInterpolator)this.animateOutInterpolator).setDuration(500L);
            }
            this.container.animate().translationYBy((float)(-this.translationY)).setInterpolator((TimeInterpolator)this.animateOutInterpolator).setDuration(833L).setListener(this.mHideAnimatorEndListener);
        }
    }
    
    @Override
    public int getLayoutId() {
        return 2130903321;
    }
    
    @Override
    public int getMessageViewId() {
        return 2131821516;
    }
    
    @Override
    public int getSecondaryMessageViewId() {
        return 2131821517;
    }
    
    @Override
    protected void showInternal() {
        super.showInternal();
        if (this.container != null) {
            this.setTranslationYForAnimation();
            this.container.setAlpha(1.0f);
            this.netflixBarView.setAlpha(1.0f);
            this.netflixBarView.animate().translationYBy((float)(-this.netflixBarView.getMeasuredHeight())).setInterpolator((TimeInterpolator)this.animateInInterpolator).setDuration(660L);
            this.messageView.animate().alpha(1.0f).translationYBy((float)(-this.translationY)).setInterpolator((TimeInterpolator)this.animateInInterpolator).setDuration(833L);
            if (this.hasSecondMessage) {
                this.secondaryMessageView.animate().alpha(1.0f).translationYBy((float)(-this.translationY)).setInterpolator((TimeInterpolator)this.animateInInterpolator).setDuration(660L).setStartDelay(100L);
            }
            this.container.animate().translationYBy((float)(-this.translationY)).setInterpolator((TimeInterpolator)this.animateInInterpolator).setDuration(660L).setListener((Animator$AnimatorListener)null);
        }
    }
}
