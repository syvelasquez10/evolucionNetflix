// 
// Decompiled by Procyon v0.5.30
// 

package android.support.graphics.drawable;

import java.util.Stack;
import android.content.res.XmlResourceParser;
import java.io.IOException;
import android.util.Log;
import org.xmlpull.v1.XmlPullParserException;
import android.util.Xml;
import android.support.v4.content.res.ResourcesCompat;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PorterDuffColorFilter;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.animation.AnimatorInflater;
import android.graphics.Region;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable$ConstantState;
import android.graphics.ColorFilter;
import android.graphics.Canvas;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.animation.TypeEvaluator;
import android.animation.ObjectAnimator;
import android.animation.AnimatorSet;
import android.support.v4.util.ArrayMap;
import android.os.Build$VERSION;
import android.content.res.TypedArray;
import java.util.ArrayList;
import android.animation.Animator;
import android.content.res.Resources$Theme;
import android.util.AttributeSet;
import org.xmlpull.v1.XmlPullParser;
import android.content.res.Resources;
import android.content.Context;
import android.graphics.drawable.Drawable$Callback;
import android.animation.ArgbEvaluator;
import android.annotation.TargetApi;
import android.graphics.drawable.Animatable;

@TargetApi(21)
public class AnimatedVectorDrawableCompat extends VectorDrawableCommon implements Animatable
{
    private AnimatedVectorDrawableCompat$AnimatedVectorDrawableCompatState mAnimatedVectorState;
    private ArgbEvaluator mArgbEvaluator;
    final Drawable$Callback mCallback;
    private Context mContext;
    
    AnimatedVectorDrawableCompat() {
        this(null, null, null);
    }
    
    private AnimatedVectorDrawableCompat(final Context context) {
        this(context, null, null);
    }
    
    private AnimatedVectorDrawableCompat(final Context mContext, final AnimatedVectorDrawableCompat$AnimatedVectorDrawableCompatState mAnimatedVectorState, final Resources resources) {
        this.mArgbEvaluator = null;
        this.mCallback = (Drawable$Callback)new AnimatedVectorDrawableCompat$1(this);
        this.mContext = mContext;
        if (mAnimatedVectorState != null) {
            this.mAnimatedVectorState = mAnimatedVectorState;
            return;
        }
        this.mAnimatedVectorState = new AnimatedVectorDrawableCompat$AnimatedVectorDrawableCompatState(mContext, mAnimatedVectorState, this.mCallback, resources);
    }
    
    public static AnimatedVectorDrawableCompat createFromXmlInner(final Context context, final Resources resources, final XmlPullParser xmlPullParser, final AttributeSet set, final Resources$Theme resources$Theme) {
        final AnimatedVectorDrawableCompat animatedVectorDrawableCompat = new AnimatedVectorDrawableCompat(context);
        animatedVectorDrawableCompat.inflate(resources, xmlPullParser, set, resources$Theme);
        return animatedVectorDrawableCompat;
    }
    
    private boolean isStarted() {
        final ArrayList<Animator> mAnimators = this.mAnimatedVectorState.mAnimators;
        if (mAnimators == null) {
            return false;
        }
        for (int size = mAnimators.size(), i = 0; i < size; ++i) {
            if (mAnimators.get(i).isRunning()) {
                return true;
            }
        }
        return false;
    }
    
    static TypedArray obtainAttributes(final Resources resources, final Resources$Theme resources$Theme, final AttributeSet set, final int[] array) {
        if (resources$Theme == null) {
            return resources.obtainAttributes(set, array);
        }
        return resources$Theme.obtainStyledAttributes(set, array, 0, 0);
    }
    
    private void setupAnimatorsForTarget(final String s, final Animator animator) {
        animator.setTarget(this.mAnimatedVectorState.mVectorDrawable.getTargetByName(s));
        if (Build$VERSION.SDK_INT < 21) {
            this.setupColorAnimator(animator);
        }
        if (this.mAnimatedVectorState.mAnimators == null) {
            this.mAnimatedVectorState.mAnimators = new ArrayList<Animator>();
            this.mAnimatedVectorState.mTargetNameMap = new ArrayMap<Animator, String>();
        }
        this.mAnimatedVectorState.mAnimators.add(animator);
        this.mAnimatedVectorState.mTargetNameMap.put(animator, s);
    }
    
