// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.content.Context;
import android.content.Intent;
import com.netflix.mediaclient.ui.iris.notifications.NotificationsActivity;
import android.view.MenuItem;
import android.app.Activity;
import android.view.MenuItem$OnMenuItemClickListener;

class DebugMenuItems$1 implements MenuItem$OnMenuItemClickListener
{
    final /* synthetic */ DebugMenuItems this$0;
    final /* synthetic */ Activity val$activity;
    
    DebugMenuItems$1(final DebugMenuItems this$0, final Activity val$activity) {
        this.this$0 = this$0;
        this.val$activity = val$activity;
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        this.val$activity.startActivity(new Intent((Context)this.val$activity, (Class)NotificationsActivity.class));
        return true;
    }
}
