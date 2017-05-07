// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.voip;

import android.database.ContentObserver;
import android.provider.Settings$System;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.support.design.widget.Snackbar;
import com.netflix.mediaclient.service.logging.apm.model.Orientation;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.IVoip$Call;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.util.log.CustomerServiceLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$Action;
import com.netflix.mediaclient.util.PermissionUtils;
import android.content.Context;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.os.Handler;
import com.netflix.mediaclient.servicemgr.IVoip;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$ReturnToDialScreenFrom;
import android.widget.ViewFlipper;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$EntryPoint;
import com.netflix.mediaclient.servicemgr.IVoip$OutboundCallListener;
import android.support.v4.app.ActivityCompat$OnRequestPermissionsResultCallback;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.View$OnClickListener;

class ContactUsActivity$2 implements View$OnClickListener
{
    final /* synthetic */ ContactUsActivity this$0;
    
    ContactUsActivity$2(final ContactUsActivity this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        ActivityCompat.requestPermissions(this.this$0, ContactUsActivity.PERMISSIONS_AUDIO, 0);
    }
}
