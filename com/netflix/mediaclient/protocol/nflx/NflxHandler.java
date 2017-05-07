// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.nflx;

import com.netflix.mediaclient.servicemgr.MovieDetails;
import com.netflix.mediaclient.servicemgr.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.LoLoMo;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;
import com.netflix.mediaclient.util.DataUtil;
import android.net.Uri;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.service.pushnotification.UserFeedbackOnReceivedPushNotification;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.ui.Asset;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import java.util.HashMap;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.util.MdxUtils;
import com.netflix.mediaclient.ui.search.SearchActivity;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import java.util.Locale;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.content.Context;
import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import com.netflix.mediaclient.servicemgr.VideoType;
import com.netflix.mediaclient.util.WebApiUtils;
import java.security.InvalidParameterException;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import com.netflix.mediaclient.Log;
import java.util.Map;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.Playable;
import com.netflix.mediaclient.servicemgr.IMdx;
import android.app.Activity;
import com.netflix.mediaclient.ui.common.PlayContext;

public class NflxHandler
{
    public static final String INTENT_RESULT = "com.netflix.mediaclient.intent.action.NFLX_HANDLER_RESULT";
    private static final String JSON_CATALOG_TITLE = "catalog_title";
    private static final String JSON_ID = "id";
    private static final String JSON_TITLE_SERIES = "title_series";
    private static final String NFLX_ACTION_HOME = "home";
    private static final String NFLX_ACTION_PLAY = "play";
    private static final String NFLX_ACTION_SEARCH = "search";
    private static final String NFLX_ACTION_SYNC = "sync";
    private static final String NFLX_ACTION_VIEW_DETAILS = "view_details";
    private static final String NFLX_ACTION_VIEW_GENRE = "g";
    private static final String NFLX_BROWSE_PATH = "/browse";
    private static final String NFLX_HOST = "www.netflix.com";
    private static final String NFLX_PARAM_ACTION = "action";
    private static final String NFLX_PARAM_EPISODE_ID = "episodeid";
    private static final String NFLX_PARAM_EPISODE_ID_URI_PATH_KEY = "programs/";
    private static final String NFLX_PARAM_GENRE_ID = "genreid";
    private static final String NFLX_PARAM_MOVIE_ID = "movieid";
    private static final String NFLX_PARAM_MOVIE_ID_MOVIE_URI_PATH_KEY = "movies/";
    private static final String NFLX_PARAM_MOVIE_ID_SERIES_URI_PATH_KEY = "series/";
    private static final String NFLX_PARAM_QUERY = "query";
    private static final String NFLX_PARAM_TARGET_ID = "targetid";
    private static final String NFLX_PARAM_TARGET_URL = "target_url";
    private static final String NFLX_PARAM_TRACKID = "trkid";
    private static final String NFLX_SCHEME = "nflx";
    public static final String PARAM_STATUS = "status";
    private static final String TAG = "NflxHandler";
    private static final String URN_SUFFIX = "::urn";
    private static final String UUID_PREFIX = "uuid:";
    private static final String WEBAPI_EXPAND_SERVICE = "http://api.netflix.com/catalog/tiny/expand/";
    
    private String extractId(final String s) {
        final int lastIndex = s.lastIndexOf("/");
        if (lastIndex <= 0) {
            return null;
        }
        return s.substring(lastIndex + 1);
    }
    
    private String extractJustUuid(final String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        int n;
        if (s.contains("uuid:")) {
            n = s.indexOf("uuid:") + "uuid:".length();
        }
        else {
            n = 0;
        }
        int n2;
        if (s.contains("::urn")) {
            n2 = s.indexOf("::urn");
        }
        else {
            n2 = s.length();
        }
        return s.substring(n, n2);
    }
    
    private String getEpisodeId(Map<String, String> decode) {
        decode = ((Map<String, String>)decode).get("episodeid");
        if (StringUtils.isEmpty(decode)) {
            Log.v("NflxHandler", "episode id uri doesn't exist in params map");
            return null;
        }
        try {
            decode = URLDecoder.decode(decode, "utf-8");
            return this.getVideoIdFromUri(decode, "programs/");
        }
        catch (UnsupportedEncodingException ex) {
            Log.e("NflxHandler", "Failed to decode URL", ex);
            return this.getVideoIdFromUri(decode, "programs/");
        }
    }
    
