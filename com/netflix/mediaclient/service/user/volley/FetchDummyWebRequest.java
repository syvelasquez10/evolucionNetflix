// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user.volley;

import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.service.user.UserAgentWebCallback;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class FetchDummyWebRequest extends FalcorVolleyWebClientRequest<String>
{
    private static final String TAG = "nf_service_user_fetchdummywebrequest";
    private final String pqlQuery;
    private final UserAgentWebCallback responseCallback;
    
    public FetchDummyWebRequest(final Context context, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface, final UserAgentWebCallback responseCallback) {
        super(context, configurationAgentInterface);
        this.responseCallback = responseCallback;
        this.pqlQuery = new StringBuilder("['dummy']").toString();
        if (Log.isLoggable("nf_service_user_fetchdummywebrequest", 2)) {
            Log.v("nf_service_user_fetchdummywebrequest", "PQL = " + this.pqlQuery);
        }
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery);
    }
    
    @Override
    protected void onFailure(final int n) {
        if (this.responseCallback != null) {
            this.responseCallback.onDummyWebCallDone(n);
        }
    }
    
    @Override
    protected void onSuccess(final String s) {
        if (this.responseCallback != null) {
            this.responseCallback.onDummyWebCallDone(0);
        }
    }
    
    @Override
    protected String parseFalcorResponse(final String s) throws FalcorParseException, FalcorServerException {
        if (Log.isLoggable("nf_service_user_fetchdummywebrequest", 2)) {}
        return Integer.toString(0);
    }
    
    @Override
    protected boolean shouldSkipProcessingOnInvalidUser() {
        return false;
    }
}
