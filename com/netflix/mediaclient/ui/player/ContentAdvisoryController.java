// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.animation.TimeInterpolator;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import android.widget.TextView;
import android.view.View;
import android.animation.Animator$AnimatorListener;
import android.widget.RelativeLayout;
import android.view.animation.Interpolator;

public class ContentAdvisoryController
{
    private static final int ADVISORY_RATING_ANIMATION_START_DELAY_MILLIS = 100;
    private static final int ANIMATION_IN_DURATION_MILLIS = 660;
    private static final int BAR_ANIMATION_OUT_DURATION_MILLIS = 400;
    private static final int CONTAINER_ANIMATE_OUT_DURATION_MILLIS = 833;
    public static final int CONTENT_ADVISORY_START_ANIMATION_DELAY_MILLIS = 500;
    private static final int DEFAULT_CONTENT_ADVISORY_DISPLAY_DURATION_MILLIS = 4000;
    private static final float HIDE = 0.0f;
    private static final int RATING_ANIMATE_OUT_DURATION_MILLIS = 500;
    private static final float SHOW = 1.0f;
    private static final String TAG = "ContentAdvisoryController";
    private Interpolator animateInInterpolator;
    private Interpolator animateOutInterpolator;
    private final RelativeLayout mContainer;
    private int mDisplayDuration;
    private Animator$AnimatorListener mHideAnimatorEndListener;
    private int mNetflixBarHeight;
    private final View mNetflixBarView;
    private final TextView mRating;
    private final TextView mRatingDesc;
    private final int translationY;
    
    ContentAdvisoryController(final NetflixFrag netflixFrag) {
        this.animateInInterpolator = (Interpolator)new DecelerateInterpolator();
        this.animateOutInterpolator = (Interpolator)new AccelerateInterpolator();
        this.mHideAnimatorEndListener = (Animator$AnimatorListener)new ContentAdvisoryController$2(this);
        final View view = netflixFrag.getView();
        this.mNetflixBarView = view.findViewById(2131689736);
        this.mNetflixBarView.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new ContentAdvisoryController$1(this));
        this.mRating = (TextView)view.findViewById(2131689734);
        this.mRatingDesc = (TextView)view.findViewById(2131689735);
        this.mContainer = (RelativeLayout)view.findViewById(2131689733);
        this.translationY = view.getResources().getDimensionPixelSize(2131362066);
        this.mContainer.setVisibility(0);
    }
    
    private void cancelAnimation() {
        this.mNetflixBarView.animate().cancel();
        this.mRatingDesc.animate().cancel();
        this.mRating.animate().cancel();
        this.mContainer.animate().cancel();
    }
    
    public static ContentAdvisoryController createInstance(final NetflixFrag netflixFrag) {
        return new ContentAdvisoryController(netflixFrag);
    }
    
    private void hideAndReset() {
        this.cancelAnimation();
        this.hideViews();
        this.resetViews();
    }
    
    private void hideViews() {
        this.mNetflixBarView.setAlpha(0.0f);
        this.mRating.setAlpha(0.0f);
        this.mRatingDesc.setAlpha(0.0f);
        this.mContainer.setAlpha(0.0f);
    }
    
    private void resetViews() {
        this.mNetflixBarView.setTranslationY(0.0f);
        this.mRating.setTranslationY(0.0f);
        this.mRatingDesc.setTranslationY(0.0f);
        this.mContainer.setTranslationY(0.0f);
    }
    
    private void setTranslationYForAnimation() {
        this.mNetflixBarView.setTranslationY((float)this.mNetflixBarHeight);
        this.mRating.setTranslationY((float)this.translationY);
        this.mRatingDesc.setTranslationY((float)this.translationY);
        this.mContainer.setTranslationY((float)this.translationY);
    }
    
    private void showViews() {
        this.resetViews();
        this.mNetflixBarView.setAlpha(1.0f);
        this.mRating.setAlpha(1.0f);
        this.mRatingDesc.setAlpha(1.0f);
        this.mContainer.setAlpha(1.0f);
    }
    
    public int getDisplayDuration() {
        if (this.mDisplayDuration <= 0) {
            return 4000;
        }
        return this.mDisplayDuration;
    }
    
    public void hide(final boolean b) {
        if (!b) {
            this.hideAndReset();
        }
        else if (this.mContainer != null) {
            this.mNetflixBarView.animate().translationYBy((float)(-this.mNetflixBarHeight)).setInterpolator((TimeInterpolator)this.animateOutInterpolator).setDuration(400L);
            this.mRating.animate().alpha(0.0f).translationYBy((float)(-this.translationY)).setInterpolator((TimeInterpolator)this.animateOutInterpolator).setDuration(500L);
            this.mRatingDesc.animate().alpha(0.0f).translationYBy((float)(-this.translationY)).setInterpolator((TimeInterpolator)this.animateOutInterpolator).setDuration(500L);
            this.mContainer.animate().translationYBy((float)(-this.translationY)).setInterpolator((TimeInterpolator)this.animateOutInterpolator).setDuration(833L).setListener(this.mHideAnimatorEndListener);
        }
    }
    
    public void populateViews(final String text, final String text2) {
        this.mRating.setText((CharSequence)text);
        this.mRatingDesc.setText((CharSequence)text2);
    }
    
    public void setDisplayDuration(final int mDisplayDuration) {
        this.mDisplayDuration = mDisplayDuration;
    }
    
    public void show(final boolean b) {
        if (!b) {
            this.showViews();
        }
        else if (this.mContainer != null) {
            this.setTranslationYForAnimation();
            this.mContainer.setAlpha(1.0f);
            this.mNetflixBarView.setAlpha(1.0f);
            this.mNetflixBarView.animate().translationYBy((float)(-this.mNetflixBarHeight)).setInterpolator((TimeInterpolator)this.animateInInterpolator).setDuration(660L);
            this.mRating.animate().alpha(1.0f).translationYBy((float)(-this.translationY)).setInterpolator((TimeInterpolator)this.animateInInterpolator).setDuration(833L);
            this.mRatingDesc.animate().alpha(1.0f).translationYBy((float)(-this.translationY)).setInterpolator((TimeInterpolator)this.animateInInterpolator).setDuration(660L).setStartDelay(100L);
            this.mContainer.animate().translationYBy((float)(-this.translationY)).setInterpolator((TimeInterpolator)this.animateInInterpolator).setDuration(660L).setListener((Animator$AnimatorListener)null);
        }
    }
}