    private static String getExpandUrl(String string) {
        if (Log.isLoggable("NflxHandler", 3)) {
            Log.d("NflxHandler", "Gets expanded tiny URL " + string);
        }
        if (StringUtils.isEmpty(string)) {
            throw new IllegalArgumentException("Tiny URL can not be empty!");
        }
        final String[] split = string.split("/");
        if (split == null || split.length < 2) {
            throw new IllegalArgumentException("Movie ID not found in tiny URL " + string);
        }
        string = "http://api.netflix.com/catalog/tiny/expand/" + split[split.length - 1] + "?output=json";
        if (Log.isLoggable("NflxHandler", 3)) {
            Log.d("NflxHandler", "Expanded tiny URL: " + string);
        }
        return string;
    }
    
    private PlayContext getPlayContext(final String s) {
        if (StringUtils.isNotEmpty(s)) {
            try {
                return new PlayContextImp(null, Integer.parseInt(s), 0, 0);
            }
            catch (Exception ex) {
                Log.e("NflxHandler", String.format("Error parsing trackId %s", s));
            }
        }
        return PlayContext.NFLX_MDX_CONTEXT;
    }
    
    private String getSource(final Map<String, String> map) {
        String s;
        if (StringUtils.isEmpty(s = map.get("action"))) {
            Log.w("NflxHandler", "Source is empty!");
            s = "uknown";
        }
        return s;
    }
    
    private String getVideoIdFromUri(String s, final String s2) {
        final int lastIndex = s.lastIndexOf(s2);
        if (lastIndex > 0) {
            final String substring = s.substring(lastIndex + s2.length());
            if (Log.isLoggable("NflxHandler", 2)) {
                Log.v("NflxHandler", "Found video id: " + substring + " for path key: " + s2);
            }
            if (StringUtils.isEmpty(substring)) {
                Log.v("NflxHandler", "empty video id");
                s = null;
            }
            else {
                final int index = substring.indexOf("/");
                s = substring;
                if (index > 0) {
                    return substring.substring(index + 1);
                }
            }
            return s;
        }
        return null;
    }
    
    private VideoInfo getVideoInfo(final NetflixActivity netflixActivity, final IMdx mdx, final String s, final Map<String, String> map) {
        final String s2 = map.get("movieid");
        final String episodeId = this.getEpisodeId(map);
        if (StringUtils.isNotEmpty(episodeId)) {
            Log.d("NflxHandler", "Episode ID found, go to SDP for that episode");
            final VideoInfo videoInfoFromMovieId = this.getVideoInfoFromMovieId(s2);
            if (videoInfoFromMovieId != null) {
                final VideoInfo fromEpisode = VideoInfo.createFromEpisode(videoInfoFromMovieId.getCatalogId(), episodeId);
                if (fromEpisode != null) {
                    if (Log.isLoggable("NflxHandler", 3)) {
                        Log.d("NflxHandler", "VideoInfo object found for episode id " + episodeId + ". Default to SDP " + fromEpisode);
                    }
                    return fromEpisode;
                }
                if (Log.isLoggable("NflxHandler", 5)) {
                    Log.w("NflxHandler", "VideoInfo object not returned for episode id " + episodeId + ". Default to show");
                }
            }
            else if (Log.isLoggable("NflxHandler", 5)) {
                Log.e("NflxHandler", "This should NOT happen! VideoInfo object not returned for show id " + s2 + ". Default to regular workflow");
            }
        }
        if (StringUtils.isEmpty(s2)) {
            return this.getVideoInfoFromTinyUrl(netflixActivity, mdx, s, map);
        }
        return this.getVideoInfoFromMovieId(s2);
    }
    
    private VideoInfo getVideoInfoFromMovieId(String s) {
        while (true) {
            try {
                s = URLDecoder.decode(s, "utf-8");
                if (Log.isLoggable("NflxHandler", 3)) {
                    Log.d("NflxHandler", "movie id uri exist in params map");
                    Log.d("NflxHandler", "Gets video info from " + s);
                }
                final String videoIdFromUri = this.getVideoIdFromUri(s, "series/");
                if (videoIdFromUri != null) {
                    return VideoInfo.createFromShow(videoIdFromUri);
                }
            }
            catch (UnsupportedEncodingException ex) {
                Log.e("NflxHandler", "Failed to decode URL", ex);
                continue;
            }
            break;
        }
        s = this.getVideoIdFromUri(s, "movies/");
        if (s != null) {
            return VideoInfo.createFromMovie(s);
        }
        Log.e("NflxHandler", "Unable to get video id! This should NOT happen!");
        return null;
    }
    
