// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import android.app.Activity;
import com.netflix.mediaclient.ui.settings.AboutActivity;
import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;

class NetflixActivity$3 implements MenuItem$OnMenuItemClickListener
{
    final /* synthetic */ NetflixActivity this$0;
    
    NetflixActivity$3(final NetflixActivity this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        this.this$0.startActivity(AboutActivity.createStartIntent(this.this$0));
        return true;
    }
}
