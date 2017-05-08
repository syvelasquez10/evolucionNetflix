// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user.volley;

import com.netflix.mediaclient.service.webclient.volley.FalkorException;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Collections;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import java.util.ArrayList;
import android.content.Context;
import com.netflix.mediaclient.util.DataUtil$StringPair;
import java.util.List;
import com.netflix.mediaclient.service.configuration.ConfigurationAgentWebCallback;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class AllocateABTestRequest extends FalkorVolleyWebClientRequest<Boolean>
{
    private static final String TAG = "AllocateABTestRequest";
    private final ConfigurationAgentWebCallback callback;
    private final List<DataUtil$StringPair> params;
    private final String pql;
    
    public AllocateABTestRequest(final Context context, final int n, final int n2, final ConfigurationAgentWebCallback callback) {
        super(context);
        this.callback = callback;
        this.pql = String.format("['allocateToABTest']", new Object[0]);
        (this.params = new ArrayList<DataUtil$StringPair>(2)).add(new DataUtil$StringPair("param", String.valueOf(n)));
        this.params.add(new DataUtil$StringPair("param", String.valueOf(n2)));
    }
    
    @Override
    protected String getMethodType() {
        return "call";
    }
    
    @Override
    protected String getOptionalParams() {
        final StringBuilder sb = new StringBuilder();
        for (final DataUtil$StringPair dataUtil$StringPair : this.params) {
            sb.append("&").append((String)dataUtil$StringPair.first).append("=").append((String)dataUtil$StringPair.second);
        }
        final String string = sb.toString();
        Log.v("AllocateABTestRequest", "Sending url params: %s", string);
        return string;
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Collections.singletonList(this.pql);
    }
    
    @Override
    protected void onFailure(final Status status) {
        Log.e("AllocateABTestRequest", "Allocate AB test failed : " + status);
        if (this.callback != null) {
            this.callback.onAllocateABTestCompleted(status);
        }
    }
    
    @Override
    protected void onSuccess(final Boolean b) {
        if (Log.isLoggable()) {
            Log.d("AllocateABTestRequest", "Response from server : " + b);
        }
        if (this.callback != null) {
            this.callback.onAllocateABTestCompleted(CommonStatus.OK);
        }
    }
    
    @Override
    protected Boolean parseFalkorResponse(final String s) {
        if (FalkorParseUtils.isEmpty(FalkorParseUtils.getDataObj("AllocateABTestRequest", s))) {
            throw new FalkorException("Test is either disabled or invalid cell Id");
        }
        if (Log.isLoggable()) {
            Log.v("AllocateABTestRequest", "String response to parse = " + s);
        }
        return true;
    }
}
