// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.jsapi;

import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.util.StringUtils;
import org.json.JSONObject;
import android.webkit.WebView;
import android.content.Context;
import com.netflix.mediaclient.partner.PartnerFactory;
import com.netflix.mediaclient.partner.PartnerCommunicationHandler;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.partner.Response;
import com.netflix.mediaclient.partner.ResponseListener;

class PartnerJSObject$1$1 implements ResponseListener
{
    final /* synthetic */ PartnerJSObject$1 this$1;
    
    PartnerJSObject$1$1(final PartnerJSObject$1 this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onResponseReceived(final Response response) {
        try {
            this.this$1.this$0.returnResultToJS("nrdpPartner.Sso._handleExternalUserToken", response.toJson());
        }
        catch (Exception ex) {
            Log.e("nf_partner", "Failed to get JSON from response", ex);
        }
    }
}
