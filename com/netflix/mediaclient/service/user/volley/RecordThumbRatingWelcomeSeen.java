// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user.volley;

import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class RecordThumbRatingWelcomeSeen extends FalkorVolleyWebClientRequest<String>
{
    private static final String TAG = "RecordThumbRatingWelcomeSeen";
    private final String pqlQuery;
    
    public RecordThumbRatingWelcomeSeen(final Context context) {
        super(context);
        this.pqlQuery = "['user', 'setThumbWelcomeSeen']";
        if (Log.isLoggable()) {
            Log.v("RecordThumbRatingWelcomeSeen", "PQL = ['user', 'setThumbWelcomeSeen']");
        }
    }
    
    @Override
    protected String getMethodType() {
        return "get";
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList("['user', 'setThumbWelcomeSeen']");
    }
    
    @Override
    protected void onFailure(final Status status) {
    }
    
    @Override
    protected void onSuccess(final String s) {
        if (Log.isLoggable()) {
            Log.v("RecordThumbRatingWelcomeSeen", "String response to parse = " + s);
        }
    }
    
    @Override
    protected String parseFalkorResponse(final String s) {
        if (Log.isLoggable()) {
            Log.v("RecordThumbRatingWelcomeSeen", "String response to parse = " + s);
        }
        return s;
    }
    
    @Override
    protected boolean shouldSkipProcessingOnInvalidUser() {
        return false;
    }
}
