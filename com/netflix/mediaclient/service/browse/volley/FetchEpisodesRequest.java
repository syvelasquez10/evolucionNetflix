// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.browse.BrowseVideoSentinels;
import com.netflix.mediaclient.service.webclient.model.branches.Video$Bookmark;
import com.netflix.mediaclient.service.webclient.model.branches.Episode$Detail;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import java.util.ArrayList;
import com.netflix.mediaclient.android.app.CommonStatus;
import java.util.Collections;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class FetchEpisodesRequest extends FalcorVolleyWebClientRequest<List<EpisodeDetails>>
{
    private static final String FIELD_VIDEOS = "videos";
    private static final String TAG = "nf_service_browse_fetchepisodesrequest";
    private final int fromEpisodes;
    private final String mVideoId;
    private final String pqlQuery;
    private final BrowseAgentCallback responseCallback;
    private final int toEpisodes;
    private final boolean userConnectedToFacebook;
    
    public FetchEpisodesRequest(final Context context, final String mVideoId, final int fromEpisodes, final int toEpisodes, final boolean userConnectedToFacebook, final BrowseAgentCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.mVideoId = mVideoId;
        this.fromEpisodes = fromEpisodes;
        this.toEpisodes = toEpisodes;
        this.userConnectedToFacebook = userConnectedToFacebook;
        this.pqlQuery = String.format("['videos','%s','episodes', {'from':%d,'to':%d}, ['detail', 'summary', 'bookmark']]", this.mVideoId, fromEpisodes, toEpisodes);
        if (Log.isLoggable("nf_service_browse_fetchepisodesrequest", 2)) {
            Log.v("nf_service_browse_fetchepisodesrequest", "PQL = " + this.pqlQuery);
        }
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onEpisodesFetched(Collections.emptyList(), status);
        }
    }
    
    @Override
    protected void onSuccess(final List<EpisodeDetails> list) {
        if (this.responseCallback != null) {
            this.responseCallback.onEpisodesFetched(list, CommonStatus.OK);
        }
    }
    
    @Override
    protected List<EpisodeDetails> parseFalcorResponse(String asJsonObject) {
        final ArrayList<com.netflix.mediaclient.service.webclient.model.EpisodeDetails> list = new ArrayList<com.netflix.mediaclient.service.webclient.model.EpisodeDetails>();
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_service_browse_fetchepisodesrequest", (String)asJsonObject);
        if (FalcorParseUtils.isEmpty(dataObj)) {
            return (List<EpisodeDetails>)list;
        }
        while (true) {
            while (true) {
                Label_0262: {
                    try {
                        final JsonObject asJsonObject2 = dataObj.getAsJsonObject("videos").getAsJsonObject(this.mVideoId);
                        if (!asJsonObject2.has("episodes")) {
                            break Label_0262;
                        }
                        asJsonObject = asJsonObject2.getAsJsonObject("episodes");
                        if (asJsonObject == null) {
                            return (List<EpisodeDetails>)list;
                        }
                    }
                    catch (Exception ex) {
                        Log.v("nf_service_browse_fetchepisodesrequest", "String response to parse = " + (String)asJsonObject);
                        throw new FalcorParseException("Does not contain required fields", ex);
                    }
                    break;
                }
                asJsonObject = null;
                continue;
            }
        }
        int i = this.toEpisodes;
        boolean b = false;
        while (i >= this.fromEpisodes) {
            final String string = Integer.toString(i);
            if (((JsonObject)asJsonObject).has(string)) {
                final JsonObject asJsonObject3 = ((JsonObject)asJsonObject).getAsJsonObject(string);
                final com.netflix.mediaclient.service.webclient.model.EpisodeDetails episodeDetails = new com.netflix.mediaclient.service.webclient.model.EpisodeDetails();
                episodeDetails.detail = FalcorParseUtils.getPropertyObject(asJsonObject3, "detail", Episode$Detail.class);
                episodeDetails.bookmark = FalcorParseUtils.getPropertyObject(asJsonObject3, "bookmark", Video$Bookmark.class);
                episodeDetails.userConnectedToFacebook = this.userConnectedToFacebook;
                list.add(0, episodeDetails);
                b = true;
            }
            else if (b) {
                Log.d("nf_service_browse_fetchepisodesrequest", String.format("Adding sentinel at index %s", string));
                list.add(0, BrowseVideoSentinels.getUnavailableEpisodeDetails());
            }
            --i;
        }
        return (List<EpisodeDetails>)list;
    }
}
