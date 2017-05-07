// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.service.webclient.model.leafs.TrackableListSummary;
import com.netflix.mediaclient.service.webclient.model.branches.KidsCharacter$KidsDetail;
import com.netflix.mediaclient.service.webclient.model.branches.KidsCharacter$KidsSummary;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseException;
import java.util.ArrayList;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import com.netflix.mediaclient.service.webclient.model.branches.Video$Detail;
import com.netflix.mediaclient.service.webclient.model.branches.Episode$Detail;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.service.webclient.model.branches.Video$Bookmark;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.model.branches.Video$Summary;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.model.details.KidsCharacterDetails;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class FetchKidsCharacterDetailsRequest extends FalkorVolleyWebClientRequest<KidsCharacterDetails>
{
    private static final Boolean DATA_CHANGED;
    private static final String FIELD_CHARACTER = "characters";
    private static final String TAG = "nf_kidscharacter";
    private final int MAX_GALLERY_VIDEOS;
    private final String mCharacterId;
    private final String pqlQuery1;
    private final String pqlQuery2;
    private final String pqlQuery3;
    private final String pqlQuery4;
    private final BrowseAgentCallback responseCallback;
    
    static {
        DATA_CHANGED = true;
    }
    
    protected FetchKidsCharacterDetailsRequest(final Context context, final String mCharacterId, final BrowseAgentCallback responseCallback) {
        super(context);
        this.MAX_GALLERY_VIDEOS = 100;
        this.mCharacterId = mCharacterId;
        this.responseCallback = responseCallback;
        this.pqlQuery1 = String.format("['characters', '%s', ['summary','detail']]", this.mCharacterId);
        this.pqlQuery2 = String.format("['characters', '%s', 'watchNext', ['summary', 'detail', 'bookmark']]", this.mCharacterId);
        this.pqlQuery3 = String.format("['characters', '%s', 'videoGallery','summary']", this.mCharacterId);
        this.pqlQuery4 = String.format("['characters', '%s', 'videoGallery', ['shows', 'movies'], {'to':%d}, 'summary']", this.mCharacterId, 100);
        if (Log.isLoggable("nf_kidscharacter", 2)) {
            Log.v("nf_kidscharacter", "PQL = " + this.pqlQuery1 + " " + this.pqlQuery2 + " " + this.pqlQuery3 + " " + this.pqlQuery4);
        }
    }
    
    private void collectGalleryVideos(final List<Video> list, final List<Video> list2, final List<Video> list3) {
        list3.addAll(list2);
        list3.addAll(list);
    }
    
    private void insertVideoInGallery(final Video$Summary video$Summary, final List<Video> list) {
        int n;
        for (n = 0; n < list.size() && video$Summary.videoYear < list.get(n).videoYear; ++n) {}
        if (n < list.size()) {
            list.add(n, video$Summary);
            return;
        }
        list.add(video$Summary);
    }
    
    protected static void insertWatchNext(JsonObject asJsonObject, final com.netflix.mediaclient.service.webclient.model.KidsCharacterDetails kidsCharacterDetails) {
        if (asJsonObject.has("watchNext")) {
            asJsonObject = asJsonObject.getAsJsonObject("watchNext");
            final VideoType typeFromPath = FalkorParseUtils.getTypeFromPath("nf_kidscharacter", asJsonObject);
            kidsCharacterDetails.watchNextSummary = FalkorParseUtils.getPropertyObject(asJsonObject, "summary", Video$Summary.class);
            kidsCharacterDetails.watchNextBookmark = FalkorParseUtils.getPropertyObject(asJsonObject, "bookmark", Video$Bookmark.class);
            if (VideoType.EPISODE.equals(typeFromPath)) {
                kidsCharacterDetails.watchNextEpisodeDetail = FalkorParseUtils.getPropertyObject(asJsonObject, "detail", Episode$Detail.class);
                kidsCharacterDetails.watchNextMovieDetail = null;
            }
            else {
                if (VideoType.MOVIE.equals(typeFromPath)) {
                    kidsCharacterDetails.watchNextEpisodeDetail = null;
                    kidsCharacterDetails.watchNextMovieDetail = FalkorParseUtils.getPropertyObject(asJsonObject, "detail", Video$Detail.class);
                    return;
                }
                Log.w("nf_kidscharacter", "Unknown watch next video type: " + typeFromPath);
            }
        }
    }
    
    private void parseGalleryVideos(final JsonObject jsonObject, final List<Video> list) {
        if (jsonObject != null) {
            for (int i = 0; i <= 100; ++i) {
                final String string = Integer.toString(i);
                if (jsonObject.has(string)) {
                    this.insertVideoInGallery(FalkorParseUtils.getPropertyObject(jsonObject.getAsJsonObject(string), "summary", Video$Summary.class), list);
                }
            }
        }
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery1, this.pqlQuery2, this.pqlQuery3, this.pqlQuery4);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onKidsCharacterDetailsFetched(null, FetchKidsCharacterDetailsRequest.DATA_CHANGED, status);
        }
    }
    
    @Override
    protected void onSuccess(final KidsCharacterDetails kidsCharacterDetails) {
        if (this.responseCallback != null) {
            this.responseCallback.onKidsCharacterDetailsFetched(kidsCharacterDetails, FetchKidsCharacterDetailsRequest.DATA_CHANGED, CommonStatus.OK);
        }
    }
    
    @Override
    protected KidsCharacterDetails parseFalkorResponse(String asJsonObject) {
        final com.netflix.mediaclient.service.webclient.model.KidsCharacterDetails kidsCharacterDetails = new com.netflix.mediaclient.service.webclient.model.KidsCharacterDetails();
        final ArrayList<Video> galleryVideos = new ArrayList<Video>();
        final ArrayList<Video> list = new ArrayList<Video>();
        final ArrayList<Video> list2 = new ArrayList<Video>();
        final JsonObject dataObj = FalkorParseUtils.getDataObj("nf_kidscharacter", asJsonObject);
        if (FalkorParseUtils.isEmpty(dataObj)) {
            throw new FalkorParseException("KidsCharacterDetails empty!!!");
        }
        try {
            final JsonObject asJsonObject2 = dataObj.getAsJsonObject("characters").getAsJsonObject(this.mCharacterId);
            kidsCharacterDetails.kidsSummary = FalkorParseUtils.getPropertyObject(asJsonObject2, "summary", KidsCharacter$KidsSummary.class);
            kidsCharacterDetails.kidsDetail = FalkorParseUtils.getPropertyObject(asJsonObject2, "detail", KidsCharacter$KidsDetail.class);
            insertWatchNext(asJsonObject2, kidsCharacterDetails);
            if (asJsonObject2.has("videoGallery")) {
                asJsonObject = (String)asJsonObject2.getAsJsonObject("videoGallery");
                if (((JsonObject)asJsonObject).has("shows")) {
                    this.parseGalleryVideos(((JsonObject)asJsonObject).getAsJsonObject("shows"), list2);
                }
                if (((JsonObject)asJsonObject).has("movies")) {
                    this.parseGalleryVideos(((JsonObject)asJsonObject).getAsJsonObject("movies"), list);
                }
                this.collectGalleryVideos(list, list2, galleryVideos);
                kidsCharacterDetails.galleryListSummary = FalkorParseUtils.getPropertyObject((JsonObject)asJsonObject, "summary", TrackableListSummary.class);
            }
            kidsCharacterDetails.galleryVideos = galleryVideos;
            return kidsCharacterDetails;
        }
        catch (Exception ex) {
            Log.v("nf_kidscharacter", "String response to parse = " + asJsonObject);
            throw new FalkorParseException("response missing expected json objects", ex);
        }
    }
}
