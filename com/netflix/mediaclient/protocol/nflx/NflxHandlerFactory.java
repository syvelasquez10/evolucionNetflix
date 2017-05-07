// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.nflx;

import com.netflix.mediaclient.servicemgr.IClientLogging;
import java.util.Locale;
import com.netflix.mediaclient.util.AndroidUtils;
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
                hashMap.put(s2.substring(0, index), s2.substring(index + 1));
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
        Log.d("NflxHandler", "Handle NFLX intent starts...");
        if (intent == null) {
            Log.v("NflxHandler", "null intent");
            return new NotHandlingActionHandler();
        }
        NflxProtocolUtils.reportIfSourceIsNotification(netflixActivity.getServiceManager().getClientLogging(), intent);
        if (!"android.intent.action.VIEW".equalsIgnoreCase(intent.getAction())) {
            Log.v("NflxHandler", "unknown action");
            return new NotHandlingActionHandler();
        }
        if (intent.getData() == null) {
            Log.v("NflxHandler", "no uri");
            return new NotHandlingActionHandler();
        }
        AndroidUtils.logVerboseIntentInfo("NflxHandler", intent);
        return getHandler(netflixActivity, intent.getData(), n);
    }
    
    private static NflxHandler handleNflxParams(final NetflixActivity netflixActivity, final Map<String, String> map, final long n) {
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
        final IClientLogging.ModalView modalView = null;
        boolean b = false;
        NflxHandler.Response response = NflxHandler.Response.HANDLING;
        final IClientLogging clientLogging = netflixActivity.getServiceManager().getClientLogging();
        NflxProtocolUtils.reportApplicationLaunchedFromDeepLinking(netflixActivity, map, lowerCase);
        HomeActionHandler homeActionHandler;
        IClientLogging.ModalView modalView2;
        if ("home".equalsIgnoreCase(lowerCase)) {
            Log.v("NflxHandler", "handleHomeAction starts...");
            b = true;
            final IClientLogging.ModalView homeScreen = IClientLogging.ModalView.homeScreen;
            homeActionHandler = new HomeActionHandler(netflixActivity, map);
            modalView2 = homeScreen;
        }
        else if (NflxProtocolUtils.isPlayAction(lowerCase)) {
            Log.v("NflxHandler", "handle play starts...");
            final IClientLogging.ModalView playback = IClientLogging.ModalView.playback;
            final PlayActionHandler playActionHandler = new PlayActionHandler(netflixActivity, map);
            modalView2 = playback;
            homeActionHandler = (HomeActionHandler)playActionHandler;
        }
        else if (NflxProtocolUtils.isViewDetailsAction(lowerCase)) {
            Log.v("NflxHandler", "view details starts...");
            if (clientLogging != null && clientLogging.getCustomerEventLogging() != null) {
                clientLogging.getCustomerEventLogging().reportMdpFromDeepLinking(map.toString());
            }
            final IClientLogging.ModalView movieDetails = IClientLogging.ModalView.movieDetails;
            final ViewDetailsActionHandler viewDetailsActionHandler = new ViewDetailsActionHandler(netflixActivity, map);
            modalView2 = movieDetails;
            homeActionHandler = (HomeActionHandler)viewDetailsActionHandler;
        }
        else if (NflxProtocolUtils.isGenreAction(lowerCase)) {
            Log.v("NflxHandler", "genre starts...");
            b = true;
            final IClientLogging.ModalView browseTitles = IClientLogging.ModalView.browseTitles;
            final GenreActionHandler genreActionHandler = new GenreActionHandler(netflixActivity, map);
            modalView2 = browseTitles;
            homeActionHandler = (HomeActionHandler)genreActionHandler;
        }
        else if ("search".equalsIgnoreCase(lowerCase)) {
            Log.v("NflxHandler", "search starts...");
            final IClientLogging.ModalView search = IClientLogging.ModalView.search;
            final SearchActionHandler searchActionHandler = new SearchActionHandler(netflixActivity, map);
            modalView2 = search;
            homeActionHandler = (HomeActionHandler)searchActionHandler;
        }
        else if ("sync".equalsIgnoreCase(lowerCase)) {
            Log.v("NflxHandler", "sync starts...");
            b = true;
            final IClientLogging.ModalView homeScreen2 = IClientLogging.ModalView.homeScreen;
            final SyncActionHandler syncActionHandler = new SyncActionHandler(netflixActivity, map);
            modalView2 = homeScreen2;
            homeActionHandler = (HomeActionHandler)syncActionHandler;
        }
        else if ("iq".equalsIgnoreCase(lowerCase)) {
            Log.v("NflxHandler", "Add to instant queue starts...");
            if (clientLogging != null && clientLogging.getCustomerEventLogging() != null) {
                clientLogging.getCustomerEventLogging().reportMdpFromDeepLinking(map.toString());
            }
            final IClientLogging.ModalView movieDetails2 = IClientLogging.ModalView.movieDetails;
            final AddToMyListActionHandler addToMyListActionHandler = new AddToMyListActionHandler(netflixActivity, map);
            modalView2 = movieDetails2;
            homeActionHandler = (HomeActionHandler)addToMyListActionHandler;
        }
        else {
            Log.w("NflxHandler", "Unknown Nflx action: " + lowerCase);
            final NotHandlingActionHandler notHandlingActionHandler = new NotHandlingActionHandler();
            response = NflxHandler.Response.NOT_HANDLING;
            modalView2 = modalView;
            homeActionHandler = (HomeActionHandler)notHandlingActionHandler;
        }
        NflxProtocolUtils.reportUiSessions(netflixActivity, response, b, modalView2, n);
        return homeActionHandler;
    }
}
