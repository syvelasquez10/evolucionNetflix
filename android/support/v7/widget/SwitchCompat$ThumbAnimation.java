// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.content.res.TypedArray;
import android.support.v7.internal.text.AllCapsTransformationMethod;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.view.accessibility.AccessibilityNodeInfo;
import android.annotation.TargetApi;
import android.view.accessibility.AccessibilityEvent;
import android.graphics.Region$Op;
import android.text.TextUtils;
import android.os.Build$VERSION;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.text.StaticLayout;
import android.text.Layout$Alignment;
import android.support.v7.internal.widget.DrawableUtils;
import android.view.View;
import android.support.v7.internal.widget.ViewUtils;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.graphics.drawable.Drawable$Callback;
import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.appcompat.R$attr;
import android.util.AttributeSet;
import android.content.Context;
import android.view.VelocityTracker;
import android.support.v7.internal.widget.TintManager;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.text.method.TransformationMethod;
import android.text.Layout;
import android.widget.CompoundButton;
import android.view.animation.Transformation;
import android.view.animation.Animation;

class SwitchCompat$ThumbAnimation extends Animation
{
    final float mDiff;
    final float mEndPosition;
    final float mStartPosition;
    final /* synthetic */ SwitchCompat this$0;
    
    private SwitchCompat$ThumbAnimation(final SwitchCompat this$0, final float mStartPosition, final float mEndPosition) {
        this.this$0 = this$0;
        this.mStartPosition = mStartPosition;
        this.mEndPosition = mEndPosition;
        this.mDiff = mEndPosition - mStartPosition;
    }
    
    protected void applyTransformation(final float n, final Transformation transformation) {
        this.this$0.setThumbPosition(this.mStartPosition + this.mDiff * n);
    }
}
