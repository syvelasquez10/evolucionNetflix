// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social.notifications;

import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.android.widget.NetflixActionBar$LogoType;
import android.os.Bundle;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.app.Fragment;
import android.content.Intent;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;
import com.netflix.mediaclient.util.NflxProtocolUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;

class SocialNotificationsActivity$1 implements ManagerStatusListener
{
    final /* synthetic */ SocialNotificationsActivity this$0;
    
    SocialNotificationsActivity$1(final SocialNotificationsActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        Log.d(SocialNotificationsActivity.TAG, "Manager is here!");
        ((ManagerStatusListener)this.this$0.getPrimaryFrag()).onManagerReady(serviceManager, status);
        this.this$0.mManagerIsReady = true;
        NflxProtocolUtils.reportUserOpenedNotification(serviceManager, this.this$0.getIntent());
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
        Log.d(SocialNotificationsActivity.TAG, "Manager isn't available!");
        ((ManagerStatusListener)this.this$0.getPrimaryFrag()).onManagerUnavailable(serviceManager, status);
    }
}
