// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import android.graphics.drawable.Drawable$Callback;
import android.annotation.SuppressLint;
import com.facebook.react.bridge.SoftAssertions;
import android.view.MotionEvent;
import android.graphics.Color;
import android.graphics.Paint$Style;
import android.graphics.Typeface;
import android.graphics.Paint$Align;
import android.view.ViewGroup$LayoutParams;
import android.graphics.Canvas;
import android.content.Context;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.touch.OnInterceptTouchEventListener;
import android.graphics.drawable.Drawable;
import android.graphics.Paint;
import android.graphics.Rect;
import java.util.ArrayList;
import android.view.View;
import android.util.SparseArray;
import com.facebook.react.uimanager.ReactPointerEventsView;
import com.facebook.react.uimanager.ReactCompoundViewGroup;
import com.facebook.react.uimanager.ReactClippingViewGroup;
import com.facebook.react.touch.ReactInterceptingViewGroup;
import com.facebook.react.touch.ReactHitSlopView;
import android.view.ViewGroup;

final class FlatViewGroup extends ViewGroup implements ReactHitSlopView, ReactInterceptingViewGroup, ReactClippingViewGroup, ReactCompoundViewGroup, ReactPointerEventsView
{
    private static final SparseArray<View> EMPTY_DETACHED_VIEWS;
    private static final ArrayList<FlatViewGroup> LAYOUT_REQUESTS;
    private static final Rect VIEW_BOUNDS;
    private static Paint sDebugCornerPaint;
    private static Rect sDebugRect;
    private static Paint sDebugRectPaint;
    private static Paint sDebugTextBackgroundPaint;
    private static Paint sDebugTextPaint;
    private boolean mAndroidDebugDraw;
    private AttachDetachListener[] mAttachDetachListeners;
    private int mDrawChildIndex;
    private DrawCommandManager mDrawCommandManager;
    private DrawCommand[] mDrawCommands;
    private Rect mHitSlopRect;
    private Drawable mHotspot;
    private FlatViewGroup$InvalidateCallback mInvalidateCallback;
    private boolean mIsAttached;
    private boolean mIsLayoutRequested;
    private long mLastTouchDownTime;
    private boolean mNeedsOffscreenAlphaCompositing;
    private NodeRegion[] mNodeRegions;
    private OnInterceptTouchEventListener mOnInterceptTouchEventListener;
    private PointerEvents mPointerEvents;
    
    static {
        LAYOUT_REQUESTS = new ArrayList<FlatViewGroup>();
        VIEW_BOUNDS = new Rect();
        EMPTY_DETACHED_VIEWS = new SparseArray(0);
    }
    
    FlatViewGroup(final Context context) {
        super(context);
        this.mDrawCommands = DrawCommand.EMPTY_ARRAY;
        this.mAttachDetachListeners = AttachDetachListener.EMPTY_ARRAY;
        this.mNodeRegions = NodeRegion.EMPTY_ARRAY;
        this.mDrawChildIndex = 0;
        this.mIsAttached = false;
        this.mIsLayoutRequested = false;
        this.mNeedsOffscreenAlphaCompositing = false;
        this.mPointerEvents = PointerEvents.AUTO;
        this.setClipChildren(false);
    }
    
    private NodeRegion anyNodeRegionWithinBounds(final float n, final float n2) {
        if (this.mDrawCommandManager == null) {
            for (int i = this.mNodeRegions.length - 1; i >= 0; --i) {
                final NodeRegion anyNodeRegionWithinBounds;
                if ((anyNodeRegionWithinBounds = this.mNodeRegions[i]).withinBounds(n, n2)) {
                    return anyNodeRegionWithinBounds;
                }
            }
            return null;
        }
        return this.mDrawCommandManager.anyNodeRegionWithinBounds(n, n2);
    }
    
    private void debugDraw(final Canvas canvas) {
        if (this.mDrawCommandManager != null) {
            this.mDrawCommandManager.debugDraw(canvas);
        }
        else {
            final DrawCommand[] mDrawCommands = this.mDrawCommands;
            for (int length = mDrawCommands.length, i = 0; i < length; ++i) {
                mDrawCommands[i].debugDraw(this, canvas);
            }
        }
        this.mDrawChildIndex = 0;
    }
    
    private void debugDrawRect(final Canvas canvas, final int n, final float n2, final float n3, final float n4, final float n5) {
        this.debugDrawNamedRect(canvas, n, "", n2, n3, n4, n5);
    }
    
    private void dispatchOnAttached(final AttachDetachListener[] array) {
        if (array.length != 0) {
            final FlatViewGroup$InvalidateCallback invalidateCallback = this.getInvalidateCallback();
            for (int length = array.length, i = 0; i < length; ++i) {
                array[i].onAttached(invalidateCallback);
            }
        }
    }
    
