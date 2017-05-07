// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.gfx;

import android.annotation.TargetApi;
import android.animation.TimeInterpolator;
import android.view.animation.LinearInterpolator;
import android.os.Build$VERSION;
import android.animation.ObjectAnimator;
import com.netflix.mediaclient.Log;
import android.animation.Animator;
import android.graphics.Bitmap;
import android.widget.ImageView;
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
        view.animate().alpha(n).setDuration((long)n2).setListener(listener).start();
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
        view.setVisibility(0);
    }
    
    public static void showViewIfHidden(final View view, final boolean b) {
        if (view != null && view.getVisibility() != 0) {
            showView(view, b);
        }
    }
    
    public static void startPressedStateCompleteAnimation(final View view) {
        alphaAnimateView(view, 0.7f, 1.0f, 150, null);
    }
    
    @TargetApi(18)
    public static Animator startViewAppearanceAnimation(final View view, final boolean b) {
        float n = 1.0f;
        if (Log.isLoggable()) {
            Log.i("AnimationUtils", "startViewAppearanceAnimation() shouldAppear: " + b);
        }
        float n2;
        if (b) {
            n2 = 0.0f;
        }
        else {
            n2 = 1.0f;
        }
        if (!b) {
            n = 0.0f;
        }
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)view, "alpha", new float[] { n2, n });
        ofFloat.setDuration(300L);
        if (Build$VERSION.SDK_INT >= 18) {
            ofFloat.setAutoCancel(true);
        }
        ofFloat.setInterpolator((TimeInterpolator)new LinearInterpolator());
        ofFloat.addListener((Animator$AnimatorListener)new AnimationUtils$1(b, view, ofFloat));
        ofFloat.start();
        return (Animator)ofFloat;
    }
}
