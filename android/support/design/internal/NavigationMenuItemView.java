// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.internal;

import android.graphics.drawable.Drawable$ConstantState;
import android.widget.TextView;
import android.support.v4.widget.TextViewCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.design.R$drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.ViewStub;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.appcompat.R$attr;
import android.util.TypedValue;
import android.graphics.drawable.StateListDrawable;
import android.view.ViewGroup$LayoutParams;
import android.support.v7.widget.LinearLayoutCompat$LayoutParams;
import android.view.View;
import android.support.v4.view.ViewCompat;
import android.support.design.R$id;
import android.support.design.R$dimen;
import android.view.ViewGroup;
import android.support.design.R$layout;
import android.view.LayoutInflater;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.CheckedTextView;
import android.support.v7.view.menu.MenuItemImpl;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v7.view.menu.MenuView$ItemView;

public class NavigationMenuItemView extends ForegroundLinearLayout implements MenuView$ItemView
{
    private static final int[] CHECKED_STATE_SET;
    private final AccessibilityDelegateCompat mAccessibilityDelegate;
    private FrameLayout mActionArea;
    boolean mCheckable;
    private Drawable mEmptyDrawable;
    private boolean mHasIconTintList;
    private final int mIconSize;
    private ColorStateList mIconTintList;
    private MenuItemImpl mItemData;
    private boolean mNeedsEmptyIcon;
    private final CheckedTextView mTextView;
    
    static {
        CHECKED_STATE_SET = new int[] { 16842912 };
    }
    
    public NavigationMenuItemView(final Context context) {
        this(context, null);
    }
    
