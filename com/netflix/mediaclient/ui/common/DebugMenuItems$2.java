// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.netflix.mediaclient.service.NetflixService;
import android.content.Intent;
import android.view.MenuItem;
import android.content.Context;
import android.view.MenuItem$OnMenuItemClickListener;

class DebugMenuItems$2 implements MenuItem$OnMenuItemClickListener
{
    final /* synthetic */ DebugMenuItems this$0;
    final /* synthetic */ Context val$context;
    
    DebugMenuItems$2(final DebugMenuItems this$0, final Context val$context) {
        this.this$0 = this$0;
        this.val$context = val$context;
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.USER_CREATE_AUTOLOGIN_TOKEN");
        intent.setClass(this.val$context, (Class)NetflixService.class);
        intent.addCategory("com.netflix.mediaclient.intent.category.USER");
        this.val$context.startService(intent);
        return true;
    }
}
