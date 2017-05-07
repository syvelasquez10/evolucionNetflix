// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.partner.reference;

import com.netflix.mediaclient.partner.PartnerCommunicationHandler;
import android.content.Intent;
import com.netflix.mediaclient.partner.PartnerRequest;
import com.netflix.mediaclient.partner.PartnerRequestType;
import com.netflix.mediaclient.partner.Partner;
import com.netflix.mediaclient.partner.Partner$SSO;
import android.content.ComponentName;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.partner.Response;
import com.netflix.mediaclient.partner.ResponseListener;

class NetflixSSO$1 implements ResponseListener
{
    final /* synthetic */ NetflixSSO this$0;
    
    NetflixSSO$1(final NetflixSSO this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onResponseReceived(final Response response) {
        final ComponentName responder = response.getResponder();
        if (responder == null) {
            Log.e("nf_partner", "getExternalSsoService did not set component name!");
            return;
        }
        if (Log.isLoggable()) {
            Log.d("nf_partner", "getExternalSsoService sets component name " + responder);
        }
        this.this$0.owner.setComponentName(responder);
    }
}
