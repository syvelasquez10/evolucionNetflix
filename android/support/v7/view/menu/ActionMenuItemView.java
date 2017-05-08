// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view.menu;

import android.view.MotionEvent;
import android.os.Parcelable;
import android.view.View$MeasureSpec;
import android.widget.Toast;
import android.support.v4.view.ViewCompat;
import android.graphics.Rect;
import android.view.View;
import android.text.TextUtils;
import android.content.res.Configuration;
import android.support.v4.content.res.ConfigurationHelper;
import android.content.res.TypedArray;
import android.content.res.Resources;
import android.support.v7.appcompat.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.ForwardingListener;
import android.view.View$OnLongClickListener;
import android.view.View$OnClickListener;
import android.support.v7.widget.ActionMenuView$ActionMenuChildView;
import android.support.v7.widget.AppCompatTextView;

public class ActionMenuItemView extends AppCompatTextView implements MenuView$ItemView, ActionMenuView$ActionMenuChildView, View$OnClickListener, View$OnLongClickListener
{
    private boolean mAllowTextWithIcon;
    private boolean mExpandedFormat;
    private ForwardingListener mForwardingListener;
    private Drawable mIcon;
    MenuItemImpl mItemData;
    MenuBuilder$ItemInvoker mItemInvoker;
    private int mMaxIconSize;
    private int mMinWidth;
    ActionMenuItemView$PopupCallback mPopupCallback;
    private int mSavedPaddingLeft;
    private CharSequence mTitle;
    
    public ActionMenuItemView(final Context context) {
        this(context, null);
    }
    
