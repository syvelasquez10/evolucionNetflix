// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import android.graphics.Rect;
import com.facebook.react.touch.ReactHitSlopView;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.UiThreadUtil;
import android.view.ViewGroup;
import android.view.View;
import android.graphics.PointF;
import android.graphics.Matrix;

public class TouchTargetHelper
{
    private static final float[] mEventCoords;
    private static final Matrix mInverseMatrix;
    private static final float[] mMatrixTransformCoords;
    private static final PointF mTempPoint;
    
    static {
        mEventCoords = new float[2];
        mTempPoint = new PointF();
        mMatrixTransformCoords = new float[2];
        mInverseMatrix = new Matrix();
    }
    
    private static View findClosestReactAncestor(View view) {
        while (view != null && view.getId() <= 0) {
            view = (View)view.getParent();
        }
        return view;
    }
    
    public static int findTargetTagAndCoordinatesForTouch(final float n, final float n2, final ViewGroup viewGroup, final float[] array, final int[] array2) {
        UiThreadUtil.assertOnUiThread();
        final int id = viewGroup.getId();
        array[0] = n;
        array[1] = n2;
        final View touchTargetView = findTouchTargetView(array, viewGroup);
        int touchTargetForView = id;
        if (touchTargetView != null) {
            final View closestReactAncestor = findClosestReactAncestor(touchTargetView);
            touchTargetForView = id;
            if (closestReactAncestor != null) {
                if (array2 != null) {
                    array2[0] = closestReactAncestor.getId();
                }
                touchTargetForView = getTouchTargetForView(closestReactAncestor, array[0], array[1]);
            }
        }
        return touchTargetForView;
    }
    
    public static int findTargetTagForTouch(final float n, final float n2, final ViewGroup viewGroup) {
        return findTargetTagAndCoordinatesForTouch(n, n2, viewGroup, TouchTargetHelper.mEventCoords, null);
    }
    
    private static View findTouchTargetView(final float[] array, final ViewGroup viewGroup) {
        int n = viewGroup.getChildCount() - 1;
        Object touchTargetViewWithPointerEvents;
        while (true) {
            touchTargetViewWithPointerEvents = viewGroup;
            if (n < 0) {
                break;
            }
            final View child = viewGroup.getChildAt(n);
            final PointF mTempPoint = TouchTargetHelper.mTempPoint;
            if (isTransformedTouchPointInView(array[0], array[1], viewGroup, child, mTempPoint)) {
                final float n2 = array[0];
                final float n3 = array[1];
                array[0] = mTempPoint.x;
                array[1] = mTempPoint.y;
                touchTargetViewWithPointerEvents = findTouchTargetViewWithPointerEvents(array, child);
                if (touchTargetViewWithPointerEvents != null) {
                    break;
                }
                array[0] = n2;
                array[1] = n3;
            }
            --n;
        }
        return (View)touchTargetViewWithPointerEvents;
    }
    
    private static View findTouchTargetViewWithPointerEvents(final float[] array, final View view) {
        PointerEvents pointerEvents;
        if (view instanceof ReactPointerEventsView) {
            pointerEvents = ((ReactPointerEventsView)view).getPointerEvents();
        }
        else {
            pointerEvents = PointerEvents.AUTO;
        }
        PointerEvents pointerEvents2 = pointerEvents;
        if (!view.isEnabled()) {
            if (pointerEvents == PointerEvents.AUTO) {
                pointerEvents2 = PointerEvents.BOX_NONE;
            }
            else if ((pointerEvents2 = pointerEvents) == PointerEvents.BOX_ONLY) {
                pointerEvents2 = PointerEvents.NONE;
            }
        }
        View view2;
        if (pointerEvents2 == PointerEvents.NONE) {
            view2 = null;
        }
        else {
            view2 = view;
            if (pointerEvents2 != PointerEvents.BOX_ONLY) {
                if (pointerEvents2 == PointerEvents.BOX_NONE) {
                    if (view instanceof ViewGroup) {
                        final View touchTargetView = findTouchTargetView(array, (ViewGroup)view);
                        if (touchTargetView != view) {
                            return touchTargetView;
                        }
                        if (view instanceof ReactCompoundView) {
                            view2 = view;
                            if (((ReactCompoundView)view).reactTagForTouch(array[0], array[1]) != view.getId()) {
                                return view2;
                            }
                        }
                    }
                    return null;
                }
                if (pointerEvents2 != PointerEvents.AUTO) {
                    throw new JSApplicationIllegalArgumentException("Unknown pointer event type: " + pointerEvents2.toString());
                }
                if (view instanceof ReactCompoundViewGroup) {
                    view2 = view;
                    if (((ReactCompoundViewGroup)view).interceptsTouchEvent(array[0], array[1])) {
                        return view2;
                    }
                }
                view2 = view;
                if (view instanceof ViewGroup) {
                    return findTouchTargetView(array, (ViewGroup)view);
                }
            }
        }
        return view2;
    }
    
    private static int getTouchTargetForView(final View view, final float n, final float n2) {
        if (view instanceof ReactCompoundView) {
            return ((ReactCompoundView)view).reactTagForTouch(n, n2);
        }
        return view.getId();
    }
    
    private static boolean isTransformedTouchPointInView(float n, float n2, final ViewGroup viewGroup, final View view, final PointF pointF) {
        n = viewGroup.getScrollX() + n - view.getLeft();
        n2 = viewGroup.getScrollY() + n2 - view.getTop();
        final Matrix matrix = view.getMatrix();
        if (!matrix.isIdentity()) {
            final float[] mMatrixTransformCoords = TouchTargetHelper.mMatrixTransformCoords;
            mMatrixTransformCoords[0] = n;
            mMatrixTransformCoords[1] = n2;
            final Matrix mInverseMatrix = TouchTargetHelper.mInverseMatrix;
            matrix.invert(mInverseMatrix);
            mInverseMatrix.mapPoints(mMatrixTransformCoords);
            n = mMatrixTransformCoords[0];
            n2 = mMatrixTransformCoords[1];
        }
        if (view instanceof ReactHitSlopView && ((ReactHitSlopView)view).getHitSlopRect() != null) {
            final Rect hitSlopRect = ((ReactHitSlopView)view).getHitSlopRect();
            if (n >= -hitSlopRect.left && n < view.getRight() - view.getLeft() + hitSlopRect.right && n2 >= -hitSlopRect.top && n2 < hitSlopRect.bottom + (view.getBottom() - view.getTop())) {
                pointF.set(n, n2);
                return true;
            }
            return false;
        }
        else {
            if (n >= 0.0f && n < view.getRight() - view.getLeft() && n2 >= 0.0f && n2 < view.getBottom() - view.getTop()) {
                pointF.set(n, n2);
                return true;
            }
            return false;
        }
    }
}
