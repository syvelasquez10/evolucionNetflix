// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.voip;

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
        this.val$context.startActivity(ContactUsActivity.createStartIntentWithAutoDial(this.val$context));
    }
}
