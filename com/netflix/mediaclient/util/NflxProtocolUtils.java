// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.service.logging.apm.model.Display;
import com.netflix.mediaclient.protocol.nflx.NflxHandler;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import com.netflix.mediaclient.service.pushnotification.UserFeedbackOnReceivedPushNotification;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.ui.common.PlayContext;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;
import com.netflix.mediaclient.Log;

public final class NflxProtocolUtils
{
    public static final String INTENT_RESULT = "com.netflix.mediaclient.intent.action.HANDLER_RESULT";
    public static final String PARAM_STATUS = "status";
    private static final String TAG = "NflxHandler";
    private static final String WEBAPI_EXPAND_SERVICE = "http://api.netflix.com/catalog/tiny/expand/";
    
    public static String extractId(final String s) {
        if (!StringUtils.isEmpty(s)) {
            final int lastIndex = s.lastIndexOf("/");
            if (lastIndex > 0) {
                return s.substring(lastIndex + 1);
            }
            Log.d("NflxHandler", "Check if this is simple ID");
            if (StringUtils.isNumeric(s)) {
                return s.trim();
            }
        }
        return null;
    }
    
    public static String extractJustUuid(final String s) {
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
    
    public static String getAction(final Map<String, String> map) {
        String s;
        if (!StringUtils.isEmpty(s = map.get("action")) || !StringUtils.isEmpty(s = map.get("a"))) {
            return s;
        }
        if (isVideoInfoAvailable(map)) {
            Log.w("NflxHandler", "Action is empty, but video info is available, default action is video detail!");
            return "view_details";
        }
        Log.w("NflxHandler", "Action is empty and video info is NOT available, default action is home ");
        return "home";
    }
    
    public static String getEpisodeId(Map<String, String> decode) {
        decode = ((Map<String, String>)decode).get("episodeid");
        if (StringUtils.isEmpty(decode)) {
            Log.v("NflxHandler", "episode id uri doesn't exist in params map");
            return null;
        }
        try {
            decode = URLDecoder.decode(decode, "utf-8");
            return getVideoIdFromUri(decode, "programs/");
        }
        catch (UnsupportedEncodingException ex) {
            Log.e("NflxHandler", "Failed to decode URL", ex);
            return getVideoIdFromUri(decode, "programs/");
        }
    }
    
    public static String getExpandUrl(String string) {
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
    
    public static PlayContext getPlayContext(final String s) {
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
    
    public static String getSource(final Map<String, String> map) {
        String s;
        if (StringUtils.isEmpty(s = map.get("source")) && StringUtils.isEmpty(s = map.get("s"))) {
            Log.w("NflxHandler", "Source is empty!");
            return "uknown";
        }
        return s;
    }
    
    public static String getTargetUrl(final Map<String, String> map) {
        final String s = map.get("target_url");
        if (StringUtils.isEmpty(s)) {
            return map.get("u");
        }
        return s;
    }
    
    public static String getTrackId(final Map<String, String> map) {
        final String s = map.get("trkid");
        if (StringUtils.isEmpty(s)) {
            return map.get("t");
        }
        return s;
    }
    
    public static String getVideoIdFromUri(String s, final String s2) {
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
    
    public static VideoInfo getVideoInfoFromMovieIdUri(String s) {
        while (true) {
            try {
                s = URLDecoder.decode(s, "utf-8");
                if (Log.isLoggable("NflxHandler", 3)) {
                    Log.d("NflxHandler", "movie id uri exist in params map");
                    Log.d("NflxHandler", "Gets video info from " + s);
                }
                final String videoIdFromUri = getVideoIdFromUri(s, "series/");
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
        s = getVideoIdFromUri(s, "movies/");
        if (s != null) {
            return VideoInfo.createFromMovie(s);
        }
        Log.e("NflxHandler", "Unable to get video id! This should NOT happen!");
        return null;
    }
    
    public static boolean isGenreAction(final String s) {
        return "genre".equalsIgnoreCase(s) || "g".equalsIgnoreCase(s);
    }
    
    public static boolean isPlayAction(final String s) {
        return "play".equalsIgnoreCase(s) || "p".equalsIgnoreCase(s);
    }
    
    public static boolean isVideoInfoAvailable(final Map<String, String> map) {
        return StringUtils.isNotEmpty(map.get("movieid")) || StringUtils.isNotEmpty(getTargetUrl(map));
    }
    
    public static boolean isViewDetailsAction(final String s) {
        return "view_details".equalsIgnoreCase(s) || "v".equalsIgnoreCase(s);
    }
    
    public static void reportApplicationLaunchedFromDeepLinking(final NetflixActivity netflixActivity, final Map<String, String> map, final String s) {
        final String source = getSource(map);
        final IClientLogging clientLogging = netflixActivity.getServiceManager().getClientLogging();
        if (clientLogging != null) {
            if (Log.isLoggable("NflxHandler", 3)) {
                Log.d("NflxHandler", "Reporting that application is started from deep link. Source: " + source + ", action: " + s);
            }
            if (clientLogging != null && clientLogging.getCustomerEventLogging() != null) {
                clientLogging.getCustomerEventLogging().reportApplicationLaunchedFromDeepLinking(source, s, map.toString());
            }
            return;
        }
        Log.w("NflxHandler", "Client logging is null. Unable to report deep linking start!");
    }
    
    public static void reportDelayedResponseHandled(final Activity activity) {
        if (AndroidUtils.isActivityFinishedOrDestroyed(activity)) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.HANDLER_RESULT");
        intent.addCategory("LocalIntentNflxUi");
        LocalBroadcastManager.getInstance((Context)activity).sendBroadcast(intent);
    }
    
    public static void reportIfSourceIsNotification(final IClientLogging clientLogging, final Intent intent) {
        if (!intent.hasExtra("guid")) {
            Log.e("NflxHandler", "Guid not found, source is not push notification");
            return;
        }
        final String stringExtra = intent.getStringExtra("guid");
        if (intent.hasExtra("messageGuid")) {
            final String stringExtra2 = intent.getStringExtra("messageGuid");
            final String stringExtra3 = intent.getStringExtra("originator");
            if (StringUtils.isEmpty(stringExtra3)) {
                Log.w("NflxHandler", "Received notification WITHOUT ORIGINATOR! Pass default!");
            }
            final MessageData messageData = new MessageData(stringExtra, stringExtra2, stringExtra3);
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
    
    public static void reportOnProfileGate(final NetflixActivity netflixActivity, final Map<String, String> map, final long n) {
        final Display display = LogUtils.getDisplay((Context)netflixActivity);
        final IClientLogging clientLogging = netflixActivity.getServiceManager().getClientLogging();
        if (clientLogging != null) {
            clientLogging.getApplicationPerformanceMetricsLogging().startUiStartupSession(ApplicationPerformanceMetricsLogging.UiStartupTrigger.touchGesture, IClientLogging.ModalView.profilesGate, n, display);
            reportApplicationLaunchedFromDeepLinking(netflixActivity, map, "profileGate");
            reportUiSessions(netflixActivity, NflxHandler.Response.HANDLING, true, IClientLogging.ModalView.profilesGate, n);
        }
    }
    
    public static void reportUiSessions(final NetflixActivity netflixActivity, final NflxHandler.Response response, final boolean b, final IClientLogging.ModalView modalView, final long n) {
        final IClientLogging clientLogging = netflixActivity.getServiceManager().getClientLogging();
        if (clientLogging != null && (response == NflxHandler.Response.HANDLING || response == NflxHandler.Response.HANDLING_WITH_DELAY)) {
            clientLogging.getApplicationPerformanceMetricsLogging().startUiStartupSession(ApplicationPerformanceMetricsLogging.UiStartupTrigger.externalControlProtocol, modalView, n, LogUtils.getDisplay((Context)netflixActivity));
            if (b) {
                clientLogging.getApplicationPerformanceMetricsLogging().startUiBrowseStartupSession(n);
            }
        }
    }
    
    public static class VideoInfo
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