    private VideoInfo getVideoInfoFromTinyUrl(final NetflixActivity netflixActivity, final IMdx mdx, final String s, final Map<String, String> map) {
        final String s2 = map.get("target_url");
        if (StringUtils.isEmpty(s2)) {
            Log.v("NflxHandler", "movie id uri and tiny url both doesn't exist in params map");
            return null;
        }
        Log.v("NflxHandler", "movie id uri doesn't exist in params map, but tiny url does. Resolve it");
        new BackgroundTask().execute(new Runnable() {
            @Override
            public void run() {
                Log.d("NflxHandler", "Resolving tiny URL in background");
                NflxHandler.this.handleTinyUrl(netflixActivity, mdx, s, s2, NflxHandler.this.extractJustUuid(map.get("targetid")), map.get("trkid"));
            }
        });
        return VideoInfo.DELAYED;
    }
    
    private Response handleEpisodeFromTinyUrl(final NetflixActivity netflixActivity, final IMdx mdx, final String s, final JSONObject jsonObject, final String s2, final String s3) throws JSONException, InvalidParameterException {
        final Response handling = Response.HANDLING;
        if ("play".equalsIgnoreCase(s)) {
            this.handleEpisodePlayFromTinyUrl(netflixActivity, mdx, s, jsonObject, s2, s3);
            return Response.HANDLING_WITH_DELAY;
        }
        if ("view_details".equalsIgnoreCase(s)) {
            this.handleEpisodeVideoDetailFromTinyUrl(netflixActivity, s, jsonObject, s3);
            return handling;
        }
        if (Log.isLoggable("NflxHandler", 6)) {
            Log.e("NflxHandler", "Not supported action for tiny URL, default to LOLOMO: " + s);
        }
        this.handleHomeAction(netflixActivity);
        return handling;
    }
    
    private void handleEpisodePlayFromTinyUrl(final NetflixActivity netflixActivity, final IMdx mdx, final String s, final JSONObject jsonObject, final String s2, final String s3) throws JSONException, InvalidParameterException {
        if (!jsonObject.has("id")) {
            Log.e("NflxHandler", "It should be episode JSON, failed to get title series id! Default to LOLOMO!");
            this.handleHomeAction(netflixActivity);
            return;
        }
        this.playVideo(netflixActivity, mdx, String.valueOf(WebApiUtils.extractIsd(null, jsonObject.getString("id")).episodeId), VideoType.EPISODE, s2, s3);
    }
    
    private void handleEpisodeVideoDetailFromTinyUrl(final Activity activity, String optString, JSONObject optJSONObject, final String s) throws JSONException, InvalidParameterException {
        optString = optJSONObject.optString("id");
        if (StringUtils.isEmpty(optString)) {
            Log.e("NflxHandler", "It should be episode JSON, failed to get id! Default to LOLOMO!");
            this.handleHomeAction(activity);
            return;
        }
        optJSONObject = optJSONObject.optJSONObject("title_series");
        if (optJSONObject == null) {
            Log.e("NflxHandler", "It should be episode JSON, failed to get title series! Default to LOLOMO!");
            this.handleHomeAction(activity);
            return;
        }
        if (!optJSONObject.has("id")) {
            Log.e("NflxHandler", "It should be episode JSON, failed to get title series id! Default to LOLOMO!");
            this.handleHomeAction(activity);
            return;
        }
        final String string = optJSONObject.getString("id");
        if (StringUtils.isEmpty(string)) {
            Log.e("NflxHandler", "It should be episode, failed to get showIdUri! Default to LOLOMO!");
            this.handleHomeAction(activity);
            return;
        }
        final String id = this.extractId(string);
        if (StringUtils.isEmpty(id)) {
            Log.e("NflxHandler", "It should be episode, failed to get show id from url! Default to LOLOMO! Url was: " + string);
            this.handleHomeAction(activity);
            return;
        }
        final String id2 = this.extractId(optString);
        if (StringUtils.isEmpty(id2)) {
            Log.e("NflxHandler", "It should be episode, failed to get episode id from url! Default to show SDP! Url was: " + optString);
            DetailsActivity.show(activity, VideoType.SHOW, id, this.getPlayContext(s));
            return;
        }
        if (Log.isLoggable("NflxHandler", 3)) {
            Log.d("NflxHandler", "Handling episode from tiny URL. Expanded to: episodeId " + id2 + " and showId " + id);
            Log.d("NflxHandler", "Expanded from: episodeIdUri " + optString + " and shodIdUri " + string);
            Log.v("NflxHandler", "Showing SDP");
        }
        DetailsActivity.showEpisodeDetails(activity, id, id2, this.getPlayContext(s));
    }
    
    private Response handleHomeAction(final Activity activity) {
        Log.v("NflxHandler", "Starting home activity");
        activity.startActivity(HomeActivity.createStartIntent((Context)activity));
        activity.overridePendingTransition(0, 0);
        return Response.HANDLING;
    }
    
