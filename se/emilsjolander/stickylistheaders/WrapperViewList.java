// 
// Decompiled by Procyon v0.5.30
// 

package se.emilsjolander.stickylistheaders;

import android.graphics.Canvas;
import java.util.ArrayList;
import android.os.Build$VERSION;
import android.widget.AbsListView;
import android.content.Context;
import android.graphics.Rect;
import java.lang.reflect.Field;
import android.view.View;
import java.util.List;
import android.widget.ListView;

class WrapperViewList extends ListView
{
    private boolean mClippingToPadding;
    private List<View> mFooterViews;
    private LifeCycleListener mLifeCycleListener;
    private Field mSelectorPositionField;
    private Rect mSelectorRect;
    private int mTopClippingLength;
    
    public WrapperViewList(final Context context) {
        super(context);
        this.mSelectorRect = new Rect();
        this.mClippingToPadding = true;
        try {
            final Field declaredField = AbsListView.class.getDeclaredField("mSelectorRect");
            declaredField.setAccessible(true);
            this.mSelectorRect = (Rect)declaredField.get(this);
            if (Build$VERSION.SDK_INT >= 14) {
                (this.mSelectorPositionField = AbsListView.class.getDeclaredField("mSelectorPosition")).setAccessible(true);
            }
        }
        catch (NoSuchFieldException ex) {
            ex.printStackTrace();
        }
        catch (IllegalArgumentException ex2) {
            ex2.printStackTrace();
        }
        catch (IllegalAccessException ex3) {
            ex3.printStackTrace();
        }
    }
    
    private int getSelectorPosition() {
        if (this.mSelectorPositionField == null) {
            for (int i = 0; i < this.getChildCount(); ++i) {
                if (this.getChildAt(i).getBottom() == this.mSelectorRect.bottom) {
                    return this.getFixedFirstVisibleItem() + i;
                }
            }
            goto Label_0065;
        }
        try {
            return this.mSelectorPositionField.getInt(this);
        }
        catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        catch (IllegalAccessException ex2) {
            ex2.printStackTrace();
            goto Label_0065;
        }
    }
    
    private void positionSelectorRect() {
        if (!this.mSelectorRect.isEmpty()) {
            final int selectorPosition = this.getSelectorPosition();
            if (selectorPosition >= 0) {
                final View child = this.getChildAt(selectorPosition - this.getFixedFirstVisibleItem());
                if (child instanceof WrapperView) {
                    final WrapperView wrapperView = (WrapperView)child;
                    this.mSelectorRect.top = wrapperView.getTop() + wrapperView.mItemTop;
                }
            }
        }
    }
    
    public void addFooterView(final View view) {
        super.addFooterView(view);
        if (this.mFooterViews == null) {
            this.mFooterViews = new ArrayList<View>();
        }
        this.mFooterViews.add(view);
    }
    
    boolean containsFooterView(final View view) {
        return this.mFooterViews != null && this.mFooterViews.contains(view);
    }
    
    protected void dispatchDraw(final Canvas canvas) {
        this.positionSelectorRect();
        if (this.mTopClippingLength != 0) {
            canvas.save();
            final Rect clipBounds = canvas.getClipBounds();
            clipBounds.top = this.mTopClippingLength;
            canvas.clipRect(clipBounds);
            super.dispatchDraw(canvas);
            canvas.restore();
        }
        else {
            super.dispatchDraw(canvas);
        }
        this.mLifeCycleListener.onDispatchDrawOccurred(canvas);
    }
    
    int getFixedFirstVisibleItem() {
        final int firstVisiblePosition = this.getFirstVisiblePosition();
        if (Build$VERSION.SDK_INT >= 11) {
            return firstVisiblePosition;
        }
        int n = 0;
        int n2;
        while (true) {
            n2 = firstVisiblePosition;
            if (n >= this.getChildCount()) {
                break;
            }
            if (this.getChildAt(n).getBottom() >= 0) {
                n2 = firstVisiblePosition + n;
                break;
            }
            ++n;
        }
        int n3 = n2;
        if (!this.mClippingToPadding) {
            n3 = n2;
            if (this.getPaddingTop() > 0 && (n3 = n2) > 0) {
                n3 = n2;
                if (this.getChildAt(0).getTop() > 0) {
                    n3 = n2 - 1;
                }
            }
        }
        return n3;
    }
    
    public boolean performItemClick(final View view, final int n, final long n2) {
        View mItem = view;
        if (view instanceof WrapperView) {
            mItem = ((WrapperView)view).mItem;
        }
        return super.performItemClick(mItem, n, n2);
    }
    
    public boolean removeFooterView(final View view) {
        if (super.removeFooterView(view)) {
            this.mFooterViews.remove(view);
            return true;
        }
        return false;
    }
    
    public void setClipToPadding(final boolean mClippingToPadding) {
        super.setClipToPadding(this.mClippingToPadding = mClippingToPadding);
    }
    
    void setLifeCycleListener(final LifeCycleListener mLifeCycleListener) {
        this.mLifeCycleListener = mLifeCycleListener;
    }
    
    void setTopClippingLength(final int mTopClippingLength) {
        this.mTopClippingLength = mTopClippingLength;
    }
    
    interface LifeCycleListener
    {
        void onDispatchDrawOccurred(final Canvas p0);
    }
}
