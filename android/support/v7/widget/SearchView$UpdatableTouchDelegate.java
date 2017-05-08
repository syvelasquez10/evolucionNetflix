// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.View;
import android.graphics.Rect;
import android.view.TouchDelegate;

class SearchView$UpdatableTouchDelegate extends TouchDelegate
{
    private final Rect mActualBounds;
    private boolean mDelegateTargeted;
    private final View mDelegateView;
    private final int mSlop;
    private final Rect mSlopBounds;
    private final Rect mTargetBounds;
    
    public SearchView$UpdatableTouchDelegate(final Rect rect, final Rect rect2, final View mDelegateView) {
        super(rect, mDelegateView);
        this.mSlop = ViewConfiguration.get(mDelegateView.getContext()).getScaledTouchSlop();
        this.mTargetBounds = new Rect();
        this.mSlopBounds = new Rect();
        this.mActualBounds = new Rect();
        this.setBounds(rect, rect2);
        this.mDelegateView = mDelegateView;
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final boolean b = true;
        boolean dispatchTouchEvent = false;
        final int n = (int)motionEvent.getX();
        final int n2 = (int)motionEvent.getY();
        int mDelegateTargeted = 0;
        boolean b2 = false;
        Label_0057: {
            switch (motionEvent.getAction()) {
                case 0: {
                    if (this.mTargetBounds.contains(n, n2)) {
                        this.mDelegateTargeted = true;
                        mDelegateTargeted = 1;
                        b2 = b;
                        break Label_0057;
                    }
                    break;
                }
                case 1:
                case 2: {
                    final boolean mDelegateTargeted2 = this.mDelegateTargeted;
                    b2 = b;
                    mDelegateTargeted = (mDelegateTargeted2 ? 1 : 0);
                    if (!mDelegateTargeted2) {
                        break Label_0057;
                    }
                    b2 = b;
                    mDelegateTargeted = (mDelegateTargeted2 ? 1 : 0);
                    if (!this.mSlopBounds.contains(n, n2)) {
                        b2 = false;
                        mDelegateTargeted = (mDelegateTargeted2 ? 1 : 0);
                    }
                    break Label_0057;
                }
                case 3: {
                    mDelegateTargeted = (this.mDelegateTargeted ? 1 : 0);
                    this.mDelegateTargeted = false;
                    b2 = b;
                    break Label_0057;
                }
            }
            mDelegateTargeted = 0;
            b2 = b;
        }
        if (mDelegateTargeted != 0) {
            if (b2 && !this.mActualBounds.contains(n, n2)) {
                motionEvent.setLocation((float)(this.mDelegateView.getWidth() / 2), (float)(this.mDelegateView.getHeight() / 2));
            }
            else {
                motionEvent.setLocation((float)(n - this.mActualBounds.left), (float)(n2 - this.mActualBounds.top));
            }
            dispatchTouchEvent = this.mDelegateView.dispatchTouchEvent(motionEvent);
        }
        return dispatchTouchEvent;
    }
    
    public void setBounds(final Rect rect, final Rect rect2) {
        this.mTargetBounds.set(rect);
        this.mSlopBounds.set(rect);
        this.mSlopBounds.inset(-this.mSlop, -this.mSlop);
        this.mActualBounds.set(rect2);
    }
}
