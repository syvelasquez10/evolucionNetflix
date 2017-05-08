// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import android.content.Context;
import android.content.Intent;
import com.netflix.mediaclient.ui.iris.notifications.NotificationsActivity;
import android.view.View;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View$OnClickListener;

class StandardSlidingMenu$4 implements View$OnClickListener
{
    final /* synthetic */ StandardSlidingMenu this$0;
    final /* synthetic */ NetflixActivity val$activity;
    
    StandardSlidingMenu$4(final StandardSlidingMenu this$0, final NetflixActivity val$activity) {
        this.this$0 = this$0;
        this.val$activity = val$activity;
    }
    
    public void onClick(final View view) {
        this.val$activity.startActivity(new Intent((Context)this.val$activity, (Class)NotificationsActivity.class));
    }
}
