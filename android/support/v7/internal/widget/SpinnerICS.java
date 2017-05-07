// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.widget.AdapterView$OnItemClickListener;
import android.database.DataSetObserver;
import android.app.AlertDialog$Builder;
import android.app.AlertDialog;
import android.widget.Adapter;
import android.content.DialogInterface;
import android.view.View$MeasureSpec;
import android.graphics.drawable.Drawable;
import android.widget.SpinnerAdapter;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.View;
import android.content.res.TypedArray;
import android.widget.ListAdapter;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Rect;
import android.content.DialogInterface$OnClickListener;

class SpinnerICS extends AbsSpinnerICS implements DialogInterface$OnClickListener
{
    private static final int MAX_ITEMS_MEASURED = 15;
    static final int MODE_DIALOG = 0;
    static final int MODE_DROPDOWN = 1;
    private static final int MODE_THEME = -1;
    private static final String TAG = "Spinner";
    int mDropDownWidth;
    private int mGravity;
    private SpinnerPopup mPopup;
    private DropDownAdapter mTempAdapter;
    private Rect mTempRect;
    
    SpinnerICS(final Context context) {
        this(context, null);
    }
    
    SpinnerICS(final Context context, final int n) {
        this(context, null, R.attr.spinnerStyle, n);
    }
    
    SpinnerICS(final Context context, final AttributeSet set) {
        this(context, set, R.attr.spinnerStyle);
    }
    
    SpinnerICS(final Context context, final AttributeSet set, final int n) {
        this(context, set, n, -1);
    }
    
