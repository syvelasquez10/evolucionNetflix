// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import android.app.Activity;
import com.netflix.mediaclient.ui.login.LogoutActivity;
import com.netflix.mediaclient.android.activity.NetflixActivity;

final class StandardSlidingMenu$10 implements Runnable
{
    final /* synthetic */ NetflixActivity val$context;
    
    StandardSlidingMenu$10(final NetflixActivity val$context) {
        this.val$context = val$context;
    }
    
    @Override
    public void run() {
        LogoutActivity.showLogoutDialog(this.val$context);
    }
}
