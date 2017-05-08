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
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$TwoButtonAlertDialogDescriptor;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import com.netflix.mediaclient.servicemgr.IVoip;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$ReturnToDialScreenFrom;
import android.widget.ViewFlipper;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$EntryPoint;
import android.view.View;
import com.netflix.mediaclient.servicemgr.IVoip$OutboundCallListener;
import android.support.v4.app.ActivityCompat$OnRequestPermissionsResultCallback;
import com.netflix.mediaclient.android.activity.NetflixActivity;
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
        this.this$0.init(serviceManager, status);
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
        Log.e("VoipActivity", "Netflix service is not fully initialized, but we still need to provide help!");
        this.this$0.init(serviceManager, status);
    }
}
