// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.view.View;
import android.animation.Animator;
import android.view.ViewGroup;
import android.annotation.TargetApi;

@TargetApi(14)
abstract class VisibilityPort extends TransitionPort
{
    private static final String[] sTransitionProperties;
    
    static {
        sTransitionProperties = new String[] { "android:visibility:visibility", "android:visibility:parent" };
    }
    
    private void captureValues(final TransitionValues transitionValues) {
        transitionValues.values.put("android:visibility:visibility", transitionValues.view.getVisibility());
        transitionValues.values.put("android:visibility:parent", transitionValues.view.getParent());
    }
    
    private VisibilityPort$VisibilityInfo getVisibilityChangeInfo(final TransitionValues transitionValues, final TransitionValues transitionValues2) {
        final VisibilityPort$VisibilityInfo visibilityPort$VisibilityInfo = new VisibilityPort$VisibilityInfo();
        visibilityPort$VisibilityInfo.visibilityChange = false;
        visibilityPort$VisibilityInfo.fadeIn = false;
        if (transitionValues != null) {
            visibilityPort$VisibilityInfo.startVisibility = transitionValues.values.get("android:visibility:visibility");
            visibilityPort$VisibilityInfo.startParent = transitionValues.values.get("android:visibility:parent");
        }
        else {
            visibilityPort$VisibilityInfo.startVisibility = -1;
            visibilityPort$VisibilityInfo.startParent = null;
        }
        if (transitionValues2 != null) {
            visibilityPort$VisibilityInfo.endVisibility = transitionValues2.values.get("android:visibility:visibility");
            visibilityPort$VisibilityInfo.endParent = transitionValues2.values.get("android:visibility:parent");
        }
        else {
            visibilityPort$VisibilityInfo.endVisibility = -1;
            visibilityPort$VisibilityInfo.endParent = null;
        }
        if (transitionValues != null && transitionValues2 != null) {
            if (visibilityPort$VisibilityInfo.startVisibility == visibilityPort$VisibilityInfo.endVisibility && visibilityPort$VisibilityInfo.startParent == visibilityPort$VisibilityInfo.endParent) {
                return visibilityPort$VisibilityInfo;
            }
            if (visibilityPort$VisibilityInfo.startVisibility != visibilityPort$VisibilityInfo.endVisibility) {
                if (visibilityPort$VisibilityInfo.startVisibility == 0) {
                    visibilityPort$VisibilityInfo.fadeIn = false;
                    visibilityPort$VisibilityInfo.visibilityChange = true;
                }
                else if (visibilityPort$VisibilityInfo.endVisibility == 0) {
                    visibilityPort$VisibilityInfo.fadeIn = true;
                    visibilityPort$VisibilityInfo.visibilityChange = true;
                }
            }
            else if (visibilityPort$VisibilityInfo.startParent != visibilityPort$VisibilityInfo.endParent) {
                if (visibilityPort$VisibilityInfo.endParent == null) {
                    visibilityPort$VisibilityInfo.fadeIn = false;
                    visibilityPort$VisibilityInfo.visibilityChange = true;
                }
                else if (visibilityPort$VisibilityInfo.startParent == null) {
                    visibilityPort$VisibilityInfo.fadeIn = true;
                    visibilityPort$VisibilityInfo.visibilityChange = true;
                }
            }
        }
        if (transitionValues == null) {
            visibilityPort$VisibilityInfo.fadeIn = true;
            visibilityPort$VisibilityInfo.visibilityChange = true;
        }
        else if (transitionValues2 == null) {
            visibilityPort$VisibilityInfo.fadeIn = false;
            visibilityPort$VisibilityInfo.visibilityChange = true;
        }
        return visibilityPort$VisibilityInfo;
    }
    
    @Override
    public void captureEndValues(final TransitionValues transitionValues) {
        this.captureValues(transitionValues);
    }
    
    @Override
    public void captureStartValues(final TransitionValues transitionValues) {
        this.captureValues(transitionValues);
    }
    
    @Override
    public Animator createAnimator(final ViewGroup viewGroup, final TransitionValues transitionValues, final TransitionValues transitionValues2) {
        boolean b = false;
        int id = -1;
        final VisibilityPort$VisibilityInfo visibilityChangeInfo = this.getVisibilityChangeInfo(transitionValues, transitionValues2);
        if (visibilityChangeInfo.visibilityChange) {
            if (this.mTargets.size() > 0 || this.mTargetIds.size() > 0) {
                View view;
                if (transitionValues != null) {
                    view = transitionValues.view;
                }
                else {
                    view = null;
                }
                View view2;
                if (transitionValues2 != null) {
                    view2 = transitionValues2.view;
                }
                else {
                    view2 = null;
                }
                int id2;
                if (view != null) {
                    id2 = view.getId();
                }
                else {
                    id2 = -1;
                }
                if (view2 != null) {
                    id = view2.getId();
                }
                if (this.isValidTarget(view, id2) || this.isValidTarget(view2, id)) {
                    b = true;
                }
                else {
                    b = false;
                }
            }
            if (b || visibilityChangeInfo.startParent != null || visibilityChangeInfo.endParent != null) {
                if (visibilityChangeInfo.fadeIn) {
                    return this.onAppear(viewGroup, transitionValues, visibilityChangeInfo.startVisibility, transitionValues2, visibilityChangeInfo.endVisibility);
                }
                return this.onDisappear(viewGroup, transitionValues, visibilityChangeInfo.startVisibility, transitionValues2, visibilityChangeInfo.endVisibility);
            }
        }
        return null;
    }
    
    @Override
    public String[] getTransitionProperties() {
        return VisibilityPort.sTransitionProperties;
    }
    
    public boolean isVisible(final TransitionValues transitionValues) {
        if (transitionValues == null) {
            return false;
        }
        final int intValue = transitionValues.values.get("android:visibility:visibility");
        final View view = transitionValues.values.get("android:visibility:parent");
        return intValue == 0 && view != null;
    }
    
    public Animator onAppear(final ViewGroup viewGroup, final TransitionValues transitionValues, final int n, final TransitionValues transitionValues2, final int n2) {
        return null;
    }
    
    public Animator onDisappear(final ViewGroup viewGroup, final TransitionValues transitionValues, final int n, final TransitionValues transitionValues2, final int n2) {
        return null;
    }
}
