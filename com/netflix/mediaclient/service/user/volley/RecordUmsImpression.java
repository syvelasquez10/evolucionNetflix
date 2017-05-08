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

public class RecordUmsImpression extends FalkorVolleyWebClientRequest<String>
{
    private static final String TAG = "eog_RecordEogImpression";
    private final String mImpressionType;
    private final String mMsgName;
    private final String pqlQuery;
    
    public RecordUmsImpression(final Context context, final String mMsgName, final String mImpressionType) {
        super(context);
        this.pqlQuery = "['ums', 'setImpression']";
        this.mImpressionType = mImpressionType;
        this.mMsgName = mMsgName;
        if (Log.isLoggable()) {
            Log.v("eog_RecordEogImpression", "PQL = ['ums', 'setImpression']");
        }
    }
    
    @Override
    protected String getMethodType() {
        return "call";
    }
    
    @Override
    protected String getOptionalParams() {
        final StringBuilder sb = new StringBuilder(UriUtil.buildUrlParam("param", UriUtil.urlEncodeParam(String.format("'%s'", this.mMsgName))));
        sb.append(UriUtil.buildUrlParam("param", UriUtil.urlEncodeParam(String.format("'%s'", this.mImpressionType))));
        return sb.toString();
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList("['ums', 'setImpression']");
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
            Log.v("eog_RecordEogImpression", "String response to parse = " + s);
        }
        return s;
    }
    
    @Override
    protected boolean shouldSkipProcessingOnInvalidUser() {
        return false;
    }
}
