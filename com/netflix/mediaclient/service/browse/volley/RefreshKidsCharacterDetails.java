// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.model.details.KidsCharacterDetails;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class RefreshKidsCharacterDetails extends FalcorVolleyWebClientRequest<KidsCharacterDetails>
{
    private static final Boolean DATA_CHANGED;
    private static final String FIELD_CHARACTER = "characters";
    private static final String TAG = "nf_kidscharacter";
    private final String mCharacterId;
    private final String pqlQuery1;
    private final BrowseAgentCallback responseCallback;
    
    static {
        DATA_CHANGED = true;
    }
    
    protected RefreshKidsCharacterDetails(final Context context, final String mCharacterId, final BrowseAgentCallback responseCallback) {
        super(context);
        this.mCharacterId = mCharacterId;
        this.responseCallback = responseCallback;
        this.pqlQuery1 = String.format("['characters', '%s', 'watchNext', ['summary', 'detail', 'bookmark']]", this.mCharacterId);
        if (Log.isLoggable("nf_kidscharacter", 2)) {
            Log.v("nf_kidscharacter", "PQL = " + this.pqlQuery1);
        }
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery1);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onKidsCharacterDetailsFetched(null, RefreshKidsCharacterDetails.DATA_CHANGED, status);
        }
    }
    
    @Override
    protected void onSuccess(final KidsCharacterDetails kidsCharacterDetails) {
        if (this.responseCallback != null) {
            this.responseCallback.onKidsCharacterDetailsFetched(kidsCharacterDetails, RefreshKidsCharacterDetails.DATA_CHANGED, CommonStatus.OK);
        }
    }
    
    @Override
    protected KidsCharacterDetails parseFalcorResponse(String s) {
        if (Log.isLoggable("nf_kidscharacter", 2)) {
            Log.v("nf_kidscharacter", "String response to parse = " + s);
        }
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_kidscharacter", s);
        if (FalcorParseUtils.isEmpty(dataObj)) {
            throw new FalcorParseException("RefreshKidsCharacterDetails empty!!!");
        }
        try {
            final JsonObject asJsonObject = dataObj.getAsJsonObject("characters").getAsJsonObject(this.mCharacterId);
            s = (String)new com.netflix.mediaclient.service.webclient.model.KidsCharacterDetails();
            FetchKidsCharacterDetailsRequest.insertWatchNext(asJsonObject, (com.netflix.mediaclient.service.webclient.model.KidsCharacterDetails)s);
            return (KidsCharacterDetails)s;
        }
        catch (Exception ex) {
            Log.v("nf_kidscharacter", "String response to parse = " + s);
            throw new FalcorParseException("response missing expected json objects", ex);
        }
    }
}
