// 
// Decompiled by Procyon v0.5.30
// 

package android.support.graphics.drawable;

import android.graphics.drawable.Drawable;
import android.content.res.Resources;
import android.graphics.drawable.Drawable$Callback;
import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.animation.Animator;
import java.util.ArrayList;
import android.graphics.drawable.Drawable$ConstantState;

class AnimatedVectorDrawableCompat$AnimatedVectorDrawableCompatState extends Drawable$ConstantState
{
    ArrayList<Animator> mAnimators;
    int mChangingConfigurations;
    ArrayMap<Animator, String> mTargetNameMap;
    VectorDrawableCompat mVectorDrawable;
    
    public AnimatedVectorDrawableCompat$AnimatedVectorDrawableCompatState(final Context context, final AnimatedVectorDrawableCompat$AnimatedVectorDrawableCompatState animatedVectorDrawableCompat$AnimatedVectorDrawableCompatState, final Drawable$Callback callback, final Resources resources) {
        int i = 0;
        if (animatedVectorDrawableCompat$AnimatedVectorDrawableCompatState != null) {
            this.mChangingConfigurations = animatedVectorDrawableCompat$AnimatedVectorDrawableCompatState.mChangingConfigurations;
            if (animatedVectorDrawableCompat$AnimatedVectorDrawableCompatState.mVectorDrawable != null) {
                final Drawable$ConstantState constantState = animatedVectorDrawableCompat$AnimatedVectorDrawableCompatState.mVectorDrawable.getConstantState();
                if (resources != null) {
                    this.mVectorDrawable = (VectorDrawableCompat)constantState.newDrawable(resources);
                }
                else {
                    this.mVectorDrawable = (VectorDrawableCompat)constantState.newDrawable();
                }
                (this.mVectorDrawable = (VectorDrawableCompat)this.mVectorDrawable.mutate()).setCallback(callback);
                this.mVectorDrawable.setBounds(animatedVectorDrawableCompat$AnimatedVectorDrawableCompatState.mVectorDrawable.getBounds());
                this.mVectorDrawable.setAllowCaching(false);
            }
            if (animatedVectorDrawableCompat$AnimatedVectorDrawableCompatState.mAnimators != null) {
                final int size = animatedVectorDrawableCompat$AnimatedVectorDrawableCompatState.mAnimators.size();
                this.mAnimators = new ArrayList<Animator>(size);
                this.mTargetNameMap = new ArrayMap<Animator, String>(size);
                while (i < size) {
                    final Animator animator = animatedVectorDrawableCompat$AnimatedVectorDrawableCompatState.mAnimators.get(i);
                    final Animator clone = animator.clone();
                    final String s = animatedVectorDrawableCompat$AnimatedVectorDrawableCompatState.mTargetNameMap.get(animator);
                    clone.setTarget(this.mVectorDrawable.getTargetByName(s));
                    this.mAnimators.add(clone);
                    this.mTargetNameMap.put(clone, s);
                    ++i;
                }
            }
        }
    }
    
    public int getChangingConfigurations() {
        return this.mChangingConfigurations;
    }
    
    public Drawable newDrawable() {
        throw new IllegalStateException("No constant state support for SDK < 24.");
    }
    
    public Drawable newDrawable(final Resources resources) {
        throw new IllegalStateException("No constant state support for SDK < 24.");
    }
}
