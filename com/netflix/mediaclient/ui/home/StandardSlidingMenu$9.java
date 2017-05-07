// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import android.content.Context;
import com.netflix.mediaclient.ui.voip.ContactUsActivity;
import com.netflix.mediaclient.android.activity.NetflixActivity;

final class StandardSlidingMenu$9 implements Runnable
{
    final /* synthetic */ NetflixActivity val$context;
    
    StandardSlidingMenu$9(final NetflixActivity val$context) {
        this.val$context = val$context;
    }
    
    @Override
    public void run() {
        this.val$context.startActivity(ContactUsActivity.createStartIntent((Context)this.val$context));
    }
}
