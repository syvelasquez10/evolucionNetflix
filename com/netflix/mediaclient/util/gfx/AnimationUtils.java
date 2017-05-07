// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.gfx;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.view.animation.GridLayoutAnimationController;
import android.view.animation.LayoutAnimationController;
import android.content.Context;
import android.animation.Animator$AnimatorListener;
import android.view.View;

public class AnimationUtils
{
    public static final float ALPHA_OPAQUE = 1.0f;
    public static final float ALPHA_TRANSPARENT = 0.0f;
    public static final float ALPHA_VALUE_ON_CLICK = 0.65f;
    public static final int ANIM_DURATION_MS = 125;
    private static final float LAYOUT_ANIMATION_DELAY_FRACTION = 0.25f;
    
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
            alphaAnimateView(view, 1.0f, 0.0f, 62, (Animator$AnimatorListener)new AnimationUtils$HideViewOnAnimatorEnd(view));
            return;
        }
        view.setVisibility(8);
    }
    
    public static void setImageBitmapWithPropertyFade(final ImageView imageView, final Bitmap imageBitmap) {
        imageView.setAlpha(0.0f);
        imageView.setImageBitmap(imageBitmap);
        imageView.animate().alpha(1.0f).setDuration(125L).start();
    }
    
    public static void showView(final View view, final boolean b) {
        if (view == null) {
            return;
        }
        if (b) {
            alphaAnimateView(view, 0.0f, 1.0f, 125, (Animator$AnimatorListener)new AnimationUtils$ShowViewOnAnimatorStart(view));
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
        alphaAnimateView(view, 0.65f, 1.0f, 125, null);
    }
}
