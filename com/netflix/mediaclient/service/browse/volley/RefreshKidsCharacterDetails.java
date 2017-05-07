// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.KidsCharacterDetails;
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
    
    protected RefreshKidsCharacterDetails(final Context context, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface, final String mCharacterId, final BrowseAgentCallback responseCallback) {
        super(context, configurationAgentInterface);
        this.mCharacterId = mCharacterId;
        this.responseCallback = responseCallback;
        this.pqlQuery1 = String.format("['characters', '%s', 'watchNext', ['summary', 'detail', 'bookmark']]", this.mCharacterId);
        if (Log.isLoggable("nf_kidscharacter", 2)) {
            Log.v("nf_kidscharacter", "PQL = " + this.pqlQuery1);
        }
    }
    
    @Override
    protected String[] getPQLQueries() {
        return new String[] { this.pqlQuery1 };
    }
    
    @Override
    protected void onFailure(final int n) {
        if (this.responseCallback != null) {
            this.responseCallback.onKidsCharacterDetailsFetched(null, RefreshKidsCharacterDetails.DATA_CHANGED, n);
        }
    }
    
    @Override
    protected void onSuccess(final KidsCharacterDetails kidsCharacterDetails) {
        if (this.responseCallback != null) {
            this.responseCallback.onKidsCharacterDetailsFetched(kidsCharacterDetails, RefreshKidsCharacterDetails.DATA_CHANGED, 0);
        }
    }
    
    @Override
    protected KidsCharacterDetails parseFalcorResponse(String s) throws FalcorParseException, FalcorServerException {
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
