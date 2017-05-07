// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.model.leafs.SocialEvidence;
import com.netflix.mediaclient.service.webclient.model.branches.Episode;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.service.webclient.model.branches.Video;
import java.util.ArrayList;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.android.app.CommonStatus;
import java.util.Collections;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.model.details.PostPlayVideo;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class FetchPostPlayVideosRequest extends FalcorVolleyWebClientRequest<List<PostPlayVideo>>
{
    public static final String FIELD_POSTPLAY = "postplay";
    public static final String FIELD_POSTPLAY_CONTEXT = "postplayContext";
    private static final String TAG = "nf_postplay";
    private final Integer MAX_POSTPLAY_RECOS;
    private final String currentPlayId;
    private final String pqlQuery1;
    private final String pqlQuery2;
    private final String pqlQuery3;
    private final BrowseAgentCallback responseCallback;
    private final boolean userConnectedToFacebook;
    
    protected FetchPostPlayVideosRequest(final Context context, final String currentPlayId, final boolean userConnectedToFacebook, final BrowseAgentCallback responseCallback) {
        super(context);
        this.MAX_POSTPLAY_RECOS = 3;
        this.responseCallback = responseCallback;
        this.currentPlayId = currentPlayId;
        this.userConnectedToFacebook = userConnectedToFacebook;
        this.pqlQuery1 = String.format("['videos', '%s', 'postplay', {'to':%d}, 'postplayContext']", this.currentPlayId, this.MAX_POSTPLAY_RECOS);
        this.pqlQuery2 = String.format("['videos', '%s', 'postplay', {'to':%d}, ['summary','detail', 'rating', 'inQueue', 'bookmark', 'socialEvidence']]", this.currentPlayId, this.MAX_POSTPLAY_RECOS);
        this.pqlQuery3 = String.format("['videos', '%s', 'postplay', {'to':%d}, 'episodes', 'current', ['detail', 'bookmark']]", this.currentPlayId, this.MAX_POSTPLAY_RECOS);
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery1, this.pqlQuery2, this.pqlQuery3);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onPostPlayVideosFetched(Collections.emptyList(), status);
        }
    }
    
    @Override
    protected void onSuccess(final List<PostPlayVideo> list) {
        if (this.responseCallback != null) {
            this.responseCallback.onPostPlayVideosFetched(list, CommonStatus.OK);
        }
    }
    
    @Override
    protected List<PostPlayVideo> parseFalcorResponse(final String s) throws FalcorParseException, FalcorServerException {
        if (Log.isLoggable("nf_postplay", 2)) {
            Log.v("nf_postplay", "String response to parse = " + s);
        }
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_postplay", s);
        List<E> list;
        if (FalcorParseUtils.isEmpty(dataObj)) {
            list = (List<E>)new ArrayList<PostPlayVideo>();
        }
        else {
            while (true) {
                final ArrayList list2 = new ArrayList<com.netflix.mediaclient.service.webclient.model.PostPlayVideo>();
                while (true) {
                    int intValue;
                    try {
                        final JsonObject asJsonObject = dataObj.getAsJsonObject().getAsJsonObject("videos").getAsJsonObject(this.currentPlayId).getAsJsonObject("postplay");
                        intValue = this.MAX_POSTPLAY_RECOS;
                        list = list2;
                        if (intValue < 0) {
                            break;
                        }
                        final String string = Integer.toString(intValue);
                        if (asJsonObject.has(string)) {
                            final com.netflix.mediaclient.service.webclient.model.PostPlayVideo postPlayVideo = new com.netflix.mediaclient.service.webclient.model.PostPlayVideo();
                            final JsonObject asJsonObject2 = asJsonObject.getAsJsonObject(string);
                            postPlayVideo.summary = FalcorParseUtils.getPropertyObject(asJsonObject2, "summary", Video.Summary.class);
                            VideoType videoType;
                            if (postPlayVideo.summary != null) {
                                videoType = postPlayVideo.summary.getType();
                            }
                            else {
                                videoType = VideoType.UNKNOWN;
                            }
                            if (VideoType.MOVIE.equals(videoType) || VideoType.SHOW.equals(videoType)) {
                                postPlayVideo.detail = FalcorParseUtils.getPropertyObject(asJsonObject2, "detail", Video.Detail.class);
                            }
                            else if (VideoType.EPISODE.equals(videoType)) {
                                postPlayVideo.episodeDetail = FalcorParseUtils.getPropertyObject(asJsonObject2, "detail", Episode.Detail.class);
                            }
                            postPlayVideo.rating = FalcorParseUtils.getPropertyObject(asJsonObject2, "rating", Video.Rating.class);
                            postPlayVideo.inQueue = FalcorParseUtils.getPropertyObject(asJsonObject2, "inQueue", Video.InQueue.class);
                            postPlayVideo.bookmark = FalcorParseUtils.getPropertyObject(asJsonObject2, "bookmark", Video.Bookmark.class);
                            postPlayVideo.socialEvidence = FalcorParseUtils.getPropertyObject(asJsonObject2, "socialEvidence", SocialEvidence.class);
                            postPlayVideo.userConnectedToFacebook = this.userConnectedToFacebook;
                            postPlayVideo.postplayContext = FalcorParseUtils.getPropertyObject(asJsonObject2, "postplayContext", com.netflix.mediaclient.service.webclient.model.PostPlayVideo.PostPlayContext.class);
                            if (VideoType.SHOW.equals(videoType) && asJsonObject2.has("episodes")) {
                                final JsonObject asJsonObject3 = asJsonObject2.getAsJsonObject("episodes");
                                if (asJsonObject3.has("current")) {
                                    final JsonObject asJsonObject4 = asJsonObject3.getAsJsonObject("current");
                                    postPlayVideo.episodeDetail = FalcorParseUtils.getPropertyObject(asJsonObject4, "detail", Episode.Detail.class);
                                    postPlayVideo.bookmark = FalcorParseUtils.getPropertyObject(asJsonObject4, "bookmark", Video.Bookmark.class);
                                }
                            }
                            list2.add(0, postPlayVideo);
                        }
                    }
                    catch (Exception ex) {
                        Log.v("nf_postplay", "String response to parse = " + s);
                        throw new FalcorParseException("response missing expected json objects", ex);
                    }
                    --intValue;
                    continue;
                }
            }
        }
        return (List<PostPlayVideo>)list;
    }
}
