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

public class RecordPlanSelection extends FalkorVolleyWebClientRequest<String>
{
    private static final String TAG = "eog_RecordPlanSelection";
    private final String mPlanId;
    private final String mPriceTier;
    private final String pqlQuery;
    
    public RecordPlanSelection(final Context context, final String mPlanId, final String mPriceTier) {
        super(context);
        this.pqlQuery = "['ums', 'selectPlan']";
        this.mPriceTier = mPriceTier;
        this.mPlanId = mPlanId;
        if (Log.isLoggable()) {
            Log.v("eog_RecordPlanSelection", "PQL = ['ums', 'selectPlan']");
        }
    }
    
    @Override
    protected String getMethodType() {
        return "call";
    }
    
    @Override
    protected String getOptionalParams() {
        final StringBuilder sb = new StringBuilder(UriUtil.buildUrlParam("param", UriUtil.urlEncodeParam(String.format("'%s'", this.mPlanId))));
        sb.append(UriUtil.buildUrlParam("param", UriUtil.urlEncodeParam(String.format("'%s'", this.mPriceTier))));
        return sb.toString();
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList("['ums', 'selectPlan']");
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
            Log.v("eog_RecordPlanSelection", "String response to parse = " + s);
        }
        return s;
    }
    
    @Override
    protected boolean shouldSkipProcessingOnInvalidUser() {
        return false;
    }
}
