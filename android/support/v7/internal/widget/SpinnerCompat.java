// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.widget.Adapter;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.os.Parcelable;
import android.content.DialogInterface;
import android.view.View$MeasureSpec;
import android.graphics.drawable.Drawable;
import android.widget.SpinnerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.view.View;
import android.support.v7.appcompat.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.ListPopupWindow$ForwardingListener;
import android.content.DialogInterface$OnClickListener;

class SpinnerCompat extends AbsSpinnerCompat implements DialogInterface$OnClickListener
{
    private boolean mDisableChildrenWhenDisabled;
    int mDropDownWidth;
    private ListPopupWindow$ForwardingListener mForwardingListener;
    private int mGravity;
    private SpinnerCompat$SpinnerPopup mPopup;
    private SpinnerCompat$DropDownAdapter mTempAdapter;
    private Rect mTempRect;
    private final TintManager mTintManager;
    
    SpinnerCompat(final Context context, final AttributeSet set, final int n) {
        this(context, set, n, -1);
    }
    
    SpinnerCompat(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n);
        this.mTempRect = new Rect();
        final TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, set, R$styleable.Spinner, n, 0);
        this.setBackgroundDrawable(obtainStyledAttributes.getDrawable(R$styleable.Spinner_android_background));
        int int1 = n2;
        if (n2 == -1) {
            int1 = obtainStyledAttributes.getInt(R$styleable.Spinner_spinnerMode, 0);
        }
        switch (int1) {
            case 0: {
                this.mPopup = new SpinnerCompat$DialogPopup(this, null);
                break;
            }
            case 1: {
                final SpinnerCompat$DropdownPopup mPopup = new SpinnerCompat$DropdownPopup(this, context, set, n);
                this.mDropDownWidth = obtainStyledAttributes.getLayoutDimension(R$styleable.Spinner_android_dropDownWidth, -2);
                mPopup.setBackgroundDrawable(obtainStyledAttributes.getDrawable(R$styleable.Spinner_android_popupBackground));
                this.mPopup = mPopup;
                this.mForwardingListener = new SpinnerCompat$1(this, (View)this, mPopup);
                break;
            }
        }
        this.mGravity = obtainStyledAttributes.getInt(R$styleable.Spinner_android_gravity, 17);
        this.mPopup.setPromptText(obtainStyledAttributes.getString(R$styleable.Spinner_prompt));
        this.mDisableChildrenWhenDisabled = obtainStyledAttributes.getBoolean(R$styleable.Spinner_disableChildrenWhenDisabled, false);
        obtainStyledAttributes.recycle();
        if (this.mTempAdapter != null) {
            this.mPopup.setAdapter((ListAdapter)this.mTempAdapter);
            this.mTempAdapter = null;
        }
        this.mTintManager = obtainStyledAttributes.getTintManager();
    }
    
    private View makeView(final int n, final boolean b) {
        if (!this.mDataChanged) {
            final View value = this.mRecycler.get(n);
            if (value != null) {
                this.setUpChild(value, b);
                return value;
            }
        }
        final View view = this.mAdapter.getView(n, (View)null, (ViewGroup)this);
        this.setUpChild(view, b);
        return view;
    }
    
    private void setUpChild(final View view, final boolean b) {
        ViewGroup$LayoutParams viewGroup$LayoutParams;
        if ((viewGroup$LayoutParams = view.getLayoutParams()) == null) {
            viewGroup$LayoutParams = this.generateDefaultLayoutParams();
        }
        if (b) {
            this.addViewInLayout(view, 0, viewGroup$LayoutParams);
        }
        view.setSelected(this.hasFocus());
        if (this.mDisableChildrenWhenDisabled) {
            view.setEnabled(this.isEnabled());
        }
        view.measure(ViewGroup.getChildMeasureSpec(this.mWidthMeasureSpec, this.mSpinnerPadding.left + this.mSpinnerPadding.right, viewGroup$LayoutParams.width), ViewGroup.getChildMeasureSpec(this.mHeightMeasureSpec, this.mSpinnerPadding.top + this.mSpinnerPadding.bottom, viewGroup$LayoutParams.height));
        final int n = this.mSpinnerPadding.top + (this.getMeasuredHeight() - this.mSpinnerPadding.bottom - this.mSpinnerPadding.top - view.getMeasuredHeight()) / 2;
        view.layout(0, n, view.getMeasuredWidth() + 0, view.getMeasuredHeight() + n);
    }
    
    public int getBaseline() {
        final int n = -1;
        final View view = null;
        View view2;
        if (this.getChildCount() > 0) {
            view2 = this.getChildAt(0);
        }
        else {
            view2 = view;
            if (this.mAdapter != null) {
                view2 = view;
                if (this.mAdapter.getCount() > 0) {
                    view2 = this.makeView(0, false);
                    this.mRecycler.put(0, view2);
                }
            }
        }
        int n2 = n;
        if (view2 != null) {
            final int baseline = view2.getBaseline();
            n2 = n;
            if (baseline >= 0) {
                n2 = view2.getTop() + baseline;
            }
        }
        return n2;
    }
    
    void layout(int left, final boolean b) {
        left = this.mSpinnerPadding.left;
        final int n = this.getRight() - this.getLeft() - this.mSpinnerPadding.left - this.mSpinnerPadding.right;
        if (this.mDataChanged) {
            this.handleDataChanged();
        }
        if (this.mItemCount == 0) {
            this.resetList();
            return;
        }
        if (this.mNextSelectedPosition >= 0) {
            this.setSelectedPositionInt(this.mNextSelectedPosition);
        }
        this.recycleAllViews();
        this.removeAllViewsInLayout();
        this.mFirstPosition = this.mSelectedPosition;
        if (this.mAdapter != null) {
            final View view = this.makeView(this.mSelectedPosition, true);
            final int measuredWidth = view.getMeasuredWidth();
            switch (GravityCompat.getAbsoluteGravity(this.mGravity, ViewCompat.getLayoutDirection((View)this)) & 0x7) {
                case 1: {
                    left = left + n / 2 - measuredWidth / 2;
                    break;
                }
                case 5: {
                    left = left + n - measuredWidth;
                    break;
                }
            }
            view.offsetLeftAndRight(left);
        }
        this.mRecycler.clear();
        this.invalidate();
        this.checkSelectionChanged();
        this.mDataChanged = false;
        this.mNeedSync = false;
        this.setNextSelectedPositionInt(this.mSelectedPosition);
    }
    
    int measureContentWidth(final SpinnerAdapter spinnerAdapter, final Drawable drawable) {
        if (spinnerAdapter == null) {
            return 0;
        }
        final int measureSpec = View$MeasureSpec.makeMeasureSpec(0, 0);
        final int measureSpec2 = View$MeasureSpec.makeMeasureSpec(0, 0);
        final int max = Math.max(0, this.getSelectedItemPosition());
        final int min = Math.min(spinnerAdapter.getCount(), max + 15);
        int i = Math.max(0, max - (15 - (min - max)));
        View view = null;
        int max2 = 0;
        int n = 0;
        while (i < min) {
            final int itemViewType = spinnerAdapter.getItemViewType(i);
            if (itemViewType != n) {
                view = null;
                n = itemViewType;
            }
            view = spinnerAdapter.getView(i, view, (ViewGroup)this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup$LayoutParams(-2, -2));
            }
            view.measure(measureSpec, measureSpec2);
            max2 = Math.max(max2, view.getMeasuredWidth());
            ++i;
        }
        if (drawable != null) {
            drawable.getPadding(this.mTempRect);
            return this.mTempRect.left + this.mTempRect.right + max2;
        }
        return max2;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int selection) {
        this.setSelection(selection);
        dialogInterface.dismiss();
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mPopup != null && this.mPopup.isShowing()) {
            this.mPopup.dismiss();
        }
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        super.onLayout(b, n, n2, n3, n4);
        this.mInLayout = true;
        this.layout(0, false);
        this.mInLayout = false;
    }
    
    @Override
    protected void onMeasure(final int n, final int n2) {
        super.onMeasure(n, n2);
        if (this.mPopup != null && View$MeasureSpec.getMode(n) == Integer.MIN_VALUE) {
            this.setMeasuredDimension(Math.min(Math.max(this.getMeasuredWidth(), this.measureContentWidth(this.getAdapter(), this.getBackground())), View$MeasureSpec.getSize(n)), this.getMeasuredHeight());
        }
    }
    
    @Override
    public void onRestoreInstanceState(final Parcelable parcelable) {
        final SpinnerCompat$SavedState spinnerCompat$SavedState = (SpinnerCompat$SavedState)parcelable;
        super.onRestoreInstanceState(spinnerCompat$SavedState.getSuperState());
        if (spinnerCompat$SavedState.showDropdown) {
            final ViewTreeObserver viewTreeObserver = this.getViewTreeObserver();
            if (viewTreeObserver != null) {
                viewTreeObserver.addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new SpinnerCompat$2(this));
            }
        }
    }
    
    @Override
    public Parcelable onSaveInstanceState() {
        final SpinnerCompat$SavedState spinnerCompat$SavedState = new SpinnerCompat$SavedState(super.onSaveInstanceState());
        spinnerCompat$SavedState.showDropdown = (this.mPopup != null && this.mPopup.isShowing());
        return (Parcelable)spinnerCompat$SavedState;
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        return (this.mForwardingListener != null && this.mForwardingListener.onTouch((View)this, motionEvent)) || super.onTouchEvent(motionEvent);
    }
    
    public boolean performClick() {
        boolean performClick;
        if (!(performClick = super.performClick())) {
            performClick = true;
            if (!this.mPopup.isShowing()) {
                this.mPopup.show();
                performClick = performClick;
            }
        }
        return performClick;
    }
    
    @Override
    public void setAdapter(final SpinnerAdapter adapter) {
        super.setAdapter(adapter);
        this.mRecycler.clear();
        if (this.getContext().getApplicationInfo().targetSdkVersion >= 21 && adapter != null && adapter.getViewTypeCount() != 1) {
            throw new IllegalArgumentException("Spinner adapter view type count must be 1");
        }
        if (this.mPopup != null) {
            this.mPopup.setAdapter((ListAdapter)new SpinnerCompat$DropDownAdapter(adapter));
            return;
        }
        this.mTempAdapter = new SpinnerCompat$DropDownAdapter(adapter);
    }
    
    public void setEnabled(final boolean b) {
        super.setEnabled(b);
        if (this.mDisableChildrenWhenDisabled) {
            for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
                this.getChildAt(i).setEnabled(b);
            }
        }
    }
    
    public void setOnItemClickListener(final AdapterViewCompat$OnItemClickListener adapterViewCompat$OnItemClickListener) {
        throw new RuntimeException("setOnItemClickListener cannot be used with a spinner.");
    }
    
    void setOnItemClickListenerInt(final AdapterViewCompat$OnItemClickListener onItemClickListener) {
        super.setOnItemClickListener(onItemClickListener);
    }
}
