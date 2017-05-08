// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.voip;

import android.provider.Settings$System;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
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
import android.content.Intent;
import android.os.Handler;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.IVoip;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$ReturnToDialScreenFrom;
import android.widget.ViewFlipper;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$EntryPoint;
import android.view.View;
import com.netflix.mediaclient.servicemgr.IVoip$OutboundCallListener;
import android.support.v4.app.ActivityCompat$OnRequestPermissionsResultCallback;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.Log;
import android.media.AudioManager;
import android.content.Context;
import android.database.ContentObserver;

class ContactUsActivity$AudioObserver extends ContentObserver
{
    Context context;
    int previousVolume;
    final /* synthetic */ ContactUsActivity this$0;
    
    public ContactUsActivity$AudioObserver(final ContactUsActivity this$0, final Context context) {
        this.this$0 = this$0;
        super(this$0.handler);
        this.context = context;
        this.previousVolume = ((AudioManager)this.context.getSystemService("audio")).getStreamVolume(3);
    }
    
    public boolean deliverSelfNotifications() {
        return super.deliverSelfNotifications();
    }
    
    public void onChange(final boolean b) {
        super.onChange(b);
        final AudioManager audioManager = (AudioManager)this.context.getSystemService("audio");
        final int streamVolume = audioManager.getStreamVolume(0);
        final int streamMaxVolume = audioManager.getStreamMaxVolume(0);
        final float outputVolume = streamVolume / streamMaxVolume;
        final int n = this.previousVolume - streamVolume;
        if (n > 0) {
            Log.d("VoipActivity", "Decreased");
            this.previousVolume = streamVolume;
        }
        else if (n < 0) {
            Log.d("VoipActivity", "Increased");
            this.previousVolume = streamVolume;
        }
        if (Log.isLoggable()) {
            Log.d("VoipActivity", "Max volume " + streamMaxVolume);
            Log.d("VoipActivity", "New volume " + streamVolume);
            Log.d("VoipActivity", "New volume as new/max: " + outputVolume);
        }
        if (this.this$0.mVoip != null) {
            this.this$0.mVoip.setOutputVolume(outputVolume);
        }
    }
}
