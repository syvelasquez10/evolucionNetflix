// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.widget.PopupWindow$OnDismissListener;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemClickListener;
import android.database.DataSetObserver;
import android.app.AlertDialog$Builder;
import android.app.AlertDialog;
import android.util.Log;
import android.widget.Adapter;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.os.Parcelable;
import android.content.DialogInterface;
import android.view.View$MeasureSpec;
import android.widget.SpinnerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.view.View;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.ListPopupWindow;
import android.content.DialogInterface$OnClickListener;

class SpinnerCompat extends AbsSpinnerCompat implements DialogInterface$OnClickListener
{
    private static final int MAX_ITEMS_MEASURED = 15;
    public static final int MODE_DIALOG = 0;
    public static final int MODE_DROPDOWN = 1;
    private static final int MODE_THEME = -1;
    private static final String TAG = "Spinner";
    private boolean mDisableChildrenWhenDisabled;
    int mDropDownWidth;
    private ListPopupWindow.ForwardingListener mForwardingListener;
    private int mGravity;
    private SpinnerPopup mPopup;
    private DropDownAdapter mTempAdapter;
    private Rect mTempRect;
    private final TintManager mTintManager;
    
    SpinnerCompat(final Context context) {
        this(context, null);
    }
    
    SpinnerCompat(final Context context, final int n) {
        this(context, null, R.attr.spinnerStyle, n);
    }
    
    SpinnerCompat(final Context context, final AttributeSet set) {
        this(context, set, R.attr.spinnerStyle);
    }
    
    SpinnerCompat(final Context context, final AttributeSet set, final int n) {
        this(context, set, n, -1);
    }
    
