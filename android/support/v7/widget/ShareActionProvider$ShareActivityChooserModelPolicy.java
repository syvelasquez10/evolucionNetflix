// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.content.pm.ResolveInfo;
import android.content.pm.PackageManager;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.SubMenu;
import android.support.v7.appcompat.R$string;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.appcompat.R$attr;
import android.util.TypedValue;
import android.support.v7.internal.widget.ActivityChooserView;
import android.view.View;
import android.content.Context;
import android.support.v4.view.ActionProvider;
import android.content.Intent;
import android.support.v7.internal.widget.ActivityChooserModel;
import android.support.v7.internal.widget.ActivityChooserModel$OnChooseActivityListener;

class ShareActionProvider$ShareActivityChooserModelPolicy implements ActivityChooserModel$OnChooseActivityListener
{
    final /* synthetic */ ShareActionProvider this$0;
    
    private ShareActionProvider$ShareActivityChooserModelPolicy(final ShareActionProvider this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public boolean onChooseActivity(final ActivityChooserModel activityChooserModel, final Intent intent) {
        if (this.this$0.mOnShareTargetSelectedListener != null) {
            this.this$0.mOnShareTargetSelectedListener.onShareTargetSelected(this.this$0, intent);
        }
        return false;
    }
}
