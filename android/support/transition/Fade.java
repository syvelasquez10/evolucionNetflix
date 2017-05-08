// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.animation.Animator;
import android.view.ViewGroup;
import android.os.Build$VERSION;

public class Fade extends Visibility
{
    public Fade() {
        this(-1);
    }
    
    public Fade(final int n) {
        super(true);
        if (Build$VERSION.SDK_INT >= 19) {
            if (n > 0) {
                this.mImpl = new FadeKitKat(this, n);
                return;
            }
            this.mImpl = new FadeKitKat(this);
        }
        else {
            if (n > 0) {
                this.mImpl = new FadeIcs(this, n);
                return;
            }
            this.mImpl = new FadeIcs(this);
        }
    }
    
    @Override
    public void captureEndValues(final TransitionValues transitionValues) {
        this.mImpl.captureEndValues(transitionValues);
    }
    
    @Override
    public void captureStartValues(final TransitionValues transitionValues) {
        this.mImpl.captureStartValues(transitionValues);
    }
    
    @Override
    public Animator createAnimator(final ViewGroup viewGroup, final TransitionValues transitionValues, final TransitionValues transitionValues2) {
        return this.mImpl.createAnimator(viewGroup, transitionValues, transitionValues2);
    }
}
