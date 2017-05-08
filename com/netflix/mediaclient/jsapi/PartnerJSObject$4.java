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

class PartnerJSObject$4 implements Runnable
{
    final /* synthetic */ PartnerJSObject this$0;
    final /* synthetic */ int val$idx;
    final /* synthetic */ String val$service;
    final /* synthetic */ String val$userId;
    
    PartnerJSObject$4(final PartnerJSObject this$0, final String val$service, final int val$idx, final String val$userId) {
        this.this$0 = this$0;
        this.val$service = val$service;
        this.val$idx = val$idx;
        this.val$userId = val$userId;
    }
    
    @Override
    public void run() {
        Log.d("nf_partner", "Find partner");
        final Partner partner = this.this$0.partnerFactory.getPartner(this.this$0.context, this.val$service, this.this$0.comHandler);
        while (true) {
            if (partner == null) {
                try {
                    Log.e("nf_partner", "Service not found!");
                    this.this$0.returnResultToJS("nrdpPartner.Signup._handleExternalUserConfirmation", getErrorForPartner(null, this.val$idx, this.val$service, "101", "Service not found!"));
                    return;
                    while (true) {
                        Log.e("nf_partner", "Service does not support Signup!");
                        this.this$0.returnResultToJS("nrdpPartner.Signup._handleExternalUserConfirmation", getErrorForPartner(this.val$userId, this.val$idx, this.val$service, "102", "Service does not support Signup!"));
                        return;
                        continue;
                    }
                }
                // iftrue(Label_0132:, partner.getSignup() != null)
                catch (Exception ex) {
                    Log.e("nf_partner", "Failed to work with JSON", ex);
                    return;
                }
                Label_0132: {
                    partner.getSignup().requestExternalUserConfirmation(this.val$service, this.val$userId, this.val$idx, new PartnerJSObject$4$1(this));
                }
                return;
            }
            continue;
        }
    }
}
