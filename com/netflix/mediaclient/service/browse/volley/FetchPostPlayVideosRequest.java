// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseException;
import com.netflix.mediaclient.service.webclient.model.PostPlayVideo$PostPlayContext;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialEvidence;
import com.netflix.mediaclient.service.webclient.model.branches.Video$Bookmark;
import com.netflix.mediaclient.service.webclient.model.branches.Video$InQueue;
import com.netflix.mediaclient.service.webclient.model.branches.Video$UserRating;
import com.netflix.mediaclient.service.webclient.model.branches.Episode$Detail;
import com.netflix.mediaclient.service.webclient.model.branches.Video$Detail;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.service.webclient.model.branches.Video$Summary;
import com.netflix.mediaclient.servicemgr.model.details.PostPlayContext;
import com.netflix.mediaclient.service.webclient.model.PostPlayVideo;
import java.util.ArrayList;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.webclient.model.PostPlayVideosProvider;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class FetchPostPlayVideosRequest extends FalkorVolleyWebClientRequest<PostPlayVideosProvider>
{
    private static final String FIELD_CURRENT = "current";
    private static final String FIELD_EPISODES = "episodes";
    private static final String FIELD_POSTPLAY = "postplay";
    private static final String FIELD_POSTPLAY_CONTEXT = "postplayContext";
    private static final String FIELD_POSTPLAY_VIDEO_REF = "videoRef";
    private static final String FIELD_VIDEOS = "videos";
    private static final String TAG = "nf_postplay";
    private final Integer MAX_POSTPLAY_RECOS;
    private final String currentPlayId;
    private final String pqlQuery1;
    private final String pqlQuery2;
    private final String pqlQuery3;
    private final BrowseAgentCallback responseCallback;
    private final boolean userConnectedToFacebook;
    
    public FetchPostPlayVideosRequest(final Context context, final String currentPlayId, final boolean userConnectedToFacebook, final BrowseAgentCallback responseCallback) {
        super(context);
        this.MAX_POSTPLAY_RECOS = 3;
        this.responseCallback = responseCallback;
        this.currentPlayId = currentPlayId;
        this.userConnectedToFacebook = userConnectedToFacebook;
        this.pqlQuery1 = String.format("['videos', '%s', 'postplay', {'to':%d}, 'postplayContext']", this.currentPlayId, this.MAX_POSTPLAY_RECOS);
        this.pqlQuery2 = String.format("['videos', '%s', 'postplay', {'to':%d}, 'videoRef', ['summary','detail', 'rating', 'inQueue', 'bookmark', 'socialEvidence']]", this.currentPlayId, this.MAX_POSTPLAY_RECOS);
        this.pqlQuery3 = String.format("['videos', '%s', 'postplay', {'to':%d}, 'videoRef', 'episodes', 'current', ['detail', 'bookmark']]", this.currentPlayId, this.MAX_POSTPLAY_RECOS);
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery1, this.pqlQuery2, this.pqlQuery3);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onPostPlayVideosFetched(null, status);
        }
    }
    
    @Override
    protected void onSuccess(final PostPlayVideosProvider postPlayVideosProvider) {
        if (this.responseCallback != null) {
            this.responseCallback.onPostPlayVideosFetched(postPlayVideosProvider, CommonStatus.OK);
        }
    }
    
    @Override
    protected PostPlayVideosProvider parseFalkorResponse(final String s) {
        if (Log.isLoggable("nf_postplay", 2)) {
            Log.v("nf_postplay", "String response to parse = " + s);
        }
        final JsonObject dataObj = FalkorParseUtils.getDataObj("nf_postplay", s);
        if (FalkorParseUtils.isEmpty(dataObj)) {
            return null;
        }
        ArrayList<PostPlayVideo> list;
        ArrayList<PostPlayContext> list2;
        while (true) {
            list = new ArrayList<PostPlayVideo>();
            list2 = new ArrayList<PostPlayContext>();
            while (true) {
                int n = 0;
                Label_0520: {
                    try {
                        final JsonObject asJsonObject = dataObj.getAsJsonObject().getAsJsonObject("videos").getAsJsonObject(this.currentPlayId).getAsJsonObject("postplay");
                        n = this.MAX_POSTPLAY_RECOS - 1;
                        if (n >= 0) {
                            final String string = Integer.toString(n);
                            if (asJsonObject.has(string)) {
                                final JsonObject asJsonObject2 = asJsonObject.getAsJsonObject(string);
                                final JsonObject asJsonObject3 = asJsonObject2.getAsJsonObject("videoRef");
                                if (Log.isLoggable("nf_postplay", 2)) {
                                    Log.v("nf_postplay", "Creating PostPlayVideo, index: " + string);
                                }
                                final PostPlayVideo postPlayVideo = new PostPlayVideo();
                                postPlayVideo.summary = FalkorParseUtils.getPropertyObject(asJsonObject3, "summary", Video$Summary.class);
                                VideoType videoType;
                                if (postPlayVideo.summary != null) {
                                    videoType = postPlayVideo.summary.getType();
                                }
                                else {
                                    videoType = VideoType.UNKNOWN;
                                }
                                if (VideoType.MOVIE.equals(videoType) || VideoType.SHOW.equals(videoType)) {
                                    postPlayVideo.detail = FalkorParseUtils.getPropertyObject(asJsonObject3, "detail", Video$Detail.class);
                                }
                                else if (VideoType.EPISODE.equals(videoType)) {
                                    postPlayVideo.episodeDetail = FalkorParseUtils.getPropertyObject(asJsonObject3, "detail", Episode$Detail.class);
                                }
                                postPlayVideo.rating = FalkorParseUtils.getPropertyObject(asJsonObject3, "rating", Video$UserRating.class);
                                postPlayVideo.inQueue = FalkorParseUtils.getPropertyObject(asJsonObject3, "inQueue", Video$InQueue.class);
                                postPlayVideo.bookmark = FalkorParseUtils.getPropertyObject(asJsonObject3, "bookmark", Video$Bookmark.class);
                                postPlayVideo.socialEvidence = FalkorParseUtils.getPropertyObject(asJsonObject3, "socialEvidence", SocialEvidence.class);
                                postPlayVideo.userConnectedToFacebook = this.userConnectedToFacebook;
                                if (VideoType.SHOW.equals(videoType) && asJsonObject3.has("episodes")) {
                                    final JsonObject asJsonObject4 = asJsonObject3.getAsJsonObject("episodes");
                                    if (asJsonObject4.has("current")) {
                                        final JsonObject asJsonObject5 = asJsonObject4.getAsJsonObject("current");
                                        postPlayVideo.episodeDetail = FalkorParseUtils.getPropertyObject(asJsonObject5, "detail", Episode$Detail.class);
                                        postPlayVideo.bookmark = FalkorParseUtils.getPropertyObject(asJsonObject5, "bookmark", Video$Bookmark.class);
                                    }
                                }
                                list.add(0, postPlayVideo);
                                list2.add(0, FalkorParseUtils.getPropertyObject(asJsonObject2, "postplayContext", PostPlayVideo$PostPlayContext.class));
                            }
                            break Label_0520;
                        }
                    }
                    catch (Exception ex) {
                        Log.d("nf_postplay", "String response to parse = " + s);
                        throw new FalkorParseException("response missing expected json objects", ex);
                    }
                    break;
                }
                --n;
                continue;
            }
        }
        return new PostPlayVideosProvider((List<com.netflix.mediaclient.servicemgr.model.details.PostPlayVideo>)list, list2);
    }
}
