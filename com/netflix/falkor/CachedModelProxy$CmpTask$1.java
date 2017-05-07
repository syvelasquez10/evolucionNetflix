// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import java.util.Collection;
import java.util.ArrayList;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.android.app.Status;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.DataUtil;
import com.netflix.mediaclient.util.ThreadUtils;
import android.content.Context;
import com.netflix.mediaclient.util.DataUtil$StringPair;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

class CachedModelProxy$CmpTask$1 extends FalcorVolleyWebClientRequest
{
    private final boolean notOnMain;
    private final List<DataUtil$StringPair> optionalRequestParams;
    private final List<String> pqls;
    final /* synthetic */ CachedModelProxy$CmpTask this$1;
    private final boolean useCallMethod;
    final /* synthetic */ List val$requestPql;
    
    CachedModelProxy$CmpTask$1(final CachedModelProxy$CmpTask this$1, final Context context, final List val$requestPql) {
        this.this$1 = this$1;
        this.val$requestPql = val$requestPql;
        super(context);
        this.notOnMain = ThreadUtils.assertNotOnMain();
        this.pqls = DataUtil.createStringListFromList(this.val$requestPql);
        this.useCallMethod = this.this$1.shouldUseCallMethod();
        this.optionalRequestParams = this.this$1.getOptionalRequestParams();
    }
    
    @Override
    protected String getMethodType() {
        if (this.useCallMethod) {
            return "call";
        }
        return "get";
    }
    
    @Override
    protected String getOptionalParams() {
        ThreadUtils.assertNotOnMain();
        String string;
        if (this.optionalRequestParams == null || this.optionalRequestParams.size() == 0) {
            string = null;
        }
        else {
            final StringBuilder sb = new StringBuilder();
            for (final DataUtil$StringPair dataUtil$StringPair : this.optionalRequestParams) {
                sb.append("&").append((String)dataUtil$StringPair.first).append("=").append((String)dataUtil$StringPair.second);
            }
            final String s = string = sb.toString();
            if (Log.isLoggable("CachedModelProxy", 2)) {
                Log.v("CachedModelProxy", "Sending optional url params: " + s);
                return s;
            }
        }
        return string;
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return this.pqls;
    }
    
    @Override
    protected void onFailure(final Status status) {
        ThreadUtils.assertOnMain();
        this.this$1.handleFailure(this.this$1.callback, status);
    }
    
    @Override
    protected void onSuccess(final Object o) {
        ThreadUtils.assertOnMain();
    }
    
    @Override
    protected Void parseFalcorResponse(final String s) {
        ThreadUtils.assertNotOnMain();
        try {
            this.this$1.this$0.merge(s);
            this.this$1.handleSuccess();
            return null;
        }
        catch (Exception ex) {
            Log.handleException("CachedModelProxy", ex);
            throw ex;
        }
    }
    
    @Override
    protected boolean parsedResponseCanBeNull() {
        return true;
    }
    
    @Override
    protected boolean shouldMaterializeRequest() {
        return true;
    }
}
