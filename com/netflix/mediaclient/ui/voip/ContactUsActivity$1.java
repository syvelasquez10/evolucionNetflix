// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.voip;

import android.database.ContentObserver;
import android.provider.Settings$System;
import android.os.Bundle;
import android.view.View$OnClickListener;
import android.support.design.widget.Snackbar;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import com.netflix.mediaclient.service.logging.apm.model.Orientation;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.IVoip$Call;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.util.log.CustomerServiceLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$Action;
import com.netflix.mediaclient.util.PermissionUtils;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import com.netflix.mediaclient.servicemgr.IVoip;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$ReturnToDialScreenFrom;
import android.widget.ViewFlipper;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$EntryPoint;
import android.view.View;
import android.support.v4.app.ActivityCompat$OnRequestPermissionsResultCallback;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.IVoip$OutboundCallListener;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;

class ContactUsActivity$1 implements ManagerStatusListener
{
    final /* synthetic */ ContactUsActivity this$0;
    
    ContactUsActivity$1(final ContactUsActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        Log.d("VoipActivity", "Manager is here!");
        this.this$0.mServiceManager = serviceManager;
        this.this$0.mVoip = this.this$0.mServiceManager.getVoip();
        this.this$0.initUI();
        if (this.this$0.mVoip != null) {
            this.this$0.mVoip.addOutboundCallListener(this.this$0);
        }
        this.this$0.reportEvent();
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
        Log.e("VoipActivity", "Manager isn't available!");
        this.this$0.mServiceManager = null;
    }
}
