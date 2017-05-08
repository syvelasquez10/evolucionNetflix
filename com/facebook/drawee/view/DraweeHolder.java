// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.view;

import com.facebook.common.internal.Objects;
import android.view.MotionEvent;
import com.facebook.common.logging.FLog;
import com.facebook.common.internal.Preconditions;
import android.graphics.drawable.Drawable;
import com.facebook.drawee.drawable.VisibilityAwareDrawable;
import android.content.Context;
import com.facebook.drawee.components.DraweeEventTracker$Event;
import com.facebook.drawee.components.DraweeEventTracker;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.drawable.VisibilityCallback;
import com.facebook.drawee.interfaces.DraweeHierarchy;

public class DraweeHolder<DH extends DraweeHierarchy> implements VisibilityCallback
{
    private DraweeController mController;
    private final DraweeEventTracker mEventTracker;
    private DH mHierarchy;
    private boolean mIsActivityStarted;
    private boolean mIsControllerAttached;
    private boolean mIsHolderAttached;
    private boolean mIsVisible;
    
    public DraweeHolder(final DH hierarchy) {
        this.mIsControllerAttached = false;
        this.mIsHolderAttached = false;
        this.mIsVisible = true;
        this.mIsActivityStarted = true;
        this.mController = null;
        this.mEventTracker = new DraweeEventTracker();
        if (hierarchy != null) {
            this.setHierarchy(hierarchy);
        }
    }
    
    private void attachController() {
        if (!this.mIsControllerAttached) {
            this.mEventTracker.recordEvent(DraweeEventTracker$Event.ON_ATTACH_CONTROLLER);
            this.mIsControllerAttached = true;
            if (this.mController != null && this.mController.getHierarchy() != null) {
                this.mController.onAttach();
            }
        }
    }
    
    private void attachOrDetachController() {
        if (this.mIsHolderAttached && this.mIsVisible && this.mIsActivityStarted) {
            this.attachController();
            return;
        }
        this.detachController();
    }
    
    public static <DH extends DraweeHierarchy> DraweeHolder<DH> create(final DH dh, final Context context) {
        final DraweeHolder<DH> draweeHolder = new DraweeHolder<DH>(dh);
        draweeHolder.registerWithContext(context);
        return draweeHolder;
    }
    
    private void detachController() {
        if (this.mIsControllerAttached) {
            this.mEventTracker.recordEvent(DraweeEventTracker$Event.ON_DETACH_CONTROLLER);
            this.mIsControllerAttached = false;
            if (this.mController != null) {
                this.mController.onDetach();
            }
        }
    }
    
    private void setVisibilityCallback(final VisibilityCallback visibilityCallback) {
        final Drawable topLevelDrawable = this.getTopLevelDrawable();
        if (topLevelDrawable instanceof VisibilityAwareDrawable) {
            ((VisibilityAwareDrawable)topLevelDrawable).setVisibilityCallback(visibilityCallback);
        }
    }
    
    public DraweeController getController() {
        return this.mController;
    }
    
    public DH getHierarchy() {
        return Preconditions.checkNotNull(this.mHierarchy);
    }
    
    public Drawable getTopLevelDrawable() {
        if (this.mHierarchy == null) {
            return null;
        }
        return this.mHierarchy.getTopLevelDrawable();
    }
    
    public void onAttach() {
        this.mEventTracker.recordEvent(DraweeEventTracker$Event.ON_HOLDER_ATTACH);
        this.mIsHolderAttached = true;
        this.attachOrDetachController();
    }
    
    public void onDetach() {
        this.mEventTracker.recordEvent(DraweeEventTracker$Event.ON_HOLDER_DETACH);
        this.mIsHolderAttached = false;
        this.attachOrDetachController();
    }
    
    @Override
    public void onDraw() {
        if (this.mIsControllerAttached) {
            return;
        }
        FLog.wtf(DraweeEventTracker.class, "%x: Draw requested for a non-attached controller %x. %s", System.identityHashCode(this), System.identityHashCode(this.mController), this.toString());
        this.mIsHolderAttached = true;
        this.mIsVisible = true;
        this.mIsActivityStarted = true;
        this.attachOrDetachController();
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        return this.mController != null && this.mController.onTouchEvent(motionEvent);
    }
    
    @Override
    public void onVisibilityChange(final boolean mIsVisible) {
        if (this.mIsVisible == mIsVisible) {
            return;
        }
        final DraweeEventTracker mEventTracker = this.mEventTracker;
        DraweeEventTracker$Event draweeEventTracker$Event;
        if (mIsVisible) {
            draweeEventTracker$Event = DraweeEventTracker$Event.ON_DRAWABLE_SHOW;
        }
        else {
            draweeEventTracker$Event = DraweeEventTracker$Event.ON_DRAWABLE_HIDE;
        }
        mEventTracker.recordEvent(draweeEventTracker$Event);
        this.mIsVisible = mIsVisible;
        this.attachOrDetachController();
    }
    
    public void registerWithContext(final Context context) {
    }
    
    public void setController(final DraweeController mController) {
        final boolean mIsControllerAttached = this.mIsControllerAttached;
        if (mIsControllerAttached) {
            this.detachController();
        }
        if (this.mController != null) {
            this.mEventTracker.recordEvent(DraweeEventTracker$Event.ON_CLEAR_OLD_CONTROLLER);
            this.mController.setHierarchy(null);
        }
        this.mController = mController;
        if (this.mController != null) {
            this.mEventTracker.recordEvent(DraweeEventTracker$Event.ON_SET_CONTROLLER);
            this.mController.setHierarchy(this.mHierarchy);
        }
        else {
            this.mEventTracker.recordEvent(DraweeEventTracker$Event.ON_CLEAR_CONTROLLER);
        }
        if (mIsControllerAttached) {
            this.attachController();
        }
    }
    
    public void setHierarchy(final DH hierarchy) {
        this.mEventTracker.recordEvent(DraweeEventTracker$Event.ON_SET_HIERARCHY);
        this.setVisibilityCallback(null);
        this.mHierarchy = Preconditions.checkNotNull(hierarchy);
        final Drawable topLevelDrawable = this.mHierarchy.getTopLevelDrawable();
        this.onVisibilityChange(topLevelDrawable == null || topLevelDrawable.isVisible());
        this.setVisibilityCallback(this);
        if (this.mController != null) {
            this.mController.setHierarchy(hierarchy);
        }
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("controllerAttached", this.mIsControllerAttached).add("holderAttached", this.mIsHolderAttached).add("drawableVisible", this.mIsVisible).add("activityStarted", this.mIsActivityStarted).add("events", this.mEventTracker.toString()).toString();
    }
}
