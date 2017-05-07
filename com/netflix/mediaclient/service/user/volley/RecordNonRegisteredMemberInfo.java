// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user.volley;

import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.util.UriUtil;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class RecordNonRegisteredMemberInfo extends FalkorVolleyWebClientRequest<String>
{
    private static final String TAG = "nf_service_user_RecordNonMemberInfo";
    private final String mNrmNetlfixId;
    private final String pqlQuery;
    
    public RecordNonRegisteredMemberInfo(final Context context, final String mNrmNetlfixId) {
        super(context);
        this.pqlQuery = "['recordNrmInfo']";
        this.mNrmNetlfixId = mNrmNetlfixId;
        if (Log.isLoggable()) {
            Log.v("nf_service_user_RecordNonMemberInfo", "PQL = ['recordNrmInfo']");
        }
    }
    
    @Override
    protected String getMethodType() {
        return "call";
    }
    
    @Override
    protected String getOptionalParams() {
        final String buildUrlParam = UriUtil.buildUrlParam("param", UriUtil.urlEncodeParam(String.format("'%s'", this.mNrmNetlfixId)));
        Log.d("nf_service_user_RecordNonMemberInfo", " getOptionalParams: " + buildUrlParam);
        return buildUrlParam;
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList("['recordNrmInfo']");
    }
    
    @Override
    protected void onFailure(final Status status) {
    }
    
    @Override
    protected void onSuccess(final String s) {
    }
    
    @Override
    protected String parseFalkorResponse(final String s) {
        if (Log.isLoggable()) {
            Log.v("nf_service_user_RecordNonMemberInfo", "String response to parse = " + s);
        }
        return s;
    }
    
    @Override
    protected boolean shouldSkipProcessingOnInvalidUser() {
        return false;
    }
}
