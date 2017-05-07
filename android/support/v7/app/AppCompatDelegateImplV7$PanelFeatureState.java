// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.content.res.TypedArray;
import android.content.res.Resources$Theme;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.internal.view.ContextThemeWrapper;
import android.support.v7.appcompat.R$style;
import android.support.v7.appcompat.R$attr;
import android.util.TypedValue;
import android.support.v7.internal.view.menu.x;
import android.support.v7.appcompat.R$layout;
import android.support.v7.internal.view.menu.z;
import android.support.v7.internal.view.menu.y;
import android.support.v7.internal.view.menu.i;
import android.content.Context;
import android.support.v7.internal.view.menu.g;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.View;

final class AppCompatDelegateImplV7$PanelFeatureState
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
    g listMenuPresenter;
    Context listPresenterContext;
    i menu;
    public boolean qwertyMode;
    boolean refreshDecorView;
    boolean refreshMenuContent;
    View shownPanelView;
    int windowAnimations;
    int x;
    int y;
    
    AppCompatDelegateImplV7$PanelFeatureState(final int featureId) {
        this.featureId = featureId;
        this.refreshDecorView = false;
    }
    
    z getListMenuView(final y y) {
        if (this.menu == null) {
            return null;
        }
        if (this.listMenuPresenter == null) {
            (this.listMenuPresenter = new g(this.listPresenterContext, R$layout.abc_list_menu_item_layout)).a(y);
            this.menu.a(this.listMenuPresenter);
        }
        return this.listMenuPresenter.a(this.decorView);
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
                if (this.listMenuPresenter.a().getCount() <= 0) {
                    return false;
                }
            }
        }
        return b2;
    }
    
    void setMenu(final i menu) {
        if (menu != this.menu) {
            if (this.menu != null) {
                this.menu.b(this.listMenuPresenter);
            }
            this.menu = menu;
            if (menu != null && this.listMenuPresenter != null) {
                menu.a(this.listMenuPresenter);
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
        final TypedArray obtainStyledAttributes = ((Context)listPresenterContext).obtainStyledAttributes(R$styleable.Theme);
        this.background = obtainStyledAttributes.getResourceId(R$styleable.Theme_panelBackground, 0);
        this.windowAnimations = obtainStyledAttributes.getResourceId(R$styleable.Theme_android_windowAnimationStyle, 0);
        obtainStyledAttributes.recycle();
    }
}
