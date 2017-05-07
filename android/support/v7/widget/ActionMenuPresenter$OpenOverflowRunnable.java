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
import android.support.v7.internal.view.menu.c;
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
import android.util.SparseBooleanArray;
import android.support.v4.view.ActionProvider$SubUiVisibilityListener;
import android.support.v7.internal.view.menu.d;
import android.view.View;

class ActionMenuPresenter$OpenOverflowRunnable implements Runnable
{
    private ActionMenuPresenter$OverflowPopup mPopup;
    final /* synthetic */ ActionMenuPresenter this$0;
    
    public ActionMenuPresenter$OpenOverflowRunnable(final ActionMenuPresenter this$0, final ActionMenuPresenter$OverflowPopup mPopup) {
        this.this$0 = this$0;
        this.mPopup = mPopup;
    }
    
    @Override
    public void run() {
        this.this$0.mMenu.g();
        final View view = (View)this.this$0.mMenuView;
        if (view != null && view.getWindowToken() != null && this.mPopup.tryShow()) {
            this.this$0.mOverflowPopup = this.mPopup;
        }
        this.this$0.mPostedOpenRunnable = null;
    }
}
