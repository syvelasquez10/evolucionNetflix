// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.view.ActionProvider;
import android.support.v7.internal.transition.ActionBarTransition;
import android.support.v7.internal.view.menu.ad;
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
import android.support.v7.internal.view.menu.aa;
import android.view.ViewGroup;
import android.view.MenuItem;
import android.support.v7.internal.view.menu.z;
import android.support.v7.appcompat.R$layout;
import android.graphics.drawable.Drawable;
import android.util.SparseBooleanArray;
import android.support.v4.view.ActionProvider$SubUiVisibilityListener;
import android.support.v7.internal.view.menu.d;
import android.support.v7.internal.view.menu.y;
import android.support.v7.appcompat.R$attr;
import android.view.View;
import android.support.v7.internal.view.menu.i;
import android.content.Context;
import android.support.v7.internal.view.menu.v;

class ActionMenuPresenter$OverflowPopup extends v
{
    final /* synthetic */ ActionMenuPresenter this$0;
    
    public ActionMenuPresenter$OverflowPopup(final ActionMenuPresenter this$0, final Context context, final i i, final View view, final boolean b) {
        this.this$0 = this$0;
        super(context, i, view, b, R$attr.actionOverflowMenuStyle);
        this.setGravity(8388613);
        this.setCallback(this$0.mPopupPresenterCallback);
    }
    
    @Override
    public void onDismiss() {
        super.onDismiss();
        this.this$0.mMenu.close();
        this.this$0.mOverflowPopup = null;
    }
}