    public ActionMenuItemView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public ActionMenuItemView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        final Resources resources = context.getResources();
        this.mAllowTextWithIcon = this.shouldAllowTextWithIcon();
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.ActionMenuItemView, n, 0);
        this.mMinWidth = obtainStyledAttributes.getDimensionPixelSize(R$styleable.ActionMenuItemView_android_minWidth, 0);
        obtainStyledAttributes.recycle();
        this.mMaxIconSize = (int)(resources.getDisplayMetrics().density * 32.0f + 0.5f);
        this.setOnClickListener((View$OnClickListener)this);
        this.setOnLongClickListener((View$OnLongClickListener)this);
        this.mSavedPaddingLeft = -1;
        this.setSaveEnabled(false);
    }
    
    private boolean shouldAllowTextWithIcon() {
        final Configuration configuration = this.getContext().getResources().getConfiguration();
        final int screenWidthDp = ConfigurationHelper.getScreenWidthDp(this.getResources());
        final int screenHeightDp = ConfigurationHelper.getScreenHeightDp(this.getResources());
        return screenWidthDp >= 480 || (screenWidthDp >= 640 && screenHeightDp >= 480) || configuration.orientation == 2;
    }
    
    private void updateTextButtonVisibility() {
        final boolean b = false;
        final boolean b2 = !TextUtils.isEmpty(this.mTitle) && true;
        boolean b3 = false;
        Label_0051: {
            if (this.mIcon != null) {
                b3 = b;
                if (!this.mItemData.showsTextAsAction()) {
                    break Label_0051;
                }
                if (!this.mAllowTextWithIcon) {
                    b3 = b;
                    if (!this.mExpandedFormat) {
                        break Label_0051;
                    }
                }
            }
            b3 = true;
        }
        CharSequence mTitle;
        if (b2 & b3) {
            mTitle = this.mTitle;
        }
        else {
            mTitle = null;
        }
        this.setText(mTitle);
    }
    
    @Override
    public MenuItemImpl getItemData() {
        return this.mItemData;
    }
    
    public boolean hasText() {
        return !TextUtils.isEmpty(this.getText());
    }
    
    @Override
    public void initialize(final MenuItemImpl mItemData, int visibility) {
        this.mItemData = mItemData;
        this.setIcon(mItemData.getIcon());
        this.setTitle(mItemData.getTitleForItemView(this));
        this.setId(mItemData.getItemId());
        if (mItemData.isVisible()) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        this.setVisibility(visibility);
        this.setEnabled(mItemData.isEnabled());
        if (mItemData.hasSubMenu() && this.mForwardingListener == null) {
            this.mForwardingListener = new ActionMenuItemView$ActionMenuItemForwardingListener(this);
        }
    }
    
    @Override
    public boolean needsDividerAfter() {
        return this.hasText();
    }
    
    @Override
    public boolean needsDividerBefore() {
        return this.hasText() && this.mItemData.getIcon() == null;
    }
    
    public void onClick(final View view) {
        if (this.mItemInvoker != null) {
            this.mItemInvoker.invokeItem(this.mItemData);
        }
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mAllowTextWithIcon = this.shouldAllowTextWithIcon();
        this.updateTextButtonVisibility();
    }
    
    public boolean onLongClick(final View view) {
        if (this.hasText()) {
            return false;
        }
        final int[] array = new int[2];
        final Rect rect = new Rect();
        this.getLocationOnScreen(array);
        this.getWindowVisibleDisplayFrame(rect);
        final Context context = this.getContext();
        final int width = this.getWidth();
        final int height = this.getHeight();
        final int n = array[1];
        final int n2 = height / 2;
        int n3 = width / 2 + array[0];
        if (ViewCompat.getLayoutDirection(view) == 0) {
            n3 = context.getResources().getDisplayMetrics().widthPixels - n3;
        }
        final Toast text = Toast.makeText(context, this.mItemData.getTitle(), 0);
        if (n + n2 < rect.height()) {
            text.setGravity(8388661, n3, array[1] + height - rect.top);
        }
        else {
            text.setGravity(81, 0, height);
        }
        text.show();
        return true;
    }
    
    protected void onMeasure(int n, final int n2) {
        final boolean hasText = this.hasText();
        if (hasText && this.mSavedPaddingLeft >= 0) {
            super.setPadding(this.mSavedPaddingLeft, this.getPaddingTop(), this.getPaddingRight(), this.getPaddingBottom());
        }
        super.onMeasure(n, n2);
        final int mode = View$MeasureSpec.getMode(n);
        n = View$MeasureSpec.getSize(n);
        final int measuredWidth = this.getMeasuredWidth();
        if (mode == Integer.MIN_VALUE) {
            n = Math.min(n, this.mMinWidth);
        }
        else {
            n = this.mMinWidth;
        }
        if (mode != 1073741824 && this.mMinWidth > 0 && measuredWidth < n) {
            super.onMeasure(View$MeasureSpec.makeMeasureSpec(n, 1073741824), n2);
        }
        if (!hasText && this.mIcon != null) {
            super.setPadding((this.getMeasuredWidth() - this.mIcon.getBounds().width()) / 2, this.getPaddingTop(), this.getPaddingRight(), this.getPaddingBottom());
        }
    }
    
    public void onRestoreInstanceState(final Parcelable parcelable) {
        super.onRestoreInstanceState((Parcelable)null);
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        return (this.mItemData.hasSubMenu() && this.mForwardingListener != null && this.mForwardingListener.onTouch((View)this, motionEvent)) || super.onTouchEvent(motionEvent);
    }
    
    @Override
    public boolean prefersCondensedTitle() {
        return true;
    }
    
    public void setCheckable(final boolean b) {
    }
    
    public void setChecked(final boolean b) {
    }
    
    public void setExpandedFormat(final boolean mExpandedFormat) {
        if (this.mExpandedFormat != mExpandedFormat) {
            this.mExpandedFormat = mExpandedFormat;
            if (this.mItemData != null) {
                this.mItemData.actionFormatChanged();
            }
        }
    }
    
    public void setIcon(final Drawable mIcon) {
        this.mIcon = mIcon;
        if (mIcon != null) {
            final int intrinsicWidth = mIcon.getIntrinsicWidth();
            int intrinsicHeight;
            final int n = intrinsicHeight = mIcon.getIntrinsicHeight();
            int mMaxIconSize;
            if ((mMaxIconSize = intrinsicWidth) > this.mMaxIconSize) {
                final float n2 = this.mMaxIconSize / intrinsicWidth;
                mMaxIconSize = this.mMaxIconSize;
                intrinsicHeight = (int)(n * n2);
            }
            int mMaxIconSize2 = intrinsicHeight;
            int n3 = mMaxIconSize;
            if (intrinsicHeight > this.mMaxIconSize) {
                final float n4 = this.mMaxIconSize / intrinsicHeight;
                mMaxIconSize2 = this.mMaxIconSize;
                n3 = (int)(mMaxIconSize * n4);
            }
            mIcon.setBounds(0, 0, n3, mMaxIconSize2);
        }
        this.setCompoundDrawables(mIcon, (Drawable)null, (Drawable)null, (Drawable)null);
        this.updateTextButtonVisibility();
    }
    
    public void setItemInvoker(final MenuBuilder$ItemInvoker mItemInvoker) {
        this.mItemInvoker = mItemInvoker;
    }
    
    public void setPadding(final int mSavedPaddingLeft, final int n, final int n2, final int n3) {
        super.setPadding(this.mSavedPaddingLeft = mSavedPaddingLeft, n, n2, n3);
    }
    
    public void setPopupCallback(final ActionMenuItemView$PopupCallback mPopupCallback) {
        this.mPopupCallback = mPopupCallback;
    }
    
    public void setTitle(final CharSequence mTitle) {
        this.setContentDescription(this.mTitle = mTitle);
        this.updateTextButtonVisibility();
    }
}