    SpinnerCompat(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n);
        this.mTempRect = new Rect();
        final TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, set, R.styleable.Spinner, n, 0);
        this.setBackgroundDrawable(obtainStyledAttributes.getDrawable(R.styleable.Spinner_android_background));
        int int1 = n2;
        if (n2 == -1) {
            int1 = obtainStyledAttributes.getInt(R.styleable.Spinner_spinnerMode, 0);
        }
        switch (int1) {
            case 0: {
                this.mPopup = (SpinnerPopup)new DialogPopup();
                break;
            }
            case 1: {
                final DropdownPopup mPopup = new DropdownPopup(context, set, n);
                this.mDropDownWidth = obtainStyledAttributes.getLayoutDimension(R.styleable.Spinner_android_dropDownWidth, -2);
                mPopup.setBackgroundDrawable(obtainStyledAttributes.getDrawable(R.styleable.Spinner_android_popupBackground));
                this.mPopup = (SpinnerPopup)mPopup;
                this.mForwardingListener = new ListPopupWindow.ForwardingListener(this) {
                    @Override
                    public ListPopupWindow getPopup() {
                        return mPopup;
                    }
                    
                    public boolean onForwardingStarted() {
                        if (!SpinnerCompat.this.mPopup.isShowing()) {
                            SpinnerCompat.this.mPopup.show();
                        }
                        return true;
                    }
                };
                break;
            }
        }
        this.mGravity = obtainStyledAttributes.getInt(R.styleable.Spinner_android_gravity, 17);
        this.mPopup.setPromptText(obtainStyledAttributes.getString(R.styleable.Spinner_prompt));
        this.mDisableChildrenWhenDisabled = obtainStyledAttributes.getBoolean(R.styleable.Spinner_disableChildrenWhenDisabled, false);
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
    
    public int getDropDownHorizontalOffset() {
        return this.mPopup.getHorizontalOffset();
    }
    
    public int getDropDownVerticalOffset() {
        return this.mPopup.getVerticalOffset();
    }
    
    public int getDropDownWidth() {
        return this.mDropDownWidth;
    }
    
    public Drawable getPopupBackground() {
        return this.mPopup.getBackground();
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
        if (this.mAdapter != null) {
            final View view = this.makeView(this.mSelectedPosition, true);
            final int measuredWidth = view.getMeasuredWidth();
            n = left;
            switch (GravityCompat.getAbsoluteGravity(this.mGravity, ViewCompat.getLayoutDirection((View)this)) & 0x7) {
                case 1: {
                    n = n2 / 2 + left - measuredWidth / 2;
                    break;
                }
                case 5: {
                    n = left + n2 - measuredWidth;
                    break;
                }
            }
            view.offsetLeftAndRight(n);
        }
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
    
    @Override
    public void onRestoreInstanceState(final Parcelable parcelable) {
        final SavedState savedState = (SavedState)parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (savedState.showDropdown) {
            final ViewTreeObserver viewTreeObserver = this.getViewTreeObserver();
            if (viewTreeObserver != null) {
                viewTreeObserver.addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new ViewTreeObserver$OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        if (!SpinnerCompat.this.mPopup.isShowing()) {
                            SpinnerCompat.this.mPopup.show();
                        }
                        final ViewTreeObserver viewTreeObserver = SpinnerCompat.this.getViewTreeObserver();
                        if (viewTreeObserver != null) {
                            viewTreeObserver.removeGlobalOnLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
                        }
                    }
                });
            }
        }
    }
    
    @Override
    public Parcelable onSaveInstanceState() {
        final SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.showDropdown = (this.mPopup != null && this.mPopup.isShowing());
        return (Parcelable)savedState;
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
            this.mPopup.setAdapter((ListAdapter)new DropDownAdapter(adapter));
            return;
        }
        this.mTempAdapter = new DropDownAdapter(adapter);
    }
    
    public void setDropDownHorizontalOffset(final int horizontalOffset) {
        this.mPopup.setHorizontalOffset(horizontalOffset);
    }
    
    public void setDropDownVerticalOffset(final int verticalOffset) {
        this.mPopup.setVerticalOffset(verticalOffset);
    }
    
    public void setDropDownWidth(final int mDropDownWidth) {
        if (!(this.mPopup instanceof DropdownPopup)) {
            Log.e("Spinner", "Cannot set dropdown width for MODE_DIALOG, ignoring");
            return;
        }
        this.mDropDownWidth = mDropDownWidth;
    }
    
    public void setEnabled(final boolean b) {
        super.setEnabled(b);
        if (this.mDisableChildrenWhenDisabled) {
            for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
                this.getChildAt(i).setEnabled(b);
            }
        }
    }
    
    public void setGravity(final int n) {
        if (this.mGravity != n) {
            int mGravity = n;
            if ((n & 0x7) == 0x0) {
                mGravity = (n | 0x800003);
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
    
    public void setPopupBackgroundDrawable(final Drawable backgroundDrawable) {
        if (!(this.mPopup instanceof DropdownPopup)) {
            Log.e("Spinner", "setPopupBackgroundDrawable: incompatible spinner mode; ignoring...");
            return;
        }
        ((DropdownPopup)this.mPopup).setBackgroundDrawable(backgroundDrawable);
    }
    
    public void setPopupBackgroundResource(final int n) {
        this.setPopupBackgroundDrawable(this.mTintManager.getDrawable(n));
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
            if (this.mPopup != null) {
                this.mPopup.dismiss();
                this.mPopup = null;
            }
        }
        
        @Override
        public Drawable getBackground() {
            return null;
        }
        
        @Override
        public CharSequence getHintText() {
            return this.mPrompt;
        }
        
        @Override
        public int getHorizontalOffset() {
            return 0;
        }
        
        @Override
        public int getVerticalOffset() {
            return 0;
        }
        
        @Override
        public boolean isShowing() {
            return this.mPopup != null && this.mPopup.isShowing();
        }
        
        public void onClick(final DialogInterface dialogInterface, final int selection) {
            SpinnerCompat.this.setSelection(selection);
            if (SpinnerCompat.this.mOnItemClickListener != null) {
                SpinnerCompat.this.performItemClick(null, selection, this.mListAdapter.getItemId(selection));
            }
            this.dismiss();
        }
        
        @Override
        public void setAdapter(final ListAdapter mListAdapter) {
            this.mListAdapter = mListAdapter;
        }
        
        @Override
        public void setBackgroundDrawable(final Drawable drawable) {
            Log.e("Spinner", "Cannot set popup background for MODE_DIALOG, ignoring");
        }
        
        @Override
        public void setHorizontalOffset(final int n) {
            Log.e("Spinner", "Cannot set horizontal offset for MODE_DIALOG, ignoring");
        }
        
        @Override
        public void setPromptText(final CharSequence mPrompt) {
            this.mPrompt = mPrompt;
        }
        
        @Override
        public void setVerticalOffset(final int n) {
            Log.e("Spinner", "Cannot set vertical offset for MODE_DIALOG, ignoring");
        }
        
        @Override
        public void show() {
            if (this.mListAdapter == null) {
                return;
            }
            final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(SpinnerCompat.this.getContext());
            if (this.mPrompt != null) {
                alertDialog$Builder.setTitle(this.mPrompt);
            }
            (this.mPopup = alertDialog$Builder.setSingleChoiceItems(this.mListAdapter, SpinnerCompat.this.getSelectedItemPosition(), (DialogInterface$OnClickListener)this).create()).show();
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
            this.setAnchorView((View)SpinnerCompat.this);
            this.setModal(true);
            this.setPromptPosition(0);
            this.setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener() {
                public void onItemClick(final AdapterView<?> adapterView, final View view, final int selection, final long n) {
                    SpinnerCompat.this.setSelection(selection);
                    if (SpinnerCompat.this.mOnItemClickListener != null) {
                        SpinnerCompat.this.performItemClick(view, selection, DropdownPopup.this.mAdapter.getItemId(selection));
                    }
                    DropdownPopup.this.dismiss();
                }
            });
        }
        
        void computeContentWidth() {
            final Drawable background = this.getBackground();
            int right = 0;
            if (background != null) {
                background.getPadding(SpinnerCompat.this.mTempRect);
                if (ViewUtils.isLayoutRtl((View)SpinnerCompat.this)) {
                    right = SpinnerCompat.this.mTempRect.right;
                }
                else {
                    right = -SpinnerCompat.this.mTempRect.left;
                }
            }
            else {
                final Rect access$400 = SpinnerCompat.this.mTempRect;
                SpinnerCompat.this.mTempRect.right = 0;
                access$400.left = 0;
            }
            final int paddingLeft = SpinnerCompat.this.getPaddingLeft();
            final int paddingRight = SpinnerCompat.this.getPaddingRight();
            final int width = SpinnerCompat.this.getWidth();
            if (SpinnerCompat.this.mDropDownWidth == -2) {
                final int measureContentWidth = SpinnerCompat.this.measureContentWidth((SpinnerAdapter)this.mAdapter, this.getBackground());
                final int n = SpinnerCompat.this.getContext().getResources().getDisplayMetrics().widthPixels - SpinnerCompat.this.mTempRect.left - SpinnerCompat.this.mTempRect.right;
                int n2;
                if ((n2 = measureContentWidth) > n) {
                    n2 = n;
                }
                this.setContentWidth(Math.max(n2, width - paddingLeft - paddingRight));
            }
            else if (SpinnerCompat.this.mDropDownWidth == -1) {
                this.setContentWidth(width - paddingLeft - paddingRight);
            }
            else {
                this.setContentWidth(SpinnerCompat.this.mDropDownWidth);
            }
            int horizontalOffset;
            if (ViewUtils.isLayoutRtl((View)SpinnerCompat.this)) {
                horizontalOffset = right + (width - paddingRight - this.getWidth());
            }
            else {
                horizontalOffset = right + paddingLeft;
            }
            this.setHorizontalOffset(horizontalOffset);
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
        
        public void show(final int n, final int n2) {
            final boolean showing = this.isShowing();
            this.computeContentWidth();
            this.setInputMethodMode(2);
            super.show();
            this.getListView().setChoiceMode(1);
            this.setSelection(SpinnerCompat.this.getSelectedItemPosition());
            if (!showing) {
                final ViewTreeObserver viewTreeObserver = SpinnerCompat.this.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    final ViewTreeObserver$OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener = (ViewTreeObserver$OnGlobalLayoutListener)new ViewTreeObserver$OnGlobalLayoutListener() {
                        public void onGlobalLayout() {
                            DropdownPopup.this.computeContentWidth();
                            DropdownPopup.this.show();
                        }
                    };
                    viewTreeObserver.addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)viewTreeObserver$OnGlobalLayoutListener);
                    this.setOnDismissListener((PopupWindow$OnDismissListener)new PopupWindow$OnDismissListener() {
                        public void onDismiss() {
                            final ViewTreeObserver viewTreeObserver = SpinnerCompat.this.getViewTreeObserver();
                            if (viewTreeObserver != null) {
                                viewTreeObserver.removeGlobalOnLayoutListener(viewTreeObserver$OnGlobalLayoutListener);
                            }
                        }
                    });
                }
            }
        }
    }
    
    static class SavedState extends AbsSpinnerCompat.SavedState
    {
        public static final Parcelable$Creator<SavedState> CREATOR;
        boolean showDropdown;
        
        static {
            CREATOR = (Parcelable$Creator)new Parcelable$Creator<SavedState>() {
                public SavedState createFromParcel(final Parcel parcel) {
                    return new SavedState(parcel);
                }
                
                public SavedState[] newArray(final int n) {
                    return new SavedState[n];
                }
            };
        }
        
        private SavedState(final Parcel parcel) {
            super(parcel);
            this.showDropdown = (parcel.readByte() != 0);
        }
        
        SavedState(final Parcelable parcelable) {
            super(parcelable);
        }
        
        @Override
        public void writeToParcel(final Parcel parcel, int n) {
            super.writeToParcel(parcel, n);
            if (this.showDropdown) {
                n = 1;
            }
            else {
                n = 0;
            }
            parcel.writeByte((byte)n);
        }
    }
    
    private interface SpinnerPopup
    {
        void dismiss();
        
        Drawable getBackground();
        
        CharSequence getHintText();
        
        int getHorizontalOffset();
        
        int getVerticalOffset();
        
        boolean isShowing();
        
        void setAdapter(final ListAdapter p0);
        
        void setBackgroundDrawable(final Drawable p0);
        
        void setHorizontalOffset(final int p0);
        
        void setPromptText(final CharSequence p0);
        
        void setVerticalOffset(final int p0);
        
        void show();
    }
}
