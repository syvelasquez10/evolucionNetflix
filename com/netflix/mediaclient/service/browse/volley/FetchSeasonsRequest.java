// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.service.browse.BrowseVideoSentinels;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.model.branches.Season$Detail;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import java.util.ArrayList;
import com.netflix.mediaclient.android.app.CommonStatus;
import java.util.Collections;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class FetchSeasonsRequest extends FalcorVolleyWebClientRequest<List<SeasonDetails>>
{
    private static final String FIELD_SEASONS = "seasons";
    private static final String FIELD_VIDEOS = "videos";
    private static final String TAG = "nf_service_browse_fetchseasonsrequest";
    private final int fromSeason;
    private final String mShowId;
    private final String pqlQuery;
    private final BrowseAgentCallback responseCallback;
    private final int toSeason;
    
    public FetchSeasonsRequest(final Context context, final String mShowId, final int fromSeason, final int toSeason, final BrowseAgentCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.mShowId = mShowId;
        this.fromSeason = fromSeason;
        this.toSeason = toSeason;
        this.pqlQuery = String.format("['videos', '%s', 'seasons', {'from':%d,'to':%d}, 'detail']", this.mShowId, fromSeason, toSeason);
        if (Log.isLoggable("nf_service_browse_fetchseasonsrequest", 2)) {
            Log.v("nf_service_browse_fetchseasonsrequest", "PQL = " + this.pqlQuery);
        }
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onSeasonsFetched(Collections.emptyList(), status);
        }
    }
    
    @Override
    protected void onSuccess(final List<SeasonDetails> list) {
        if (this.responseCallback != null) {
            this.responseCallback.onSeasonsFetched(list, CommonStatus.OK);
        }
    }
    
    @Override
    protected List<SeasonDetails> parseFalcorResponse(String o) {
        if (Log.isLoggable("nf_service_browse_fetchseasonsrequest", 2)) {}
        final ArrayList<com.netflix.mediaclient.service.webclient.model.SeasonDetails> list = new ArrayList<com.netflix.mediaclient.service.webclient.model.SeasonDetails>();
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_service_browse_fetchseasonsrequest", (String)o);
        if (FalcorParseUtils.isEmpty(dataObj)) {
            return (List<SeasonDetails>)list;
        }
        while (true) {
            while (true) {
                boolean b = false;
                Label_0177: {
                    try {
                        final JsonObject asJsonObject = dataObj.getAsJsonObject("videos").getAsJsonObject(this.mShowId).getAsJsonObject("seasons");
                        int i = this.toSeason;
                        b = false;
                        while (i >= this.fromSeason) {
                            o = Integer.toString(i);
                            if (!asJsonObject.has((String)o)) {
                                break Label_0177;
                            }
                            o = asJsonObject.getAsJsonObject((String)o);
                            final com.netflix.mediaclient.service.webclient.model.SeasonDetails seasonDetails = new com.netflix.mediaclient.service.webclient.model.SeasonDetails();
                            seasonDetails.detail = FalcorParseUtils.getPropertyObject((JsonObject)o, "detail", Season$Detail.class);
                            list.add(0, seasonDetails);
                            b = true;
                            --i;
                        }
                        break;
                    }
                    catch (Exception ex) {
                        Log.v("nf_service_browse_fetchseasonsrequest", "String response to parse = " + (String)o);
                        throw new FalcorParseException("response missing expected json objects", ex);
                    }
                }
                Label_0211: {
                    if (b) {
                        Log.d("nf_service_browse_fetchseasonsrequest", String.format("Adding sentinel at index %s", o));
                        list.add(0, BrowseVideoSentinels.getUnavailableSeasonsDetails());
                        break Label_0211;
                    }
                    break Label_0211;
                }
                continue;
            }
        }
        return (List<SeasonDetails>)list;
    }
}
