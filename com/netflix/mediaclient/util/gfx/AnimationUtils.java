// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.gfx;

import android.animation.Animator;
import android.animation.Animator$AnimatorListener;
import android.graphics.drawable.TransitionDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.view.animation.GridLayoutAnimationController;
import android.view.animation.LayoutAnimationController;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;
import android.view.View;

public class AnimationUtils
{
    public static final int ANIM_DURATION_MS = 125;
    private static final float LAYOUT_ANIMATION_DELAY_FRACTION = 0.25f;
    public static final float NO_ALPHA = 1.0f;
    public static final float ON_CLICK_ALPHA_VALUE = 0.65f;
    
    private static void animateView(final View view, final int n, final int n2, final Animation$AnimationListener animationListener) {
        final Animation loadAnimation = android.view.animation.AnimationUtils.loadAnimation(view.getContext(), n);
        loadAnimation.setAnimationListener(animationListener);
        loadAnimation.setDuration((long)n2);
        view.startAnimation(loadAnimation);
    }
    
    public static LayoutAnimationController createGridLayoutAnimator(final Context context) {
        final GridLayoutAnimationController gridLayoutAnimationController = new GridLayoutAnimationController(android.view.animation.AnimationUtils.loadAnimation(context, 17432576));
        gridLayoutAnimationController.setColumnDelay(0.25f);
        gridLayoutAnimationController.setRowDelay(0.25f);
        gridLayoutAnimationController.setDirection(0);
        return (LayoutAnimationController)gridLayoutAnimationController;
    }
    
    public static void hideView(final View view, final boolean b) {
        if (view == null) {
            return;
        }
        view.clearAnimation();
        if (b) {
            animateView(view, 17432577, 62, (Animation$AnimationListener)new HideViewOnAnimationEnd(view));
            return;
        }
        view.setVisibility(8);
    }
    
    public static void setImageBitmapWithPropertyFade(final ImageView imageView, final Bitmap imageBitmap) {
        imageView.clearAnimation();
        imageView.setAlpha(0.0f);
        imageView.setImageBitmap(imageBitmap);
        imageView.animate().alpha(1.0f).setDuration(125L).start();
    }
    
    public static void setImageBitmapWithTransitionFade(final ImageView imageView, final Bitmap bitmap) {
        imageView.clearAnimation();
        final BitmapDrawable imageDrawable = new BitmapDrawable(imageView.getResources(), bitmap);
        final Drawable drawable = imageView.getDrawable();
        if (drawable == null) {
            imageView.setImageDrawable((Drawable)imageDrawable);
            return;
        }
        final TransitionDrawable imageDrawable2 = new TransitionDrawable(new Drawable[] { drawable, imageDrawable });
        imageDrawable2.setCrossFadeEnabled(true);
        imageView.setImageDrawable((Drawable)imageDrawable2);
        imageDrawable2.startTransition(125);
    }
    
    public static void showView(final View view, final boolean b) {
        if (view == null) {
            return;
        }
        view.clearAnimation();
        if (b) {
            animateView(view, 17432576, 125, (Animation$AnimationListener)new ShowViewOnAnimationStart(view));
            return;
        }
        view.setVisibility(0);
    }
    
    public static void showViewIfHidden(final View view, final boolean b) {
        if (view != null && view.getVisibility() != 0) {
            showView(view, b);
        }
    }
    
    public static void startAlphaFadeInAnimation(final View view) {
        view.clearAnimation();
        view.setAlpha(0.65f);
        view.animate().alpha(1.0f).setDuration(125L).start();
    }
    
    public static class HideViewOnAnimationEnd implements Animation$AnimationListener
    {
        private final View view;
        
        public HideViewOnAnimationEnd(final View view) {
            this.view = view;
        }
        
        public void onAnimationEnd(final Animation animation) {
            this.view.setVisibility(8);
        }
        
        public void onAnimationRepeat(final Animation animation) {
        }
        
        public void onAnimationStart(final Animation animation) {
        }
    }
    
    public static class HideViewOnAnimatorEnd implements Animator$AnimatorListener
    {
        private final View view;
        
        public HideViewOnAnimatorEnd(final View view) {
            this.view = view;
        }
        
        public void onAnimationCancel(final Animator animator) {
        }
        
        public void onAnimationEnd(final Animator animator) {
            this.view.setVisibility(8);
        }
        
        public void onAnimationRepeat(final Animator animator) {
        }
        
        public void onAnimationStart(final Animator animator) {
        }
    }
    
    private static class ShowViewOnAnimationStart implements Animation$AnimationListener
    {
        private final View view;
        
        public ShowViewOnAnimationStart(final View view) {
            this.view = view;
        }
        
        public void onAnimationEnd(final Animation animation) {
        }
        
        public void onAnimationRepeat(final Animation animation) {
        }
        
        public void onAnimationStart(final Animation animation) {
            this.view.setVisibility(0);
        }
    }
}