    private Response handleMovieFromTinyUrl(final NetflixActivity netflixActivity, final IMdx mdx, final String s, final JSONObject jsonObject, final String s2, final String s3) throws JSONException {
        if (!jsonObject.has("id")) {
            Log.e("NflxHandler", "It should be movie JSON, failed to get ID URL! Default to LOLOMO!");
            this.handleHomeAction(netflixActivity);
            return Response.HANDLING;
        }
        final String string = jsonObject.getString("id");
        final String videoIdFromUri = this.getVideoIdFromUri(string, "movies/");
        if (Log.isLoggable("NflxHandler", 3)) {
            Log.d("NflxHandler", "Handling movie from tiny URL. Expanded to: " + string + ". Extracted video id: " + videoIdFromUri);
        }
        if (videoIdFromUri != null) {
            if ("play".equalsIgnoreCase(s)) {
                this.playVideo(netflixActivity, mdx, videoIdFromUri, VideoType.MOVIE, s2, s3);
                return Response.HANDLING_WITH_DELAY;
            }
            if ("view_details".equalsIgnoreCase(s)) {
                if (Log.isLoggable("NflxHandler", 2)) {
                    Log.v("NflxHandler", "Showing MDP for: " + videoIdFromUri);
                }
                DetailsActivity.show(netflixActivity, VideoType.MOVIE, videoIdFromUri, this.getPlayContext(s3));
            }
            else {
                if (Log.isLoggable("NflxHandler", 6)) {
                    Log.e("NflxHandler", "Not supported action for tiny URL, default to LOLOMO: " + s);
                }
                this.handleHomeAction(netflixActivity);
            }
        }
        else {
            Log.e("NflxHandler", "Video ID not found, return to LOLOMO");
            this.handleHomeAction(netflixActivity);
        }
        return Response.HANDLING;
    }
    
    private Response handleNflxParams(final NetflixActivity netflixActivity, final IMdx mdx, final IClientLogging clientLogging, final Map<String, String> map, final long n) {
        if (Log.isLoggable("NflxHandler", 2)) {
            Log.v("NflxHandler", "Params map: " + map.toString());
        }
        Response not_HANDLING;
        if (map.size() <= 0) {
            Log.w("NflxHandler", "no params exist");
            not_HANDLING = Response.NOT_HANDLING;
        }
        else {
            final String s = map.get("action");
            if (s == null) {
                Log.w("NflxHandler", "Action is null!");
                return Response.NOT_HANDLING;
            }
            final String lowerCase = s.toLowerCase(Locale.US);
            final Response not_HANDLING2 = Response.NOT_HANDLING;
            final IClientLogging.ModalView modalView = null;
            final String source = this.getSource(map);
            int n2 = 0;
            if (clientLogging != null) {
                if (Log.isLoggable("NflxHandler", 3)) {
                    Log.d("NflxHandler", "Reporting that application is started from deep link. Source: " + source + ", action: " + lowerCase);
                }
                if (clientLogging != null && clientLogging.getCustomerEventLogging() != null) {
                    clientLogging.getCustomerEventLogging().reportApplicationLaunchedFromDeepLinking(source, lowerCase);
                }
            }
            else {
                Log.w("NflxHandler", "Client logging is null. Unable to report deep linking start!");
            }
            Enum<IClientLogging.ModalView> enum1;
            Response response;
            if ("home".equalsIgnoreCase(lowerCase)) {
                Log.v("NflxHandler", "handleHomeAction starts...");
                n2 = 1;
                enum1 = IClientLogging.ModalView.homeScreen;
                response = this.handleHomeAction(netflixActivity);
            }
            else if ("play".equalsIgnoreCase(lowerCase)) {
                final IClientLogging.ModalView playback = IClientLogging.ModalView.playback;
                response = this.handlePlayAction(netflixActivity, mdx, map);
                enum1 = playback;
            }
            else if ("view_details".equalsIgnoreCase(lowerCase)) {
                if (clientLogging != null && clientLogging.getCustomerEventLogging() != null) {
                    clientLogging.getCustomerEventLogging().reportMdpFromDeepLinking();
                }
                enum1 = IClientLogging.ModalView.movieDetails;
                response = this.handleViewDetailsAction(netflixActivity, map);
            }
            else if ("g".equalsIgnoreCase(lowerCase)) {
                n2 = 1;
                enum1 = IClientLogging.ModalView.browseTitles;
                response = this.handleViewGenreAction(netflixActivity, map);
            }
            else if ("search".equalsIgnoreCase(lowerCase)) {
                enum1 = IClientLogging.ModalView.search;
                response = this.handleSearchAction(netflixActivity, map);
            }
            else if ("sync".equalsIgnoreCase(lowerCase)) {
                n2 = 1;
                final IClientLogging.ModalView homeScreen = IClientLogging.ModalView.homeScreen;
                response = this.handleSyncAction(netflixActivity, mdx, map);
                enum1 = homeScreen;
            }
            else {
                Log.w("NflxHandler", "Unknown Nflx action: " + lowerCase);
                enum1 = modalView;
                response = not_HANDLING2;
            }
            not_HANDLING = response;
            if (clientLogging != null && (response == Response.HANDLING || (not_HANDLING = response) == Response.HANDLING_WITH_DELAY)) {
                clientLogging.getApplicationPerformanceMetricsLogging().startUiStartupSession(ApplicationPerformanceMetricsLogging.UiStartupTrigger.externalControlProtocol, (IClientLogging.ModalView)enum1, n);
                not_HANDLING = response;
                if (n2 != 0) {
                    clientLogging.getApplicationPerformanceMetricsLogging().startUiBrowseStartupSession(n);
                    return response;
                }
            }
        }
        return not_HANDLING;
    }
    
