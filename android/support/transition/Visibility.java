// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.animation.Animator;
import android.view.ViewGroup;
import android.os.Build$VERSION;

public abstract class Visibility extends Transition implements VisibilityInterface
{
    Visibility(final boolean b) {
        super(true);
        if (!b) {
            if (Build$VERSION.SDK_INT >= 19) {
                this.mImpl = new VisibilityKitKat();
            }
            else {
                this.mImpl = new VisibilityIcs();
            }
            this.mImpl.init(this);
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
    public boolean isVisible(final TransitionValues transitionValues) {
        return ((VisibilityImpl)this.mImpl).isVisible(transitionValues);
    }
    
    @Override
    public Animator onAppear(final ViewGroup viewGroup, final TransitionValues transitionValues, final int n, final TransitionValues transitionValues2, final int n2) {
        return ((VisibilityImpl)this.mImpl).onAppear(viewGroup, transitionValues, n, transitionValues2, n2);
    }
    
    @Override
    public Animator onDisappear(final ViewGroup viewGroup, final TransitionValues transitionValues, final int n, final TransitionValues transitionValues2, final int n2) {
        return ((VisibilityImpl)this.mImpl).onDisappear(viewGroup, transitionValues, n, transitionValues2, n2);
    }
}
