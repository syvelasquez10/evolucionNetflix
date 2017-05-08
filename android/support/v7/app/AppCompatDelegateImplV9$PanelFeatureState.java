// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.content.res.TypedArray;
import android.content.res.Resources$Theme;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.appcompat.R$style;
import android.support.v7.appcompat.R$attr;
import android.util.TypedValue;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.appcompat.R$layout;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.MenuPresenter$Callback;
import android.support.v7.view.menu.MenuBuilder;
import android.content.Context;
import android.support.v7.view.menu.ListMenuPresenter;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.View;

public final class AppCompatDelegateImplV9$PanelFeatureState
{
    int background;
    View createdPanelView;
    ViewGroup decorView;
    int featureId;
    Bundle frozenActionViewState;
    int gravity;
    boolean isHandled;
    boolean isOpen;
    boolean isPrepared;
    ListMenuPresenter listMenuPresenter;
    Context listPresenterContext;
    MenuBuilder menu;
    public boolean qwertyMode;
    boolean refreshDecorView;
    boolean refreshMenuContent;
    View shownPanelView;
    int windowAnimations;
    int x;
    int y;
    
    AppCompatDelegateImplV9$PanelFeatureState(final int featureId) {
        this.featureId = featureId;
        this.refreshDecorView = false;
    }
    
    MenuView getListMenuView(final MenuPresenter$Callback callback) {
        if (this.menu == null) {
            return null;
        }
        if (this.listMenuPresenter == null) {
            (this.listMenuPresenter = new ListMenuPresenter(this.listPresenterContext, R$layout.abc_list_menu_item_layout)).setCallback(callback);
            this.menu.addMenuPresenter(this.listMenuPresenter);
        }
        return this.listMenuPresenter.getMenuView(this.decorView);
    }
    
    public boolean hasPanelItems() {
        final boolean b = true;
        boolean b2;
        if (this.shownPanelView == null) {
            b2 = false;
        }
        else {
            b2 = b;
            if (this.createdPanelView == null) {
                b2 = b;
                if (this.listMenuPresenter.getAdapter().getCount() <= 0) {
                    return false;
                }
            }
        }
        return b2;
    }
    
    void setMenu(final MenuBuilder menu) {
        if (menu != this.menu) {
            if (this.menu != null) {
                this.menu.removeMenuPresenter(this.listMenuPresenter);
            }
            this.menu = menu;
            if (menu != null && this.listMenuPresenter != null) {
                menu.addMenuPresenter(this.listMenuPresenter);
            }
        }
    }
    
    void setStyle(final Context context) {
        final TypedValue typedValue = new TypedValue();
        final Resources$Theme theme = context.getResources().newTheme();
        theme.setTo(context.getTheme());
        theme.resolveAttribute(R$attr.actionBarPopupTheme, typedValue, true);
        if (typedValue.resourceId != 0) {
            theme.applyStyle(typedValue.resourceId, true);
        }
        theme.resolveAttribute(R$attr.panelMenuListTheme, typedValue, true);
        if (typedValue.resourceId != 0) {
            theme.applyStyle(typedValue.resourceId, true);
        }
        else {
            theme.applyStyle(R$style.Theme_AppCompat_CompactMenu, true);
        }
        final ContextThemeWrapper listPresenterContext = new ContextThemeWrapper(context, 0);
        ((Context)listPresenterContext).getTheme().setTo(theme);
        this.listPresenterContext = (Context)listPresenterContext;
        final TypedArray obtainStyledAttributes = ((Context)listPresenterContext).obtainStyledAttributes(R$styleable.AppCompatTheme);
        this.background = obtainStyledAttributes.getResourceId(R$styleable.AppCompatTheme_panelBackground, 0);
        this.windowAnimations = obtainStyledAttributes.getResourceId(R$styleable.AppCompatTheme_android_windowAnimationStyle, 0);
        obtainStyledAttributes.recycle();
    }
}