    public NavigationMenuItemView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public NavigationMenuItemView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mAccessibilityDelegate = new NavigationMenuItemView$1(this);
        this.setOrientation(0);
        LayoutInflater.from(context).inflate(R$layout.design_navigation_menu_item, (ViewGroup)this, true);
        this.mIconSize = context.getResources().getDimensionPixelSize(R$dimen.design_navigation_icon_size);
        (this.mTextView = (CheckedTextView)this.findViewById(R$id.design_menu_item_text)).setDuplicateParentStateEnabled(true);
        ViewCompat.setAccessibilityDelegate((View)this.mTextView, this.mAccessibilityDelegate);
    }
    
    private void adjustAppearance() {
        if (this.shouldExpandActionArea()) {
            this.mTextView.setVisibility(8);
            if (this.mActionArea != null) {
                final LinearLayoutCompat$LayoutParams layoutParams = (LinearLayoutCompat$LayoutParams)this.mActionArea.getLayoutParams();
                layoutParams.width = -1;
                this.mActionArea.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            }
        }
        else {
            this.mTextView.setVisibility(0);
            if (this.mActionArea != null) {
                final LinearLayoutCompat$LayoutParams layoutParams2 = (LinearLayoutCompat$LayoutParams)this.mActionArea.getLayoutParams();
                layoutParams2.width = -2;
                this.mActionArea.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
            }
        }
    }
    
    private StateListDrawable createDefaultBackground() {
        final TypedValue typedValue = new TypedValue();
        if (this.getContext().getTheme().resolveAttribute(R$attr.colorControlHighlight, typedValue, true)) {
            final StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(NavigationMenuItemView.CHECKED_STATE_SET, (Drawable)new ColorDrawable(typedValue.data));
            stateListDrawable.addState(NavigationMenuItemView.EMPTY_STATE_SET, (Drawable)new ColorDrawable(0));
            return stateListDrawable;
        }
        return null;
    }
    
    private void setActionView(final View view) {
        if (view != null) {
            if (this.mActionArea == null) {
                this.mActionArea = (FrameLayout)((ViewStub)this.findViewById(R$id.design_menu_item_action_area_stub)).inflate();
            }
            this.mActionArea.removeAllViews();
            this.mActionArea.addView(view);
        }
    }
    
    private boolean shouldExpandActionArea() {
        return this.mItemData.getTitle() == null && this.mItemData.getIcon() == null && this.mItemData.getActionView() != null;
    }
    
    @Override
    public MenuItemImpl getItemData() {
        return this.mItemData;
    }
    
    @Override
    public void initialize(final MenuItemImpl mItemData, int visibility) {
        this.mItemData = mItemData;
        if (mItemData.isVisible()) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        this.setVisibility(visibility);
        if (this.getBackground() == null) {
            this.setBackgroundDrawable((Drawable)this.createDefaultBackground());
        }
        this.setCheckable(mItemData.isCheckable());
        this.setChecked(mItemData.isChecked());
        this.setEnabled(mItemData.isEnabled());
        this.setTitle(mItemData.getTitle());
        this.setIcon(mItemData.getIcon());
        this.setActionView(mItemData.getActionView());
        this.adjustAppearance();
    }
    
    protected int[] onCreateDrawableState(final int n) {
        final int[] onCreateDrawableState = super.onCreateDrawableState(n + 1);
        if (this.mItemData != null && this.mItemData.isCheckable() && this.mItemData.isChecked()) {
            mergeDrawableStates(onCreateDrawableState, NavigationMenuItemView.CHECKED_STATE_SET);
        }
        return onCreateDrawableState;
    }
    
    @Override
    public boolean prefersCondensedTitle() {
        return false;
    }
    
    public void recycle() {
        if (this.mActionArea != null) {
            this.mActionArea.removeAllViews();
        }
        this.mTextView.setCompoundDrawables((Drawable)null, (Drawable)null, (Drawable)null, (Drawable)null);
    }
    
    public void setCheckable(final boolean mCheckable) {
        this.refreshDrawableState();
        if (this.mCheckable != mCheckable) {
            this.mCheckable = mCheckable;
            this.mAccessibilityDelegate.sendAccessibilityEvent((View)this.mTextView, 2048);
        }
    }
    
    public void setChecked(final boolean checked) {
        this.refreshDrawableState();
        this.mTextView.setChecked(checked);
    }
    
    public void setIcon(Drawable drawable) {
        if (drawable != null) {
            Drawable mutate = drawable;
            if (this.mHasIconTintList) {
                final Drawable$ConstantState constantState = drawable.getConstantState();
                if (constantState != null) {
                    drawable = constantState.newDrawable();
                }
                mutate = DrawableCompat.wrap(drawable).mutate();
                DrawableCompat.setTintList(mutate, this.mIconTintList);
            }
            mutate.setBounds(0, 0, this.mIconSize, this.mIconSize);
            drawable = mutate;
        }
        else if (this.mNeedsEmptyIcon) {
            if (this.mEmptyDrawable == null) {
                this.mEmptyDrawable = ResourcesCompat.getDrawable(this.getResources(), R$drawable.navigation_empty_icon, this.getContext().getTheme());
                if (this.mEmptyDrawable != null) {
                    this.mEmptyDrawable.setBounds(0, 0, this.mIconSize, this.mIconSize);
                }
            }
            drawable = this.mEmptyDrawable;
        }
        TextViewCompat.setCompoundDrawablesRelative((TextView)this.mTextView, drawable, null, null, null);
    }
    
    void setIconTintList(final ColorStateList mIconTintList) {
        this.mIconTintList = mIconTintList;
        this.mHasIconTintList = (this.mIconTintList != null);
        if (this.mItemData != null) {
            this.setIcon(this.mItemData.getIcon());
        }
    }
    
    public void setNeedsEmptyIcon(final boolean mNeedsEmptyIcon) {
        this.mNeedsEmptyIcon = mNeedsEmptyIcon;
    }
    
    public void setShortcut(final boolean b, final char c) {
    }
    
    public void setTextAppearance(final Context context, final int n) {
        this.mTextView.setTextAppearance(context, n);
    }
    
    public void setTextColor(final ColorStateList textColor) {
        this.mTextView.setTextColor(textColor);
    }
    
    public void setTitle(final CharSequence text) {
        this.mTextView.setText(text);
    }
    
    public boolean showsIcon() {
        return true;
    }
}
