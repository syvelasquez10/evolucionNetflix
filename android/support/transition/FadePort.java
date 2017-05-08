// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.support.v4.view.ViewCompat;
import android.view.ViewGroup;
import android.animation.Animator$AnimatorListener;
import android.util.Log;
import android.animation.ObjectAnimator;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.annotation.TargetApi;

@TargetApi(14)
class FadePort extends VisibilityPort
{
    private static boolean DBG;
    private int mFadingMode;
    
    static {
        FadePort.DBG = false;
    }
    
    public FadePort() {
        this(3);
    }
    
    public FadePort(final int mFadingMode) {
        this.mFadingMode = mFadingMode;
    }
    
    private void captureValues(final TransitionValues transitionValues) {
        final int[] array = new int[2];
        transitionValues.view.getLocationOnScreen(array);
        transitionValues.values.put("android:fade:screenX", array[0]);
        transitionValues.values.put("android:fade:screenY", array[1]);
    }
    
    private Animator createAnimation(final View view, final float n, final float n2, final AnimatorListenerAdapter animatorListenerAdapter) {
        final Animator animator = null;
        Object o;
        if (n == n2) {
            o = animator;
            if (animatorListenerAdapter != null) {
                animatorListenerAdapter.onAnimationEnd((Animator)null);
                o = animator;
            }
        }
        else {
            final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)view, "alpha", new float[] { n, n2 });
            if (FadePort.DBG) {
                Log.d("Fade", "Created animator " + ofFloat);
            }
            o = ofFloat;
            if (animatorListenerAdapter != null) {
                ofFloat.addListener((Animator$AnimatorListener)animatorListenerAdapter);
                return (Animator)ofFloat;
            }
        }
        return (Animator)o;
    }
    
    @Override
    public void captureStartValues(final TransitionValues transitionValues) {
        super.captureStartValues(transitionValues);
        this.captureValues(transitionValues);
    }
    
    @Override
    public Animator onAppear(final ViewGroup viewGroup, final TransitionValues transitionValues, final int n, final TransitionValues transitionValues2, final int n2) {
        if ((this.mFadingMode & 0x1) != 0x1 || transitionValues2 == null) {
            return null;
        }
        final View view = transitionValues2.view;
        if (FadePort.DBG) {
            View view2;
            if (transitionValues != null) {
                view2 = transitionValues.view;
            }
            else {
                view2 = null;
            }
            Log.d("Fade", "Fade.onAppear: startView, startVis, endView, endVis = " + view2 + ", " + n + ", " + view + ", " + n2);
        }
        view.setAlpha(0.0f);
        this.addListener(new FadePort$1(this, view));
        return this.createAnimation(view, 0.0f, 1.0f, null);
    }
    
    @Override
    public Animator onDisappear(final ViewGroup viewGroup, final TransitionValues transitionValues, int n, final TransitionValues transitionValues2, final int n2) {
        if ((this.mFadingMode & 0x2) == 0x2) {
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
            if (FadePort.DBG) {
                Log.d("Fade", "Fade.onDisappear: startView, startVis, endView, endVis = " + view + ", " + n + ", " + view2 + ", " + n2);
            }
            View view4 = null;
            View view5 = null;
            View view6 = null;
            Label_0128: {
                if (view2 == null || view2.getParent() == null) {
                    if (view2 != null) {
                        final View view3 = null;
                        view4 = view2;
                        view5 = view2;
                        view6 = view3;
                    }
                    else {
                        if (view != null) {
                            if (view.getParent() == null) {
                                view6 = null;
                                final View view7 = view;
                                view5 = view;
                                view4 = view7;
                                break Label_0128;
                            }
                            if (view.getParent() instanceof View && view.getParent().getParent() == null) {
                                n = ((View)view.getParent()).getId();
                                View view8;
                                if (n != -1 && viewGroup.findViewById(n) != null && this.mCanRemoveViews) {
                                    view8 = view;
                                }
                                else {
                                    view8 = null;
                                    view = null;
                                }
                                final View view9 = null;
                                final View view10 = view8;
                                view5 = view;
                                view6 = view9;
                                view4 = view10;
                                break Label_0128;
                            }
                        }
                        view6 = null;
                        view4 = null;
                        view5 = null;
                    }
                }
                else if (n2 == 4) {
                    final View view11 = view2;
                    final View view12 = null;
                    view5 = view2;
                    view6 = view11;
                    view4 = view12;
                }
                else if (view == view2) {
                    final View view13 = view2;
                    final View view14 = null;
                    view5 = view2;
                    view6 = view13;
                    view4 = view14;
                }
                else {
                    view6 = null;
                    final View view15 = view;
                    view5 = view;
                    view4 = view15;
                }
            }
            if (view4 != null) {
                n = transitionValues.values.get("android:fade:screenX");
                final int intValue = transitionValues.values.get("android:fade:screenY");
                final int[] array = new int[2];
                viewGroup.getLocationOnScreen(array);
                ViewCompat.offsetLeftAndRight(view4, n - array[0] - view4.getLeft());
                ViewCompat.offsetTopAndBottom(view4, intValue - array[1] - view4.getTop());
                ViewGroupOverlay.createFrom(viewGroup).add(view4);
                return this.createAnimation(view5, 1.0f, 0.0f, new FadePort$2(this, view5, view6, n2, view4, viewGroup));
            }
            if (view6 != null) {
                view6.setVisibility(0);
                return this.createAnimation(view5, 1.0f, 0.0f, new FadePort$3(this, view5, view6, n2, view4, viewGroup));
            }
        }
        return null;
    }
}
