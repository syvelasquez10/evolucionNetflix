// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.content.res.Resources$Theme;
import android.view.ContextThemeWrapper;
import android.support.v7.appcompat.R$style;
import android.support.v7.appcompat.R$attr;
import android.util.TypedValue;
import android.support.v7.internal.view.menu.x;
import android.support.v7.appcompat.R$layout;
import android.support.v7.internal.view.menu.z;
import android.support.v7.internal.view.menu.y;
import android.view.View;
import android.support.v7.internal.view.menu.i;
import android.content.Context;
import android.support.v7.internal.view.menu.g;
import android.os.Bundle;
import android.view.ViewGroup;

final class ActionBarActivityDelegateBase$PanelFeatureState
{
    ViewGroup decorView;
    int featureId;
    Bundle frozenActionViewState;
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
    
    ActionBarActivityDelegateBase$PanelFeatureState(final int featureId) {
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
        return this.shownPanelView != null && this.listMenuPresenter.a().getCount() > 0;
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
    }
}
