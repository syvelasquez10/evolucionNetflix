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
import com.netflix.mediaclient.partner.Partner;
import com.netflix.mediaclient.partner.ResponseListener;
import com.netflix.mediaclient.Log;

class PartnerJSObject$1 implements Runnable
{
    final /* synthetic */ PartnerJSObject this$0;
    final /* synthetic */ int val$idx;
    final /* synthetic */ String val$service;
    
    PartnerJSObject$1(final PartnerJSObject this$0, final String val$service, final int val$idx) {
        this.this$0 = this$0;
        this.val$service = val$service;
        this.val$idx = val$idx;
    }
    
    @Override
    public void run() {
        Log.d("nf_partner", "Find partner");
        final Partner partner = this.this$0.partnerFactory.getPartner(this.this$0.context, this.val$service, this.this$0.comHandler);
        while (true) {
            if (partner == null) {
                try {
                    Log.e("nf_partner", "Service not found!");
                    this.this$0.returnResultToJS("nrdpPartner.Sso._handleExternalUserToken", getErrorForPartner(null, this.val$idx, this.val$service, "101", "Service not found!"));
                    return;
                    while (true) {
                        Log.e("nf_partner", "Service does not support SSO!");
                        this.this$0.returnResultToJS("nrdpPartner.Sso._handleExternalUserToken", getErrorForPartner(null, this.val$idx, this.val$service, "102", "Service does not support SSO!"));
                        return;
                        continue;
                    }
                }
                // iftrue(Label_0129:, partner.getSSO() != null)
                catch (Exception ex) {
                    Log.e("nf_partner", "Failed to work with JSON", ex);
                    return;
                }
                Label_0129: {
                    partner.getSSO().getExternalUserToken(this.val$service, this.val$idx, new PartnerJSObject$1$1(this));
                }
                return;
            }
            continue;
        }
    }
}
