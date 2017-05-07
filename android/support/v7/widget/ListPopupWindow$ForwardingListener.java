// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.widget.PopupWindowCompat;
import android.os.Build$VERSION;
import android.widget.PopupWindow$OnDismissListener;
import android.widget.AdapterView;
import android.view.KeyEvent$DispatcherState;
import android.view.KeyEvent;
import android.widget.ListView;
import android.view.ViewParent;
import android.view.ViewGroup;
import android.view.View$MeasureSpec;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.widget.AbsListView$OnScrollListener;
import android.content.res.TypedArray;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.internal.widget.AppCompatPopupWindow;
import android.support.v7.appcompat.R$styleable;
import android.util.AttributeSet;
import android.support.v7.appcompat.R$attr;
import android.util.Log;
import android.graphics.Rect;
import android.widget.PopupWindow;
import android.database.DataSetObserver;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.AdapterView$OnItemClickListener;
import android.os.Handler;
import android.graphics.drawable.Drawable;
import android.content.Context;
import android.widget.ListAdapter;
import java.lang.reflect.Method;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.os.SystemClock;
import android.view.ViewConfiguration;
import android.view.View;
import android.view.View$OnTouchListener;

public abstract class ListPopupWindow$ForwardingListener implements View$OnTouchListener
{
    private int mActivePointerId;
    private Runnable mDisallowIntercept;
    private boolean mForwarding;
    private final int mLongPressTimeout;
    private final float mScaledTouchSlop;
    private final View mSrc;
    private final int mTapTimeout;
    private final int[] mTmpLocation;
    private Runnable mTriggerLongPress;
    private boolean mWasLongPress;
    
    public ListPopupWindow$ForwardingListener(final View mSrc) {
        this.mTmpLocation = new int[2];
        this.mSrc = mSrc;
        this.mScaledTouchSlop = ViewConfiguration.get(mSrc.getContext()).getScaledTouchSlop();
        this.mTapTimeout = ViewConfiguration.getTapTimeout();
        this.mLongPressTimeout = (this.mTapTimeout + ViewConfiguration.getLongPressTimeout()) / 2;
    }
    
    private void clearCallbacks() {
        if (this.mTriggerLongPress != null) {
            this.mSrc.removeCallbacks(this.mTriggerLongPress);
        }
        if (this.mDisallowIntercept != null) {
            this.mSrc.removeCallbacks(this.mDisallowIntercept);
        }
    }
    
    private void onLongPress() {
        this.clearCallbacks();
        if (this.mSrc.isEnabled() && this.onForwardingStarted()) {
            this.mSrc.getParent().requestDisallowInterceptTouchEvent(true);
            final long uptimeMillis = SystemClock.uptimeMillis();
            final MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            this.mSrc.onTouchEvent(obtain);
            obtain.recycle();
            this.mForwarding = true;
            this.mWasLongPress = true;
        }
    }
    
    private boolean onTouchForwarded(final MotionEvent motionEvent) {
        boolean b = true;
        final View mSrc = this.mSrc;
        final ListPopupWindow popup = this.getPopup();
        if (popup != null && popup.isShowing()) {
            final ListPopupWindow$DropDownListView access$600 = popup.mDropDownList;
            if (access$600 != null && access$600.isShown()) {
                final MotionEvent obtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
                this.toGlobalMotionEvent(mSrc, obtainNoHistory);
                this.toLocalMotionEvent((View)access$600, obtainNoHistory);
                final boolean onForwardedEvent = access$600.onForwardedEvent(obtainNoHistory, this.mActivePointerId);
                obtainNoHistory.recycle();
                final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
                boolean b2;
                if (actionMasked != 1 && actionMasked != 3) {
                    b2 = true;
                }
                else {
                    b2 = false;
                }
                if (!onForwardedEvent || !b2) {
                    b = false;
                }
                return b;
            }
        }
        return false;
    }
    
    private boolean onTouchObserved(final MotionEvent motionEvent) {
        final View mSrc = this.mSrc;
        if (mSrc.isEnabled()) {
            switch (MotionEventCompat.getActionMasked(motionEvent)) {
                default: {
                    return false;
                }
                case 0: {
                    this.mActivePointerId = motionEvent.getPointerId(0);
                    this.mWasLongPress = false;
                    if (this.mDisallowIntercept == null) {
                        this.mDisallowIntercept = new ListPopupWindow$ForwardingListener$DisallowIntercept(this, null);
                    }
                    mSrc.postDelayed(this.mDisallowIntercept, (long)this.mTapTimeout);
                    if (this.mTriggerLongPress == null) {
                        this.mTriggerLongPress = new ListPopupWindow$ForwardingListener$TriggerLongPress(this, null);
                    }
                    mSrc.postDelayed(this.mTriggerLongPress, (long)this.mLongPressTimeout);
                    return false;
                }
                case 2: {
                    final int pointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                    if (pointerIndex >= 0 && !pointInView(mSrc, motionEvent.getX(pointerIndex), motionEvent.getY(pointerIndex), this.mScaledTouchSlop)) {
                        this.clearCallbacks();
                        mSrc.getParent().requestDisallowInterceptTouchEvent(true);
                        return true;
                    }
                    break;
                }
                case 1:
                case 3: {
                    this.clearCallbacks();
                    return false;
                }
            }
        }
        return false;
    }
    
    private static boolean pointInView(final View view, final float n, final float n2, final float n3) {
        return n >= -n3 && n2 >= -n3 && n < view.getRight() - view.getLeft() + n3 && n2 < view.getBottom() - view.getTop() + n3;
    }
    
    private boolean toGlobalMotionEvent(final View view, final MotionEvent motionEvent) {
        final int[] mTmpLocation = this.mTmpLocation;
        view.getLocationOnScreen(mTmpLocation);
        motionEvent.offsetLocation((float)mTmpLocation[0], (float)mTmpLocation[1]);
        return true;
    }
    
    private boolean toLocalMotionEvent(final View view, final MotionEvent motionEvent) {
        final int[] mTmpLocation = this.mTmpLocation;
        view.getLocationOnScreen(mTmpLocation);
        motionEvent.offsetLocation((float)(-mTmpLocation[0]), (float)(-mTmpLocation[1]));
        return true;
    }
    
    public abstract ListPopupWindow getPopup();
    
    protected boolean onForwardingStarted() {
        final ListPopupWindow popup = this.getPopup();
        if (popup != null && !popup.isShowing()) {
            popup.show();
        }
        return true;
    }
    
    protected boolean onForwardingStopped() {
        final ListPopupWindow popup = this.getPopup();
        if (popup != null && popup.isShowing()) {
            popup.dismiss();
        }
        return true;
    }
    
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        final boolean b = false;
        final boolean mForwarding = this.mForwarding;
        boolean onTouchForwarded;
        if (mForwarding) {
            if (this.mWasLongPress) {
                onTouchForwarded = this.onTouchForwarded(motionEvent);
            }
            else {
                onTouchForwarded = (this.onTouchForwarded(motionEvent) || !this.onForwardingStopped());
            }
        }
        else {
            onTouchForwarded = (this.onTouchObserved(motionEvent) && this.onForwardingStarted());
            if (onTouchForwarded) {
                final long uptimeMillis = SystemClock.uptimeMillis();
                final MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                this.mSrc.onTouchEvent(obtain);
                obtain.recycle();
            }
        }
        if (!(this.mForwarding = onTouchForwarded)) {
            final boolean b2 = b;
            if (!mForwarding) {
                return b2;
            }
        }
        return true;
    }
}