    private static void dispatchOnDetached(final AttachDetachListener[] array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            array[i].onDetached();
        }
    }
    
    private static void drawCorner(final Canvas canvas, final Paint paint, final float n, final float n2, final float n3, final float n4, final float n5) {
        fillRect(canvas, paint, n, n2, n + n3, n2 + sign(n4) * n5);
        fillRect(canvas, paint, n, n2, n + sign(n3) * n5, n2 + n4);
    }
    
    private static void drawRectCorners(final Canvas canvas, final float n, final float n2, final float n3, final float n4, final Paint paint, final int n5, final int n6) {
        drawCorner(canvas, paint, n, n2, n5, n5, n6);
        drawCorner(canvas, paint, n, n4, n5, -n5, n6);
        drawCorner(canvas, paint, n3, n2, -n5, n5, n6);
        drawCorner(canvas, paint, n3, n4, -n5, -n5, n6);
    }
    
    private ViewGroup$LayoutParams ensureLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (this.checkLayoutParams(viewGroup$LayoutParams)) {
            return viewGroup$LayoutParams;
        }
        return this.generateDefaultLayoutParams();
    }
    
    private static void fillRect(final Canvas canvas, final Paint paint, float n, float n2, float n3, final float n4) {
        if (n != n3 && n2 != n4) {
            if (n > n3) {
                final float n5 = n3;
                n3 = n;
                n = n5;
            }
            float n6;
            if (n2 > n4) {
                n6 = n2;
                n2 = n4;
            }
            else {
                n6 = n4;
            }
            canvas.drawRect(n, n2, n3, n6, paint);
        }
    }
    
    private FlatViewGroup$InvalidateCallback getInvalidateCallback() {
        if (this.mInvalidateCallback == null) {
            this.mInvalidateCallback = new FlatViewGroup$InvalidateCallback(this, (FlatViewGroup$1)null);
        }
        return this.mInvalidateCallback;
    }
    
    private void initDebugDrawResources() {
        if (FlatViewGroup.sDebugTextPaint == null) {
            (FlatViewGroup.sDebugTextPaint = new Paint()).setTextAlign(Paint$Align.RIGHT);
            FlatViewGroup.sDebugTextPaint.setTextSize((float)this.dipsToPixels(9));
            FlatViewGroup.sDebugTextPaint.setTypeface(Typeface.MONOSPACE);
            FlatViewGroup.sDebugTextPaint.setAntiAlias(true);
            FlatViewGroup.sDebugTextPaint.setColor(-65536);
        }
        if (FlatViewGroup.sDebugTextBackgroundPaint == null) {
            (FlatViewGroup.sDebugTextBackgroundPaint = new Paint()).setColor(-1);
            FlatViewGroup.sDebugTextBackgroundPaint.setAlpha(200);
            FlatViewGroup.sDebugTextBackgroundPaint.setStyle(Paint$Style.FILL);
        }
        if (FlatViewGroup.sDebugRectPaint == null) {
            (FlatViewGroup.sDebugRectPaint = new Paint()).setAlpha(100);
            FlatViewGroup.sDebugRectPaint.setStyle(Paint$Style.STROKE);
        }
        if (FlatViewGroup.sDebugCornerPaint == null) {
            (FlatViewGroup.sDebugCornerPaint = new Paint()).setAlpha(200);
            FlatViewGroup.sDebugCornerPaint.setColor(Color.rgb(63, 127, 255));
            FlatViewGroup.sDebugCornerPaint.setStyle(Paint$Style.FILL);
        }
        if (FlatViewGroup.sDebugRect == null) {
            FlatViewGroup.sDebugRect = new Rect();
        }
    }
    
    private static int sign(final float n) {
        if (n >= 0.0f) {
            return 1;
        }
        return -1;
    }
    
    private NodeRegion virtualNodeRegionWithinBounds(final float n, final float n2) {
        if (this.mDrawCommandManager != null) {
            return this.mDrawCommandManager.virtualNodeRegionWithinBounds(n, n2);
        }
        for (int i = this.mNodeRegions.length - 1; i >= 0; --i) {
            final NodeRegion nodeRegion = this.mNodeRegions[i];
            if (nodeRegion.mIsVirtual && nodeRegion.withinBounds(n, n2)) {
                return nodeRegion;
            }
        }
        return null;
    }
    
    void addViewInLayout(final View view, final int n) {
        this.addViewInLayout(view, n, this.ensureLayoutParams(view.getLayoutParams()), true);
    }
    
    void attachViewToParent(final View view, final int n) {
        this.attachViewToParent(view, n, this.ensureLayoutParams(view.getLayoutParams()));
    }
    
    void debugDrawNamedRect(final Canvas canvas, final int n, final String s, final float n2, final float n3, final float n4, final float n5) {
        FlatViewGroup.sDebugRectPaint.setColor((FlatViewGroup.sDebugRectPaint.getColor() & 0xFF000000) | (0xFFFFFF & n));
        FlatViewGroup.sDebugRectPaint.setAlpha(100);
        canvas.drawRect(n2, n3, n4 - 1.0f, n5 - 1.0f, FlatViewGroup.sDebugRectPaint);
        drawRectCorners(canvas, n2, n3, n4, n5, FlatViewGroup.sDebugCornerPaint, this.dipsToPixels(8), this.dipsToPixels(1));
    }
    
    void debugDrawNextChild(final Canvas canvas) {
        final View child = this.getChildAt(this.mDrawChildIndex);
        int n;
        if (child instanceof FlatViewGroup) {
            n = -12303292;
        }
        else {
            n = -65536;
        }
        this.debugDrawRect(canvas, n, child.getLeft(), child.getTop(), child.getRight(), child.getBottom());
        ++this.mDrawChildIndex;
    }
    
    protected void detachAllViewsFromParent() {
        super.detachAllViewsFromParent();
    }
    
    int dipsToPixels(final int n) {
        return (int)(this.getResources().getDisplayMetrics().density * n + 0.5f);
    }
    
    public void dispatchDraw(final Canvas canvas) {
        this.mAndroidDebugDraw = false;
        super.dispatchDraw(canvas);
        if (this.mDrawCommandManager != null) {
            this.mDrawCommandManager.draw(canvas);
        }
        else {
            final DrawCommand[] mDrawCommands = this.mDrawCommands;
            for (int length = mDrawCommands.length, i = 0; i < length; ++i) {
                mDrawCommands[i].draw(this, canvas);
            }
        }
        if (this.mDrawChildIndex != this.getChildCount()) {
            throw new RuntimeException("Did not draw all children: " + this.mDrawChildIndex + " / " + this.getChildCount());
        }
        this.mDrawChildIndex = 0;
        if (this.mAndroidDebugDraw) {
            this.initDebugDrawResources();
            this.debugDraw(canvas);
        }
        if (this.mHotspot != null) {
            this.mHotspot.draw(canvas);
        }
    }
    
    public void dispatchDrawableHotspotChanged(final float n, final float n2) {
        if (this.mHotspot != null) {
            this.mHotspot.setHotspot(n, n2);
            this.invalidate();
        }
    }
    
    protected boolean drawChild(final Canvas canvas, final View view, final long n) {
        return false;
    }
    
    void drawNextChild(final Canvas canvas) {
        final View child = this.getChildAt(this.mDrawChildIndex);
        if (child instanceof FlatViewGroup) {
            super.drawChild(canvas, child, this.getDrawingTime());
        }
        else {
            canvas.save(2);
            child.getHitRect(FlatViewGroup.VIEW_BOUNDS);
            canvas.clipRect(FlatViewGroup.VIEW_BOUNDS);
            super.drawChild(canvas, child, this.getDrawingTime());
            canvas.restore();
        }
        ++this.mDrawChildIndex;
    }
    
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mHotspot != null && this.mHotspot.isStateful()) {
            this.mHotspot.setState(this.getDrawableState());
        }
    }
    
    public void getClippingRect(final Rect rect) {
        if (this.mDrawCommandManager == null) {
            throw new RuntimeException("Trying to get the clipping rect for a non-clipping FlatViewGroup");
        }
        this.mDrawCommandManager.getClippingRect(rect);
    }
    
    public Rect getHitSlopRect() {
        return this.mHitSlopRect;
    }
    
    public PointerEvents getPointerEvents() {
        return this.mPointerEvents;
    }
    
    public boolean getRemoveClippedSubviews() {
        return this.mDrawCommandManager != null;
    }
    
    public boolean hasOverlappingRendering() {
        return this.mNeedsOffscreenAlphaCompositing;
    }
    
    public boolean interceptsTouchEvent(final float n, final float n2) {
        final NodeRegion anyNodeRegionWithinBounds = this.anyNodeRegionWithinBounds(n, n2);
        return anyNodeRegionWithinBounds != null && anyNodeRegionWithinBounds.mIsVirtual;
    }
    
    public void invalidate() {
        this.invalidate(0, 0, this.getWidth() + 1, this.getHeight() + 1);
    }
    
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if (this.mHotspot != null) {
            this.mHotspot.jumpToCurrentState();
        }
    }
    
    protected void onAttachedToWindow() {
        if (this.mIsAttached) {
            return;
        }
        this.mIsAttached = true;
        super.onAttachedToWindow();
        this.dispatchOnAttached(this.mAttachDetachListeners);
        this.updateClippingRect();
    }
    
    protected void onDetachedFromWindow() {
        if (!this.mIsAttached) {
            throw new RuntimeException("Double detach");
        }
        this.mIsAttached = false;
        super.onDetachedFromWindow();
        dispatchOnDetached(this.mAttachDetachListeners);
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        final long downTime = motionEvent.getDownTime();
        Label_0036: {
            if (downTime == this.mLastTouchDownTime) {
                break Label_0036;
            }
            this.mLastTouchDownTime = downTime;
            if (!this.interceptsTouchEvent(motionEvent.getX(), motionEvent.getY())) {
                break Label_0036;
            }
            return true;
        }
        if ((this.mOnInterceptTouchEventListener == null || !this.mOnInterceptTouchEventListener.onInterceptTouchEvent(this, motionEvent)) && this.mPointerEvents != PointerEvents.NONE && this.mPointerEvents != PointerEvents.BOX_ONLY) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return true;
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
    }
    
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        if (this.mHotspot != null) {
            this.mHotspot.setBounds(0, 0, n, n2);
            this.invalidate();
        }
        this.updateClippingRect();
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        return this.mPointerEvents != PointerEvents.NONE && (this.mPointerEvents != PointerEvents.BOX_NONE || this.virtualNodeRegionWithinBounds(motionEvent.getX(), motionEvent.getY()) != null);
    }
    
    public int reactTagForTouch(final float n, final float n2) {
        SoftAssertions.assertCondition(this.mPointerEvents != PointerEvents.NONE, "TouchTargetHelper should not allow calling this method when pointer events are NONE");
        if (this.mPointerEvents != PointerEvents.BOX_ONLY) {
            final NodeRegion virtualNodeRegionWithinBounds = this.virtualNodeRegionWithinBounds(n, n2);
            if (virtualNodeRegionWithinBounds != null) {
                return virtualNodeRegionWithinBounds.getReactTag(n, n2);
            }
        }
        return this.getId();
    }
    
    public void removeAllViewsInLayout() {
        this.mDrawCommands = DrawCommand.EMPTY_ARRAY;
        super.removeAllViewsInLayout();
    }
    
    void removeDetachedView(final View view) {
        this.removeDetachedView(view, false);
    }
    
    @SuppressLint({ "MissingSuperCall" })
    public void requestLayout() {
        if (this.mIsLayoutRequested) {
            return;
        }
        this.mIsLayoutRequested = true;
        FlatViewGroup.LAYOUT_REQUESTS.add(this);
    }
    
    void setHitSlopRect(final Rect mHitSlopRect) {
        this.mHitSlopRect = mHitSlopRect;
    }
    
    void setHotspot(final Drawable mHotspot) {
        if (this.mHotspot != null) {
            this.mHotspot.setCallback((Drawable$Callback)null);
            this.unscheduleDrawable(this.mHotspot);
        }
        if (mHotspot != null) {
            mHotspot.setCallback((Drawable$Callback)this);
            if (mHotspot.isStateful()) {
                mHotspot.setState(this.getDrawableState());
            }
        }
        this.mHotspot = mHotspot;
        this.invalidate();
    }
    
    void setNeedsOffscreenAlphaCompositing(final boolean mNeedsOffscreenAlphaCompositing) {
        this.mNeedsOffscreenAlphaCompositing = mNeedsOffscreenAlphaCompositing;
    }
    
    public void setOnInterceptTouchEventListener(final OnInterceptTouchEventListener mOnInterceptTouchEventListener) {
        this.mOnInterceptTouchEventListener = mOnInterceptTouchEventListener;
    }
    
    void setPointerEvents(final PointerEvents mPointerEvents) {
        this.mPointerEvents = mPointerEvents;
    }
    
    public void setRemoveClippedSubviews(final boolean b) {
        final boolean removeClippedSubviews = this.getRemoveClippedSubviews();
        if (b == removeClippedSubviews) {
            return;
        }
        if (removeClippedSubviews) {
            throw new RuntimeException("Trying to transition FlatViewGroup from clipping to non-clipping state");
        }
        this.mDrawCommandManager = DrawCommandManager.getVerticalClippingInstance(this, this.mDrawCommands);
        this.mDrawCommands = DrawCommand.EMPTY_ARRAY;
    }
    
    public void updateClippingRect() {
        if (this.mDrawCommandManager != null && this.mDrawCommandManager.updateClippingRect()) {
            this.invalidate();
        }
    }
    
    @SuppressLint({ "MissingSuperCall" })
    protected boolean verifyDrawable(final Drawable drawable) {
        return true;
    }
}
