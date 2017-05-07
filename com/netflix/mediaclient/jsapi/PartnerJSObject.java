// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.jsapi;

import com.netflix.mediaclient.partner.Partner;
import com.netflix.mediaclient.partner.Response;
import com.netflix.mediaclient.partner.ResponseListener;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import org.json.JSONException;
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
    
    private static JSONObject getErrorForPartner(final String s, final int n, final String s2, final String s3, final String s4) throws JSONException {
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
        if (Log.isLoggable("nf_partner", 3)) {
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
        if (Log.isLoggable("nf_partner", 3)) {
            Log.d("nf_partner", "nrdpPartner.Signup.getExternalUserData: service " + s + ", idx " + n + ", userid " + s2);
        }
        new BackgroundTask().execute(new Runnable() {
            @Override
            public void run() {
                Log.d("nf_partner", "Find partner");
                final Partner partner = PartnerJSObject.this.partnerFactory.getPartner(PartnerJSObject.this.context, s, PartnerJSObject.this.comHandler);
                while (true) {
                    if (partner == null) {
                        try {
                            Log.e("nf_partner", "Service not found!");
                            PartnerJSObject.this.returnResultToJS("nrdpPartner.Signup._handleExternalUserData", getErrorForPartner(null, n, s, "101", "Service not found!"));
                            return;
                            // iftrue(Label_0132:, partner.getSignup() != null)
                            Log.e("nf_partner", "Service does not support SSO!");
                            PartnerJSObject.this.returnResultToJS("nrdpPartner.Signup._handleExternalUserData", getErrorForPartner(s2, n, s, "102", "Service does not support Signup!"));
                            return;
                        }
                        catch (Exception ex) {
                            Log.e("nf_partner", "Failed to work with JSON", ex);
                            return;
                        }
                        Label_0132: {
                            partner.getSignup().getExternalUserData(s, s2, n, new ResponseListener() {
                                @Override
                                public void onResponseReceived(final Response response) {
                                    try {
                                        PartnerJSObject.this.returnResultToJS("nrdpPartner.Signup._handleExternalUserData", response.toJson());
                                    }
                                    catch (Exception ex) {
                                        Log.e("nf_partner", "Failed to get JSON from response", ex);
                                    }
                                }
                            });
                        }
                        return;
                    }
                    continue;
                }
            }
        });
    }
    
    public void getExternalUserToken(final String s, final int n) {
        if (Log.isLoggable("nf_partner", 3)) {
            Log.d("nf_partner", "nrdpPartner.Sso.getExternalUserToken:: service " + s + ", idx " + n);
        }
        new BackgroundTask().execute(new Runnable() {
            @Override
            public void run() {
                Log.d("nf_partner", "Find partner");
                final Partner partner = PartnerJSObject.this.partnerFactory.getPartner(PartnerJSObject.this.context, s, PartnerJSObject.this.comHandler);
                while (true) {
                    if (partner == null) {
                        try {
                            Log.e("nf_partner", "Service not found!");
                            PartnerJSObject.this.returnResultToJS("nrdpPartner.Sso._handleExternalUserToken", getErrorForPartner(null, n, s, "101", "Service not found!"));
                            return;
                            // iftrue(Label_0129:, partner.getSSO() != null)
                            Log.e("nf_partner", "Service does not support SSO!");
                            PartnerJSObject.this.returnResultToJS("nrdpPartner.Sso._handleExternalUserToken", getErrorForPartner(null, n, s, "102", "Service does not support SSO!"));
                            return;
                        }
                        catch (Exception ex) {
                            Log.e("nf_partner", "Failed to work with JSON", ex);
                            return;
                        }
                        Label_0129: {
                            partner.getSSO().getExternalUserToken(s, n, new ResponseListener() {
                                @Override
                                public void onResponseReceived(final Response response) {
                                    try {
                                        PartnerJSObject.this.returnResultToJS("nrdpPartner.Sso._handleExternalUserToken", response.toJson());
                                    }
                                    catch (Exception ex) {
                                        Log.e("nf_partner", "Failed to get JSON from response", ex);
                                    }
                                }
                            });
                        }
                        return;
                    }
                    continue;
                }
            }
        });
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
        if (Log.isLoggable("nf_partner", 3)) {
            Log.d("nf_partner", "nrdpPartner.Sso.requestExternalUserAuth:: service " + s + ", idx " + n);
        }
        new BackgroundTask().execute(new Runnable() {
            @Override
            public void run() {
                Log.d("nf_partner", "Find partner");
                final Partner partner = PartnerJSObject.this.partnerFactory.getPartner(PartnerJSObject.this.context, s, PartnerJSObject.this.comHandler);
                while (true) {
                    if (partner == null) {
                        try {
                            Log.e("nf_partner", "Service not found!");
                            PartnerJSObject.this.returnResultToJS("nrdpPartner.Sso._handleExternalUserAuth", getErrorForPartner(null, n, s, "101", "Service not found!"));
                            return;
                            // iftrue(Label_0129:, partner.getSSO() != null)
                            Log.e("nf_partner", "Service does not support SSO!");
                            PartnerJSObject.this.returnResultToJS("nrdpPartner.Sso._handleExternalUserAuth", getErrorForPartner(null, n, s, "102", "Service does not support SSO!"));
                            return;
                        }
                        catch (Exception ex) {
                            Log.e("nf_partner", "Failed to work with JSON", ex);
                            return;
                        }
                        Label_0129: {
                            partner.getSSO().requestExternalUserAuth(s, n, new ResponseListener() {
                                @Override
                                public void onResponseReceived(final Response response) {
                                    try {
                                        PartnerJSObject.this.returnResultToJS("nrdpPartner.Sso._handleExternalUserAuth", response.toJson());
                                    }
                                    catch (Exception ex) {
                                        Log.e("nf_partner", "Failed to get JSON from response", ex);
                                    }
                                }
                            });
                        }
                        return;
                    }
                    continue;
                }
            }
        });
    }
    
    public void requestExternalUserConfirmation(final String s, final String s2, final int n) {
        if (Log.isLoggable("nf_partner", 3)) {
            Log.d("nf_partner", "nrdpPartner.Signup.requestExternalUserConfirmation: service " + s + ", idx " + n + ", userid " + s2);
        }
        new BackgroundTask().execute(new Runnable() {
            @Override
            public void run() {
                Log.d("nf_partner", "Find partner");
                final Partner partner = PartnerJSObject.this.partnerFactory.getPartner(PartnerJSObject.this.context, s, PartnerJSObject.this.comHandler);
                while (true) {
                    if (partner == null) {
                        try {
                            Log.e("nf_partner", "Service not found!");
                            PartnerJSObject.this.returnResultToJS("nrdpPartner.Signup._handleExternalUserConfirmation", getErrorForPartner(null, n, s, "101", "Service not found!"));
                            return;
                            // iftrue(Label_0132:, partner.getSignup() != null)
                            Log.e("nf_partner", "Service does not support Signup!");
                            PartnerJSObject.this.returnResultToJS("nrdpPartner.Signup._handleExternalUserConfirmation", getErrorForPartner(s2, n, s, "102", "Service does not support Signup!"));
                            return;
                        }
                        catch (Exception ex) {
                            Log.e("nf_partner", "Failed to work with JSON", ex);
                            return;
                        }
                        Label_0132: {
                            partner.getSignup().requestExternalUserConfirmation(s, s2, n, new ResponseListener() {
                                @Override
                                public void onResponseReceived(final Response response) {
                                    try {
                                        PartnerJSObject.this.returnResultToJS("nrdpPartner.Signup._handleExternalUserConfirmation", response.toJson());
                                    }
                                    catch (Exception ex) {
                                        Log.e("nf_partner", "Failed to get JSON from response", ex);
                                    }
                                }
                            });
                        }
                        return;
                    }
                    continue;
                }
            }
        });
    }
}
