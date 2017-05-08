// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import android.support.v7.view.menu.MenuItemImpl;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View$MeasureSpec;
import android.support.v4.view.WindowInsetsCompat;
import android.view.Menu;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.appcompat.R$attr;
import android.support.v7.content.res.AppCompatResources;
import android.util.TypedValue;
import android.graphics.drawable.Drawable;
import android.content.res.ColorStateList;
import android.view.ViewGroup;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuBuilder$Callback;
import android.view.View;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.TintTypedArray;
import android.support.design.R$style;
import android.support.design.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.support.design.internal.NavigationMenuPresenter;
import android.view.MenuInflater;
import android.support.design.internal.NavigationMenu;
import android.support.design.internal.ScrimInsetsFrameLayout;

public class NavigationView extends ScrimInsetsFrameLayout
{
    private static final int[] CHECKED_STATE_SET;
    private static final int[] DISABLED_STATE_SET;
    private static final int PRESENTER_NAVIGATION_VIEW_ID = 1;
    NavigationView$OnNavigationItemSelectedListener mListener;
    private int mMaxWidth;
    private final NavigationMenu mMenu;
    private MenuInflater mMenuInflater;
    private final NavigationMenuPresenter mPresenter;
    
    static {
        CHECKED_STATE_SET = new int[] { 16842912 };
        DISABLED_STATE_SET = new int[] { -16842910 };
    }
    
    public NavigationView(final Context context) {
        this(context, null);
    }
    
