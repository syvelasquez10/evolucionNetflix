// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.service.browse.BrowseVideoSentinels;
import com.netflix.mediaclient.service.webclient.model.branches.Episode;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.model.branches.Video;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import java.util.ArrayList;
import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
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
    protected List<EpisodeDetails> parseFalcorResponse(String o) throws FalcorParseException, FalcorServerException {
        final ArrayList<com.netflix.mediaclient.service.webclient.model.EpisodeDetails> list = (ArrayList<com.netflix.mediaclient.service.webclient.model.EpisodeDetails>)new ArrayList<EpisodeDetails>();
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_service_browse_fetchepisodesrequest", (String)o);
        if (!FalcorParseUtils.isEmpty(dataObj)) {
            while (true) {
                JsonObject asJsonObject = null;
                while (true) {
                    int n = 0;
                    Label_0235: {
                        try {
                            final JsonObject asJsonObject2 = dataObj.getAsJsonObject("videos").getAsJsonObject(this.mVideoId);
                            if (asJsonObject2.has("episodes")) {
                                asJsonObject = asJsonObject2.getAsJsonObject("episodes");
                            }
                            if (asJsonObject != null) {
                                n = 0;
                                int n2;
                                for (int i = this.toEpisodes; i >= this.fromEpisodes; --i, n = n2) {
                                    o = Integer.toString(i);
                                    if (!asJsonObject.has((String)o)) {
                                        break Label_0235;
                                    }
                                    n2 = 1;
                                    o = asJsonObject.getAsJsonObject((String)o);
                                    final com.netflix.mediaclient.service.webclient.model.EpisodeDetails episodeDetails = new com.netflix.mediaclient.service.webclient.model.EpisodeDetails();
                                    episodeDetails.summary = FalcorParseUtils.getPropertyObject((JsonObject)o, "summary", Video.Summary.class);
                                    episodeDetails.detail = FalcorParseUtils.getPropertyObject((JsonObject)o, "detail", Episode.Detail.class);
                                    episodeDetails.bookmark = FalcorParseUtils.getPropertyObject((JsonObject)o, "bookmark", Video.Bookmark.class);
                                    episodeDetails.userConnectedToFacebook = this.userConnectedToFacebook;
                                    list.add(0, episodeDetails);
                                }
                                break;
                            }
                            break;
                        }
                        catch (Exception ex) {
                            Log.v("nf_service_browse_fetchepisodesrequest", "String response to parse = " + (String)o);
                            throw new FalcorParseException("Does not contain required fields", ex);
                        }
                    }
                    int n2;
                    if ((n2 = n) != 0) {
                        Log.d("nf_service_browse_fetchepisodesrequest", String.format("Adding sentinel at index %s", o));
                        list.add(0, BrowseVideoSentinels.getUnavailableEpisodeDetails());
                        n2 = n;
                        continue;
                    }
                    continue;
                }
            }
        }
        return (List<EpisodeDetails>)list;
    }
}
