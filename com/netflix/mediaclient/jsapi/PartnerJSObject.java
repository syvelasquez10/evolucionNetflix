// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.jsapi;

import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import android.webkit.WebView;
import android.content.Context;
import com.netflix.mediaclient.partner.PartnerFactory;
import com.netflix.mediaclient.partner.PartnerCommunicationHandler;

public class PartnerJSObject extends JSObject
{
    public static final String INTERFACE_NAME = "n_partner_api";
    private static final String JSAPI_handleExternalUserAuth = "nrdpPartner.Sso._handleExternalUserAuth";
    private static final String JSAPI_handleExternalUserConfirmation = "nrdpPartner.Signup._handleExternalUserConfirmation";
    private static final String JSAPI_handleExternalUserData = "nrdpPartner.Signup._handleExternalUserData";
    private static final String JSAPI_handleExternalUserToken = "nrdpPartner.Sso._handleExternalUserToken";
    private static final String TAG = "nf_partner";
    private PartnerCommunicationHandler comHandler;
    private PartnerFactory partnerFactory;
    
    public PartnerJSObject(final Context context, final WebView webView, final PartnerCommunicationHandler comHandler) {
        super(context, webView);
        this.comHandler = comHandler;
        this.partnerFactory = new PartnerFactory();
    }
    
    private static JSONObject getErrorForPartner(final String s, final int n, final String s2, final String s3, final String s4) {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("idx", n);
        jsonObject.put("service", (Object)s2);
        jsonObject.put("status", 1);
        if (s != null) {
            jsonObject.put("userid", (Object)s);
        }
        jsonObject.put("errcode", (Object)s3);
        jsonObject.put("msg", (Object)s4);
        return jsonObject;
    }
    
    private void returnResultToJS(String javaScript, final JSONObject jsonObject) {
        javaScript = this.toJavaScript(javaScript, jsonObject);
        if (Log.isLoggable()) {
            Log.d("nf_partner", "Injecting event " + javaScript);
        }
        this.injectJavaScript(javaScript);
    }
    
    private String toJavaScript(final String s, final JSONObject jsonObject) {
        final StringBuilder sb = new StringBuilder();
        sb.append("javascript:(function() { ");
        sb.append(" console.log('Sending event...');");
        sb.append(" ").append(s).append("(");
        sb.append(jsonObject.toString());
        sb.append(");");
        sb.append(" console.log('Event sent.');");
        sb.append("})()");
        return sb.toString();
    }
    
    public String getExternalSignUpServices() {
        return StringUtils.joinArray(this.partnerFactory.getExternalSignUpServices(this.context));
    }
    
    public String getExternalSsoServices() {
        return StringUtils.joinArray(this.partnerFactory.getExternalSsoServices(this.context));
    }
    
    public void getExternalUserData(final String s, final String s2, final int n) {
        if (Log.isLoggable()) {
            Log.d("nf_partner", "nrdpPartner.Signup.getExternalUserData: service " + s + ", idx " + n + ", userid " + s2);
        }
        new BackgroundTask().execute(new PartnerJSObject$3(this, s, n, s2));
    }
    
    public void getExternalUserToken(final String s, final int n) {
        if (Log.isLoggable()) {
            Log.d("nf_partner", "nrdpPartner.Sso.getExternalUserToken:: service " + s + ", idx " + n);
        }
        new BackgroundTask().execute(new PartnerJSObject$1(this, s, n));
    }
    
    @Override
    public String getInterfaceName() {
        return "n_partner_api";
    }
    
    @Override
    public void release() {
        super.release();
        this.partnerFactory.releasePartners();
        this.partnerFactory = null;
        this.comHandler = null;
    }
    
    public void requestExternalUserAuth(final String s, final int n) {
        if (Log.isLoggable()) {
            Log.d("nf_partner", "nrdpPartner.Sso.requestExternalUserAuth:: service " + s + ", idx " + n);
        }
        new BackgroundTask().execute(new PartnerJSObject$2(this, s, n));
    }
    
    public void requestExternalUserConfirmation(final String s, final String s2, final int n) {
        if (Log.isLoggable()) {
            Log.d("nf_partner", "nrdpPartner.Signup.requestExternalUserConfirmation: service " + s + ", idx " + n + ", userid " + s2);
        }
        new BackgroundTask().execute(new PartnerJSObject$4(this, s, n, s2));
    }
}