    private Response handlePlayAction(final NetflixActivity netflixActivity, final IMdx mdx, final Map<String, String> map) {
        Log.v("NflxHandler", "handlePlayAction starts...");
        final String justUuid = this.extractJustUuid(map.get("targetid"));
        final VideoInfo videoInfo = this.getVideoInfo(netflixActivity, mdx, "play", map);
        if (videoInfo == null) {
            Log.e("NflxHandler", "handlePlayAction fails, no video info found!");
            return Response.NOT_HANDLING;
        }
        if (videoInfo.handleWithDelay()) {
            Log.v("NflxHandler", "handlePlayAction ends, handling with delay.");
            return Response.HANDLING_WITH_DELAY;
        }
        if (videoInfo != null) {
            Log.v("NflxHandler", "handlePlayAction ends, handling.");
            final VideoType videoType = videoInfo.getVideoType();
            if (videoType == VideoType.MOVIE) {
                this.playVideo(netflixActivity, mdx, videoInfo.getCatalogId(), VideoType.MOVIE, justUuid, map.get("trkid"));
                return Response.HANDLING_WITH_DELAY;
            }
            if (videoType == VideoType.EPISODE) {
                final String episodeId = this.getEpisodeId(map);
                if (StringUtils.isEmpty(episodeId)) {
                    Log.v("NflxHandler", "no episode id");
                    return Response.NOT_HANDLING;
                }
                this.playVideo(netflixActivity, mdx, episodeId, VideoType.EPISODE, justUuid, map.get("trkid"));
                return Response.HANDLING_WITH_DELAY;
            }
            else if (Log.isLoggable("NflxHandler", 2)) {
                Log.v("NflxHandler", "Can't play video of type: " + videoType);
            }
        }
        return Response.NOT_HANDLING;
    }
    
    private Response handleSearchAction(final NetflixActivity netflixActivity, final Map<String, String> map) {
        final String s = map.get("query");
        if (StringUtils.isEmpty(s)) {
            Log.v("NflxHandler", "Could not find query param");
            return Response.NOT_HANDLING;
        }
        SearchActivity.search(netflixActivity, s);
        return Response.HANDLING;
    }
    
    private Response handleSyncAction(final NetflixActivity netflixActivity, final IMdx mdx, final Map<String, String> map) {
        final String justUuid = this.extractJustUuid(map.get("targetid"));
        if (mdx == null) {
            Log.e("NflxHandler", "Sync action is required, MDX agent is null. This should NOT happen!");
            return Response.NOT_HANDLING;
        }
        if (MdxUtils.isMdxTargetAvailable(netflixActivity.getServiceManager(), justUuid)) {
            Log.d("NflxHandler", "Sync action is required, target is available, sync");
            final boolean setDialUuidAsCurrentTarget = mdx.setDialUuidAsCurrentTarget(justUuid);
            if (Log.isLoggable("NflxHandler", 3)) {
                Log.d("NflxHandler", "Set dial uuid as current target was success " + setDialUuidAsCurrentTarget);
            }
            return Response.NOT_HANDLING;
        }
        Log.d("NflxHandler", "Sync action is required, target not available, do nothing!");
        return Response.NOT_HANDLING;
    }
    
