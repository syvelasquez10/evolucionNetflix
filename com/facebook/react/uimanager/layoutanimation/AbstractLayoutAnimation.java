// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager.layoutanimation;

import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.bridge.ReadableMap;
import android.view.animation.Animation;
import android.view.View;
import com.facebook.react.common.MapBuilder;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Interpolator;
import java.util.Map;

abstract class AbstractLayoutAnimation
{
    private static final Map<InterpolatorType, Interpolator> INTERPOLATOR;
    protected AnimatedPropertyType mAnimatedProperty;
    private int mDelayMs;
    protected int mDurationMs;
    private Interpolator mInterpolator;
    
    static {
        INTERPOLATOR = MapBuilder.of(InterpolatorType.LINEAR, new LinearInterpolator(), InterpolatorType.EASE_IN, new AccelerateInterpolator(), InterpolatorType.EASE_OUT, new DecelerateInterpolator(), InterpolatorType.EASE_IN_EASE_OUT, new AccelerateDecelerateInterpolator(), InterpolatorType.SPRING, new SimpleSpringInterpolator());
    }
    
    private static Interpolator getInterpolator(final InterpolatorType interpolatorType) {
        final Interpolator interpolator = AbstractLayoutAnimation.INTERPOLATOR.get(interpolatorType);
        if (interpolator == null) {
            throw new IllegalArgumentException("Missing interpolator for type : " + interpolatorType);
        }
        return interpolator;
    }
    
    public final Animation createAnimation(final View view, final int n, final int n2, final int n3, final int n4) {
        Animation animation;
        if (!this.isValid()) {
            animation = null;
        }
        else {
            final Animation animationImpl = this.createAnimationImpl(view, n, n2, n3, n4);
            if ((animation = animationImpl) != null) {
                animationImpl.setDuration((long)(this.mDurationMs * 1));
                animationImpl.setStartOffset((long)(1 * this.mDelayMs));
                animationImpl.setInterpolator(this.mInterpolator);
                return animationImpl;
            }
        }
        return animation;
    }
    
    abstract Animation createAnimationImpl(final View p0, final int p1, final int p2, final int p3, final int p4);
    
    public void initializeFromConfig(final ReadableMap readableMap, int n) {
        AnimatedPropertyType fromString;
        if (readableMap.hasKey("property")) {
            fromString = AnimatedPropertyType.fromString(readableMap.getString("property"));
        }
        else {
            fromString = null;
        }
        this.mAnimatedProperty = fromString;
        if (readableMap.hasKey("duration")) {
            n = readableMap.getInt("duration");
        }
        this.mDurationMs = n;
        if (readableMap.hasKey("delay")) {
            n = readableMap.getInt("delay");
        }
        else {
            n = 0;
        }
        this.mDelayMs = n;
        if (!readableMap.hasKey("type")) {
            throw new IllegalArgumentException("Missing interpolation type.");
        }
        this.mInterpolator = getInterpolator(InterpolatorType.fromString(readableMap.getString("type")));
        if (!this.isValid()) {
            throw new IllegalViewOperationException("Invalid layout animation : " + readableMap);
        }
    }
    
    abstract boolean isValid();
    
    public void reset() {
        this.mAnimatedProperty = null;
        this.mDurationMs = 0;
        this.mDelayMs = 0;
        this.mInterpolator = null;
    }
}
