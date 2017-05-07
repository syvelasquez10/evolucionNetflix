// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.voip;

import android.content.Intent;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.ui.voip.ContactUsActivity;
import com.netflix.mediaclient.Log;
import android.content.Context;

final class VoipErrorDialogDescriptorFactory$1 implements Runnable
{
    final /* synthetic */ Context val$context;
    
    VoipErrorDialogDescriptorFactory$1(final Context val$context) {
        this.val$context = val$context;
    }
    
    @Override
    public void run() {
        Log.d("ErrorManager", "Start Contact us activity!");
        final Intent startIntentWithAutoDial = ContactUsActivity.createStartIntentWithAutoDial(this.val$context);
        startIntentWithAutoDial.putExtra("source", IClientLogging$ModalView.contactUs.name());
        this.val$context.startActivity(startIntentWithAutoDial);
    }
}
