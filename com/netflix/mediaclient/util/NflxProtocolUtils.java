// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.service.pushnotification.UserFeedbackOnReceivedPushNotification;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.service.logging.apm.model.Display;
import com.netflix.mediaclient.protocol.nflx.NflxHandler$Response;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$UiStartupTrigger;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
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
        if (StringUtils.isEmpty(s = map.get("action")) && StringUtils.isEmpty(s = map.get("a"))) {
            if (!isVideoInfoAvailable(map)) {
                Log.w("NflxHandler", "Action is empty and video info is NOT available, default action is home ");
                return "home";
            }
            Log.w("NflxHandler", "Action is empty, but video info is available, default action is video detail!");
            s = "view_details";
        }
        return s;
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
            s = "uknown";
        }
        return s;
    }
    
    public static String getTargetUrl(final Map<String, String> map) {
        String s;
        if (StringUtils.isEmpty(s = map.get("target_url"))) {
            s = map.get("u");
        }
        return s;
    }
    
    public static String getTrackId(final Map<String, String> map) {
        String s;
        if (StringUtils.isEmpty(s = map.get("trkid"))) {
            s = map.get("t");
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
    
    public static NflxProtocolUtils$VideoInfo getVideoInfoFromMovieIdUri(String s) {
        while (true) {
            try {
                s = URLDecoder.decode(s, "utf-8");
                if (Log.isLoggable("NflxHandler", 3)) {
                    Log.d("NflxHandler", "movie id uri exist in params map");
                    Log.d("NflxHandler", "Gets video info from " + s);
                }
                final String videoIdFromUri = getVideoIdFromUri(s, "series/");
                if (videoIdFromUri != null) {
                    return NflxProtocolUtils$VideoInfo.createFromShow(videoIdFromUri);
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
            return NflxProtocolUtils$VideoInfo.createFromMovie(s);
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
    
    public static void reportOnProfileGate(final NetflixActivity netflixActivity, final Map<String, String> map, final long n) {
        final Display display = ConsolidatedLoggingUtils.getDisplay((Context)netflixActivity);
        final IClientLogging clientLogging = netflixActivity.getServiceManager().getClientLogging();
        if (clientLogging != null) {
            clientLogging.getApplicationPerformanceMetricsLogging().startUiStartupSession(ApplicationPerformanceMetricsLogging$UiStartupTrigger.touchGesture, IClientLogging$ModalView.profilesGate, n, display);
            reportApplicationLaunchedFromDeepLinking(netflixActivity, map, "profileGate");
            reportUiSessions(netflixActivity, NflxHandler$Response.HANDLING, true, IClientLogging$ModalView.profilesGate, n);
        }
    }
    
    public static void reportUiSessions(final NetflixActivity netflixActivity, final NflxHandler$Response nflxHandler$Response, final boolean b, final IClientLogging$ModalView clientLogging$ModalView, final long n) {
        final IClientLogging clientLogging = netflixActivity.getServiceManager().getClientLogging();
        if (clientLogging != null && (nflxHandler$Response == NflxHandler$Response.HANDLING || nflxHandler$Response == NflxHandler$Response.HANDLING_WITH_DELAY)) {
            clientLogging.getApplicationPerformanceMetricsLogging().startUiStartupSession(ApplicationPerformanceMetricsLogging$UiStartupTrigger.externalControlProtocol, clientLogging$ModalView, n, ConsolidatedLoggingUtils.getDisplay((Context)netflixActivity));
            if (b) {
                clientLogging.getApplicationPerformanceMetricsLogging().startUiBrowseStartupSession(n);
            }
        }
    }
    
    public static void reportUserOpenedNotification(final NetflixService netflixService, final Intent intent) {
        if (netflixService == null) {
            Log.w("NflxHandler", "Netflix service is null, enable to report that user opened notification");
            return;
        }
        reportUserOpenedNotification(netflixService.getClientLogging(), intent);
    }
    
    private static void reportUserOpenedNotification(final IClientLogging clientLogging, final Intent intent) {
        Log.d("NflxHandler", "reportIfSourceIsNotification", intent);
        if (clientLogging == null || clientLogging.getCmpEventLogging() == null) {
            Log.w("NflxHandler", "CL or CMP is null, enable to report that user opened notification");
            return;
        }
        final MessageData instance = MessageData.createInstance(intent);
        if (instance == null) {
            Log.e("NflxHandler", "Unable to report since message data are missing!");
            return;
        }
        if (Log.isLoggable("NflxHandler", 3)) {
            Log.d("NflxHandler", "User opened notification " + instance);
        }
        clientLogging.getCmpEventLogging().reportUserFeedbackOnReceivedPushNotification(instance, UserFeedbackOnReceivedPushNotification.opened);
    }
    
    public static void reportUserOpenedNotification(final ServiceManager serviceManager, final Intent intent) {
        if (serviceManager == null) {
            Log.w("NflxHandler", "Service manager is null, enable to report that user opened notification");
            return;
        }
        reportUserOpenedNotification(serviceManager.getClientLogging(), intent);
    }
}
