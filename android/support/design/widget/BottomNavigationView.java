// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.Menu;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.appcompat.R$attr;
import android.support.v7.content.res.AppCompatResources;
import android.util.TypedValue;
import android.content.res.ColorStateList;
import android.support.design.R$dimen;
import android.support.v4.content.ContextCompat;
import android.support.design.R$color;
import android.support.v7.view.menu.MenuBuilder$Callback;
import android.os.Build$VERSION;
import android.view.View;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.TintTypedArray;
import android.support.design.R$style;
import android.support.design.R$styleable;
import android.support.v7.view.menu.MenuPresenter;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.support.design.internal.BottomNavigationMenu;
import android.util.AttributeSet;
import android.content.Context;
import android.support.design.internal.BottomNavigationPresenter;
import android.support.design.internal.BottomNavigationMenuView;
import android.view.MenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.widget.FrameLayout;

public class BottomNavigationView extends FrameLayout
{
    private static final int[] CHECKED_STATE_SET;
    private static final int[] DISABLED_STATE_SET;
    private BottomNavigationView$OnNavigationItemSelectedListener mListener;
    private final MenuBuilder mMenu;
    private MenuInflater mMenuInflater;
    private final BottomNavigationMenuView mMenuView;
    private final BottomNavigationPresenter mPresenter;
    
    static {
        CHECKED_STATE_SET = new int[] { 16842912 };
        DISABLED_STATE_SET = new int[] { -16842910 };
    }
    
    public BottomNavigationView(final Context context) {
        this(context, null);
    }
    
    public BottomNavigationView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public BottomNavigationView(final Context context, final AttributeSet set, int resourceId) {
        super(context, set, resourceId);
        this.mPresenter = new BottomNavigationPresenter();
        ThemeUtils.checkAppCompatTheme(context);
        this.mMenu = new BottomNavigationMenu(context);
        this.mMenuView = new BottomNavigationMenuView(context);
        final FrameLayout$LayoutParams layoutParams = new FrameLayout$LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.mMenuView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.mPresenter.setBottomNavigationMenuView(this.mMenuView);
        this.mMenuView.setPresenter(this.mPresenter);
        this.mMenu.addMenuPresenter(this.mPresenter);
        this.mPresenter.initForMenu(this.getContext(), this.mMenu);
        final TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, set, R$styleable.BottomNavigationView, resourceId, R$style.Widget_Design_BottomNavigationView);
        if (obtainStyledAttributes.hasValue(R$styleable.BottomNavigationView_itemIconTint)) {
            this.mMenuView.setIconTintList(obtainStyledAttributes.getColorStateList(R$styleable.BottomNavigationView_itemIconTint));
        }
        else {
            this.mMenuView.setIconTintList(this.createDefaultColorStateList(16842808));
        }
        if (obtainStyledAttributes.hasValue(R$styleable.BottomNavigationView_itemTextColor)) {
            this.mMenuView.setItemTextColor(obtainStyledAttributes.getColorStateList(R$styleable.BottomNavigationView_itemTextColor));
        }
        else {
            this.mMenuView.setItemTextColor(this.createDefaultColorStateList(16842808));
        }
        if (obtainStyledAttributes.hasValue(R$styleable.BottomNavigationView_elevation)) {
            ViewCompat.setElevation((View)this, obtainStyledAttributes.getDimensionPixelSize(R$styleable.BottomNavigationView_elevation, 0));
        }
        resourceId = obtainStyledAttributes.getResourceId(R$styleable.BottomNavigationView_itemBackground, 0);
        this.mMenuView.setItemBackgroundRes(resourceId);
        if (obtainStyledAttributes.hasValue(R$styleable.BottomNavigationView_menu)) {
            this.inflateMenu(obtainStyledAttributes.getResourceId(R$styleable.BottomNavigationView_menu, 0));
        }
        obtainStyledAttributes.recycle();
        this.addView((View)this.mMenuView, (ViewGroup$LayoutParams)layoutParams);
        if (Build$VERSION.SDK_INT < 21) {
            this.addCompatibilityTopDivider(context);
        }
        this.mMenu.setCallback(new BottomNavigationView$1(this));
    }
    
    private void addCompatibilityTopDivider(final Context context) {
        final View view = new View(context);
        view.setBackgroundColor(ContextCompat.getColor(context, R$color.design_bottom_navigation_shadow_color));
        view.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, this.getResources().getDimensionPixelSize(R$dimen.design_bottom_navigation_shadow_height)));
        this.addView(view);
    }
    
    private ColorStateList createDefaultColorStateList(int data) {
        final TypedValue typedValue = new TypedValue();
        if (this.getContext().getTheme().resolveAttribute(data, typedValue, true)) {
            final ColorStateList colorStateList = AppCompatResources.getColorStateList(this.getContext(), typedValue.resourceId);
            if (this.getContext().getTheme().resolveAttribute(R$attr.colorPrimary, typedValue, true)) {
                data = typedValue.data;
                final int defaultColor = colorStateList.getDefaultColor();
                return new ColorStateList(new int[][] { BottomNavigationView.DISABLED_STATE_SET, BottomNavigationView.CHECKED_STATE_SET, BottomNavigationView.EMPTY_STATE_SET }, new int[] { colorStateList.getColorForState(BottomNavigationView.DISABLED_STATE_SET, defaultColor), data, defaultColor });
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
    
    public int getItemBackgroundResource() {
        return this.mMenuView.getItemBackgroundRes();
    }
    
    public ColorStateList getItemIconTintList() {
        return this.mMenuView.getIconTintList();
    }
    
    public ColorStateList getItemTextColor() {
        return this.mMenuView.getItemTextColor();
    }
    
    public int getMaxItemCount() {
        return 5;
    }
    
    public Menu getMenu() {
        return (Menu)this.mMenu;
    }
    
    public void inflateMenu(final int n) {
        this.mPresenter.setUpdateSuspended(true);
        this.getMenuInflater().inflate(n, (Menu)this.mMenu);
        this.mPresenter.setUpdateSuspended(false);
        this.mPresenter.updateMenuView(true);
    }
    
    public void setItemBackgroundResource(final int itemBackgroundRes) {
        this.mMenuView.setItemBackgroundRes(itemBackgroundRes);
    }
    
    public void setItemIconTintList(final ColorStateList iconTintList) {
        this.mMenuView.setIconTintList(iconTintList);
    }
    
    public void setItemTextColor(final ColorStateList itemTextColor) {
        this.mMenuView.setItemTextColor(itemTextColor);
    }
    
    public void setOnNavigationItemSelectedListener(final BottomNavigationView$OnNavigationItemSelectedListener mListener) {
        this.mListener = mListener;
    }
}
