// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.service.webclient.volley.FalkorParseException;
import com.netflix.mediaclient.android.app.CommonStatus;
import java.util.Collections;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import com.netflix.mediaclient.service.browse.BrowseVideoSentinels;
import com.netflix.mediaclient.service.webclient.model.PlayableVideo;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.model.leafs.SocialBadge;
import com.netflix.mediaclient.service.webclient.model.BillboardDetails;
import java.util.ArrayList;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class FetchBBVideosRequest extends FalkorVolleyWebClientRequest<List<Billboard>>
{
    public static final String BB_SOCIAL_BADGE_QUERY = "['lists', '%s', 'socialEvidence', {'from':%d,'to':%d}, 'socialBadge']";
    private static final String TAG = "nf_service_browse_fetchbbvideosrequest";
    private final BrowseWebClientCache browseCache;
    private final int fromVideo;
    private final LoMo mLoMo;
    private final String pqlQuery1;
    private final String pqlQuery2;
    private final BrowseAgentCallback responseCallback;
    private final String socialPqlQuery;
    private final int toVideo;
    private final boolean userConnectedToFacebook;
    
    public FetchBBVideosRequest(final Context context, final BrowseWebClientCache browseCache, final LoMo mLoMo, final int fromVideo, final int toVideo, final int n, final boolean userConnectedToFacebook, final BrowseAgentCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.mLoMo = mLoMo;
        this.fromVideo = fromVideo;
        this.toVideo = toVideo;
        this.userConnectedToFacebook = userConnectedToFacebook;
        this.browseCache = browseCache;
        final String format = String.format("['lists','%s', 'videoEvidence'", mLoMo.getId());
        this.pqlQuery1 = String.format("%s, {'from':%d,'to':%d}, ['summary', 'detail', 'rating', 'inQueue', 'bookmark', 'bookmarkStill']]", format, fromVideo, toVideo);
        this.pqlQuery2 = String.format("%s, {'from':%d,'to':%d}, 'episodes', 'current', ['detail', 'bookmark']]", format, fromVideo, toVideo);
        this.socialPqlQuery = String.format("['lists', '%s', 'socialEvidence', {'from':%d,'to':%d}, 'socialBadge']", mLoMo.getId(), fromVideo, toVideo);
        if (Log.isLoggable("nf_service_browse_fetchbbvideosrequest", 2)) {
            Log.v("nf_service_browse_fetchbbvideosrequest", "PQL = " + this.pqlQuery1 + " " + this.pqlQuery2 + " " + this.socialPqlQuery);
        }
    }
    
    public static List<Billboard> buildBBVideoList(final JsonObject jsonObject, final JsonObject jsonObject2, final int n, int i, final boolean b, final BrowseWebClientCache browseWebClientCache) {
        final ArrayList<BillboardDetails> list = (ArrayList<BillboardDetails>)new ArrayList<Billboard>();
        int n2 = 0;
        while (i >= n) {
            final String string = Integer.toString(i);
            int n3;
            if (jsonObject.has(string)) {
                final BillboardDetails billboardDetails = new BillboardDetails();
                if (jsonObject2 != null && jsonObject2.has(string)) {
                    billboardDetails.socialBadge = FalkorParseUtils.getPropertyObject(jsonObject2.getAsJsonObject(string), "socialBadge", SocialBadge.class);
                }
                FalkorParseUtils.fillPlayableVideo(jsonObject.getAsJsonObject(string), billboardDetails, b);
                billboardDetails.inQueue = browseWebClientCache.updateInQueueCacheRecord(billboardDetails.getId(), billboardDetails.inQueue);
                list.add(0, billboardDetails);
                n3 = 1;
            }
            else if ((n3 = n2) != 0) {
                Log.d("nf_service_browse_fetchbbvideosrequest", String.format("Adding sentinel at index %s", string));
                list.add(0, BrowseVideoSentinels.getUnavailableBBVideo());
                n3 = n2;
            }
            --i;
            n2 = n3;
        }
        return (List<Billboard>)list;
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery1, this.pqlQuery2, this.socialPqlQuery);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onBBVideosFetched(Collections.emptyList(), status);
        }
    }
    
    @Override
    protected void onSuccess(final List<Billboard> list) {
        if (this.responseCallback != null) {
            this.responseCallback.onBBVideosFetched(list, CommonStatus.OK);
        }
    }
    
    @Override
    protected List<Billboard> parseFalkorResponse(final String s) {
        if (Log.isLoggable("nf_service_browse_fetchbbvideosrequest", 2)) {
            Log.v("nf_service_browse_fetchbbvideosrequest", "String response to parse = " + s);
        }
        final JsonObject dataObj = FalkorParseUtils.getDataObj("nf_service_browse_fetchbbvideosrequest", s);
        if (FalkorParseUtils.isEmpty(dataObj)) {
            return new ArrayList<Billboard>();
        }
        try {
            final JsonObject asJsonObject = dataObj.getAsJsonObject("lists").getAsJsonObject(this.mLoMo.getId());
            return buildBBVideoList(asJsonObject.getAsJsonObject("videoEvidence"), asJsonObject.getAsJsonObject("socialEvidence"), this.fromVideo, this.toVideo, this.userConnectedToFacebook, this.browseCache);
        }
        catch (Exception ex) {
            Log.v("nf_service_browse_fetchbbvideosrequest", "String response to parse = " + s);
            throw new FalkorParseException("Does not contain required fields", ex);
        }
    }
}
