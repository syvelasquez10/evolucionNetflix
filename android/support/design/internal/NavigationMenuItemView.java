// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.internal;

import android.support.v4.widget.TextViewCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.support.design.R$attr;
import android.util.TypedValue;
import android.graphics.drawable.StateListDrawable;
import android.support.design.R$dimen;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v7.internal.view.menu.m;
import android.content.res.ColorStateList;
import android.support.v7.internal.view.menu.aa;
import android.widget.TextView;

public class NavigationMenuItemView extends TextView implements aa
{
    private static final int[] CHECKED_STATE_SET;
    private int mIconSize;
    private ColorStateList mIconTintList;
    private m mItemData;
    
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
        this.mIconSize = context.getResources().getDimensionPixelSize(R$dimen.navigation_icon_size);
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
    
    public m getItemData() {
        return this.mItemData;
    }
    
    public void initialize(final m mItemData, int visibility) {
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
    }
    
    protected int[] onCreateDrawableState(final int n) {
        final int[] onCreateDrawableState = super.onCreateDrawableState(n + 1);
        if (this.mItemData != null && this.mItemData.isCheckable() && this.mItemData.isChecked()) {
            mergeDrawableStates(onCreateDrawableState, NavigationMenuItemView.CHECKED_STATE_SET);
        }
        return onCreateDrawableState;
    }
    
    public boolean prefersCondensedTitle() {
        return false;
    }
    
    public void setCheckable(final boolean b) {
        this.refreshDrawableState();
    }
    
    public void setChecked(final boolean b) {
        this.refreshDrawableState();
    }
    
    public void setIcon(final Drawable drawable) {
        Drawable mutate = drawable;
        if (drawable != null) {
            mutate = DrawableCompat.wrap(drawable.getConstantState().newDrawable()).mutate();
            mutate.setBounds(0, 0, this.mIconSize, this.mIconSize);
            DrawableCompat.setTintList(mutate, this.mIconTintList);
        }
        TextViewCompat.setCompoundDrawablesRelative(this, mutate, null, null, null);
    }
    
    void setIconTintList(final ColorStateList mIconTintList) {
        this.mIconTintList = mIconTintList;
        if (this.mItemData != null) {
            this.setIcon(this.mItemData.getIcon());
        }
    }
    
    public void setTitle(final CharSequence text) {
        this.setText(text);
    }
}