    public NavigationView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public NavigationView(final Context context, final AttributeSet set, int resourceId) {
        super(context, set, resourceId);
        this.mPresenter = new NavigationMenuPresenter();
        ThemeUtils.checkAppCompatTheme(context);
        this.mMenu = new NavigationMenu(context);
        final TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, set, R$styleable.NavigationView, resourceId, R$style.Widget_Design_NavigationView);
        ViewCompat.setBackground((View)this, obtainStyledAttributes.getDrawable(R$styleable.NavigationView_android_background));
        if (obtainStyledAttributes.hasValue(R$styleable.NavigationView_elevation)) {
            ViewCompat.setElevation((View)this, obtainStyledAttributes.getDimensionPixelSize(R$styleable.NavigationView_elevation, 0));
        }
        ViewCompat.setFitsSystemWindows((View)this, obtainStyledAttributes.getBoolean(R$styleable.NavigationView_android_fitsSystemWindows, false));
        this.mMaxWidth = obtainStyledAttributes.getDimensionPixelSize(R$styleable.NavigationView_android_maxWidth, 0);
        ColorStateList itemIconTintList;
        if (obtainStyledAttributes.hasValue(R$styleable.NavigationView_itemIconTint)) {
            itemIconTintList = obtainStyledAttributes.getColorStateList(R$styleable.NavigationView_itemIconTint);
        }
        else {
            itemIconTintList = this.createDefaultColorStateList(16842808);
        }
        boolean b;
        if (obtainStyledAttributes.hasValue(R$styleable.NavigationView_itemTextAppearance)) {
            resourceId = obtainStyledAttributes.getResourceId(R$styleable.NavigationView_itemTextAppearance, 0);
            b = true;
        }
        else {
            resourceId = 0;
            b = false;
        }
        ColorStateList colorStateList = null;
        if (obtainStyledAttributes.hasValue(R$styleable.NavigationView_itemTextColor)) {
            colorStateList = obtainStyledAttributes.getColorStateList(R$styleable.NavigationView_itemTextColor);
        }
        ColorStateList defaultColorStateList = colorStateList;
        if (!b && (defaultColorStateList = colorStateList) == null) {
            defaultColorStateList = this.createDefaultColorStateList(16842806);
        }
        final Drawable drawable = obtainStyledAttributes.getDrawable(R$styleable.NavigationView_itemBackground);
        this.mMenu.setCallback(new NavigationView$1(this));
        this.mPresenter.setId(1);
        this.mPresenter.initForMenu(context, this.mMenu);
        this.mPresenter.setItemIconTintList(itemIconTintList);
        if (b) {
            this.mPresenter.setItemTextAppearance(resourceId);
        }
        this.mPresenter.setItemTextColor(defaultColorStateList);
        this.mPresenter.setItemBackground(drawable);
        this.mMenu.addMenuPresenter(this.mPresenter);
        this.addView((View)this.mPresenter.getMenuView((ViewGroup)this));
        if (obtainStyledAttributes.hasValue(R$styleable.NavigationView_menu)) {
            this.inflateMenu(obtainStyledAttributes.getResourceId(R$styleable.NavigationView_menu, 0));
        }
        if (obtainStyledAttributes.hasValue(R$styleable.NavigationView_headerLayout)) {
            this.inflateHeaderView(obtainStyledAttributes.getResourceId(R$styleable.NavigationView_headerLayout, 0));
        }
        obtainStyledAttributes.recycle();
    }
    
    private ColorStateList createDefaultColorStateList(int data) {
        final TypedValue typedValue = new TypedValue();
        if (this.getContext().getTheme().resolveAttribute(data, typedValue, true)) {
            final ColorStateList colorStateList = AppCompatResources.getColorStateList(this.getContext(), typedValue.resourceId);
            if (this.getContext().getTheme().resolveAttribute(R$attr.colorPrimary, typedValue, true)) {
                data = typedValue.data;
                final int defaultColor = colorStateList.getDefaultColor();
                return new ColorStateList(new int[][] { NavigationView.DISABLED_STATE_SET, NavigationView.CHECKED_STATE_SET, NavigationView.EMPTY_STATE_SET }, new int[] { colorStateList.getColorForState(NavigationView.DISABLED_STATE_SET, defaultColor), data, defaultColor });
            }
        }
        return null;
    }
    
    private MenuInflater getMenuInflater() {
        if (this.mMenuInflater == null) {
            this.mMenuInflater = new SupportMenuInflater(this.getContext());
        }
        return this.mMenuInflater;
    }
    
    public void addHeaderView(final View view) {
        this.mPresenter.addHeaderView(view);
    }
    
    public int getHeaderCount() {
        return this.mPresenter.getHeaderCount();
    }
    
    public View getHeaderView(final int n) {
        return this.mPresenter.getHeaderView(n);
    }
    
    public Drawable getItemBackground() {
        return this.mPresenter.getItemBackground();
    }
    
    public ColorStateList getItemIconTintList() {
        return this.mPresenter.getItemTintList();
    }
    
    public ColorStateList getItemTextColor() {
        return this.mPresenter.getItemTextColor();
    }
    
    public Menu getMenu() {
        return (Menu)this.mMenu;
    }
    
    public View inflateHeaderView(final int n) {
        return this.mPresenter.inflateHeaderView(n);
    }
    
    public void inflateMenu(final int n) {
        this.mPresenter.setUpdateSuspended(true);
        this.getMenuInflater().inflate(n, (Menu)this.mMenu);
        this.mPresenter.setUpdateSuspended(false);
        this.mPresenter.updateMenuView(false);
    }
    
    @Override
    protected void onInsetsChanged(final WindowInsetsCompat windowInsetsCompat) {
        this.mPresenter.dispatchApplyWindowInsets(windowInsetsCompat);
    }
    
    protected void onMeasure(final int n, final int n2) {
        int n3 = n;
        Label_0042: {
            switch (View$MeasureSpec.getMode(n)) {
                default: {
                    n3 = n;
                    break Label_0042;
                }
                case 0: {
                    n3 = View$MeasureSpec.makeMeasureSpec(this.mMaxWidth, 1073741824);
                    break Label_0042;
                }
                case Integer.MIN_VALUE: {
                    n3 = View$MeasureSpec.makeMeasureSpec(Math.min(View$MeasureSpec.getSize(n), this.mMaxWidth), 1073741824);
                }
                case 1073741824: {
                    super.onMeasure(n3, n2);
                }
            }
        }
    }
    
    protected void onRestoreInstanceState(final Parcelable parcelable) {
        if (!(parcelable instanceof NavigationView$SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        final NavigationView$SavedState navigationView$SavedState = (NavigationView$SavedState)parcelable;
        super.onRestoreInstanceState(navigationView$SavedState.getSuperState());
        this.mMenu.restorePresenterStates(navigationView$SavedState.menuState);
    }
    
    protected Parcelable onSaveInstanceState() {
        final NavigationView$SavedState navigationView$SavedState = new NavigationView$SavedState(super.onSaveInstanceState());
        navigationView$SavedState.menuState = new Bundle();
        this.mMenu.savePresenterStates(navigationView$SavedState.menuState);
        return (Parcelable)navigationView$SavedState;
    }
    
    public void removeHeaderView(final View view) {
        this.mPresenter.removeHeaderView(view);
    }
    
    public void setCheckedItem(final int n) {
        final MenuItem item = this.mMenu.findItem(n);
        if (item != null) {
            this.mPresenter.setCheckedItem((MenuItemImpl)item);
        }
    }
    
    public void setItemBackground(final Drawable itemBackground) {
        this.mPresenter.setItemBackground(itemBackground);
    }
    
    public void setItemBackgroundResource(final int n) {
        this.setItemBackground(ContextCompat.getDrawable(this.getContext(), n));
    }
    
    public void setItemIconTintList(final ColorStateList itemIconTintList) {
        this.mPresenter.setItemIconTintList(itemIconTintList);
    }
    
    public void setItemTextAppearance(final int itemTextAppearance) {
        this.mPresenter.setItemTextAppearance(itemTextAppearance);
    }
    
    public void setItemTextColor(final ColorStateList itemTextColor) {
        this.mPresenter.setItemTextColor(itemTextColor);
    }
    
    public void setNavigationItemSelectedListener(final NavigationView$OnNavigationItemSelectedListener mListener) {
        this.mListener = mListener;
    }
}
