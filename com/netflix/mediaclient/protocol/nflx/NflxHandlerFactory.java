// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.nflx;

import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import java.util.Locale;
import com.netflix.mediaclient.util.NflxProtocolUtils;
import android.content.Intent;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.DataUtil;
import android.net.Uri;
import java.util.Map;
import java.util.HashMap;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public final class NflxHandlerFactory
{
    private static final String TAG = "NflxHandler";
    
    private static NflxHandler findHandleForUriParams(final NetflixActivity netflixActivity, final String s, final long n) {
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
                final String substring = s2.substring(0, index);
                final String substring2 = s2.substring(index + 1);
                if (Log.isLoggable("NflxHandler", 3)) {
                    Log.d("NflxHandler", "Param name: " + substring + ", value: " + substring2);
                }
                hashMap.put(substring, substring2);
            }
        }
        return handleNflxParams(netflixActivity, hashMap, n);
    }
    
    public static NflxHandler getHandler(final NetflixActivity netflixActivity, final Uri uri, final long n) {
        DataUtil.logVerboseUriInfo("NflxHandler", uri);
        if (!"nflx".equalsIgnoreCase(uri.getScheme())) {
            Log.v("NflxHandler", "unknown scheme");
            return new NotHandlingActionHandler();
        }
        if (!"www.netflix.com".equalsIgnoreCase(uri.getHost())) {
            Log.v("NflxHandler", "invalid host");
            return new NotHandlingActionHandler();
        }
        if (!"/browse".equalsIgnoreCase(uri.getPath())) {
            Log.v("NflxHandler", "invalid path");
            return new NotHandlingActionHandler();
        }
        final String queryParameter = uri.getQueryParameter("q");
        if (StringUtils.isEmpty(queryParameter)) {
            Log.v("NflxHandler", "no nflx params");
            return new NotHandlingActionHandler();
        }
        return findHandleForUriParams(netflixActivity, queryParameter, n);
    }
    
    public static NflxHandler getHandlerForIntent(final NetflixActivity netflixActivity, final Intent intent, final long n) {
        NflxProtocolUtils.reportUserOpenedNotification(netflixActivity.getServiceManager(), intent);
        Log.d("NflxHandler", "Handle NFLX intent starts...");
        if (intent == null) {
            Log.v("NflxHandler", "null intent");
            return new NotHandlingActionHandler();
        }
        if (!"android.intent.action.VIEW".equalsIgnoreCase(intent.getAction())) {
            Log.v("NflxHandler", "unknown action");
            return new NotHandlingActionHandler();
        }
        if (intent.getData() == null) {
            Log.v("NflxHandler", "no uri");
            return new NotHandlingActionHandler();
        }
        Log.v("NflxHandler", intent);
        return getHandler(netflixActivity, intent.getData(), n);
    }
    
    private static NflxHandler handleNflxParams(final NetflixActivity netflixActivity, final Map<String, String> map, final long n) {
        boolean b = true;
        if (Log.isLoggable("NflxHandler", 2)) {
            Log.v("NflxHandler", "Params map: " + map.toString());
        }
        if (map.size() <= 0) {
            Log.w("NflxHandler", "no params exist");
            return new NotHandlingActionHandler();
        }
        if (map.get("profileGate") != null) {
            return new ProfileGateActionHandler(netflixActivity, map, n);
        }
        final String action = NflxProtocolUtils.getAction(map);
        if (action == null) {
            Log.w("NflxHandler", "Action is null!");
            return new NotHandlingActionHandler();
        }
        final String lowerCase = action.toLowerCase(Locale.US);
        NflxHandler$Response nflxHandler$Response = NflxHandler$Response.HANDLING;
        final IClientLogging clientLogging = netflixActivity.getServiceManager().getClientLogging();
        NflxProtocolUtils.reportApplicationLaunchedFromDeepLinking(netflixActivity, map, lowerCase);
        NflxHandler nflxHandler;
        IClientLogging$ModalView clientLogging$ModalView;
        if ("home".equalsIgnoreCase(lowerCase)) {
            Log.v("NflxHandler", "handleHomeAction starts...");
            final IClientLogging$ModalView homeScreen = IClientLogging$ModalView.homeScreen;
            nflxHandler = new HomeActionHandler(netflixActivity, map);
            clientLogging$ModalView = homeScreen;
        }
        else if (NflxProtocolUtils.isPlayAction(lowerCase)) {
            Log.v("NflxHandler", "handle play starts...");
            final IClientLogging$ModalView playback = IClientLogging$ModalView.playback;
            nflxHandler = new PlayActionHandler(netflixActivity, map);
            clientLogging$ModalView = playback;
            b = false;
        }
        else if (NflxProtocolUtils.isViewDetailsAction(lowerCase)) {
            Log.v("NflxHandler", "view details starts...");
            if (clientLogging != null && clientLogging.getCustomerEventLogging() != null) {
                clientLogging.getCustomerEventLogging().reportMdpFromDeepLinking(map.toString());
            }
            final IClientLogging$ModalView movieDetails = IClientLogging$ModalView.movieDetails;
            nflxHandler = new ViewDetailsActionHandler(netflixActivity, map);
            clientLogging$ModalView = movieDetails;
            b = false;
        }
        else if (NflxProtocolUtils.isGenreAction(lowerCase)) {
            Log.v("NflxHandler", "genre starts...");
            final IClientLogging$ModalView browseTitles = IClientLogging$ModalView.browseTitles;
            final GenreActionHandler genreActionHandler = new GenreActionHandler(netflixActivity, map);
            clientLogging$ModalView = browseTitles;
            nflxHandler = genreActionHandler;
        }
        else if ("search".equalsIgnoreCase(lowerCase)) {
            Log.v("NflxHandler", "search starts...");
            final IClientLogging$ModalView search = IClientLogging$ModalView.search;
            nflxHandler = new SearchActionHandler(netflixActivity, map);
            clientLogging$ModalView = search;
            b = false;
        }
        else if ("sync".equalsIgnoreCase(lowerCase)) {
            Log.v("NflxHandler", "sync starts...");
            final IClientLogging$ModalView homeScreen2 = IClientLogging$ModalView.homeScreen;
            final SyncActionHandler syncActionHandler = new SyncActionHandler(netflixActivity, map);
            clientLogging$ModalView = homeScreen2;
            nflxHandler = syncActionHandler;
        }
        else if ("iq".equalsIgnoreCase(lowerCase)) {
            Log.v("NflxHandler", "Add to instant queue starts...");
            if (clientLogging != null && clientLogging.getCustomerEventLogging() != null) {
                clientLogging.getCustomerEventLogging().reportMdpFromDeepLinking(map.toString());
            }
            final IClientLogging$ModalView movieDetails2 = IClientLogging$ModalView.movieDetails;
            nflxHandler = new AddToMyListActionHandler(netflixActivity, map);
            clientLogging$ModalView = movieDetails2;
            b = false;
        }
        else if ("send_thanks".equalsIgnoreCase(lowerCase)) {
            Log.v("NflxHandler", "Send thanks to social notification starts...");
            final IClientLogging$ModalView homeScreen3 = IClientLogging$ModalView.homeScreen;
            nflxHandler = new SendThanksToSocialNotificationActionHandler(netflixActivity, map);
            clientLogging$ModalView = homeScreen3;
            b = false;
        }
        else {
            Log.w("NflxHandler", "Unknown Nflx action: " + lowerCase);
            nflxHandler = new NotHandlingActionHandler();
            nflxHandler$Response = NflxHandler$Response.NOT_HANDLING;
            b = false;
            clientLogging$ModalView = null;
        }
        NflxProtocolUtils.reportUiSessions(netflixActivity, nflxHandler$Response, b, clientLogging$ModalView, n);
        return nflxHandler;
    }
}