    private void setupColorAnimator(final Animator animator) {
        if (animator instanceof AnimatorSet) {
            final ArrayList childAnimations = ((AnimatorSet)animator).getChildAnimations();
            if (childAnimations != null) {
                for (int i = 0; i < childAnimations.size(); ++i) {
                    this.setupColorAnimator((Animator)childAnimations.get(i));
                }
            }
        }
        if (animator instanceof ObjectAnimator) {
            final ObjectAnimator objectAnimator = (ObjectAnimator)animator;
            final String propertyName = objectAnimator.getPropertyName();
            if ("fillColor".equals(propertyName) || "strokeColor".equals(propertyName)) {
                if (this.mArgbEvaluator == null) {
                    this.mArgbEvaluator = new ArgbEvaluator();
                }
                objectAnimator.setEvaluator((TypeEvaluator)this.mArgbEvaluator);
            }
        }
    }
    
    @Override
    public void applyTheme(final Resources$Theme resources$Theme) {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.applyTheme(this.mDelegateDrawable, resources$Theme);
        }
    }
    
    public boolean canApplyTheme() {
        return this.mDelegateDrawable != null && DrawableCompat.canApplyTheme(this.mDelegateDrawable);
    }
    
    public void draw(final Canvas canvas) {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.draw(canvas);
        }
        else {
            this.mAnimatedVectorState.mVectorDrawable.draw(canvas);
            if (this.isStarted()) {
                this.invalidateSelf();
            }
        }
    }
    
    public int getAlpha() {
        if (this.mDelegateDrawable != null) {
            return DrawableCompat.getAlpha(this.mDelegateDrawable);
        }
        return this.mAnimatedVectorState.mVectorDrawable.getAlpha();
    }
    
    public int getChangingConfigurations() {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getChangingConfigurations();
        }
        return super.getChangingConfigurations() | this.mAnimatedVectorState.mChangingConfigurations;
    }
    
    public Drawable$ConstantState getConstantState() {
        if (this.mDelegateDrawable != null) {
            return new AnimatedVectorDrawableCompat$AnimatedVectorDrawableDelegateState(this.mDelegateDrawable.getConstantState());
        }
        return null;
    }
    
    public int getIntrinsicHeight() {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getIntrinsicHeight();
        }
        return this.mAnimatedVectorState.mVectorDrawable.getIntrinsicHeight();
    }
    
    public int getIntrinsicWidth() {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getIntrinsicWidth();
        }
        return this.mAnimatedVectorState.mVectorDrawable.getIntrinsicWidth();
    }
    
    public int getOpacity() {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getOpacity();
        }
        return this.mAnimatedVectorState.mVectorDrawable.getOpacity();
    }
    
    public void inflate(final Resources resources, final XmlPullParser xmlPullParser, final AttributeSet set) {
        this.inflate(resources, xmlPullParser, set, null);
    }
    
    public void inflate(final Resources resources, final XmlPullParser xmlPullParser, final AttributeSet set, final Resources$Theme resources$Theme) {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.inflate(this.mDelegateDrawable, resources, xmlPullParser, set, resources$Theme);
        }
        else {
            for (int i = xmlPullParser.getEventType(); i != 1; i = xmlPullParser.next()) {
                if (i == 2) {
                    final String name = xmlPullParser.getName();
                    if ("animated-vector".equals(name)) {
                        final TypedArray obtainAttributes = obtainAttributes(resources, resources$Theme, set, AndroidResources.styleable_AnimatedVectorDrawable);
                        final int resourceId = obtainAttributes.getResourceId(0, 0);
                        if (resourceId != 0) {
                            final VectorDrawableCompat create = VectorDrawableCompat.create(resources, resourceId, resources$Theme);
                            create.setAllowCaching(false);
                            create.setCallback(this.mCallback);
                            if (this.mAnimatedVectorState.mVectorDrawable != null) {
                                this.mAnimatedVectorState.mVectorDrawable.setCallback((Drawable$Callback)null);
                            }
                            this.mAnimatedVectorState.mVectorDrawable = create;
                        }
                        obtainAttributes.recycle();
                    }
                    else if ("target".equals(name)) {
                        final TypedArray obtainAttributes2 = resources.obtainAttributes(set, AndroidResources.styleable_AnimatedVectorDrawableTarget);
                        final String string = obtainAttributes2.getString(0);
                        final int resourceId2 = obtainAttributes2.getResourceId(1, 0);
                        if (resourceId2 != 0) {
                            if (this.mContext == null) {
                                throw new IllegalStateException("Context can't be null when inflating animators");
                            }
                            this.setupAnimatorsForTarget(string, AnimatorInflater.loadAnimator(this.mContext, resourceId2));
                        }
                        obtainAttributes2.recycle();
                    }
                }
            }
        }
    }
    
    public boolean isRunning() {
        if (this.mDelegateDrawable != null) {
            return ((AnimatedVectorDrawable)this.mDelegateDrawable).isRunning();
        }
        final ArrayList<Animator> mAnimators = this.mAnimatedVectorState.mAnimators;
        for (int size = mAnimators.size(), i = 0; i < size; ++i) {
            if (mAnimators.get(i).isRunning()) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isStateful() {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.isStateful();
        }
        return this.mAnimatedVectorState.mVectorDrawable.isStateful();
    }
    
    public Drawable mutate() {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.mutate();
            return this;
        }
        throw new IllegalStateException("Mutate() is not supported for older platform");
    }
    
    @Override
    protected void onBoundsChange(final Rect rect) {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setBounds(rect);
            return;
        }
        this.mAnimatedVectorState.mVectorDrawable.setBounds(rect);
    }
    
    @Override
    protected boolean onLevelChange(final int n) {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.setLevel(n);
        }
        return this.mAnimatedVectorState.mVectorDrawable.setLevel(n);
    }
    
    protected boolean onStateChange(final int[] array) {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.setState(array);
        }
        return this.mAnimatedVectorState.mVectorDrawable.setState(array);
    }
    
    public void setAlpha(final int n) {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setAlpha(n);
            return;
        }
        this.mAnimatedVectorState.mVectorDrawable.setAlpha(n);
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setColorFilter(colorFilter);
            return;
        }
        this.mAnimatedVectorState.mVectorDrawable.setColorFilter(colorFilter);
    }
    
    public void setTint(final int tint) {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.setTint(this.mDelegateDrawable, tint);
            return;
        }
        this.mAnimatedVectorState.mVectorDrawable.setTint(tint);
    }
    
    public void setTintList(final ColorStateList tintList) {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.setTintList(this.mDelegateDrawable, tintList);
            return;
        }
        this.mAnimatedVectorState.mVectorDrawable.setTintList(tintList);
    }
    
    public void setTintMode(final PorterDuff$Mode tintMode) {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.setTintMode(this.mDelegateDrawable, tintMode);
            return;
        }
        this.mAnimatedVectorState.mVectorDrawable.setTintMode(tintMode);
    }
    
    public boolean setVisible(final boolean b, final boolean b2) {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.setVisible(b, b2);
        }
        this.mAnimatedVectorState.mVectorDrawable.setVisible(b, b2);
        return super.setVisible(b, b2);
    }
    
    public void start() {
        if (this.mDelegateDrawable != null) {
            ((AnimatedVectorDrawable)this.mDelegateDrawable).start();
        }
        else if (!this.isStarted()) {
            final ArrayList<Animator> mAnimators = this.mAnimatedVectorState.mAnimators;
            for (int size = mAnimators.size(), i = 0; i < size; ++i) {
                mAnimators.get(i).start();
            }
            this.invalidateSelf();
        }
    }
    
    public void stop() {
        if (this.mDelegateDrawable != null) {
            ((AnimatedVectorDrawable)this.mDelegateDrawable).stop();
        }
        else {
            final ArrayList<Animator> mAnimators = this.mAnimatedVectorState.mAnimators;
            for (int size = mAnimators.size(), i = 0; i < size; ++i) {
                mAnimators.get(i).end();
            }
        }
    }
}
