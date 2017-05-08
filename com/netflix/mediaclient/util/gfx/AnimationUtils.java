// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.gfx;

import android.animation.TimeInterpolator;
import android.view.animation.LinearInterpolator;
import com.netflix.mediaclient.Log;
import android.view.ViewPropertyAnimator;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.netflix.mediaclient.util.AndroidUtils;
import android.view.animation.GridLayoutAnimationController;
import android.view.animation.LayoutAnimationController;
import android.content.Context;
import android.animation.Animator$AnimatorListener;
import android.view.View;

public class AnimationUtils
{
    public static final int ANIM_DURATION_MS = 150;
    public static final int APPEARANCE_ANIMATION_MS = 300;
    private static final float LAYOUT_ANIMATION_DELAY_FRACTION = 0.25f;
    private static final String TAG = "AnimationUtils";
    
    private static void alphaAnimateView(final View view, final float alpha, final float n, final int n2, final Animator$AnimatorListener listener) {
        view.setAlpha(alpha);
        view.animate().withLayer().alpha(n).setDuration((long)n2).setListener(listener).start();
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
        if (b) {
            alphaAnimateView(view, 1.0f, 0.0f, 75, (Animator$AnimatorListener)new AnimationUtils$HideViewOnAnimatorEnd(view));
            return;
        }
        view.setVisibility(8);
    }
    
    public static boolean isTransitionAnimationSupported() {
        return AndroidUtils.getAndroidVersion() >= 21;
    }
    
    public static void setImageBitmapWithPropertyFade(final ImageView imageView, final Bitmap imageBitmap) {
        imageView.setAlpha(0.0f);
        imageView.setImageBitmap(imageBitmap);
        imageView.animate().alpha(1.0f).setDuration(150L).start();
    }
    
    public static void showView(final View view, final boolean b) {
        if (view == null) {
            return;
        }
        if (b) {
            alphaAnimateView(view, 0.0f, 1.0f, 150, (Animator$AnimatorListener)new AnimationUtils$ShowViewOnAnimatorStart(view));
            return;
        }
        view.animate().cancel();
        view.setAlpha(1.0f);
        view.setVisibility(0);
    }
    
    public static void showViewIfHidden(final View view, final boolean b) {
        if (view != null && view.getVisibility() != 0) {
            showView(view, b);
        }
    }
    
    public static void startPressedStateCompleteAnimation(final View view, final Animator$AnimatorListener animator$AnimatorListener) {
        alphaAnimateView(view, 0.7f, 1.0f, 150, animator$AnimatorListener);
    }
    
    public static ViewPropertyAnimator startViewAppearanceAnimation(final View view, final boolean b) {
        return startViewAppearanceAnimation(view, b, 300);
    }
    
    public static ViewPropertyAnimator startViewAppearanceAnimation(final View view, final boolean b, final int n) {
        if (Log.isLoggable()) {
            Log.i("AnimationUtils", "startViewAppearanceAnimation() shouldAppear: " + b);
        }
        float n2;
        if (b) {
            n2 = 1.0f;
        }
        else {
            n2 = 0.0f;
        }
        final ViewPropertyAnimator alpha = view.animate().alpha(n2);
        alpha.setDuration((long)n).setInterpolator((TimeInterpolator)new LinearInterpolator()).setListener((Animator$AnimatorListener)new AnimationUtils$1(b, view, alpha));
        alpha.start();
        return alpha;
    }
}