    private void handleTinyUrl(final NetflixActivity netflixActivity, final IMdx mdx, final String s, String remoteDataAsString, final String s2, final String s3) {
        ThreadUtils.assertNotOnMain();
        final Response handling = Response.HANDLING;
        while (true) {
            try {
                remoteDataAsString = StringUtils.getRemoteDataAsString(getExpandUrl(remoteDataAsString));
                if (Log.isLoggable("NflxHandler", 3)) {
                    Log.d("NflxHandler", "Received response " + remoteDataAsString);
                }
                final JSONObject jsonObject = new JSONObject(remoteDataAsString);
                Response response;
                if (!jsonObject.has("catalog_title")) {
                    Log.e("NflxHandler", "No catalog_title in JSON object! Go to LOLOMO.");
                    this.handleHomeAction(netflixActivity);
                    response = handling;
                }
                else {
                    final JSONObject jsonObject2 = jsonObject.getJSONObject("catalog_title");
                    if (!jsonObject2.has("title_series")) {
                        Log.d("NflxHandler", "No title series in JSON object title. It must be movie. ");
                        response = this.handleMovieFromTinyUrl(netflixActivity, mdx, s, jsonObject2, s2, s3);
                    }
                    else {
                        Log.d("NflxHandler", "Title series in JSON object title. It must be episode. ");
                        response = this.handleEpisodeFromTinyUrl(netflixActivity, mdx, s, jsonObject2, s2, s3);
                    }
                }
                if (!Response.HANDLING_WITH_DELAY.equals(response)) {
                    this.reportDelayedResponseHandled(netflixActivity);
                }
                return;
            }
            catch (Throwable t) {
                Log.e("NflxHandler", "We failed to get expanded URL ", t);
                this.handleHomeAction(netflixActivity);
                final Response response = handling;
                continue;
            }
            continue;
        }
    }
    
    private Response handleUriParams(final NetflixActivity netflixActivity, final IMdx mdx, final IClientLogging clientLogging, final String s, final long n) {
        if (Log.isLoggable("NflxHandler", 2)) {
            Log.v("NflxHandler", "nflx params string: " + s);
        }
        final String[] split = s.split("[?&]");
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        for (int length = split.length, i = 0; i < length; ++i) {
            final String s2 = split[i];
            final int index = s2.indexOf("=");
            if (index <= 0) {
                Log.w("NflxHandler", "No params found for: " + s2);
            }
            else {
                hashMap.put(s2.substring(0, index), s2.substring(index + 1));
            }
        }
        return this.handleNflxParams(netflixActivity, mdx, clientLogging, hashMap, n);
    }
    
    private Response handleViewDetailsAction(final NetflixActivity netflixActivity, final Map<String, String> map) {
        Log.v("NflxHandler", "handleViewDetailsAction starts...");
        final VideoInfo videoInfo = this.getVideoInfo(netflixActivity, null, "view_details", map);
        if (videoInfo == null) {
            Log.e("NflxHandler", "handleViewDetailsAction fails, no video info found!");
            return Response.NOT_HANDLING;
        }
        if (videoInfo.handleWithDelay()) {
            Log.v("NflxHandler", "handleViewDetailsAction ends, handling with delay.");
            return Response.HANDLING_WITH_DELAY;
        }
        final VideoType videoType = videoInfo.getVideoType();
        final String catalogId = videoInfo.getCatalogId();
        if (videoType == VideoType.EPISODE) {
            if (Log.isLoggable("NflxHandler", 2)) {
                Log.v("NflxHandler", "Showing details for episode: " + catalogId + ", type: " + videoType + ", show: " + videoInfo.getShowId());
            }
            DetailsActivity.showEpisodeDetails(netflixActivity, videoInfo.getShowId(), catalogId, this.getPlayContext(map.get("trkid")));
        }
        else {
            if (Log.isLoggable("NflxHandler", 2)) {
                Log.v("NflxHandler", "Showing details for: " + catalogId + ", type: " + videoType);
            }
            DetailsActivity.show(netflixActivity, videoType, catalogId, this.getPlayContext(map.get("trkid")));
        }
        return Response.HANDLING;
    }
    
    private Response handleViewGenreAction(final Activity activity, final Map<String, String> map) {
        final String s = map.get("genreid");
        if (s == null) {
            Log.v("NflxHandler", "Could not find genre ID");
            return Response.NOT_HANDLING;
        }
        ((NetflixActivity)activity).getServiceManager().fetchLoLoMoSummary(s, new FetchLoLoMoSummaryCallback(activity, s));
        return Response.HANDLING_WITH_DELAY;
    }
    
