// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.nccp;

import com.netflix.mediaclient.nccp.response.EmptyReponse;
import org.apache.http.util.EntityUtils;
import com.netflix.mediaclient.nccp.response.NccpTransactionHttpError;
import com.netflix.mediaclient.Log;
import org.apache.http.HttpResponse;
import android.util.Base64;
import com.netflix.mediaclient.util.AndroidManifestUtils;
import com.netflix.mediaclient.repository.SecurityRepository;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import android.content.Context;

public abstract class BaseNccpTransaction implements NccpTransaction
{
    protected static final String ENDPOINT = "https://android.nccp.netflix.com";
    protected static final String NS = "nccp";
    protected static final String TAG = "nf_nccp";
    protected static final String nccp_bcp47 = "nccp:bcp47";
    protected static final String nccp_certificationversion = "nccp:certificationversion";
    protected static final String nccp_header = "nccp:header";
    protected static final String nccp_index = "nccp:index";
    protected static final String nccp_language = "nccp:language";
    protected static final String nccp_payload = "nccp:payload";
    protected static final String nccp_platformselectedlanguages = "nccp:platformselectedlanguages";
    protected static final String nccp_preferredlanguages = "nccp:preferredlanguages";
    protected static final String nccp_request = "nccp:request";
    protected static final String nccp_sdkversion = "nccp:sdkversion";
    protected static final String nccp_softwareversion = "nccp:softwareversion";
    protected static final String nccp_uiversion = "nccp:uiversion";
    protected Context mContext;
    protected EsnProvider mEsn;
    protected NccpResponseHandler responseHandler;
    protected String userAgent;
    
    public BaseNccpTransaction(final Context mContext, final EsnProvider mEsn, final NccpResponseHandler responseHandler) {
        this.mContext = mContext;
        this.mEsn = mEsn;
        this.responseHandler = responseHandler;
        this.userAgent = AndroidUtils.getUserAgent(mContext);
    }
    
    protected abstract StringBuilder createCustomRequest(final StringBuilder p0);
    
    protected StringBuilder createHeader(final StringBuilder sb) {
        StringBuilder sb2 = sb;
        if (sb == null) {
            sb2 = new StringBuilder();
        }
        sb2.append("<nccp:header>");
        sb2.append("<nccp:sdkversion>").append(SecurityRepository.getNrdLibVersion()).append("</nccp:sdkversion>");
        String version;
        if ((version = AndroidManifestUtils.getVersion(this.mContext)) == null) {
            version = "N/A";
        }
        sb2.append("<nccp:softwareversion>").append(version).append("</nccp:softwareversion>");
        sb2.append("<nccp:uiversion>").append(version).append("</nccp:uiversion>");
        sb2.append("<nccp:certificationversion>").append(0).append("</nccp:certificationversion>");
        this.createLanguages(sb2);
        this.createHeaderPayload(sb2);
        sb2.append("</nccp:header>");
        return sb2;
    }
    
    protected StringBuilder createHeaderPayload(final StringBuilder sb) {
        StringBuilder sb2 = sb;
        if (sb == null) {
            sb2 = new StringBuilder();
        }
        sb2.append("<").append("nccp:payload").append(" compressed=\"false\" encrypted=\"false\"").append(">");
        sb2.append(Base64.encodeToString(this.createHeaderPayloadValue().toString().getBytes(), 2));
        sb2.append("</").append("nccp:payload").append(">");
        return sb2;
    }
    
    protected StringBuilder createHeaderPayloadValue() {
        final StringBuilder sb = new StringBuilder();
        final long n = System.currentTimeMillis() / 1000L;
        sb.append("<nccp:clientservertimes>");
        sb.append("<nccp:clienttime>").append(n).append("</nccp:clienttime>");
        sb.append("<nccp:servertime>").append(n).append("</nccp:servertime>");
        sb.append("</nccp:clientservertimes>");
        return sb;
    }
    
    protected StringBuilder createLanguages(final StringBuilder sb) {
        StringBuilder sb2 = sb;
        if (sb == null) {
            sb2 = new StringBuilder();
        }
        sb2.append("<").append("nccp:preferredlanguages").append(">");
        sb2.append("<").append("nccp:platformselectedlanguages").append(">");
        sb2.append("<").append("nccp:language").append(">");
        sb2.append("<").append("nccp:index").append(">");
        sb2.append("0");
        sb2.append("</").append("nccp:index").append(">");
        sb2.append("<").append("nccp:bcp47").append(">");
        sb2.append("en-US");
        sb2.append("</").append("nccp:bcp47").append(">");
        sb2.append("</").append("nccp:language").append(">");
        sb2.append("</").append("nccp:platformselectedlanguages").append(">");
        sb2.append("</").append("nccp:preferredlanguages").append(">");
        return sb2;
    }
    
    protected StringBuilder createRequest(final StringBuilder sb) {
        StringBuilder sb2 = sb;
        if (sb == null) {
            sb2 = new StringBuilder();
        }
        sb2.append("<?xml version='1.0' encoding='UTF-8'?>\n");
        sb2.append("<nccp:request xmlns:nccp=\"http://www.netflix.com/eds/nccp/").append(this.getNccpVersion()).append("\">");
        this.createHeader(sb2);
        this.createCustomRequest(sb2);
        sb2.append("</nccp:request>");
        return sb2;
    }
    
    @Override
    public String getContentType() {
        return "text/xml";
    }
    
    @Override
    public String getEndpoint() {
        return "https://android.nccp.netflix.com/nccp/controller/" + this.getNccpVersion() + "/" + this.getName();
    }
    
    @Override
    public String getNccpVersion() {
        return "2.15";
    }
    
    @Override
    public String getRequestBody() {
        return this.createRequest(null).toString();
    }
    
    @Override
    public NccpResponseHandler getResponseHandler() {
        return this.responseHandler;
    }
    
    @Override
    public String getUserAgent() {
        return this.userAgent;
    }
    
    @Override
    public NccpResponse processResponse(final HttpResponse httpResponse) {
        final int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            if (Log.isLoggable()) {
                Log.d("nf_nccp", "Server returned HTTP error code " + statusCode);
            }
            return new NccpTransactionHttpError(statusCode, this.getName());
        }
        Log.d("nf_nccp", "Response status OK");
        final byte[] byteArray = EntityUtils.toByteArray(httpResponse.getEntity());
        if (byteArray == null) {
            Log.d("nf_nccp", "Response body is null");
            return new EmptyReponse(this.getName());
        }
        final String s = new String(byteArray);
        if (Log.isLoggable()) {
            Log.d("nf_nccp", "Response body is " + s);
        }
        return this.processResponseBody(s);
    }
    
    public abstract NccpResponse processResponseBody(final String p0);
}
