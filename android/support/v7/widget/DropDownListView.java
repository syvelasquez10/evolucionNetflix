// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.widget.ListView;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.os.Build$VERSION;
import android.view.View;
import android.util.AttributeSet;
import android.support.v7.appcompat.R$attr;
import android.content.Context;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v4.view.ViewPropertyAnimatorCompat;

class DropDownListView extends ListViewCompat
{
    private ViewPropertyAnimatorCompat mClickAnimation;
    private boolean mDrawsInPressedState;
    private boolean mHijackFocus;
    private boolean mListSelectionHidden;
    private ListViewAutoScrollHelper mScrollHelper;
    
    public DropDownListView(final Context context, final boolean mHijackFocus) {
        super(context, null, R$attr.dropDownListViewStyle);
        this.mHijackFocus = mHijackFocus;
        this.setCacheColorHint(0);
    }
    
    private void clearPressedItem() {
        this.setPressed(this.mDrawsInPressedState = false);
        this.drawableStateChanged();
        final View child = this.getChildAt(this.mMotionPosition - this.getFirstVisiblePosition());
        if (child != null) {
            child.setPressed(false);
        }
        if (this.mClickAnimation != null) {
            this.mClickAnimation.cancel();
            this.mClickAnimation = null;
        }
    }
    
    private void clickPressedItem(final View view, final int n) {
        this.performItemClick(view, n, this.getItemIdAtPosition(n));
    }
    
    private void setPressedItem(final View view, final int mMotionPosition, final float n, final float n2) {
        this.mDrawsInPressedState = true;
        if (Build$VERSION.SDK_INT >= 21) {
            this.drawableHotspotChanged(n, n2);
        }
        if (!this.isPressed()) {
            this.setPressed(true);
        }
        this.layoutChildren();
        if (this.mMotionPosition != -1) {
            final View child = this.getChildAt(this.mMotionPosition - this.getFirstVisiblePosition());
            if (child != null && child != view && child.isPressed()) {
                child.setPressed(false);
            }
        }
        this.mMotionPosition = mMotionPosition;
        final float n3 = view.getLeft();
        final float n4 = view.getTop();
        if (Build$VERSION.SDK_INT >= 21) {
            view.drawableHotspotChanged(n - n3, n2 - n4);
        }
        if (!view.isPressed()) {
            view.setPressed(true);
        }
        this.positionSelectorLikeTouchCompat(mMotionPosition, view, n, n2);
        this.setSelectorEnabled(false);
        this.refreshDrawableState();
    }
    
    public boolean hasFocus() {
        return this.mHijackFocus || super.hasFocus();
    }
    
    public boolean hasWindowFocus() {
        return this.mHijackFocus || super.hasWindowFocus();
    }
    
    public boolean isFocused() {
        return this.mHijackFocus || super.isFocused();
    }
    
    public boolean isInTouchMode() {
        return (this.mHijackFocus && this.mListSelectionHidden) || super.isInTouchMode();
    }
    
    public boolean onForwardedEvent(final MotionEvent motionEvent, int n) {
        final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
    Label_0037_Outer:
        while (true) {
            int n2;
            int pointToPosition;
            while (true) {
                Label_0107: {
                    boolean b = false;
                    switch (actionMasked) {
                        default: {
                            n = 0;
                            b = true;
                            break;
                        }
                        case 3: {
                            n = 0;
                            b = false;
                            break;
                        }
                        case 1: {
                            b = false;
                            break Label_0107;
                        }
                        case 2: {
                            b = true;
                            break Label_0107;
                        }
                    }
                    if (!b || n != 0) {
                        this.clearPressedItem();
                    }
                    if (b) {
                        if (this.mScrollHelper == null) {
                            this.mScrollHelper = new ListViewAutoScrollHelper(this);
                        }
                        this.mScrollHelper.setEnabled(true);
                        this.mScrollHelper.onTouch((View)this, motionEvent);
                    }
                    else if (this.mScrollHelper != null) {
                        this.mScrollHelper.setEnabled(false);
                        return b;
                    }
                    return b;
                }
                final int pointerIndex = motionEvent.findPointerIndex(n);
                if (pointerIndex < 0) {
                    n = 0;
                    final boolean b = false;
                    continue;
                }
                n = (int)motionEvent.getX(pointerIndex);
                n2 = (int)motionEvent.getY(pointerIndex);
                pointToPosition = this.pointToPosition(n, n2);
                if (pointToPosition == -1) {
                    n = 1;
                    continue;
                }
                break;
            }
            final View child = this.getChildAt(pointToPosition - this.getFirstVisiblePosition());
            this.setPressedItem(child, pointToPosition, n, n2);
            if (actionMasked == 1) {
                this.clickPressedItem(child, pointToPosition);
            }
            continue Label_0037_Outer;
        }
    }
    
    void setListSelectionHidden(final boolean mListSelectionHidden) {
        this.mListSelectionHidden = mListSelectionHidden;
    }
    
    @Override
    protected boolean touchModeDrawsInPressedStateCompat() {
        return this.mDrawsInPressedState || super.touchModeDrawsInPressedStateCompat();
    }
}
