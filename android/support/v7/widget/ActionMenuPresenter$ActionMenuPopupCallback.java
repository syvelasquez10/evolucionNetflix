// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.view.ActionProvider;
import android.support.v7.internal.transition.ActionBarTransition;
import android.support.v7.internal.view.menu.ae;
import android.os.Parcelable;
import android.support.v7.appcompat.R$integer;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.internal.view.ActionBarPolicy;
import android.view.ViewGroup$LayoutParams;
import java.util.ArrayList;
import android.view.View$MeasureSpec;
import android.support.v7.internal.view.menu.k;
import android.support.v7.internal.view.menu.ActionMenuItemView;
import android.support.v7.internal.view.menu.m;
import android.support.v7.internal.view.menu.ab;
import android.view.ViewGroup;
import android.view.MenuItem;
import android.support.v7.internal.view.menu.aa;
import android.support.v7.internal.view.menu.i;
import android.support.v7.appcompat.R$layout;
import android.content.Context;
import android.view.View;
import android.util.SparseBooleanArray;
import android.support.v4.view.ActionProvider$SubUiVisibilityListener;
import android.support.v7.internal.view.menu.d;
import android.support.v7.internal.view.menu.c;

class ActionMenuPresenter$ActionMenuPopupCallback extends c
{
    final /* synthetic */ ActionMenuPresenter this$0;
    
    private ActionMenuPresenter$ActionMenuPopupCallback(final ActionMenuPresenter this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public ListPopupWindow getPopup() {
        if (this.this$0.mActionButtonPopup != null) {
            return this.this$0.mActionButtonPopup.getPopup();
        }
        return null;
    }
}