    private void play(final Activity activity, final IMdx mdx, final Playable playable, final String dialUuidAsCurrentTarget, final PlayContext playContext) {
        if (StringUtils.isEmpty(dialUuidAsCurrentTarget)) {
            Log.d("NflxHandler", "Starting local playback");
            PlayerActivity.playVideo(activity, playable, playContext);
            return;
        }
        if (Log.isLoggable("NflxHandler", 3)) {
            Log.d("NflxHandler", "Remote play required on target " + dialUuidAsCurrentTarget);
        }
        if (mdx == null) {
            Log.d("NflxHandler", "MDX is null, go local playback");
        }
        else {
            Log.d("NflxHandler", "MDX exist, check if target is available");
            if (mdx.setDialUuidAsCurrentTarget(dialUuidAsCurrentTarget)) {
                this.handleHomeAction(activity);
                PlaybackLauncher.startPlaybackForceRemote((NetflixActivity)activity, Asset.create(playable, playContext));
                return;
            }
            Log.d("NflxHandler", "MDX does not know target dial UUID, go local playback");
        }
        PlayerActivity.playVideo(activity, playable, playContext);
    }
    
    private void playVideo(final NetflixActivity netflixActivity, final IMdx mdx, final String s, final VideoType videoType, final String s2, final String s3) {
        if (Log.isLoggable("NflxHandler", 2)) {
            Log.v("NflxHandler", "Playing video: " + s);
        }
        if (VideoType.MOVIE.equals(videoType)) {
            netflixActivity.getServiceManager().fetchMovieDetails(s, new FetchPlayableCallback((Activity)netflixActivity, s3, mdx, s2));
        }
        else if (VideoType.EPISODE.equals(videoType)) {
            netflixActivity.getServiceManager().fetchEpisodeDetails(s, new FetchPlayableCallback((Activity)netflixActivity, s3, mdx, s2));
        }
    }
    
    private void reportDelayedResponseHandled(final Activity activity) {
        if (AndroidUtils.isActivityFinishedOrDestroyed(activity)) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.NFLX_HANDLER_RESULT");
        intent.addCategory("LocalIntentNflxUi");
        LocalBroadcastManager.getInstance((Context)activity).sendBroadcast(intent);
    }
    
    private void reportIfSourceIsNotification(final IClientLogging clientLogging, final Intent intent) {
        if (!intent.hasExtra("guid")) {
            Log.e("NflxHandler", "Guid not found, source is not push notification");
            return;
        }
        final String stringExtra = intent.getStringExtra("guid");
        if (intent.hasExtra("messageGuid")) {
            final MessageData messageData = new MessageData(stringExtra, intent.getStringExtra("messageGuid"));
            if (Log.isLoggable("NflxHandler", 3)) {
                Log.d("NflxHandler", "User opened notification " + messageData);
            }
            if (clientLogging != null && clientLogging.getCmpEventLogging() != null) {
                clientLogging.getCmpEventLogging().reportUserFeedbackOnReceivedPushNotification(messageData, UserFeedbackOnReceivedPushNotification.opened);
            }
            return;
        }
        Log.e("NflxHandler", "Message guid not found, source is not push notification");
    }
    
    public Response handleNflxIntent(final NetflixActivity netflixActivity, final ServiceManager serviceManager, final Intent intent, final long n) {
        Log.d("NflxHandler", "Handle NFLX intent starts...");
        if (intent == null) {
            Log.v("NflxHandler", "null intent");
            return Response.NOT_HANDLING;
        }
        final IMdx mdx = serviceManager.getMdx();
        final IClientLogging clientLogging = serviceManager.getClientLogging();
        this.reportIfSourceIsNotification(clientLogging, intent);
        if (!"android.intent.action.VIEW".equalsIgnoreCase(intent.getAction())) {
            Log.v("NflxHandler", "unknown action");
            return Response.NOT_HANDLING;
        }
        if (intent.getData() == null) {
            Log.v("NflxHandler", "no uri");
            return Response.NOT_HANDLING;
        }
        AndroidUtils.logVerboseIntentInfo("NflxHandler", intent);
        return this.handleUri(netflixActivity, mdx, clientLogging, intent.getData(), n);
    }
    
    public Response handleUri(final NetflixActivity netflixActivity, final IMdx mdx, final IClientLogging clientLogging, final Uri uri, final long n) {
        DataUtil.logVerboseUriInfo("NflxHandler", uri);
        if (!"nflx".equalsIgnoreCase(uri.getScheme())) {
            Log.v("NflxHandler", "unknown scheme");
            return Response.NOT_HANDLING;
        }
        if (!"www.netflix.com".equalsIgnoreCase(uri.getHost())) {
            Log.v("NflxHandler", "invalid host");
            return Response.NOT_HANDLING;
        }
        if (!"/browse".equalsIgnoreCase(uri.getPath())) {
            Log.v("NflxHandler", "invalid path");
            return Response.NOT_HANDLING;
        }
        final String queryParameter = uri.getQueryParameter("q");
        if (StringUtils.isEmpty(queryParameter)) {
            Log.v("NflxHandler", "no nflx params");
            return Response.NOT_HANDLING;
        }
        return this.handleUriParams(netflixActivity, mdx, clientLogging, queryParameter, n);
    }
    
