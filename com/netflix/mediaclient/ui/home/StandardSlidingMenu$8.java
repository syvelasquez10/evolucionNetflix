// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import android.app.Activity;
import com.netflix.mediaclient.ui.settings.AboutActivity;
import com.netflix.mediaclient.android.activity.NetflixActivity;

final class StandardSlidingMenu$8 implements Runnable
{
    final /* synthetic */ NetflixActivity val$context;
    
    StandardSlidingMenu$8(final NetflixActivity val$context) {
        this.val$context = val$context;
    }
    
    @Override
    public void run() {
        this.val$context.startActivity(AboutActivity.createStartIntent(this.val$context));
    }
}
