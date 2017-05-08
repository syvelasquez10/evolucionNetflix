// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user.volley;

import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import android.content.Context;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class MarkSurveyReadRequest extends FalkorVolleyWebClientRequest<Void>
{
    private static final String TAG = "MarkSurveyReadRequest";
    
    public MarkSurveyReadRequest(final Context context) {
        super(context);
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList("['survey', 'mark_read']");
    }
    
    @Override
    protected void onFailure(final Status status) {
    }
    
    @Override
    protected void onSuccess(final Void void1) {
    }
    
    @Override
    protected Void parseFalkorResponse(final String s) {
        return null;
    }
    
    @Override
    protected boolean shouldSkipProcessingOnInvalidUser() {
        return false;
    }
}
