// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.nccp.transaction;

import com.netflix.mediaclient.nccp.response.EmptyReponse;
import com.netflix.mediaclient.nccp.response.CdmProvisionNccpResponse;
import com.netflix.mediaclient.nccp.response.NccpTransactionApplicationError;
import com.netflix.mediaclient.nccp.NccpResponse;
import java.util.ArrayList;
import com.netflix.mediaclient.nccp.RequestParameter;
import java.util.List;
import com.netflix.mediaclient.Log;
import android.util.Base64;
import com.netflix.mediaclient.nccp.NccpResponseHandler;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import android.content.Context;
import com.netflix.mediaclient.nccp.BaseNccpTransaction;

public class CdmProvisionNccpTransaction extends BaseNccpTransaction
{
    private byte[] keyRequest;
    
    public CdmProvisionNccpTransaction(final Context context, final EsnProvider esnProvider, final NccpResponseHandler nccpResponseHandler, final byte[] keyRequest) {
        super(context, esnProvider, nccpResponseHandler);
        this.keyRequest = keyRequest;
    }
    
    @Override
    protected StringBuilder createCustomRequest(final StringBuilder sb) {
        final String encodeToString = Base64.encodeToString(this.keyRequest, 2);
        if (Log.isLoggable()) {
            Log.d("nf_nccp", "Key request encoded base64: " + encodeToString);
        }
        sb.append("<nccp:cdmprovision>");
        sb.append("<nccp:cdmtype>MediaDrm.Widevine</nccp:cdmtype>");
        sb.append("<nccp:keyrequest>");
        sb.append(encodeToString);
        sb.append("</nccp:keyrequest>");
        sb.append("</nccp:cdmprovision>");
        return sb;
    }
    
    @Override
    public String getName() {
        return "cdmprovision";
    }
    
    @Override
    public List<RequestParameter> getRequestHeaders() {
        final ArrayList<RequestParameter> list = new ArrayList<RequestParameter>();
        list.add(new RequestParameter("X-AuthenticationType", "NONE"));
        list.add(new RequestParameter("X-AllowCompression", "false"));
        list.add(new RequestParameter("Content-Type", "application/x-www-form-urlencoded"));
        list.add(new RequestParameter("X-ESN", this.mEsn.getEsn()));
        list.add(new RequestParameter("X-DeviceModel", this.mEsn.getDeviceModel()));
        return list;
    }
    
    @Override
    public NccpResponse processError(final Throwable t) {
        return new NccpTransactionApplicationError(t, this.getName());
    }
    
    @Override
    public NccpResponse processResponseBody(final String s) {
        try {
            return new CdmProvisionNccpResponse(s);
        }
        catch (Throwable t) {
            Log.e("nf_nccp", "Failed to parse response for " + this.getName(), t);
            return new EmptyReponse(this.getName());
        }
    }
}
