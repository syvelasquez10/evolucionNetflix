// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import java.util.Map;
import android.animation.TypeEvaluator;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.Bitmap$Config;
import android.animation.Animator$AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.Animator;
import android.view.ViewGroup;
import android.view.View;
import android.graphics.Rect;
import android.annotation.TargetApi;

@TargetApi(14)
class ChangeBoundsPort extends TransitionPort
{
    private static RectEvaluator sRectEvaluator;
    private static final String[] sTransitionProperties;
    boolean mReparent;
    boolean mResizeClip;
    int[] tempLocation;
    
    static {
        sTransitionProperties = new String[] { "android:changeBounds:bounds", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY" };
        ChangeBoundsPort.sRectEvaluator = new RectEvaluator();
    }
    
    ChangeBoundsPort() {
        this.tempLocation = new int[2];
        this.mResizeClip = false;
        this.mReparent = false;
    }
    
    private void captureValues(final TransitionValues transitionValues) {
        final View view = transitionValues.view;
        transitionValues.values.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
        transitionValues.values.put("android:changeBounds:parent", transitionValues.view.getParent());
        transitionValues.view.getLocationInWindow(this.tempLocation);
        transitionValues.values.put("android:changeBounds:windowX", this.tempLocation[0]);
        transitionValues.values.put("android:changeBounds:windowY", this.tempLocation[1]);
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
        if (transitionValues == null || transitionValues2 == null) {
            return null;
        }
        final Map<String, Object> values = transitionValues.values;
        final Map<String, Object> values2 = transitionValues2.values;
        final ViewGroup viewGroup2 = values.get("android:changeBounds:parent");
        final ViewGroup viewGroup3 = values2.get("android:changeBounds:parent");
        if (viewGroup2 == null || viewGroup3 == null) {
            return null;
        }
        final View view = transitionValues2.view;
        boolean b;
        if (viewGroup2 == viewGroup3 || viewGroup2.getId() == viewGroup3.getId()) {
            b = true;
        }
        else {
            b = false;
        }
        if (!this.mReparent || b) {
            final Rect rect = transitionValues.values.get("android:changeBounds:bounds");
            final Rect rect2 = transitionValues2.values.get("android:changeBounds:bounds");
            final int left = rect.left;
            final int left2 = rect2.left;
            final int top = rect.top;
            final int top2 = rect2.top;
            final int right = rect.right;
            final int right2 = rect2.right;
            final int bottom = rect.bottom;
            final int bottom2 = rect2.bottom;
            final int n = right - left;
            final int n2 = bottom - top;
            final int n3 = right2 - left2;
            final int n4 = bottom2 - top2;
            final int n5 = 0;
            int n6 = 0;
            int n7 = n5;
            if (n != 0) {
                n7 = n5;
                if (n2 != 0) {
                    n7 = n5;
                    if (n3 != 0) {
                        n7 = n5;
                        if (n4 != 0) {
                            if (left != left2) {
                                n6 = 1;
                            }
                            int n8 = n6;
                            if (top != top2) {
                                n8 = n6 + 1;
                            }
                            int n9 = n8;
                            if (right != right2) {
                                n9 = n8 + 1;
                            }
                            n7 = n9;
                            if (bottom != bottom2) {
                                n7 = n9 + 1;
                            }
                        }
                    }
                }
            }
            if (n7 > 0) {
                if (!this.mResizeClip) {
                    final PropertyValuesHolder[] array = new PropertyValuesHolder[n7];
                    if (left != left2) {
                        view.setLeft(left);
                    }
                    if (top != top2) {
                        view.setTop(top);
                    }
                    if (right != right2) {
                        view.setRight(right);
                    }
                    if (bottom != bottom2) {
                        view.setBottom(bottom);
                    }
                    int n10;
                    if (left != left2) {
                        n10 = 1;
                        array[0] = PropertyValuesHolder.ofInt("left", new int[] { left, left2 });
                    }
                    else {
                        n10 = 0;
                    }
                    if (top != top2) {
                        final int n11 = n10 + 1;
                        array[n10] = PropertyValuesHolder.ofInt("top", new int[] { top, top2 });
                        n10 = n11;
                    }
                    if (right != right2) {
                        final int n12 = n10 + 1;
                        array[n10] = PropertyValuesHolder.ofInt("right", new int[] { right, right2 });
                        n10 = n12;
                    }
                    if (bottom != bottom2) {
                        array[n10] = PropertyValuesHolder.ofInt("bottom", new int[] { bottom, bottom2 });
                    }
                    final ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder((Object)view, array);
                    if (view.getParent() instanceof ViewGroup) {
                        final ViewGroup viewGroup4 = (ViewGroup)view.getParent();
                        this.addListener(new ChangeBoundsPort$1(this));
                    }
                    return (Animator)ofPropertyValuesHolder;
                }
                if (n != n3) {
                    view.setRight(Math.max(n, n3) + left2);
                }
                if (n2 != n4) {
                    view.setBottom(Math.max(n2, n4) + top2);
                }
                if (left != left2) {
                    view.setTranslationX((float)(left - left2));
                }
                if (top != top2) {
                    view.setTranslationY((float)(top - top2));
                }
                final float n13 = left2 - left;
                final float n14 = top2 - top;
                final int n15 = n3 - n;
                final int n16 = n4 - n2;
                int n17 = 0;
                if (n13 != 0.0f) {
                    n17 = 1;
                }
                int n18 = n17;
                if (n14 != 0.0f) {
                    n18 = n17 + 1;
                }
                int n19 = 0;
                Label_0732: {
                    if (n15 == 0) {
                        n19 = n18;
                        if (n16 == 0) {
                            break Label_0732;
                        }
                    }
                    n19 = n18 + 1;
                }
                final PropertyValuesHolder[] array2 = new PropertyValuesHolder[n19];
                int n20;
                if (n13 != 0.0f) {
                    n20 = 1;
                    array2[0] = PropertyValuesHolder.ofFloat("translationX", new float[] { view.getTranslationX(), 0.0f });
                }
                else {
                    n20 = 0;
                }
                if (n14 != 0.0f) {
                    array2[n20] = PropertyValuesHolder.ofFloat("translationY", new float[] { view.getTranslationY(), 0.0f });
                }
                if (n15 != 0 || n16 != 0) {
                    new Rect(0, 0, n, n2);
                    new Rect(0, 0, n3, n4);
                }
                final ObjectAnimator ofPropertyValuesHolder2 = ObjectAnimator.ofPropertyValuesHolder((Object)view, array2);
                if (view.getParent() instanceof ViewGroup) {
                    final ViewGroup viewGroup5 = (ViewGroup)view.getParent();
                    this.addListener(new ChangeBoundsPort$2(this));
                }
                ofPropertyValuesHolder2.addListener((Animator$AnimatorListener)new ChangeBoundsPort$3(this));
                return (Animator)ofPropertyValuesHolder2;
            }
        }
        else {
            final int intValue = transitionValues.values.get("android:changeBounds:windowX");
            final int intValue2 = transitionValues.values.get("android:changeBounds:windowY");
            final int intValue3 = transitionValues2.values.get("android:changeBounds:windowX");
            final int intValue4 = transitionValues2.values.get("android:changeBounds:windowY");
            if (intValue != intValue3 || intValue2 != intValue4) {
                viewGroup.getLocationInWindow(this.tempLocation);
                final Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap$Config.ARGB_8888);
                view.draw(new Canvas(bitmap));
                final BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
                view.setVisibility(4);
                ViewOverlay.createFrom((View)viewGroup).add((Drawable)bitmapDrawable);
                final ObjectAnimator ofObject = ObjectAnimator.ofObject((Object)bitmapDrawable, "bounds", (TypeEvaluator)ChangeBoundsPort.sRectEvaluator, new Object[] { new Rect(intValue - this.tempLocation[0], intValue2 - this.tempLocation[1], intValue - this.tempLocation[0] + view.getWidth(), intValue2 - this.tempLocation[1] + view.getHeight()), new Rect(intValue3 - this.tempLocation[0], intValue4 - this.tempLocation[1], intValue3 - this.tempLocation[0] + view.getWidth(), intValue4 - this.tempLocation[1] + view.getHeight()) });
                ofObject.addListener((Animator$AnimatorListener)new ChangeBoundsPort$4(this, viewGroup, bitmapDrawable, view));
                return (Animator)ofObject;
            }
        }
        return null;
    }
    
    @Override
    public String[] getTransitionProperties() {
        return ChangeBoundsPort.sTransitionProperties;
    }
}
