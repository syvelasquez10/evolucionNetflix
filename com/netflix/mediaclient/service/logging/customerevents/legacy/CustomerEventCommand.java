// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.customerevents.legacy;

import com.netflix.mediaclient.Log;
import java.util.ArrayList;
import org.apache.http.NameValuePair;
import java.util.List;
import android.util.Pair;
import com.netflix.mediaclient.webapi.CommonRequestParameters;
import com.netflix.mediaclient.webapi.AuthorizationCredentials;
import com.netflix.mediaclient.webapi.ResponseWebApiPostCommand;

public class CustomerEventCommand extends ResponseWebApiPostCommand
{
    protected static final String PARAMETER_DATA = "data";
    protected static final String PATH_EVENTS = "/users/customerevents";
    protected static final String TAG = "nf_rest";
    protected String mPayload;
    
    CustomerEventCommand(final String s, final AuthorizationCredentials authorizationCredentials, final String mPayload) {
        super(s, authorizationCredentials, CommonRequestParameters.getInstanceWithCredentials());
        this.verifyNotNull((Object)mPayload, "Payload");
        this.mPayload = mPayload;
    }
    
    public String getCommandPath() {
        return "/users/customerevents";
    }
    
    public Pair<String, String>[] getHeaders() {
        return (Pair<String, String>[])new Pair[] { new Pair((Object)"Content-Type", (Object)"application/x-www-form-urlencoded") };
    }
    
    protected List<NameValuePair> getParameters() {
        final ArrayList<NameValuePair> list = new ArrayList<NameValuePair>(1);
        final String string = this.mPayload.toString();
        if (Log.isLoggable()) {
            Log.d("nf_rest", "Payload: " + string);
        }
        this.addIfNotNull((List)list, "data", string);
        return list;
    }
    
    protected StringBuilder getUrl() {
        return this.getBaseCustomerEventBeaconUrl();
    }
}
