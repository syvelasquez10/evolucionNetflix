// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.util.AndroidRuntimeException;
import android.animation.TimeInterpolator;
import android.view.ViewGroup;
import android.view.View;
import java.util.Iterator;
import java.util.ArrayList;
import android.annotation.TargetApi;

@TargetApi(14)
class TransitionSetPort extends TransitionPort
{
    int mCurrentListeners;
    private boolean mPlayTogether;
    boolean mStarted;
    ArrayList<TransitionPort> mTransitions;
    
    public TransitionSetPort() {
        this.mTransitions = new ArrayList<TransitionPort>();
        this.mStarted = false;
        this.mPlayTogether = true;
    }
    
    private void setupStartEndListeners() {
        final TransitionSetPort$TransitionSetListener transitionSetPort$TransitionSetListener = new TransitionSetPort$TransitionSetListener(this);
        final Iterator<TransitionPort> iterator = this.mTransitions.iterator();
        while (iterator.hasNext()) {
            iterator.next().addListener(transitionSetPort$TransitionSetListener);
        }
        this.mCurrentListeners = this.mTransitions.size();
    }
    
    @Override
    public TransitionSetPort addListener(final TransitionPort$TransitionListener transitionPort$TransitionListener) {
        return (TransitionSetPort)super.addListener(transitionPort$TransitionListener);
    }
    
    @Override
    public TransitionSetPort addTarget(final int n) {
        return (TransitionSetPort)super.addTarget(n);
    }
    
    @Override
    public TransitionSetPort addTarget(final View view) {
        return (TransitionSetPort)super.addTarget(view);
    }
    
    public TransitionSetPort addTransition(final TransitionPort transitionPort) {
        if (transitionPort != null) {
            this.mTransitions.add(transitionPort);
            transitionPort.mParent = this;
            if (this.mDuration >= 0L) {
                transitionPort.setDuration(this.mDuration);
            }
        }
        return this;
    }
    
    @Override
    public void captureEndValues(final TransitionValues transitionValues) {
        final int id = transitionValues.view.getId();
        if (this.isValidTarget(transitionValues.view, id)) {
            for (final TransitionPort transitionPort : this.mTransitions) {
                if (transitionPort.isValidTarget(transitionValues.view, id)) {
                    transitionPort.captureEndValues(transitionValues);
                }
            }
        }
    }
    
    @Override
    public void captureStartValues(final TransitionValues transitionValues) {
        final int id = transitionValues.view.getId();
        if (this.isValidTarget(transitionValues.view, id)) {
            for (final TransitionPort transitionPort : this.mTransitions) {
                if (transitionPort.isValidTarget(transitionValues.view, id)) {
                    transitionPort.captureStartValues(transitionValues);
                }
            }
        }
    }
    
    @Override
    public TransitionSetPort clone() {
        final TransitionSetPort transitionSetPort = (TransitionSetPort)super.clone();
        transitionSetPort.mTransitions = new ArrayList<TransitionPort>();
        for (int size = this.mTransitions.size(), i = 0; i < size; ++i) {
            transitionSetPort.addTransition(this.mTransitions.get(i).clone());
        }
        return transitionSetPort;
    }
    
    @Override
    protected void createAnimators(final ViewGroup viewGroup, final TransitionValuesMaps transitionValuesMaps, final TransitionValuesMaps transitionValuesMaps2) {
        final Iterator<TransitionPort> iterator = this.mTransitions.iterator();
        while (iterator.hasNext()) {
            iterator.next().createAnimators(viewGroup, transitionValuesMaps, transitionValuesMaps2);
        }
    }
    
    @Override
    public void pause(final View view) {
        super.pause(view);
        for (int size = this.mTransitions.size(), i = 0; i < size; ++i) {
            this.mTransitions.get(i).pause(view);
        }
    }
    
    @Override
    public TransitionSetPort removeListener(final TransitionPort$TransitionListener transitionPort$TransitionListener) {
        return (TransitionSetPort)super.removeListener(transitionPort$TransitionListener);
    }
    
    @Override
    public TransitionSetPort removeTarget(final int n) {
        return (TransitionSetPort)super.removeTarget(n);
    }
    
    @Override
    public TransitionSetPort removeTarget(final View view) {
        return (TransitionSetPort)super.removeTarget(view);
    }
    
    @Override
    public void resume(final View view) {
        super.resume(view);
        for (int size = this.mTransitions.size(), i = 0; i < size; ++i) {
            this.mTransitions.get(i).resume(view);
        }
    }
    
    @Override
    protected void runAnimators() {
        if (this.mTransitions.isEmpty()) {
            this.start();
            this.end();
        }
        else {
            this.setupStartEndListeners();
            if (!this.mPlayTogether) {
                for (int i = 1; i < this.mTransitions.size(); ++i) {
                    this.mTransitions.get(i - 1).addListener(new TransitionSetPort$1(this, this.mTransitions.get(i)));
                }
                final TransitionPort transitionPort = this.mTransitions.get(0);
                if (transitionPort != null) {
                    transitionPort.runAnimators();
                }
            }
            else {
                final Iterator<TransitionPort> iterator = this.mTransitions.iterator();
                while (iterator.hasNext()) {
                    iterator.next().runAnimators();
                }
            }
        }
    }
    
    @Override
    public TransitionSetPort setDuration(final long n) {
        super.setDuration(n);
        if (this.mDuration >= 0L) {
            for (int size = this.mTransitions.size(), i = 0; i < size; ++i) {
                this.mTransitions.get(i).setDuration(n);
            }
        }
        return this;
    }
    
    @Override
    public TransitionSetPort setInterpolator(final TimeInterpolator interpolator) {
        return (TransitionSetPort)super.setInterpolator(interpolator);
    }
    
    public TransitionSetPort setOrdering(final int n) {
        switch (n) {
            default: {
                throw new AndroidRuntimeException("Invalid parameter for TransitionSet ordering: " + n);
            }
            case 1: {
                this.mPlayTogether = false;
                return this;
            }
            case 0: {
                this.mPlayTogether = true;
                return this;
            }
        }
    }
    
    @Override
    public TransitionSetPort setStartDelay(final long startDelay) {
        return (TransitionSetPort)super.setStartDelay(startDelay);
    }
    
    @Override
    String toString(final String s) {
        String s2 = super.toString(s);
        for (int i = 0; i < this.mTransitions.size(); ++i) {
            s2 = s2 + "\n" + this.mTransitions.get(i).toString(s + "  ");
        }
        return s2;
    }
}
