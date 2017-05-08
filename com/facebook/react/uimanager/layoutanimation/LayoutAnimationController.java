// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager.layoutanimation;

import com.facebook.react.bridge.ReadableMap;
import android.view.animation.Animation$AnimationListener;
import android.view.animation.Animation;
import com.facebook.react.bridge.UiThreadUtil;
import android.view.ViewGroup;
import android.view.View;

public class LayoutAnimationController
{
    private final AbstractLayoutAnimation mLayoutCreateAnimation;
    private final AbstractLayoutAnimation mLayoutDeleteAnimation;
    private final AbstractLayoutAnimation mLayoutUpdateAnimation;
    private boolean mShouldAnimateLayout;
    
    public LayoutAnimationController() {
        this.mLayoutCreateAnimation = new LayoutCreateAnimation();
        this.mLayoutUpdateAnimation = new LayoutUpdateAnimation();
        this.mLayoutDeleteAnimation = new LayoutDeleteAnimation();
    }
    
    private void disableUserInteractions(final View view) {
        int i = 0;
        view.setClickable(false);
        if (view instanceof ViewGroup) {
            for (ViewGroup viewGroup = (ViewGroup)view; i < viewGroup.getChildCount(); ++i) {
                this.disableUserInteractions(viewGroup.getChildAt(i));
            }
        }
    }
    
    public void applyLayoutUpdate(final View view, final int n, final int n2, final int n3, final int n4) {
        UiThreadUtil.assertOnUiThread();
        AbstractLayoutAnimation abstractLayoutAnimation;
        if (view.getWidth() == 0 || view.getHeight() == 0) {
            abstractLayoutAnimation = this.mLayoutCreateAnimation;
        }
        else {
            abstractLayoutAnimation = this.mLayoutUpdateAnimation;
        }
        final Animation animation = abstractLayoutAnimation.createAnimation(view, n, n2, n3, n4);
        if (animation == null || !(animation instanceof HandleLayout)) {
            view.layout(n, n2, n + n3, n2 + n4);
        }
        if (animation != null) {
            view.startAnimation(animation);
        }
    }
    
    public void deleteView(final View view, final LayoutAnimationListener layoutAnimationListener) {
        UiThreadUtil.assertOnUiThread();
        final Animation animation = this.mLayoutDeleteAnimation.createAnimation(view, view.getLeft(), view.getTop(), view.getWidth(), view.getHeight());
        if (animation != null) {
            this.disableUserInteractions(view);
            animation.setAnimationListener((Animation$AnimationListener)new LayoutAnimationController$1(this, layoutAnimationListener));
            view.startAnimation(animation);
            return;
        }
        layoutAnimationListener.onAnimationEnd();
    }
    
    public void initializeFromConfig(final ReadableMap readableMap) {
        int int1 = 0;
        if (readableMap == null) {
            this.reset();
        }
        else {
            this.mShouldAnimateLayout = false;
            if (readableMap.hasKey("duration")) {
                int1 = readableMap.getInt("duration");
            }
            if (readableMap.hasKey(LayoutAnimationType.CREATE.toString())) {
                this.mLayoutCreateAnimation.initializeFromConfig(readableMap.getMap(LayoutAnimationType.CREATE.toString()), int1);
                this.mShouldAnimateLayout = true;
            }
            if (readableMap.hasKey(LayoutAnimationType.UPDATE.toString())) {
                this.mLayoutUpdateAnimation.initializeFromConfig(readableMap.getMap(LayoutAnimationType.UPDATE.toString()), int1);
                this.mShouldAnimateLayout = true;
            }
            if (readableMap.hasKey(LayoutAnimationType.DELETE.toString())) {
                this.mLayoutDeleteAnimation.initializeFromConfig(readableMap.getMap(LayoutAnimationType.DELETE.toString()), int1);
                this.mShouldAnimateLayout = true;
            }
        }
    }
    
    public void reset() {
        this.mLayoutCreateAnimation.reset();
        this.mLayoutUpdateAnimation.reset();
        this.mLayoutDeleteAnimation.reset();
        this.mShouldAnimateLayout = false;
    }
    
    public boolean shouldAnimateLayout(final View view) {
        return this.mShouldAnimateLayout && view.getParent() != null;
    }
}
