// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.content.res.TypedArray;
import android.widget.FrameLayout$LayoutParams;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v7.appcompat.R;
import android.graphics.Rect;
import android.view.View;
import android.support.v7.app.ActionBar;
import android.widget.FrameLayout;

public class ActionBarOverlayLayout extends FrameLayout
{
    static final int[] mActionBarSizeAttr;
    private ActionBar mActionBar;
    private View mActionBarBottom;
    private int mActionBarHeight;
    private View mActionBarTop;
    private ActionBarView mActionView;
    private ActionBarContainer mContainerView;
    private View mContent;
    private final Rect mZeroRect;
    
    static {
        mActionBarSizeAttr = new int[] { R.attr.actionBarSize };
    }
    
    public ActionBarOverlayLayout(final Context context) {
        super(context);
        this.mZeroRect = new Rect(0, 0, 0, 0);
        this.init(context);
    }
    
    public ActionBarOverlayLayout(final Context context, final AttributeSet set) {
        super(context, set);
        this.mZeroRect = new Rect(0, 0, 0, 0);
        this.init(context);
    }
    
    private boolean applyInsets(final View view, final Rect rect, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        final boolean b5 = false;
        final FrameLayout$LayoutParams frameLayout$LayoutParams = (FrameLayout$LayoutParams)view.getLayoutParams();
        boolean b6 = b5;
        if (b) {
            b6 = b5;
            if (frameLayout$LayoutParams.leftMargin != rect.left) {
                b6 = true;
                frameLayout$LayoutParams.leftMargin = rect.left;
            }
        }
        boolean b7 = b6;
        if (b2) {
            b7 = b6;
            if (frameLayout$LayoutParams.topMargin != rect.top) {
                b7 = true;
                frameLayout$LayoutParams.topMargin = rect.top;
            }
        }
        boolean b8 = b7;
        if (b4) {
            b8 = b7;
            if (frameLayout$LayoutParams.rightMargin != rect.right) {
                b8 = true;
                frameLayout$LayoutParams.rightMargin = rect.right;
            }
        }
        boolean b9 = b8;
        if (b3) {
            b9 = b8;
            if (frameLayout$LayoutParams.bottomMargin != rect.bottom) {
                b9 = true;
                frameLayout$LayoutParams.bottomMargin = rect.bottom;
            }
        }
        return b9;
    }
    
    private void init(final Context context) {
        final TypedArray obtainStyledAttributes = this.getContext().getTheme().obtainStyledAttributes(ActionBarOverlayLayout.mActionBarSizeAttr);
        this.mActionBarHeight = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        obtainStyledAttributes.recycle();
    }
    
    void pullChildren() {
        if (this.mContent == null) {
            this.mContent = this.findViewById(R.id.action_bar_activity_content);
            if (this.mContent == null) {
                this.mContent = this.findViewById(16908290);
            }
            this.mActionBarTop = this.findViewById(R.id.top_action_bar);
            this.mContainerView = (ActionBarContainer)this.findViewById(R.id.action_bar_container);
            this.mActionView = (ActionBarView)this.findViewById(R.id.action_bar);
            this.mActionBarBottom = this.findViewById(R.id.split_action_bar);
        }
    }
    
    public void setActionBar(final ActionBar mActionBar) {
        this.mActionBar = mActionBar;
    }
}