    class FetchLoLoMoSummaryCallback extends SimpleManagerCallback
    {
        private final Activity activity;
        private final String genreId;
        
        FetchLoLoMoSummaryCallback(final Activity activity, final String genreId) {
            this.genreId = genreId;
            this.activity = activity;
        }
        
        @Override
        public void onLoLoMoSummaryFetched(final LoLoMo loLoMo, final int n) {
            if (n == 0) {
                HomeActivity.showGenreList(this.activity, this.genreId, loLoMo.getTitle());
            }
            NflxHandler.this.reportDelayedResponseHandled(this.activity);
        }
    }
    
    class FetchPlayableCallback extends SimpleManagerCallback
    {
        private final Activity activity;
        private final String trackId;
        final /* synthetic */ IMdx val$mdx;
        final /* synthetic */ String val$targetDialUuid;
        
        FetchPlayableCallback(final Activity activity, final String trackId, final Activity val$mdx, final String val$targetDialUuid) {
            this.val$mdx = (IMdx)val$mdx;
            this.val$targetDialUuid = val$targetDialUuid;
            this.activity = activity;
            this.trackId = trackId;
        }
        
        @Override
        public void onEpisodeDetailsFetched(final EpisodeDetails episodeDetails, final int n) {
            if (n == 0) {
                NflxHandler.this.play(this.activity, this.val$mdx, episodeDetails, this.val$targetDialUuid, NflxHandler.this.getPlayContext(this.trackId));
            }
            NflxHandler.this.reportDelayedResponseHandled(this.activity);
        }
        
        @Override
        public void onMovieDetailsFetched(final MovieDetails movieDetails, final int n) {
            if (n == 0) {
                NflxHandler.this.play(this.activity, this.val$mdx, movieDetails, this.val$targetDialUuid, NflxHandler.this.getPlayContext(this.trackId));
            }
            NflxHandler.this.reportDelayedResponseHandled(this.activity);
        }
    }
    
    public enum Response
    {
        HANDLING, 
        HANDLING_WITH_DELAY, 
        NOT_HANDLING;
    }
    
    private static class VideoInfo
    {
        public static VideoInfo DELAYED;
        private final String mCatalogId;
        private final boolean mHandleWithDelay;
        private final String mShowId;
        private final VideoType mVideoType;
        
        static {
            VideoInfo.DELAYED = new VideoInfo(true, VideoType.UNAVAILABLE, null);
        }
        
        private VideoInfo(final boolean mHandleWithDelay, final VideoType mVideoType, final String mCatalogId) {
            this.mHandleWithDelay = mHandleWithDelay;
            this.mVideoType = mVideoType;
            this.mCatalogId = mCatalogId;
            this.mShowId = null;
        }
        
        private VideoInfo(final boolean mHandleWithDelay, final String mCatalogId) {
            this.mHandleWithDelay = mHandleWithDelay;
            this.mVideoType = VideoType.SHOW;
            this.mCatalogId = mCatalogId;
            this.mShowId = null;
        }
        
        private VideoInfo(final boolean mHandleWithDelay, final String mShowId, final String mCatalogId) {
            this.mHandleWithDelay = mHandleWithDelay;
            this.mVideoType = VideoType.EPISODE;
            this.mCatalogId = mCatalogId;
            this.mShowId = mShowId;
        }
        
        public static VideoInfo createFromEpisode(final String s, final String s2) {
            if (StringUtils.safeEquals(s, s2)) {
                return new VideoInfo(false, s);
            }
            return new VideoInfo(false, s, s2);
        }
        
        public static VideoInfo createFromMovie(final String s) {
            return new VideoInfo(false, VideoType.MOVIE, s);
        }
        
        public static VideoInfo createFromShow(final String s) {
            return new VideoInfo(false, VideoType.SHOW, s);
        }
        
        public String getCatalogId() {
            return this.mCatalogId;
        }
        
        public String getShowId() {
            return this.mShowId;
        }
        
        public VideoType getVideoType() {
            return this.mVideoType;
        }
        
        public boolean handleWithDelay() {
            return this.mHandleWithDelay;
        }
    }
}
