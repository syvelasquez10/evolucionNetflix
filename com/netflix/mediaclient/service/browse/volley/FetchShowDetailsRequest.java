// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.model.branches.Episode;
import com.netflix.mediaclient.service.webclient.model.leafs.SocialEvidence;
import com.netflix.mediaclient.service.webclient.model.branches.Video;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.ShowDetails;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class FetchShowDetailsRequest extends FalcorVolleyWebClientRequest<ShowDetails>
{
    private static final String FIELD_VIDEOS = "videos";
    private static final String TAG = "nf_service_browse_fetchshowdetailsrequest";
    private final boolean isCurrentEpisodeLocal;
    private final String mReqEpisodeId;
    private final String mShowId;
    private final String pqlQuery;
    private final String pqlQuery2;
    private final BrowseAgentCallback responseCallback;
    private final boolean userConnectedToFacebook;
    
    public FetchShowDetailsRequest(final Context context, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface, final String mShowId, final String mReqEpisodeId, final boolean userConnectedToFacebook, final BrowseAgentCallback responseCallback) {
        super(context, configurationAgentInterface);
        this.responseCallback = responseCallback;
        this.mShowId = mShowId;
        this.mReqEpisodeId = mReqEpisodeId;
        this.isCurrentEpisodeLocal = StringUtils.isNotEmpty(this.mReqEpisodeId);
        this.userConnectedToFacebook = userConnectedToFacebook;
        this.pqlQuery = "['videos','" + this.mShowId + "',['summary','detail', 'rating', 'inQueue', 'socialEvidence']]";
        if (this.isCurrentEpisodeLocal) {
            this.pqlQuery2 = "['episodes','" + this.mReqEpisodeId + "', ['detail', 'bookmark']]";
        }
        else {
            this.pqlQuery2 = "['videos','" + this.mShowId + "','episodes', 'current', ['detail', 'bookmark']]";
        }
        if (Log.isLoggable("nf_service_browse_fetchshowdetailsrequest", 2)) {
            Log.v("nf_service_browse_fetchshowdetailsrequest", "PQL = " + this.pqlQuery + this.pqlQuery2);
        }
    }
    
    @Override
    protected String[] getPQLQueries() {
        return new String[] { this.pqlQuery, this.pqlQuery2 };
    }
    
    @Override
    protected void onFailure(final int n) {
        if (this.responseCallback != null) {
            this.responseCallback.onShowDetailsFetched(null, n);
        }
    }
    
    @Override
    protected void onSuccess(final ShowDetails showDetails) {
        if (this.responseCallback != null) {
            this.responseCallback.onShowDetailsFetched(showDetails, 0);
        }
    }
    
    @Override
    protected ShowDetails parseFalcorResponse(String s) throws FalcorParseException, FalcorServerException {
        if (Log.isLoggable("nf_service_browse_fetchshowdetailsrequest", 2)) {
            Log.v("nf_service_browse_fetchshowdetailsrequest", "String response to parse = " + s);
        }
        final com.netflix.mediaclient.service.webclient.model.ShowDetails showDetails = new com.netflix.mediaclient.service.webclient.model.ShowDetails();
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_service_browse_fetchshowdetailsrequest", s);
        if (FalcorParseUtils.isEmpty(dataObj)) {
            throw new FalcorParseException("ShowDetails empty!!!");
        }
        while (true) {
            while (true) {
                try {
                    final JsonObject asJsonObject = dataObj.getAsJsonObject("videos").getAsJsonObject(this.mShowId);
                    showDetails.summary = FalcorParseUtils.getPropertyObject(asJsonObject, "summary", Video.Summary.class);
                    showDetails.detail = FalcorParseUtils.getPropertyObject(asJsonObject, "detail", Video.Detail.class);
                    showDetails.rating = FalcorParseUtils.getPropertyObject(asJsonObject, "rating", Video.Rating.class);
                    showDetails.inQueue = FalcorParseUtils.getPropertyObject(asJsonObject, "inQueue", Video.InQueue.class);
                    showDetails.socialEvidence = FalcorParseUtils.getPropertyObject(asJsonObject, "socialEvidence", SocialEvidence.class);
                    if (!this.isCurrentEpisodeLocal) {
                        if (asJsonObject.has("episodes")) {
                            s = (String)asJsonObject.getAsJsonObject("episodes");
                            if (((JsonObject)s).has("current")) {
                                s = (String)((JsonObject)s).getAsJsonObject("current");
                                showDetails.currentEpisode = FalcorParseUtils.getPropertyObject((JsonObject)s, "detail", Episode.Detail.class);
                                showDetails.currentEpisodeBookmark = FalcorParseUtils.getPropertyObject((JsonObject)s, "bookmark", Video.Bookmark.class);
                            }
                        }
                        showDetails.userConnectedToFacebook = this.userConnectedToFacebook;
                        return showDetails;
                    }
                }
                catch (Exception ex) {
                    Log.v("nf_service_browse_fetchshowdetailsrequest", "String response to parse = " + s);
                    throw new FalcorParseException("response missing expected json objects", ex);
                }
                if (!dataObj.has("episodes")) {
                    continue;
                }
                final JsonObject asJsonObject2 = dataObj.getAsJsonObject("episodes");
                try {
                    final JsonObject asJsonObject3 = asJsonObject2.getAsJsonObject(this.mReqEpisodeId);
                    showDetails.currentEpisode = FalcorParseUtils.getPropertyObject(asJsonObject3, "detail", Episode.Detail.class);
                    showDetails.currentEpisodeBookmark = FalcorParseUtils.getPropertyObject(asJsonObject3, "bookmark", Video.Bookmark.class);
                    continue;
                }
                catch (Exception ex2) {
                    Log.v("nf_service_browse_fetchshowdetailsrequest", "String response to parse = " + s);
                    throw new FalcorParseException("response missing expected json objects", ex2);
                }
                break;
            }
        }
    }
}
