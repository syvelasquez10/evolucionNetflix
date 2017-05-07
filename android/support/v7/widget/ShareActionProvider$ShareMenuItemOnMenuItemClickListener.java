// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.content.pm.ResolveInfo;
import android.content.pm.PackageManager;
import android.view.SubMenu;
import android.support.v7.appcompat.R$string;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.appcompat.R$attr;
import android.util.TypedValue;
import android.support.v7.internal.widget.ActivityChooserView;
import android.view.View;
import android.support.v7.internal.widget.ActivityChooserModel$OnChooseActivityListener;
import android.content.Context;
import android.support.v4.view.ActionProvider;
import android.content.Intent;
import android.support.v7.internal.widget.ActivityChooserModel;
import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;

class ShareActionProvider$ShareMenuItemOnMenuItemClickListener implements MenuItem$OnMenuItemClickListener
{
    final /* synthetic */ ShareActionProvider this$0;
    
    private ShareActionProvider$ShareMenuItemOnMenuItemClickListener(final ShareActionProvider this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        final Intent chooseActivity = ActivityChooserModel.get(this.this$0.mContext, this.this$0.mShareHistoryFileName).chooseActivity(menuItem.getItemId());
        if (chooseActivity != null) {
            chooseActivity.addFlags(524288);
            this.this$0.mContext.startActivity(chooseActivity);
        }
        return true;
    }
}