    SpinnerICS(final Context context, final AttributeSet set, int n, final int n2) {
        super(context, set, n);
        this.mTempRect = new Rect();
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.Spinner, n, 0);
        int int1 = n2;
        if (n2 == -1) {
            int1 = obtainStyledAttributes.getInt(7, 0);
        }
        switch (int1) {
            case 0: {
                this.mPopup = (SpinnerPopup)new DialogPopup();
                break;
            }
            case 1: {
                final DropdownPopup mPopup = new DropdownPopup(context, set, n);
                this.mDropDownWidth = obtainStyledAttributes.getLayoutDimension(3, -2);
                mPopup.setBackgroundDrawable(obtainStyledAttributes.getDrawable(2));
                n = obtainStyledAttributes.getDimensionPixelOffset(5, 0);
                if (n != 0) {
                    mPopup.setVerticalOffset(n);
                }
                n = obtainStyledAttributes.getDimensionPixelOffset(4, 0);
                if (n != 0) {
                    mPopup.setHorizontalOffset(n);
                }
                this.mPopup = (SpinnerPopup)mPopup;
                break;
            }
        }
        this.mGravity = obtainStyledAttributes.getInt(0, 17);
        this.mPopup.setPromptText(obtainStyledAttributes.getString(6));
        obtainStyledAttributes.recycle();
        if (this.mTempAdapter != null) {
            this.mPopup.setAdapter((ListAdapter)this.mTempAdapter);
            this.mTempAdapter = null;
        }
    }
    
    private View makeAndAddView(final int n) {
        if (!this.mDataChanged) {
            final View value = this.mRecycler.get(n);
            if (value != null) {
                this.setUpChild(value);
                return value;
            }
        }
        final View view = this.mAdapter.getView(n, (View)null, (ViewGroup)this);
        this.setUpChild(view);
        return view;
    }
    
    private void setUpChild(final View view) {
        ViewGroup$LayoutParams viewGroup$LayoutParams;
        if ((viewGroup$LayoutParams = view.getLayoutParams()) == null) {
            viewGroup$LayoutParams = this.generateDefaultLayoutParams();
        }
        this.addViewInLayout(view, 0, viewGroup$LayoutParams);
        view.setSelected(this.hasFocus());
        view.measure(ViewGroup.getChildMeasureSpec(this.mWidthMeasureSpec, this.mSpinnerPadding.left + this.mSpinnerPadding.right, viewGroup$LayoutParams.width), ViewGroup.getChildMeasureSpec(this.mHeightMeasureSpec, this.mSpinnerPadding.top + this.mSpinnerPadding.bottom, viewGroup$LayoutParams.height));
        final int n = this.mSpinnerPadding.top + (this.getMeasuredHeight() - this.mSpinnerPadding.bottom - this.mSpinnerPadding.top - view.getMeasuredHeight()) / 2;
        view.layout(0, n, 0 + view.getMeasuredWidth(), n + view.getMeasuredHeight());
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
                    view2 = this.makeAndAddView(0);
                    this.mRecycler.put(0, view2);
                    this.removeAllViewsInLayout();
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
    
    public CharSequence getPrompt() {
        return this.mPopup.getHintText();
    }
    
    @Override
    void layout(int n, final boolean b) {
        final int left = this.mSpinnerPadding.left;
        final int n2 = this.getRight() - this.getLeft() - this.mSpinnerPadding.left - this.mSpinnerPadding.right;
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
        final View andAddView = this.makeAndAddView(this.mSelectedPosition);
        final int measuredWidth = andAddView.getMeasuredWidth();
        n = left;
        switch (this.mGravity & 0x7) {
            case 1: {
                n = n2 / 2 + left - measuredWidth / 2;
                break;
            }
            case 5: {
                n = left + n2 - measuredWidth;
                break;
            }
        }
        andAddView.offsetLeftAndRight(n);
        this.mRecycler.clear();
        this.invalidate();
        this.checkSelectionChanged();
        this.mDataChanged = false;
        this.mNeedSync = false;
        this.setNextSelectedPositionInt(this.mSelectedPosition);
    }
    
    int measureContentWidth(final SpinnerAdapter spinnerAdapter, final Drawable drawable) {
        int n;
        if (spinnerAdapter == null) {
            n = 0;
        }
        else {
            int max = 0;
            View view = null;
            int n2 = 0;
            final int measureSpec = View$MeasureSpec.makeMeasureSpec(0, 0);
            final int measureSpec2 = View$MeasureSpec.makeMeasureSpec(0, 0);
            final int max2 = Math.max(0, this.getSelectedItemPosition());
            int n3;
            for (int min = Math.min(spinnerAdapter.getCount(), max2 + 15), i = Math.max(0, max2 - (15 - (min - max2))); i < min; ++i, n2 = n3) {
                final int itemViewType = spinnerAdapter.getItemViewType(i);
                if (itemViewType != (n3 = n2)) {
                    n3 = itemViewType;
                    view = null;
                }
                view = spinnerAdapter.getView(i, view, (ViewGroup)this);
                if (view.getLayoutParams() == null) {
                    view.setLayoutParams(new ViewGroup$LayoutParams(-2, -2));
                }
                view.measure(measureSpec, measureSpec2);
                max = Math.max(max, view.getMeasuredWidth());
            }
            n = max;
            if (drawable != null) {
                drawable.getPadding(this.mTempRect);
                return max + (this.mTempRect.left + this.mTempRect.right);
            }
        }
        return n;
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
        if (this.mPopup != null) {
            this.mPopup.setAdapter((ListAdapter)new DropDownAdapter(adapter));
            return;
        }
        this.mTempAdapter = new DropDownAdapter(adapter);
    }
    
    public void setGravity(final int n) {
        if (this.mGravity != n) {
            int mGravity = n;
            if ((n & 0x7) == 0x0) {
                mGravity = (n | 0x3);
            }
            this.mGravity = mGravity;
            this.requestLayout();
        }
    }
    
    public void setOnItemClickListener(final OnItemClickListener onItemClickListener) {
        throw new RuntimeException("setOnItemClickListener cannot be used with a spinner.");
    }
    
    void setOnItemClickListenerInt(final OnItemClickListener onItemClickListener) {
        super.setOnItemClickListener(onItemClickListener);
    }
    
    public void setPrompt(final CharSequence promptText) {
        this.mPopup.setPromptText(promptText);
    }
    
    public void setPromptId(final int n) {
        this.setPrompt(this.getContext().getText(n));
    }
    
    private class DialogPopup implements SpinnerPopup, DialogInterface$OnClickListener
    {
        private ListAdapter mListAdapter;
        private AlertDialog mPopup;
        private CharSequence mPrompt;
        
        @Override
        public void dismiss() {
            this.mPopup.dismiss();
            this.mPopup = null;
        }
        
        @Override
        public CharSequence getHintText() {
            return this.mPrompt;
        }
        
        @Override
        public boolean isShowing() {
            return this.mPopup != null && this.mPopup.isShowing();
        }
        
        public void onClick(final DialogInterface dialogInterface, final int selection) {
            SpinnerICS.this.setSelection(selection);
            if (SpinnerICS.this.mOnItemClickListener != null) {
                SpinnerICS.this.performItemClick(null, selection, this.mListAdapter.getItemId(selection));
            }
            this.dismiss();
        }
        
        @Override
        public void setAdapter(final ListAdapter mListAdapter) {
            this.mListAdapter = mListAdapter;
        }
        
        @Override
        public void setPromptText(final CharSequence mPrompt) {
            this.mPrompt = mPrompt;
        }
        
        @Override
        public void show() {
            final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(SpinnerICS.this.getContext());
            if (this.mPrompt != null) {
                alertDialog$Builder.setTitle(this.mPrompt);
            }
            this.mPopup = alertDialog$Builder.setSingleChoiceItems(this.mListAdapter, SpinnerICS.this.getSelectedItemPosition(), (DialogInterface$OnClickListener)this).show();
        }
    }
    
    private static class DropDownAdapter implements ListAdapter, SpinnerAdapter
    {
        private SpinnerAdapter mAdapter;
        private ListAdapter mListAdapter;
        
        public DropDownAdapter(final SpinnerAdapter mAdapter) {
            this.mAdapter = mAdapter;
            if (mAdapter instanceof ListAdapter) {
                this.mListAdapter = (ListAdapter)mAdapter;
            }
        }
        
        public boolean areAllItemsEnabled() {
            final ListAdapter mListAdapter = this.mListAdapter;
            return mListAdapter == null || mListAdapter.areAllItemsEnabled();
        }
        
        public int getCount() {
            if (this.mAdapter == null) {
                return 0;
            }
            return this.mAdapter.getCount();
        }
        
        public View getDropDownView(final int n, final View view, final ViewGroup viewGroup) {
            if (this.mAdapter == null) {
                return null;
            }
            return this.mAdapter.getDropDownView(n, view, viewGroup);
        }
        
        public Object getItem(final int n) {
            if (this.mAdapter == null) {
                return null;
            }
            return this.mAdapter.getItem(n);
        }
        
        public long getItemId(final int n) {
            if (this.mAdapter == null) {
                return -1L;
            }
            return this.mAdapter.getItemId(n);
        }
        
        public int getItemViewType(final int n) {
            return 0;
        }
        
        public View getView(final int n, final View view, final ViewGroup viewGroup) {
            return this.getDropDownView(n, view, viewGroup);
        }
        
        public int getViewTypeCount() {
            return 1;
        }
        
        public boolean hasStableIds() {
            return this.mAdapter != null && this.mAdapter.hasStableIds();
        }
        
        public boolean isEmpty() {
            return this.getCount() == 0;
        }
        
        public boolean isEnabled(final int n) {
            final ListAdapter mListAdapter = this.mListAdapter;
            return mListAdapter == null || mListAdapter.isEnabled(n);
        }
        
        public void registerDataSetObserver(final DataSetObserver dataSetObserver) {
            if (this.mAdapter != null) {
                this.mAdapter.registerDataSetObserver(dataSetObserver);
            }
        }
        
        public void unregisterDataSetObserver(final DataSetObserver dataSetObserver) {
            if (this.mAdapter != null) {
                this.mAdapter.unregisterDataSetObserver(dataSetObserver);
            }
        }
    }
    
    private class DropdownPopup extends ListPopupWindow implements SpinnerPopup
    {
        private ListAdapter mAdapter;
        private CharSequence mHintText;
        
        public DropdownPopup(final Context context, final AttributeSet set, final int n) {
            super(context, set, n);
            this.setAnchorView((View)SpinnerICS.this);
            this.setModal(true);
            this.setPromptPosition(0);
            this.setOnItemClickListener((AdapterView$OnItemClickListener)new OnItemClickListenerWrapper(SpinnerICS.this, new OnItemClickListener() {
                @Override
                public void onItemClick(final AdapterViewICS adapterViewICS, final View view, final int selection, final long n) {
                    SpinnerICS.this.setSelection(selection);
                    if (SpinnerICS.this.mOnItemClickListener != null) {
                        SpinnerICS.this.performItemClick(view, selection, DropdownPopup.this.mAdapter.getItemId(selection));
                    }
                    DropdownPopup.this.dismiss();
                }
            }));
        }
        
        @Override
        public CharSequence getHintText() {
            return this.mHintText;
        }
        
        @Override
        public void setAdapter(final ListAdapter listAdapter) {
            super.setAdapter(listAdapter);
            this.mAdapter = listAdapter;
        }
        
        @Override
        public void setPromptText(final CharSequence mHintText) {
            this.mHintText = mHintText;
        }
        
        @Override
        public void show() {
            final int paddingLeft = SpinnerICS.this.getPaddingLeft();
            if (SpinnerICS.this.mDropDownWidth == -2) {
                this.setContentWidth(Math.max(SpinnerICS.this.measureContentWidth((SpinnerAdapter)this.mAdapter, this.getBackground()), SpinnerICS.this.getWidth() - paddingLeft - SpinnerICS.this.getPaddingRight()));
            }
            else if (SpinnerICS.this.mDropDownWidth == -1) {
                this.setContentWidth(SpinnerICS.this.getWidth() - paddingLeft - SpinnerICS.this.getPaddingRight());
            }
            else {
                this.setContentWidth(SpinnerICS.this.mDropDownWidth);
            }
            final Drawable background = this.getBackground();
            int n = 0;
            if (background != null) {
                background.getPadding(SpinnerICS.this.mTempRect);
                n = -SpinnerICS.this.mTempRect.left;
            }
            this.setHorizontalOffset(n + paddingLeft);
            this.setInputMethodMode(2);
            super.show();
            this.getListView().setChoiceMode(1);
            this.setSelection(SpinnerICS.this.getSelectedItemPosition());
        }
    }
    
    private interface SpinnerPopup
    {
        void dismiss();
        
        CharSequence getHintText();
        
        boolean isShowing();
        
        void setAdapter(final ListAdapter p0);
        
        void setPromptText(final CharSequence p0);
        
        void show();
    }
}
