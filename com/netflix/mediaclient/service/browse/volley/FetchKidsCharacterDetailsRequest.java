// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.service.webclient.model.leafs.TrackableListSummary;
import com.netflix.mediaclient.service.webclient.model.branches.KidsCharacter;
import java.util.ArrayList;
import java.util.Arrays;
import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.service.webclient.model.branches.Episode;
import com.netflix.mediaclient.servicemgr.VideoType;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.google.gson.JsonObject;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.Video;
import java.util.List;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.KidsCharacterDetails;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class FetchKidsCharacterDetailsRequest extends FalcorVolleyWebClientRequest<KidsCharacterDetails>
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
    
    protected FetchKidsCharacterDetailsRequest(final Context context, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface, final String mCharacterId, final BrowseAgentCallback responseCallback) {
        super(context, configurationAgentInterface);
        this.MAX_GALLERY_VIDEOS = 100;
        this.mCharacterId = mCharacterId;
        this.responseCallback = responseCallback;
        this.pqlQuery1 = String.format("['characters', '%s', ['summary','detail']]", this.mCharacterId);
        this.pqlQuery2 = String.format("['characters', '%s', 'watchNext', ['summary', 'detail', 'bookmark']]", this.mCharacterId);
        this.pqlQuery3 = String.format("['characters', '%s', 'gallery','summary']", this.mCharacterId);
        this.pqlQuery4 = String.format("['characters', '%s', 'gallery', ['shows', 'movies'], {'to':%d}, 'summary']", this.mCharacterId, 100);
        if (Log.isLoggable("nf_kidscharacter", 2)) {
            Log.v("nf_kidscharacter", "PQL = " + this.pqlQuery1 + " " + this.pqlQuery2 + " " + this.pqlQuery3 + " " + this.pqlQuery4);
        }
    }
    
    private void collectGalleryVideos(final List<Video> list, final List<Video> list2, final List<Video> list3) {
        list3.addAll(list2);
        list3.addAll(list);
    }
    
    private void insertVideoInGallery(final com.netflix.mediaclient.service.webclient.model.branches.Video.Summary summary, final List<Video> list) {
        int n;
        for (n = 0; n < list.size() && summary.videoYear < list.get(n).videoYear; ++n) {}
        if (n < list.size()) {
            list.add(n, summary);
            return;
        }
        list.add(summary);
    }
    
    protected static void insertWatchNext(JsonObject asJsonObject, final com.netflix.mediaclient.service.webclient.model.KidsCharacterDetails kidsCharacterDetails) throws FalcorParseException, FalcorServerException {
        if (asJsonObject.has("watchNext")) {
            asJsonObject = asJsonObject.getAsJsonObject("watchNext");
            final VideoType typeFromPath = FalcorParseUtils.getTypeFromPath("nf_kidscharacter", asJsonObject);
            if (VideoType.EPISODE.equals(typeFromPath)) {
                kidsCharacterDetails.watchNextSummary = FalcorParseUtils.getPropertyObject(asJsonObject, "summary", com.netflix.mediaclient.service.webclient.model.branches.Video.Summary.class);
                kidsCharacterDetails.watchNextEpisodeDetail = FalcorParseUtils.getPropertyObject(asJsonObject, "detail", Episode.Detail.class);
                kidsCharacterDetails.watchNextBookmark = FalcorParseUtils.getPropertyObject(asJsonObject, "bookmark", com.netflix.mediaclient.service.webclient.model.branches.Video.Bookmark.class);
                kidsCharacterDetails.watchNextMovieDetail = null;
            }
            if (VideoType.MOVIE.equals(typeFromPath)) {
                kidsCharacterDetails.watchNextSummary = FalcorParseUtils.getPropertyObject(asJsonObject, "summary", com.netflix.mediaclient.service.webclient.model.branches.Video.Summary.class);
                kidsCharacterDetails.watchNextMovieDetail = FalcorParseUtils.getPropertyObject(asJsonObject, "detail", com.netflix.mediaclient.service.webclient.model.branches.Video.Detail.class);
                kidsCharacterDetails.watchNextBookmark = FalcorParseUtils.getPropertyObject(asJsonObject, "bookmark", com.netflix.mediaclient.service.webclient.model.branches.Video.Bookmark.class);
                kidsCharacterDetails.watchNextEpisodeDetail = null;
            }
        }
    }
    
    private void parseGalleryVideos(final JsonObject jsonObject, final List<Video> list) {
        if (jsonObject != null) {
            for (int i = 0; i <= 100; ++i) {
                final String string = Integer.toString(i);
                if (jsonObject.has(string)) {
                    this.insertVideoInGallery(FalcorParseUtils.getPropertyObject(jsonObject.getAsJsonObject(string), "summary", com.netflix.mediaclient.service.webclient.model.branches.Video.Summary.class), list);
                }
            }
        }
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery1, this.pqlQuery2, this.pqlQuery3, this.pqlQuery4);
    }
    
    @Override
    protected void onFailure(final int n) {
        if (this.responseCallback != null) {
            this.responseCallback.onKidsCharacterDetailsFetched(null, FetchKidsCharacterDetailsRequest.DATA_CHANGED, n);
        }
    }
    
    @Override
    protected void onSuccess(final KidsCharacterDetails kidsCharacterDetails) {
        if (this.responseCallback != null) {
            this.responseCallback.onKidsCharacterDetailsFetched(kidsCharacterDetails, FetchKidsCharacterDetailsRequest.DATA_CHANGED, 0);
        }
    }
    
    @Override
    protected KidsCharacterDetails parseFalcorResponse(String asJsonObject) throws FalcorParseException, FalcorServerException {
        final com.netflix.mediaclient.service.webclient.model.KidsCharacterDetails kidsCharacterDetails = new com.netflix.mediaclient.service.webclient.model.KidsCharacterDetails();
        final ArrayList<Video> galleryVideos = new ArrayList<Video>();
        final ArrayList<Video> list = new ArrayList<Video>();
        final ArrayList<Video> list2 = new ArrayList<Video>();
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_kidscharacter", asJsonObject);
        if (FalcorParseUtils.isEmpty(dataObj)) {
            throw new FalcorParseException("KidsCharacterDetails empty!!!");
        }
        try {
            final JsonObject asJsonObject2 = dataObj.getAsJsonObject("characters").getAsJsonObject(this.mCharacterId);
            kidsCharacterDetails.kidsSummary = FalcorParseUtils.getPropertyObject(asJsonObject2, "summary", KidsCharacter.KidsSummary.class);
            kidsCharacterDetails.kidsDetail = FalcorParseUtils.getPropertyObject(asJsonObject2, "detail", KidsCharacter.KidsDetail.class);
            insertWatchNext(asJsonObject2, kidsCharacterDetails);
            if (asJsonObject2.has("gallery")) {
                asJsonObject = (String)asJsonObject2.getAsJsonObject("gallery");
                if (((JsonObject)asJsonObject).has("shows")) {
                    this.parseGalleryVideos(((JsonObject)asJsonObject).getAsJsonObject("shows"), list2);
                }
                if (((JsonObject)asJsonObject).has("movies")) {
                    this.parseGalleryVideos(((JsonObject)asJsonObject).getAsJsonObject("movies"), list);
                }
                this.collectGalleryVideos(list, list2, galleryVideos);
                kidsCharacterDetails.galleryListSummary = FalcorParseUtils.getPropertyObject((JsonObject)asJsonObject, "summary", TrackableListSummary.class);
            }
            kidsCharacterDetails.galleryVideos = galleryVideos;
            return kidsCharacterDetails;
        }
        catch (Exception ex) {
            Log.v("nf_kidscharacter", "String response to parse = " + asJsonObject);
            throw new FalcorParseException("response missing expected json objects", ex);
        }
    }
}
