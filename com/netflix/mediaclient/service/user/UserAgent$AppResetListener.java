// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.service.NetflixService;
import android.content.Intent;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.event.UIEvent;
import com.netflix.mediaclient.javabridge.ui.EventListener;

class UserAgent$AppResetListener implements EventListener
{
    final /* synthetic */ UserAgent this$0;
    
    private UserAgent$AppResetListener(final UserAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void received(final UIEvent uiEvent) {
        Log.d("nf_service_useragent", "Received an App reset event ");
        AndroidUtils.clearApplicationData(this.this$0.getContext());
        NetflixActivity.finishAllActivities(this.this$0.getContext());
        final Intent intent = new Intent();
        intent.setClass(this.this$0.getContext(), (Class)NetflixService.class);
        this.this$0.getContext().stopService(intent);
    }
}
