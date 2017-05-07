// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.util.Log;
import android.widget.Adapter;
import android.view.MotionEvent;
import android.os.Parcelable;
import android.content.DialogInterface;
import android.view.View$MeasureSpec;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.appcompat.R$attr;
import android.support.v7.widget.ListPopupWindow$ForwardingListener;
import android.content.DialogInterface$OnClickListener;
import android.view.ViewTreeObserver;
import android.widget.PopupWindow$OnDismissListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.widget.SpinnerAdapter;
import android.widget.AdapterView$OnItemClickListener;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.ListAdapter;
import android.support.v7.widget.ListPopupWindow;

class SpinnerCompat$DropdownPopup extends ListPopupWindow implements SpinnerCompat$SpinnerPopup
{
    private ListAdapter mAdapter;
    private CharSequence mHintText;
    final /* synthetic */ SpinnerCompat this$0;
    
    public SpinnerCompat$DropdownPopup(final SpinnerCompat spinnerCompat, final Context context, final AttributeSet set, final int n) {
        this.this$0 = spinnerCompat;
        super(context, set, n);
        this.setAnchorView((View)spinnerCompat);
        this.setModal(true);
        this.setPromptPosition(0);
        this.setOnItemClickListener((AdapterView$OnItemClickListener)new SpinnerCompat$DropdownPopup$1(this, spinnerCompat));
    }
    
    void computeContentWidth() {
        final Drawable background = this.getBackground();
        int right;
        if (background != null) {
            background.getPadding(this.this$0.mTempRect);
            if (ViewUtils.isLayoutRtl((View)this.this$0)) {
                right = this.this$0.mTempRect.right;
            }
            else {
                right = -this.this$0.mTempRect.left;
            }
        }
        else {
            final Rect access$400 = this.this$0.mTempRect;
            this.this$0.mTempRect.right = 0;
            access$400.left = 0;
            right = 0;
        }
        final int paddingLeft = this.this$0.getPaddingLeft();
        final int paddingRight = this.this$0.getPaddingRight();
        final int width = this.this$0.getWidth();
        if (this.this$0.mDropDownWidth == -2) {
            int measureContentWidth = this.this$0.measureContentWidth((SpinnerAdapter)this.mAdapter, this.getBackground());
            final int n = this.this$0.getContext().getResources().getDisplayMetrics().widthPixels - this.this$0.mTempRect.left - this.this$0.mTempRect.right;
            if (measureContentWidth > n) {
                measureContentWidth = n;
            }
            this.setContentWidth(Math.max(measureContentWidth, width - paddingLeft - paddingRight));
        }
        else if (this.this$0.mDropDownWidth == -1) {
            this.setContentWidth(width - paddingLeft - paddingRight);
        }
        else {
            this.setContentWidth(this.this$0.mDropDownWidth);
        }
        int horizontalOffset;
        if (ViewUtils.isLayoutRtl((View)this.this$0)) {
            horizontalOffset = width - paddingRight - this.getWidth() + right;
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
        this.setSelection(this.this$0.getSelectedItemPosition());
        if (!showing) {
            final ViewTreeObserver viewTreeObserver = this.this$0.getViewTreeObserver();
            if (viewTreeObserver != null) {
                final SpinnerCompat$DropdownPopup$2 spinnerCompat$DropdownPopup$2 = new SpinnerCompat$DropdownPopup$2(this);
                viewTreeObserver.addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)spinnerCompat$DropdownPopup$2);
                this.setOnDismissListener((PopupWindow$OnDismissListener)new SpinnerCompat$DropdownPopup$3(this, (ViewTreeObserver$OnGlobalLayoutListener)spinnerCompat$DropdownPopup$2));
            }
        }
    }
}
